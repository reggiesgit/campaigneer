package com.ufpr.campaigneer.dao;

import com.ufpr.campaigneer.model.Brand;
import com.ufpr.campaigneer.utils.HibernateUtils;
import org.hibernate.Hibernate;
import org.hibernate.Session;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 02/06/2021
 */

public class BrandDAO {

    private Session session;

    public Brand createBrand(Brand brand) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            brand.setId((Integer) session.save(brand));
            session.getTransaction().commit();
            return brand;
        } finally {
            session.close();
        }
    }
}
