package edu.gemini.gmp.status.translator

import edu.gemini.aspen.giapi.status.StatusItem
import edu.gemini.aspen.giapi.statusservice.StatusHandlerAggregate
import edu.gemini.gmp.top.Top
import edu.gemini.jms.api.JmsArtifact
import edu.gemini.jms.api.JmsProvider
import java.util.logging.Logger

/**
 * Class LocalStatusItemTranslatorImpl will publish status items translations directly back to the status handler aggregate
 *
 * @author Nicolas A. Barriga
 *         Date: 4/5/12
 */
class LocalStatusItemTranslator(top: Top, aggregate: StatusHandlerAggregate, xmlFileName: String) extends AbstractStatusItemTranslator(top, xmlFileName) with JmsArtifact with StatusItemTranslator {
  private final val LOG: Logger = Logger.getLogger(classOf[LocalStatusItemTranslator].getName)

   def start {
    LOG.finer("Start validate")
    //initItems
    LOG.finer("End validate")
  }

  def update[T](item: StatusItem[T]) {
    for (newItem <- translate(item)) {
      LOG.fine(s"Publishing translated status item: $newItem")
      aggregate.update(newItem)
    }
  }

}