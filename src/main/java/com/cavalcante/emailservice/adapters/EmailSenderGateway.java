package com.cavalcante.emailservice.adapters;

import java.io.IOException;

public interface EmailSenderGateway {
    void sendEmail(String to, String subject, String body) throws IOException;
}
