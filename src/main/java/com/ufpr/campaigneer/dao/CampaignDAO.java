package com.ufpr.campaigneer.dao;

import com.ufpr.campaigneer.model.Campaign;
import com.ufpr.campaigneer.model.Product;
import com.ufpr.campaigneer.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 04/07/2021
 */

public class CampaignDAO {

    private Session session;

    public Campaign create(Campaign campaign) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            campaign.setCreated(new Timestamp(new Date().getTime()));
            campaign.setId((Integer) session.save(campaign));
            session.getTransaction().commit();
            return campaign;
        } finally {
            session.close();
        }
    }

    public Campaign findByCode(String code) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            Query query = session
                    .createQuery("from Campaign campaign " +
                            "where campaign.code = :code " +
                            "and campaign.deleted is null");
            query.setParameter("code", code);
            query.setMaxResults(1);
            Campaign campaign = (Campaign) query.uniqueResult();
            return campaign;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public Campaign update(Campaign campaign) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            campaign.setUpdated(new Timestamp(new Date().getTime()));
            session.merge(campaign);
            session.getTransaction().commit();
            return campaign;
        } finally {
            session.close();
        }
    }

    public void delete(Campaign Campaign) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            Campaign.setDeleted(new Timestamp(new Date().getTime()));
            session.merge(Campaign);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public Campaign findDeletedByCode(String code) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            Query query = session
                    .createQuery("from Campaign campaign " +
                            "where campaign.code = :code " +
                            "and campaign.deleted is not null");
            query.setParameter("code", code);
            query.setMaxResults(1);
            Campaign campaign = (Campaign) query.uniqueResult();
            return campaign;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public void remove(Campaign campaign) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            session.delete(campaign);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public Campaign findById(int id) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            return session.load(Campaign.class, id);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }


    public Set<Campaign> findByBrand(int id) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            Query query = session
                    .createQuery("from Campaign campaign " +
                            "where campaign.promoter = :id " +
                            "and campaign.deleted is not null");
            query.setParameter("id", id);
            Set<Campaign> campaignSet = (Set<Campaign>) query.getResultList();
            return campaignSet;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public Set<Campaign> findByEan(String ean) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            Query query = session
                    .createQuery("from Campaign campaign " +
                            "join fetch campaign.participatingProducts as product " +
                            "where product.ean = :ean " +
                            "and campaign.deleted is not null");
            query.setParameter("ean", ean);
            Set<Campaign> campaignSet = (Set<Campaign>) query.getResultList();
            return campaignSet;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }
}
