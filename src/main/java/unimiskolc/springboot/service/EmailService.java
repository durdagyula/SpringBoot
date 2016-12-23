package unimiskolc.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import unimiskolc.springboot.model.User;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(User user, String email) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom("mscpringboot@gmail.com");
        mail.setSubject("Registration");
        mail.setText("Hi, you successfully registered yourself on my webpage." +
                "Your username: " + user.getUserName() + " " +
                "and your password: " + user.getUserPassword());
        javaMailSender.send(mail);
    }
}
