package com.ufpr.campaigneer.dao;

import com.ufpr.campaigneer.model.DataCorrection;
import com.ufpr.campaigneer.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 04/10/2021
 */

public class DataCorrectionDAO {

    private Session session;

    public DataCorrection create(DataCorrection correction) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            correction.setCreated(new Timestamp(new Date().getTime()));
            correction.setId((Long) session.save(correction));
            session.getTransaction().commit();
            return correction;
        } finally {
            session.close();
        }
    }

    public DataCorrection update(DataCorrection correction) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            correction.setUpdated(new Timestamp(new Date().getTime()));
            session.merge(correction);
            session.getTransaction().commit();
            return correction;
        } finally {
            session.close();
        }
    }

    public DataCorrection findByCode(String code) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            Query query = session
                    .createQuery("from DataCorrection correction " +
                            "where correction.code = :code " +
                            "and correction.deleted is null");
            query.setParameter("code", code);
            query.setMaxResults(1);
            DataCorrection result = (DataCorrection) query.uniqueResult();
            return result;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public DataCorrection findByParticipationId(Long participationId) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            Query query = session
                    .createQuery("from DataCorrection correction " +
                            "where correction.participation.id = :participation " +
                            "and correction.isValid = :true " +
                            "and correction.deleted is null");
            query.setParameter("participation", participationId);
            query.setParameter("true", true);
            query.setMaxResults(1);
            DataCorrection result = (DataCorrection) query.uniqueResult();
            return result;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public List<DataCorrection> findCorrectionAttempts(Long participationId) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            Query query = session
                    .createQuery("from DataCorrection correction " +
                            "where correction.participation.id = :participation " +
                            "and correction.deleted is null");
            query.setParameter("participation", participationId);
            List<DataCorrection> result = query.getResultList();
            return result;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }
}
