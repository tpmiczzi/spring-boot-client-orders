package aplication.service;

import aplication.dao.OrdersRepositoryImpl;
import aplication.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrdersServicesImpl implements OrdersServices {
    @Autowired
    private OrdersRepositoryImpl ordersRepository;

    @Override
    public List<Orders> getAllOrders() {
        return ordersRepository.getAllOrders();
    }

    @Override
    public Orders createOrdersByClient(Orders orders) {
        return ordersRepository.createOrdersByClient(orders);
    }

    @Override
    public Orders approveOrdersByClient(long idClient, long idOrders, String status) {
        return ordersRepository.approveOrdersByClient(idClient, idOrders, status);
    }

    @Override
    public List<Orders> getAllOrdersByClient(long idClient) {
        return ordersRepository.getAllOrdersByClient(idClient);
    }
}
