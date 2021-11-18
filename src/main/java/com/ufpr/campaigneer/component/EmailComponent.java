package com.ufpr.campaigneer.component;

import com.ufpr.campaigneer.model.Participation;
import com.ufpr.campaigneer.utils.EmailKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

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
        emailSender = EmailKeys.getJavaMailSender();
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
        emailSender = EmailKeys.getJavaMailSender();
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
        emailSender = EmailKeys.getJavaMailSender();
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

    public void sendPaidStatusMail(Participation participation) {
        emailSender = EmailKeys.getJavaMailSender();
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("regisandre@ufpr.br");
        mail.setTo(participation.getEmail());
        mail.setSubject("O pagamento de sua Participação chegou!");
        mail.setText("Prezado(a) " + participation.getName() + "\n\n" +
                "O premio de sua Participação para a Campanha " + participation.getTriggeredCampaign().getName() +
                " foi processado e deve estar disponivel entre 3 e 5 dias úteis. \n" +
                "Observe que, caso haja um envio pelo Correios envolvido, esse tempo pode ser maior.\n" +
                "Cordialmente, o Campanheiro.");
        try {
            emailSender.send(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendClosedStatusMail(Participation participation) {
        emailSender = EmailKeys.getJavaMailSender();
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("regisandre@ufpr.br");
        mail.setTo(participation.getEmail());
        mail.setSubject("Estamos encerrando Participação");
        mail.setText("Prezado(a) " + participation.getName() + "\n\n" +
                "O premio de sua Participação para a Campanha " + participation.getTriggeredCampaign().getName() +
                " já foi entregue. \n" +
                "Considerando as leis vigentes, seus dados não são mais necessários para esta Participação.\n" +
                "Estaremos agora anonimizando as suas informações e encerrando sua Participação nesta Campanha. \n\n" +
                "Cordialmente, o Campanheiro.");
        try {
            emailSender.send(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
