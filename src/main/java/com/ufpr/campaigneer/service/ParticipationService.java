package com.ufpr.campaigneer.service;

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

    Optional<Participation> findByEmail(String email);
}