package main.java.utility;


import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.methods.SlackApiException;
import com.github.seratch.jslack.api.methods.request.chat.ChatPostMessageRequest;
import com.github.seratch.jslack.api.methods.response.chat.ChatPostMessageResponse;

import java.io.IOException;

public class SlackMessageHelper {

    public void sendMessage(String channelName, String message, String token) throws IOException {
        Slack slack = Slack.getInstance();
        ChatPostMessageResponse postResponse = null;
        try {
            postResponse = slack.methods().chatPostMessage(
                    ChatPostMessageRequest.builder()
                            .token(token)
                            .channel(channelName)
                            .text(message)
                            .build());
        } catch (SlackApiException e) {
            e.printStackTrace();
            System.out.println("Something went wrong while sending Slack message: "+postResponse.getError());
        }
    }
}
