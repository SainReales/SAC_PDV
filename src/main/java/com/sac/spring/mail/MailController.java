package com.sac.spring.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
@Controller
public class MailController {
    @Autowired
    public JavaMailSender emailSender;

    @ResponseBody
    @RequestMapping("/mail")
    public String sendAttachmentEmail() throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();

        boolean multipart = true;

        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(InternetAddress.parse("ransai1212@gmail.com,saiwear24@gmail.com,saiyong1212@gmail.com"));

        helper.setSubject("Cierre");

        helper.setText("Cierre realizado, sin novedades.");



        emailSender.send(message);

        return "Mensajes de cierre enviados";
    }
}
