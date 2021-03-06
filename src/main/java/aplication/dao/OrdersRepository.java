package aplication.dao;

import aplication.entity.Orders;

import java.util.List;

public interface OrdersRepository {

    List<Orders> getAllOrders();

    Orders createOrdersByClient(Orders orders);

    Orders approveOrdersByClient(long idClient, long idOrders, String status);

    List<Orders> getAllOrdersByClient(long idClient);
}
