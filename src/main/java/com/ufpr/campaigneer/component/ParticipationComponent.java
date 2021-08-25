package com.ufpr.campaigneer.component;

import com.ufpr.campaigneer.dao.ParticipationDAO;
import com.ufpr.campaigneer.model.Participation;
import com.ufpr.campaigneer.service.ParticipationService;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 21/08/2021
 */

@Component
public class ParticipationComponent implements ParticipationService {

    ParticipationDAO dao = new ParticipationDAO();

    @Override
    public Optional<Participation> create(Participation participation) {
        return Optional.ofNullable(dao.create(participation));
    }

    @Override
    public Optional<Participation> update(Participation participation) {
        return Optional.ofNullable(dao.update(participation));
    }

    @Override
    public void delete(Participation participation) {
        dao.delete(participation);
    }

    @Override
    public Optional<Participation> findById(Long id) {
        return Optional.ofNullable(dao.findById(id));
    }

    @Override
    public Optional<Participation> findByEmail(String email) {
        return Optional.empty();
    }
}
