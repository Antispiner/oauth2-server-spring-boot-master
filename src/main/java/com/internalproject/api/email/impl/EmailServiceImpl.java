package com.internalproject.api.email.impl;
import com.internalproject.api.email.templates.EmailTemplate;
import com.internalproject.api.exceptions.models.EmailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.internalproject.api.email.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static java.nio.charset.StandardCharsets.UTF_8;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Slf4j
@Service
public class EmailServiceImpl implements EmailService {
    @Value("${spring.mail.username}")
    private String hostEmail;

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;


    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    @Async
    public void sendEmail(String to, String subject, EmailTemplate template) {
        try {
            MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
                MimeMessageHelper messageHelper = createMessageHelper(subject, template, mimeMessage);
                messageHelper.setTo(to);
                messageHelper.setFrom(hostEmail);
            };
            javaMailSender.send(mimeMessagePreparator);
        } catch (Exception e) {
            if(e.getMessage().contains("550")){
                throw new EmailException(e.getMessage(),HttpStatus.BAD_REQUEST);
            }
            throw new EmailException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private MimeMessageHelper createMessageHelper(String subject, EmailTemplate template, MimeMessage mimeMessage) throws MessagingException {
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, UTF_8.name());
        messageHelper.setSubject(subject);
        String content = templateEngine.process(template.getHtmlForm(), template.getContext());
        messageHelper.setText(content, true);
        return messageHelper;
    }
}
