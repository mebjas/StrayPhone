package xyz.minhazav.strayphone;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * SMS to slack
 */
public class SMSToSlackRelay extends AbstractSMSHttpRelay implements ISMSRelay
{
    /**
     * JSON Media type
     */
    private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * Name of the relay
     */
    private final String RELAYNAME = "SLACK";

    /**
     * contains key for webhook
     */
    private String webhookURL = null;

    public SMSToSlackRelay(String webhookUrl)
    {
        webhookURL = webhookURL;
    }

    /**
     * Method to create request object for sending to SLACK channel
     * @param sms SMS Data Model
     * @return request object
     */
    @Override
    public Request createRequest(SMSDataModel sms)
    {
        String encodedSlackMessage = "{\"text\":\"" +sms.body +"\"}";
        RequestBody requestBody = RequestBody.create(JSON, encodedSlackMessage);
        Request request = new Request.Builder()
                .url(webhookURL)
                .post(requestBody)
                .build();

        return request;
    }

    /**
     * Method to get name of the relay
     * @return name of the relay
     */
    @Override
    public String getName() {
        return RELAYNAME;
    }
}
