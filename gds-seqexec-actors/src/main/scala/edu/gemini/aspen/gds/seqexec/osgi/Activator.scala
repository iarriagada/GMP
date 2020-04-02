package edu.gemini.aspen.gds.seqexec.osgi

import java.util

import edu.gemini.aspen.gds.api.KeywordActorsFactory
import edu.gemini.aspen.gds.seqexec.SeqexecActorsFactory
import edu.gemini.aspen.gds.staticheaderreceiver.TemporarySeqexecKeywordsDatabase
import edu.gemini.util.osgi.Tracker
import org.osgi.framework.{BundleActivator, BundleContext, ServiceRegistration}
import org.osgi.util.tracker.ServiceTracker

class Activator extends BundleActivator {
  var factoryRegistration: Option[ServiceRegistration[_]] = None
  var tracker: Option[ServiceTracker[TemporarySeqexecKeywordsDatabase, SeqexecActorsFactory]] = None

  override def start(context: BundleContext): Unit = {
    tracker = Option(Tracker.track[TemporarySeqexecKeywordsDatabase, SeqexecActorsFactory](context) { p =>
      val factory = new SeqexecActorsFactory(p)
      factoryRegistration = Some(context.registerService(classOf[KeywordActorsFactory].getName, factory, new util.Hashtable[String, String]()))
      factory
    } { _ => })
    tracker.foreach(_.open(true))
  }

  override def stop(context: BundleContext): Unit = {
    factoryRegistration.foreach(_.unregister())
    factoryRegistration = None
    tracker.foreach(_.close())
    tracker = None
  }
}