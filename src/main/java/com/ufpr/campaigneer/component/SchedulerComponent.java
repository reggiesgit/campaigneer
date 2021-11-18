package com.ufpr.campaigneer.component;

import com.ufpr.campaigneer.enums.CampaignStatus;
import com.ufpr.campaigneer.model.Participation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import java.util.List;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 17/11/2021
 */

@EnableScheduling
@Component
public class SchedulerComponent {

    private final long MINUTO = 1000 * 60;
    Logger logger = LoggerFactory.getLogger(SchedulerComponent.class);

    ParticipationComponent participationComponent = new ParticipationComponent();
    EmailComponent emailComponent = new EmailComponent();

    @Scheduled(initialDelay = 5 * MINUTO, fixedDelay = 10 * MINUTO)
    public void scheduleClosure() {
        logger.info("Running scheduled search for Paid Participations.");
        List<Participation> toClose = participationComponent.findAllPaid();
        logger.info("Found " + toClose.size() + " Participations to be closed.");
        toClose.forEach(each -> {
            logger.info("Closing and anonimizing Participation with id: " + each.getId());
            each.setCampaignStatus(CampaignStatus.CLOSED);
            emailComponent.sendClosedStatusMail(each);
            participationComponent.anonimize(each);
            participationComponent.update(each);
        });
        logger.info("Finished scheduled search for Paid Participations.");
    }

}
