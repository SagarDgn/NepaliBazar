package com.nepalibazar.service;

import io.micronaut.context.annotation.Property;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

@Singleton
public class EmailService {
    private Session session;
    private String fromEmail;

    @Inject
    public EmailService(@Property(name = "smtp.username") String username,
                        @Property(name = "smtp.password") String password){
        this.fromEmail=username;
        Properties properties= new Properties();
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.auth","true" );
        properties.put("mail.smtp.port","587" );
        properties.put("mail.smtp.starttls.enable", "true");
        this.session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        });

    }

    public void sendOtp(String email, String otp) {
        if (email == null || email.isBlank()) {
            throw new RuntimeException("Email is null or blank");
        }

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("NepaliBazar account verification");
            message.setText("Your OTP is: " + otp);
            Transport.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage(), e);
        }
    }

}
