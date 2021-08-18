package com.ufpr.campaigneer.dao;

import com.ufpr.campaigneer.enums.AddressType;
import com.ufpr.campaigneer.model.AddressCountry;
import com.ufpr.campaigneer.model.Brand;
import com.ufpr.campaigneer.utils.HibernateUtils;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 02/06/2021
 */

public class BrandDAO {

    private Session session;

    public Brand create(Brand brand) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            brand.setCreated(new Timestamp(new Date().getTime()));
            brand.setId((Integer) session.save(brand));
            session.getTransaction().commit();
            return brand;
        } finally {
            session.close();
        }
    }

    public Brand findByName(String brandName) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            Query query = session
                .createQuery("from Brand brand " +
                                "where brand.name = :brandName " +
                                    "and brand.deleted is null");
            query.setParameter("brandName", brandName);
            query.setMaxResults(1);
            Brand brand = (Brand) query.uniqueResult();
            return brand;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public Brand findByNameAndCountryCode(String brandName, String countryCode) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            Query query = session
                .createQuery("from Brand brand " +
                                "join fetch brand.addresses as a " +
                                    "where brand.name = :brandName " +
                                    "and a.city.state.country.code = :countryCode " +
                                    "and brand.deleted is null");
            query.setParameter("brandName", brandName);
            query.setParameter("countryCode", countryCode);
            query.setMaxResults(1);
            Brand brand = (Brand) query.uniqueResult();
            return brand;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public Brand update(Brand brand) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            brand.setUpdated(new Timestamp(new Date().getTime()));
            session.merge(brand);
            session.getTransaction().commit();
            return brand;
        } finally {
            session.close();
        }
    }

    public Brand delete(Brand brand) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            brand.setDeleted(new Timestamp(new Date().getTime()));
            session.merge(brand);
            session.getTransaction().commit();
            return brand;
        } finally {
            session.close();
        }
    }

    public void remove(Brand brand) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            session.delete(brand);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public Brand findDeletedByNameAndCountryName(String brandName, String countryCode) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            Query query = session
                    .createQuery("from Brand brand " +
                                    "join fetch brand.addresses as a " +
                                        "where brand.name = :brandName " +
                                        "and a.city.state.country.code = :countryCode " +
                                        "and brand.deleted is not null");
            query.setParameter("brandName", brandName);
            query.setParameter("countryCode", countryCode);
            query.setMaxResults(1);
            Brand brand = (Brand) query.uniqueResult();
            return brand;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public Brand findDeletedByName(String brandName) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            Query query = session
                .createQuery("from Brand brand " +
                                    "where brand.name = :brandName " +
                                    "and brand.deleted is not null");
            query.setParameter("brandName", brandName);
            query.setMaxResults(1);
            Brand brand = (Brand) query.uniqueResult();
            return brand;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }
}
