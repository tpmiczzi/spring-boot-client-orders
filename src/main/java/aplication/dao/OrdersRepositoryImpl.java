package aplication.dao;

import aplication.entity.Orders;
import org.hibernate.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("ordersRepository")
public class OrdersRepositoryImpl implements OrdersRepository {
    Transaction tx = null;

    @Override
    public List<Orders> getAllOrders() {
        List<Orders> ordersList = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            ordersList = session.createQuery("FROM Orders").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return ordersList;
    }

    @Override
    public Orders createOrdersByClient(Orders orders) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(orders);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Orders approveOrdersByClient(long idClient, long idOrders, String status) {
        Orders orders = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            orders = session.get(Orders.class, idOrders);
            orders.setStatus(status);
            session.update(orders);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return orders;
    }


    @Override
    public List<Orders> getAllOrdersByClient(long idClient) {
        List<Orders> ordersList = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            ordersList = session.createQuery("FROM Orders AS orders WHERE orders.client = " + idClient).list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return ordersList;
    }

}
