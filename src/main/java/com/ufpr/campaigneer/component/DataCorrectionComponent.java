package com.ufpr.campaigneer.component;

import com.ufpr.campaigneer.dao.DataCorrectionDAO;
import com.ufpr.campaigneer.enums.ViolationType;
import com.ufpr.campaigneer.model.DataCorrection;
import com.ufpr.campaigneer.model.Participation;
import com.ufpr.campaigneer.service.DataCorrectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotFoundException;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 04/10/2021
 */

@Component
public class DataCorrectionComponent implements DataCorrectionService {

    DataCorrectionDAO dao = new DataCorrectionDAO();
    @Autowired
    ParticipationComponent participationComponent; //= new ParticipationComponent();

    public String setupCorrection(Participation part) {
        DataCorrection correction = new DataCorrection();
        correction.setCode(UUID.randomUUID().toString());
        correction.setParticipation(part);
        correction.setValid(true);
        DataCorrection created = dao.create(correction);
        return created.getCode();
    }

    public boolean validateCode(String uuid) {
        DataCorrection correction = dao.findByCode(uuid);
        return correction.isValid();
    }

    @Override
    public Long findByValidationCode(String uuid) {
        DataCorrection correction = dao.findByCode(uuid);
        if (correction.isValid()) {
            Participation valid = participationComponent.findById(correction.getParticipation().getId())
                    .orElseThrow(() -> new NotFoundException("No Participation eligible for correction found."));
            return valid.getId();
        } else {
            throw new ForbiddenException("This Participation is not eligible for correction");
        }
    }

    public void makeInvalid(Long id) {
        DataCorrection correction = dao.findByParticipationId(id);
        correction.setValid(false);
        dao.update(correction);
    }
}
