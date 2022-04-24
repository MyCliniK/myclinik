package com.myclinik.controller;

import com.myclinik.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;

import java.util.List;

import com.myclinik.model.Client;
import com.myclinik.service.IClientService;

@Controller
public class SendMailController {

    // Creamos una instancia del servicio para poder enviar emails
    @Autowired
    private SendMailService mailService;

	@Autowired
	private IClientService clientService;

    // Cargamos la vista del formulario en la ruta /mail
    @GetMapping("/mail")
    public String mailForm(Model model){
		var clients = (List<Client>) clientService.findAll();
		var client = new Client();
		model.addAttribute("clients", clients);
		model.addAttribute("client", client);
        return "send_mail_view";
    }

	@RequestMapping(value = "/mail", params = "clientId")
	public String mailForm(@RequestParam("clientId") Long clientId, Model model){
		var clients = (List<Client>) clientService.findAll();
		model.addAttribute("clients", clients);
		model.addAttribute("clientId", clientId);
        return "send_mail_view";
	}

    // Envío del email desde el formulario
    @PostMapping("/sendMail")
    public String sendMail(@RequestParam("client") Client client, @RequestParam("subject") String subject, @RequestParam("body") String body){ // Como parámetro llegan los inputs del formulario
		String to = client.getEmail();
        String from = "myclinik.org@gmail.com";
        String message = body +"\n\n\n Te hemos enviado este correo informativo a " + to + " porque has contratado nuestros servicios recientemente. Si no quieres recibir correos con ofertas y promociones, háznoslo saber y te daremos de baja. \n © 2022 MyClinik Org, Av. Complutense, 30, 28040 Madrid, España."; // Formato del email
        mailService.sendMail(from,to,subject,message);

        return "redirect:/mail";
    }
}
