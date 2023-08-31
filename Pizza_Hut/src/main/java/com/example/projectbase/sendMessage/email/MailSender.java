package com.example.projectbase.sendMessage.email;

public interface MailSender {
    String sendMail(String to, String email);
    String sendPassword(String to, String email);
}
