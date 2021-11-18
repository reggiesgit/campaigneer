package com.ufpr.campaigneer.dao;

import com.ufpr.campaigneer.enums.CampaignStatus;
import com.ufpr.campaigneer.model.Participation;
import com.ufpr.campaigneer.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 12/07/2021
 */

public class ParticipationDAO {

    private Session session;

    public Participation create(Participation participation) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            participation.setCreated(new Timestamp(new Date().getTime()));
            participation.setId((Long) session.save(participation));
            session.getTransaction().commit();
            return participation;
        } finally {
            session.close();
        }
    }

    public Participation findById(Long id) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            Participation result = session.get(Participation.class, id);
            return result;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public Participation findByEmail(String email) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            Query query = session
                    .createQuery("from Participation part " +
                            "where part.email = :email " +
                            "and part.deleted is null");
            query.setParameter("email", email);
            query.setMaxResults(1);
            Participation result = (Participation) query.uniqueResult();
            return result;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public Participation update(Participation participation) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            participation.setUpdated(new Timestamp(new Date().getTime()));
            session.merge(participation);
            session.getTransaction().commit();
            return participation;
        } finally {
            session.close();
        }
    }

    public void delete(Participation participation) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            participation.setDeleted(new Timestamp(new Date().getTime()));
            session.merge(participation);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public void remove(Participation participation) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            session.delete(participation);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public List<Participation> findTwinParticipations(Participation part) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            Query query = session
                    .createQuery("from Participation part " +
                            "where part.name = :otherName " +
                            "and part.lastName = :otherLastName " +
                            "and part.email = :otherEmail " +
                            "and part.triggeredCampaign.id = :otherCampaign " +
                            "and part.deleted is null ");
            query.setParameter("otherName", part.getName());
            query.setParameter("otherLastName", part.getLastName());
            query.setParameter("otherEmail", part.getEmail());
            query.setParameter("otherCampaign", part.getTriggeredCampaign().getId());
            List<Participation> result = query.getResultList();
            return result;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public Participation findFromQueueByCampaign(Long campaignId) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            Query query = session
                    .createQuery("from Participation part " +
                            "where part.triggeredCampaign.id = :campaignId " +
                            "and part.campaignStatus = :status " +
                            "and part.deleted is null ");
            query.setParameter("campaignId", campaignId);
            query.setParameter("status", CampaignStatus.VALIDATION_QUEUE);
            query.setMaxResults(1);
            Participation result = (Participation) query.uniqueResult();
            return result;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public List<Participation> findAllPaid() {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            Query query = session
                    .createQuery("from Participation part " +
                            "where part.campaignStatus = :status " +
                            "and part.deleted is null ");
            query.setParameter("status", CampaignStatus.PAID);
            List<Participation> result = query.getResultList();
            return result;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }
}
