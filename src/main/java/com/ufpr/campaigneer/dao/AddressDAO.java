package com.ufpr.campaigneer.dao;

import com.ufpr.campaigneer.model.AddressCountry;
import com.ufpr.campaigneer.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;


/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 26/02/2021
 */

public class AddressDAO {

    private Session session;

    public AddressCountry createCountry(AddressCountry country) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            country.setId((Integer) session.save(country));
            session.getTransaction().commit();
            return country;
        } finally {
            session.close();
        }
    }

    public AddressCountry updateCountry(AddressCountry country) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            session.merge(country);
            session.getTransaction().commit();
            return country;
        } finally {
            session.close();
        }
    }

    public void deleteCountry(AddressCountry country) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            session.delete(country);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public AddressCountry findCountryByCode(String code) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            Query query = session.createQuery("from AddressCountry country where country.code = :code ");
            query.setParameter("code", code);
            query.setMaxResults(1);
            AddressCountry country = (AddressCountry) query.uniqueResult();
            return country;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }
}