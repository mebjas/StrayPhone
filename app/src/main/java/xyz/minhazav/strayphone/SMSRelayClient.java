package xyz.minhazav.strayphone;

import java.util.ArrayList;

/**
 * Relay client to publish the SMS to all registered relays
 */
public class SMSRelayClient {
    /**
     * Name of the feature for book keeping
     */
    public static final String FEATURENAME = "SMS Relay";

    /**
     * Static instance
     */
    private static SMSRelayClient instance = null;

    /**
     * List of registered relays
     */
    private ArrayList<ISMSRelay> relays = new ArrayList<>();

    /**
     * Private construcpor
     */
    private SMSRelayClient() {
    }

    /**
     * Public accessor of the singleton class instance
     * @return instance of this class
     */
    public static SMSRelayClient Instance() {
        if (instance == null) {
            instance = new SMSRelayClient();
        }

        return instance;
    }

    /**
     * Method to register a relay
     * @param relay object
     */
    public void register(ISMSRelay relay) {
        relays.add(relay);
    }

    /**
     * Method to relay the SMS to registered relays
     * @param sms SMS data model
     */
    public void relay(SMSDataModel sms, BookKeeper bookKeeper) {
        //// TODO: as of now these are all being done sequentially
        //// add logic to finish them in parallel
        for (ISMSRelay relay: relays)
        {
            relay.relay(sms);
            bookKeeper.smsRelayBookKeeper.Log(relay.getName());
        }
    }
}
