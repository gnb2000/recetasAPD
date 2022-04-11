package com.recetasAPD.recetasAPD.services.EmailService;

public interface EmailService {

    boolean sendEmail(String destination, String subject, String content);
}
