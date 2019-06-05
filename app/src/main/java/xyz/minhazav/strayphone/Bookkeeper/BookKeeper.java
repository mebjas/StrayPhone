package xyz.minhazav.strayphone.Bookkeeper;

import java.util.ArrayList;
import java.util.Date;

/**
 * Method for logging statistics to Data Base and reading from it
 */
public class BookKeeper {
    /**
     * Instance of sms relay book keeper
     */
    public SMSRelayBookKeeper smsRelayBookKeeper = new SMSRelayBookKeeper();

    /**
     * Method to insert a row into DB
     * @param feature name of feature
     * @param namespace namespace within feature name
     * @param hits number of hits
     * @param datetime date time of insertion
     */
    private void Insert(String feature, String namespace, Integer hits, Date datetime) {
        throw new UnsupportedOperationException();
    }

    /**
     * Method to insert a row into DB
     * @param feature name of feature
     * @param namespace namespace within feature name
     */
    public void Insert(String feature, String namespace) {
        Insert(feature, namespace, 1, new Date(System.currentTimeMillis()));
    }

    /**
     * Method to get list of all features in DB
     * @return list of all features in DB
     */
    public ArrayList<String> GetFeatures() {
        throw new UnsupportedOperationException();
    }

    /**
     * Method to fet list of all namespace by feature
     * @param featureName name of the feature
     * @return list of namespaces in a feature
     */
    public ArrayList<String> GetNamespacesByFeature(String featureName) {
        throw new UnsupportedOperationException();
    }

    /**
     * Method to get count of rows for feature namespace combination
     * @param feature name of feature
     * @param namespace namespace within feature name
     * @return number of rows for a gives feature and namespace
     */
    public Integer GetCount(String feature, String namespace) {
        throw new UnsupportedOperationException();
    }

    /***
     * Class to define SMS Relay book keeper
     */
    public class SMSRelayBookKeeper {
        /**
         * Name of the feature
         */
        private final String FEATURE = "SMSRelay";

        /**
         * Logs a hit into namespace
         * @param relayName name of relay, acts as namespace here
         */
        public void Log(String relayName) {
            Insert(FEATURE, relayName);
        }
    }
}
