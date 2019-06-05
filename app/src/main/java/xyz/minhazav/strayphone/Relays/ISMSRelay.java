package xyz.minhazav.strayphone.Relays;

/**
 * interface defines Relay client for SMS
 */
public interface ISMSRelay
{
    /**
     * method to relay the SMS to some relay server
     * @param sms SMS Object
     * TODO: have some return type to return information back to the caller
     */
    void relay(SMSDataModel sms);

    /**
     * Method to get name of the relay
     * @return name of the relay
     */
    String getName();
}