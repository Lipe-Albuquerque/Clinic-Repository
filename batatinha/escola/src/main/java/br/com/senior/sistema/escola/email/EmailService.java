package br.com.senior.sistema.escola.email;

public interface EmailService {

 String sendSimpleMail(EmailDetails details);

 String sendMailWithAttachment(EmailDetails details);
}