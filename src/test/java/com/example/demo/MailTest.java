package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {
    @Resource
    private JavaMailSender javaMailSender;

    @Test
    public void sendMail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("sainreales12@gmail.com");
        msg.setTo("elbananoclasico@gmail.com");
        msg.setSubject("hey");
        msg.setText("hola");
        javaMailSender.send(msg);
    }
}
