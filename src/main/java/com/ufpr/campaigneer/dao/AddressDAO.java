package com.ufpr.campaigneer.dao;

import com.ufpr.campaigneer.model.Address;
import com.ufpr.campaigneer.model.AddressCity;
import com.ufpr.campaigneer.model.AddressCountry;
import com.ufpr.campaigneer.model.AddressState;
import com.ufpr.campaigneer.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;


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

    public AddressCountry findByCountryCode(String code) {
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

    public AddressState createState(AddressState state) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            state.setId((Integer) session.save(state));
            session.getTransaction().commit();
            return state;
        } finally {
            session.close();
        }
    }

    public AddressState updateState(AddressState state) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            session.merge(state);
            session.getTransaction().commit();
            return state;
        } finally {
            session.close();
        }
    }

    public void deleteState(AddressState state) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            session.delete(state);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public AddressState findByStateCodeAndCountryCode(String stateCode, String countryCode) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            Query query = session
                    .createQuery("from AddressState state " +
                                        "join AddressCountry country " +
                                    "where country.id = state.country.id " +
                                        "and state.code = :stateCode " +
                                        "and country.code = :countryCode ");
            query.setParameter("stateCode", stateCode);
            query.setParameter("countryCode", countryCode);
            query.setMaxResults(1);
            AddressState state = (AddressState) query.uniqueResult();
            return state;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public AddressCity createCity(AddressCity city) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            city.setId((Integer) session.save(city));
            session.getTransaction().commit();
            return city;
        } finally {
            session.close();
        }
    }

    public void deleteCity(AddressCity city) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            session.delete(city);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public AddressCity updateCity(AddressCity city) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            session.merge(city);
            session.getTransaction().commit();
            return city;
        } finally {
            session.close();
        }
    }

    public AddressCity findByCityNameAndStateCode(String cityName, String stateCode) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            Query query = session
                    .createQuery("from AddressCity city " +
                                        "join AddressState state " +
                                    "where state.id = city.state.id " +
                                        "and city.name = :cityName " +
                                        "and state.code = :stateCode ");
            query.setParameter("cityName", cityName);
            query.setParameter("stateCode", stateCode);
            query.setMaxResults(1);
            AddressCity city = (AddressCity) query.uniqueResult();
            return city;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public Address createAddress(Address address) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            address.setId((Integer) session.save(address));
            session.getTransaction().commit();
            return address;
        } finally {
            session.close();
        }
    }

    public Address updateAddress(Address address) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            session.merge(address);
            session.getTransaction().commit();
            return address;
        } finally {
            session.close();
        }
    }

    public void deleteAddress(Address address) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            session.delete(address);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }
}