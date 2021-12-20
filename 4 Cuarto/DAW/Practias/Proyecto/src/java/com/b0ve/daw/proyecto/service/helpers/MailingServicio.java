package com.b0ve.daw.proyecto.service.helpers;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import static com.b0ve.daw.proyecto.helpers.InstallationConstants.*;

@Deprecated
public class MailingServicio {
    
    private final static MailingServicio instance = new MailingServicio();

    public static MailingServicio instance() {
        return instance;
    }

    @Deprecated
    public void sendMail(String to, String toName, String subject, String body) {
        Email email = EmailBuilder.startingBlank()
                .to(toName, to)
                .withReplyTo(EMAIL_REPLY_NAME, EMAIL_REPLY)
                .from(EMAIL_FROM_NAME, EMAIL_FROM)
                .withSubject(subject)
                .withHTMLText(body)
                .buildEmail();

        Mailer mailer = MailerBuilder
                .withSMTPServer(EMAIL_SMTP_SERVER, EMAIL_SMTP_PORT, EMAIL_FROM, EMAIL_FROM_PASS)
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withDebugLogging(true)
                .async()
                .buildMailer();

        mailer.sendMail(email);
    }
}
