package com.ufpr.campaigneer.dao;

import com.ufpr.campaigneer.model.Product;
import com.ufpr.campaigneer.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 02/07/2021
 */

public class ProductDAO {

    private Session session;

    public Product create(Product product) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            product.setCreated(new Timestamp(new Date().getTime()));
            product.setId((Long) session.save(product));
            session.getTransaction().commit();
            return product;
        } finally {
            session.close();
        }
    }

    public Product findById(Long id) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            return session.load(Product.class, id);
        } finally {
            session.close();
        }
    }

    public Product findByEAN(String ean) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            Query query = session
                    .createQuery("from Product product " +
                                    "where product.ean = :ean " +
                                        "and product.deleted is null");
            query.setParameter("ean", ean);
            query.setMaxResults(1);
            Product product = (Product) query.uniqueResult();
            return product;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public Product update(Product product) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            product.setUpdated(new Timestamp(new Date().getTime()));
            session.merge(product);
            session.getTransaction().commit();
            return product;
        } finally {
            session.close();
        }
    }

    public void delete(Product product) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            product.setDeleted(new Timestamp(new Date().getTime()));
            session.merge(product);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public Product findDeletedByEAN(String ean) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            Query query = session
                    .createQuery("from Product product " +
                            "where product.ean = :ean " +
                            "and product.deleted is not null");
            query.setParameter("ean", ean);
            query.setMaxResults(1);
            Product product = (Product) query.uniqueResult();
            return product;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public void remove(Product product) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            session.delete(product);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }
}
