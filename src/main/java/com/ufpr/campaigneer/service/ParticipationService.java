package com.ufpr.campaigneer.service;

import com.ufpr.campaigneer.model.CampaignViolations;
import com.ufpr.campaigneer.model.Participation;
import org.jvnet.hk2.annotations.Service;

import java.util.Optional;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 21/08/2021
 */

@Service
public interface ParticipationService {

    Optional<Participation> create(Participation participation);
    Optional<Participation> update(Participation participation);
    void delete(Participation participation);

    Optional<Participation> findById(Long id);
    Optional<Participation> findByEmail(String email);

    Participation reprocess(Long id);

    Optional<Participation> retrieveFromCorrectionQueue(Long campaignId);

    Participation uptadeVerification(Long id, CampaignViolations violations);

    Optional<Participation> correctData(Participation mapJson, String uuid);

    Participation setPaid(Long id);
}
