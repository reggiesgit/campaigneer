package com.ufpr.campaigneer.dao;

import com.ufpr.campaigneer.model.Address;
import com.ufpr.campaigneer.model.AddressCity;
import com.ufpr.campaigneer.model.AddressCountry;
import com.ufpr.campaigneer.model.AddressState;
import com.ufpr.campaigneer.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;
import java.sql.Timestamp;
import java.util.Date;


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
            country.setCreated(new Timestamp(new Date().getTime()));
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
            country.setUpdated(new Timestamp(new Date().getTime()));
            session.merge(country);
            session.getTransaction().commit();
            return country;
        } finally {
            session.close();
        }
    }

    public AddressCountry findByCountryCode(String code) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            Query query = session
                    .createQuery("from AddressCountry country " +
                                    "where country.code = :code " +
                                        "and country.deleted is null ");
            query.setParameter("code", code);
            query.setMaxResults(1);
            AddressCountry country = (AddressCountry) query.uniqueResult();
            return country;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public AddressCountry findByDeletedCountryCode(String code) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            Query query = session
                    .createQuery("from AddressCountry country " +
                            "where country.code = :code " +
                            "and country.deleted is not null ");
            query.setParameter("code", code);
            query.setMaxResults(1);
            AddressCountry country = (AddressCountry) query.uniqueResult();
            return country;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public void deleteCountry(AddressCountry country) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            country.setDeleted(new Timestamp(new Date().getTime()));
            session.merge(country);
            session.getTransaction().commit();
        } catch(PersistenceException pe) {

        } finally {
            session.close();
        }
    }

    public void removeCountry(AddressCountry country) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            session.delete(country);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public AddressState createState(AddressState state) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            state.setCreated(new Timestamp(new Date().getTime()));
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
            state.setUpdated(new Timestamp(new Date().getTime()));
            session.merge(state);
            session.getTransaction().commit();
            return state;
        } finally {
            session.close();
        }
    }

    public AddressState findByStateCodeAndCountryCode(String stateCode, String countryCode) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            Query query = session
                    .createQuery("from AddressState state " +
                                    "where state.code = :stateCode " +
                                        "and country.code = :countryCode " +
                                        "and state.deleted is null ");
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

    public AddressState findByDeletedStateCodeAndCountryCode(String stateCode, String countryCode) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            Query query = session
                    .createQuery("from AddressState state " +
                            "where state.code = :stateCode " +
                            "and country.code = :countryCode " +
                            "and state.deleted is not null");
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

    public void deleteState(AddressState state) {
        try {
            if (state.getDeleted() == null) {
                session = HibernateUtils.initSession();
                session.beginTransaction();
                state.setDeleted(new Timestamp(new Date().getTime()));
                session.merge(state);
                session.getTransaction().commit();
            } else {
                removeState(state);
            }
        } finally {
            session.close();
        }
    }

    public void removeState(AddressState state) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            session.delete(state);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public AddressCity createCity(AddressCity city) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            city.setCreated(new Timestamp(new Date().getTime()));
            city.setId((Integer) session.save(city));
            session.getTransaction().commit();
            return city;
        } finally {
            session.close();
        }
    }

    public AddressCity updateCity(AddressCity city) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            city.setUpdated(new Timestamp(new Date().getTime()));
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
                                    "where city.name = :cityName " +
                                        "and state.code = :stateCode " +
                                        "and city.deleted is null ");
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

    public AddressCity findByDeletedCityNameAndStateCode(String cityName, String stateCode) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            Query query = session
                    .createQuery("from AddressCity city " +
                                    "where city.name = :cityName " +
                                        "and state.code = :stateCode " +
                                        "and city.deleted is not null ");
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

    public void deleteCity(AddressCity city) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            city.setDeleted(new Timestamp(new Date().getTime()));
            session.merge(city);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public void removeCity(AddressCity city) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            session.delete(city);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public Address createAddress(Address address) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            address.setCreated(new Timestamp(new Date().getTime()));
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
            address.setUpdated(new Timestamp(new Date().getTime()));
            session.merge(address);
            session.getTransaction().commit();
            return address;
        } finally {
            session.close();
        }
    }

    public Address findByPostalCodeAndNumber(String cep, int number) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            Query query = session
                    .createQuery("from Address address " +
                                    "where address.postalCode = :postalCode " +
                                        "and address.streetNumber = :streetNumber " +
                                        "and address.deleted is null");
            query.setParameter("postalCode", cep);
            query.setParameter("streetNumber", number);
            query.setMaxResults(1);
            Address address = (Address) query.uniqueResult();
            return address;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public Address findByDeletedPostalCodeAndNumber(String cep, int number) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            Query query = session
                    .createQuery("from Address address " +
                                    "where address.postalCode = :postalCode " +
                                        "and address.streetNumber = :streetNumber " +
                                        "and address.deleted is not null");
            query.setParameter("postalCode", cep);
            query.setParameter("streetNumber", number);
            query.setMaxResults(1);
            Address address = (Address) query.uniqueResult();
            return address;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public void deleteAddress(Address address) {
        try {
            session = HibernateUtils.initSession();
            session.beginTransaction();
            address.setDeleted(new Timestamp(new Date().getTime()));
            session.merge(address);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public void removeAddress(Address address) {
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