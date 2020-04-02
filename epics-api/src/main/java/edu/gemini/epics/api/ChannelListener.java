package edu.gemini.epics.api;

import java.util.List;

/**
 * Interface ChannelListener
 *
 * @author Nicolas A. Barriga
 *         Date: 3/16/11
 */
public interface ChannelListener<T> extends EpicsListener<T> {

    /**
     * Called when the specified channel value changes.
     *
     * @param channelName name of the epics channel that changed.
     * @param values      the new values for the epics channel
     */
    void valueChanged(String channelName, List<T> values);
}
