package com.cavalcante.emailservice.infra.mailgun;

import com.cavalcante.emailservice.adapters.EmailSenderGateway;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.ws.rs.core.MediaType;
import org.springframework.stereotype.Service;

@Service
public class MailGunEmailSender implements EmailSenderGateway {
    @Override
    public void sendEmail(String to, String subject, String body) {

        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api", "fd5ad30a942096639f95c9d18d712000-8c9e82ec-55b1513f"));
        WebResource webResource = client.resource("https://api.mailgun.net/v3/sandbox9d8652f1bff34ee5aa8b48887a704d13.mailgun.org/messages");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", to); // putting receiver only for testing !!
        formData.add("to", to);
        formData.add("subject", subject);
        formData.add("text", body + "\n by mailGun.");
        webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);
    }
}
