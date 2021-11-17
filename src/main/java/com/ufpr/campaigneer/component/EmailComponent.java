package com.ufpr.campaigneer.component;

import com.ufpr.campaigneer.enums.CampaignStatus;
import com.ufpr.campaigneer.model.Participation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Properties;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 18/10/2021
 */

@Component
public class EmailComponent {

    Logger logger = LoggerFactory.getLogger(EmailComponent.class);

    @Autowired
    private JavaMailSender emailSender;

    public void sendNoCampaignMail(Participation participation) {
        emailSender = getJavaMailSender();
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("regisandre@ufpr.br");
        mail.setTo(participation.getEmail());
        mail.setSubject("Recebemos seus dados!");
        mail.setText("Prezado(a) " + participation.getName() + "\n\n" +
                "Recebemos os dados da sua Participação, mas não identificamos nenhuma Campanha aplicável. \n" +
                "Por favor, certifique-se que os dados informados estão de acordo com os Tremos da Campanha desejada.\n\n" +
                "Cordialmente, o Campanheiro.");
        try {
            emailSender.send(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendEnqueuedStatusMail(Participation participation) {
        emailSender = getJavaMailSender();
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("regisandre@ufpr.br");
        mail.setTo(participation.getEmail());
        mail.setSubject("Sua Participação está na fila de verificação");
        mail.setText("Prezado(a) " + participation.getName() + "\n\n" +
                "Verificamos os dados de sua Participação para a Campanha " + participation.getTriggeredCampaign().getName() +
                " e está tudo certo! \n" +
                "Sua participação passará agora pela fase de audição da Nota Fiscal e logo enviaremos o seu prêmio. \n\n" +
                "Cordialmente, o Campanheiro.");
        try {
            emailSender.send(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendCorrectionStatusMail(Participation participation, String problems, String codigo) {
        emailSender = getJavaMailSender();
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("regisandre@ufpr.br");
        mail.setTo(participation.getEmail());
        mail.setSubject("Recebemos sua Participação!");
        mail.setText("Prezado(a) " + participation.getName() + "\n\n" +
                "Recebemos sua Participação para a Campanha " + participation.getTriggeredCampaign().getName() +
                " e verificamos algumas inconformidades que impedem que sua Participação receba o prêmio oferecido. \n" +
                "Para que sua Participação seja avaliada novamente, por favor utilize o código informado para efetuar a correção dos seguintes dados: \n\n" +
                problems + "\n\n" +
                "Codigo da correção: " + codigo + "\n" +
                "Cordialmente, o Campanheiro.");
        try {
            emailSender.send(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendValidStatusMail(Participation participation) {
        emailSender = getJavaMailSender();
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("regisandre@ufpr.br");
        mail.setTo(participation.getEmail());
        mail.setSubject("Sua Participação foi aprovada!");
        mail.setText("Prezado(a) " + participation.getName() + "\n\n" +
                "Parabéns, sua Participação para a Campanha " + participation.getTriggeredCampaign().getName() +
                " foi processada e a avaliação foi positiva. \n" +
                "Sua participação está agora na fila para o pagamento, que poderá ser processado até o enceramento da campanha, de acordo com os termos da mesma.\n" +
                "Cordialmente, o Campanheiro.");
        try {
            emailSender.send(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp-mail.outlook.com");
        mailSender.setPort(587);

        mailSender.setUsername("regisandre@ufpr.br");
        mailSender.setPassword("v=YDHxT4UT8N");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

}
