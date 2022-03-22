package com.myclinik.myclinik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendMailService {
    
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String from, String to, String subject, String body) {

        SimpleMailMessage mail = new SimpleMailMessage(); // Para construir el email

        // Cambiamos los atributos de la clase por los que nos llegan como parámetro
        mail.setFrom(from);
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(body);

        // Enviamos el email haciendo uso del método sen de la clase JavaMailSender
        javaMailSender.send(mail);
    }
}
