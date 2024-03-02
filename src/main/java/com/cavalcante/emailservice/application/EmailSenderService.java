package com.cavalcante.emailservice.application;

import com.cavalcante.emailservice.adapters.EmailSenderGateway;
import com.cavalcante.emailservice.core.EmailSenderUserCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailSenderService  implements EmailSenderUserCase {

    private final EmailSenderGateway emailSenderGateway;

    @Autowired
    public EmailSenderService( EmailSenderGateway emailSenderGateway) { // @Qualifier("mailGunEmailSender") add to force the use with mailGun
        this.emailSenderGateway = emailSenderGateway;
    }

    @Override
    public void sendEmail(String to, String subject, String body) throws IOException {
        this.emailSenderGateway.sendEmail(to, subject, body);
    }
}