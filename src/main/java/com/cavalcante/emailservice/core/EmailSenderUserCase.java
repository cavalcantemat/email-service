package com.cavalcante.emailservice.core;

import java.io.IOException;

public interface EmailSenderUserCase {
    void sendEmail(String to, String subject, String body) throws IOException;
}
