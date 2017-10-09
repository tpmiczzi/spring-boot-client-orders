package aplication.dao;

import aplication.entity.Client;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("clientRepository")
public class ClientRepositoryImpl implements ClientRepository {
    Transaction tx = null;

    public Client createClient(Client client) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(client);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return client;
    }

    public Client getClientById(long id) {
        Client client = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            client = session.get(Client.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return client;
    }

    public List<Client> getAllClient() {
        List<Client> clientList = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            clientList = session.createQuery("FROM Client").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return clientList;
    }

    public Client editClientById(long id, String name, String surname, Date birthday, String sex) {
        Client client = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            client = session.get(Client.class, id);
            client.setName(name);
            client.setSurname(surname);
            client.setBirthday(birthday);
            client.setSex(sex);
            session.update(client);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return client;
    }
}
