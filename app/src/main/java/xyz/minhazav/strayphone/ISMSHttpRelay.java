package xyz.minhazav.strayphone;

import okhttp3.Request;

/**
 * Interface to define HTTP based relay for SMS forwarding
 */
public interface ISMSHttpRelay extends ISMSRelay
{
    /**
     * Method to create object for relaying SMS to some HTTP server
     * @param sms SMS Data Model
     * @return Request object created
     */
    Request createRequest(SMSDataModel sms);
}