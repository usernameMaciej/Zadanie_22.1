package com.example.zadanie221;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    void send(String name, String email, String description, boolean confirmation, String supportEmail) {
        if (!confirmation) {
            sendMail(name, email, description, supportEmail);
        }
        sendBackMessage(name, email, supportEmail);
    }

    private void sendMail(String name, String sender, String description, String supportEmail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setSubject("Formularz kontaktowy od: " + name + " - " + sender);
        mailMessage.setReplyTo(sender);
        mailMessage.setText(description);
        mailMessage.setTo(supportEmail);
        javaMailSender.send(mailMessage);
    }

    private void sendBackMessage(String name, String receiver, String supportMail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        String replyMessage = name + ",\nTwój formularz został pomyślnie wysłany do naszego działu pomocy. Wkrótce ktoś się z Tobą skontaktuje.";
        mailMessage.setFrom(supportMail);
        mailMessage.setSubject("Potwierdzenie");
        mailMessage.setText(replyMessage);
        mailMessage.setTo(receiver);
        javaMailSender.send(mailMessage);
    }
}
