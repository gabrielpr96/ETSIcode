package com.b0ve.daw.proyecto.service.helpers;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class MailingServicio {

    private final static String REPLAY_TO = "b0ve@b0ve.com",
            REPLAY_TO_NAME = "B0vE",
            SEND_FROM = "vencejocam1@gmail.com",
            SEND_FROM_NAME = "Proyecto DAW",
            SEND_FROM_PASS = "colegiovirgendelrocio";
    
    private final static MailingServicio instance = new MailingServicio();

    public static MailingServicio instance() {
        return instance;
    }

    public void sendMail(String to, String toName, String subject, String body) {
        Email email = EmailBuilder.startingBlank()
                .to(toName, to)
                .withReplyTo(REPLAY_TO_NAME, REPLAY_TO)
                .from(SEND_FROM_NAME, SEND_FROM)
                .withSubject(subject)
                .withHTMLText(body)
                .buildEmail();

        Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587, SEND_FROM, SEND_FROM_PASS)
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withDebugLogging(true)
                .async()
                .buildMailer();

        mailer.sendMail(email);
    }
}
