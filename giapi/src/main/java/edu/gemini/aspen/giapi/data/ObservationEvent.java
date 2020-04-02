package edu.gemini.aspen.giapi.data;

/**
 * Definiton of the GIAPI Observation Events.
 *
 */
public enum ObservationEvent {
    /**
     * Event sent as instrument starts preparation for starting
     * acquisition of a dataset.
     */
    OBS_PREP("OBS_PREP"),
    /**
     * Event sent just before data acquisition starts.
     */
    OBS_START_ACQ("OBS_START_ACQ"),
    /**
     * Event sent when the requested acquisition has completed.
     */
    OBS_END_ACQ("OBS_END_ACQ"),
    /**
     * Event indicates that data is being transferred from the
     * detector or other activities needed to write data.
     */
    OBS_START_READOUT("OBS_START_READOUT"),
    /**
     * Event indicates readout or write preparations have completed
     */
    OBS_END_READOUT("OBS_END_READOUT"),
    /**
     * Event indicates that the instrument has started writing
     * the dataset to GSDN
     */
    OBS_START_DSET_WRITE("OBS_START_DSET_WRITE"),
    /**
     * Event indicates that the instrument has completed writing
     * the dataset to GSDN
     */
    OBS_END_DSET_WRITE("OBS_END_DSET_WRITE"),

    /**
     * Optional event that is used to indicate that an observation is
     * started by an external entity
     */
    EXT_START_OBS("EXT_START_OBS"),

    /**
     * Optional event that is used to indicate that an observation has
     * been completed by an external entity
     */
    EXT_END_OBS("EXT_END_OBS");

    private final String eventName;

    private ObservationEvent(String name) {
        eventName = name;
    }

    public String getObservationEventName() {
        return eventName;
    }

}