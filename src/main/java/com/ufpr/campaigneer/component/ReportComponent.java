package com.ufpr.campaigneer.component;

import com.ufpr.campaigneer.dao.CampaignDAO;
import com.ufpr.campaigneer.dao.ParticipationDAO;
import com.ufpr.campaigneer.json.CampaignReportJSON;
import com.ufpr.campaigneer.model.Participation;
import com.ufpr.campaigneer.service.ReportService;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import java.util.List;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 25/01/2022
 */

@Component
public class ReportComponent implements ReportService {

    ParticipationDAO dao = new ParticipationDAO();

    @Override
    public CampaignReportJSON participationByCampaign(Long campaignId) {
        List<Participation> result =  dao.findAllByCampanign(campaignId);
        return mountReport(result);
    }

    private CampaignReportJSON mountReport(List<Participation> result) {
        CampaignReportJSON report = new CampaignReportJSON();
        try {
            report.setCampaignName(result.get(0).getTriggeredCampaign().getName());
            report.setPromoterName(result.get(0).getTriggeredCampaign().getPromoter().getName());
            report.setTotalParticipations(result.size());
            result.forEach(each -> {
                switch (each.getCampaignStatus()) {
                    case CORRECTION_QUEUE:
                        report.setCorrectionQueue(report.getCorrectionQueue() + 1);
                        break;
                    case VALID:
                        report.setValid(report.getValid() + 1);
                        break;
                    case PAID:
                        report.setPaid(report.getPaid() + 1);
                        break;
                    case CLOSED:
                        report.setClosed(report.getClosed() + 1);
                        break;
                    default:
                        report.setNotValidated(report.getNotValidated() + 1);
                        break;
                }
            });
        } catch (Exception e) {
            throw new NotFoundException("Failed to reprocess Participation. " + e.getMessage());
        }
        return report;
    }
}

