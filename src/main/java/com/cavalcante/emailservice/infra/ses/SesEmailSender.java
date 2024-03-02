package com.cavalcante.emailservice.infra.ses;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.cavalcante.emailservice.adapters.EmailSenderGateway;
import com.cavalcante.emailservice.core.exception.EmailServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class SesEmailSender implements EmailSenderGateway {

    private final AmazonSimpleEmailService amazonSimpleEmailService;

    @Autowired
    public SesEmailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
        this.amazonSimpleEmailService = amazonSimpleEmailService;
    }
    @Override
    public void sendEmail(String to, String subject, String body) {
        SendEmailRequest request = new SendEmailRequest()
                .withSource("email@domain.com") // add your email here
                .withDestination(new Destination().withToAddresses(to))
                .withMessage(new Message()
                        .withSubject(new Content(subject))
                        .withBody(new Body().withText(new Content(body + "\n by ses.")))
                );

        try {
            this.amazonSimpleEmailService.sendEmail(request);
        } catch (AmazonServiceException e) {
            throw new EmailServiceException("Failure while send email", e);
        }
    }
}
