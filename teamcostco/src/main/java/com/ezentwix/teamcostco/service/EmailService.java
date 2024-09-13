package com.ezentwix.teamcostco.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendVerificationEmail(String toEmail, String token) {
        String verificationUrl = "http://localhost:9999/verify?token=" + token;
        String htmlContent = "<p>안녕하세요!</p>" +
                             "<p>아래 링크를 클릭하여 팀코스트코 이메일 인증을 완료해 주세요:</p>" +
                             "<a href=\"" + verificationUrl + "\">이메일 인증하기</a>" +
                             "<p>감사합니다.</p>";

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setTo(toEmail);
            messageHelper.setSubject("팀코스트코 회원 인증 이메일");
            messageHelper.setText(htmlContent, true); // true indicates HTML content

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("이메일 전송에 실패했습니다", e);
        }
    }
}