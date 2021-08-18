package com.ufpr.campaigneer.dao;

import com.ufpr.campaigneer.model.Campaign;
import com.ufpr.campaigneer.model.Participation;
import com.ufpr.campaigneer.utils.HibernateUtils;
import org.hibernate.Session;

import java.sql.Timestamp;
import java.util.Date;

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
            participation.setId((Integer) session.save(participation));
            session.getTransaction().commit();
            return participation;
        } finally {
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

}
