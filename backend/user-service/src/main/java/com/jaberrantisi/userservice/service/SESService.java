package com.jaberrantisi.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

@Service
public class SESService {

    private final SesClient sesClient;

    @Autowired
    public SESService(SesClient sesClient) {
        this.sesClient = sesClient;
    }
    public void sendEmail(String sender, String recipient, String sub, String text) {
        Destination destination = Destination.builder()
                .toAddresses(recipient)
                .build();
        Content subject = Content.builder()
                .data(sub)
                .build();
        Content textBody = Content.builder()
                .data(text)
                .build();
        Body body = Body.builder()
                .text(textBody)
                .build();
        Message message = Message.builder()
                .subject(subject)
                .body(body)
                .build();
        SendEmailRequest emailRequest = SendEmailRequest.builder()
                .destination(destination)
                .message(message)
                .source(sender)
                .build();
        sesClient.sendEmail(emailRequest);
    }
}
