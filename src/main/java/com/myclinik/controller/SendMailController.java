package com.myclinik.controller;

import com.myclinik.myclinik.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SendMailController {

    // Creamos una instancia del servicio para poder enviar emails
    @Autowired
    private SendMailService mailService;

    // Cargamos la vista del formulario en la ruta /mail
    @GetMapping("/mail")
    public String index(){
        return "send_mail_view";
    }

    // Envío del email desde el formulario
    @PostMapping("/sendMail")
    public String sendMail(@RequestParam("name") String name, @RequestParam("mail") String mail, @RequestParam("subject") String subject, @RequestParam("body") String body){ // Como parámetro llegan los inputs del formulario

        String from = "myclinik.org@gmail.com";
        String message = body +"\n\n\n Te hemos enviado este correo informativo a " + mail + "porque has contratado nuestros servicios recientemente. Si no quieres recibir correos con ofertas y promociones, háznoslo saber y te daremos de baja. \n © 2022 MyClinik Org, Av. Complutense, 30, 28040 Madrid, España."; // Formato del email
        mailService.sendMail(from,mail,subject,message);

        return "send_mail_view";
    }
}
