package aplication.dao;


import aplication.BaseTest;
import aplication.entity.Client;
import aplication.entity.Orders;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

@TestComponent
public class OrdersRepositoryImplTest extends BaseTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Autowired
    OrdersRepositoryImpl ordersRepository;
    @Autowired
    ClientRepositoryImpl clientRepository;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void createOrdersByClient() {
        Orders expected = null;
        try {
            clientRepository.createClient(new Client("Dima", "Pupkin", new SimpleDateFormat("yyyy-MM-dd").parse("2000-12-10"), "men"));
            expected = ordersRepository.createOrdersByClient(new Orders(new SimpleDateFormat("yyyy-MM-dd").parse("2017-10-11"),
                    "createNew", 1000, "dollar", clientRepository.getClientById(1)));
        } catch (ParseException pex) {
            System.out.println(pex.getStackTrace());
        }
        assertEquals(expected.getCurrency(), "dollar");
        assertEquals(expected.getStatus(), "createNew");
    }


    @Test
    public void getAllOrders() {
        ordersRepository.clearTable();
        try {
            clientRepository.createClient(new Client("Dima", "Pupkin", new SimpleDateFormat("yyyy-MM-dd").parse("2000-12-10"), "men"));
            ordersRepository.createOrdersByClient(new Orders(new SimpleDateFormat("yyyy-MM-dd").parse("2017-10-11"),
                    "createNew", 1000, "dollar", clientRepository.getClientById(1)));
            ordersRepository.createOrdersByClient(new Orders(new SimpleDateFormat("yyyy-MM-dd").parse("2017-10-11"),
                    "createNew", 2000, "dollar", clientRepository.getClientById(1)));
        } catch (ParseException pex) {
            System.out.println(pex.getStackTrace());
        }
        assertEquals(ordersRepository.getAllOrders().size(), 2);
    }

    @Test
    public void approveOrdersByClient() {
        ordersRepository.clearTable();
        Orders expected = null;
        try {
            clientRepository.createClient(new Client("Dima", "Pupkin", new SimpleDateFormat("yyyy-MM-dd").parse("2000-12-10"), "men"));
            ordersRepository.createOrdersByClient(new Orders(new SimpleDateFormat("yyyy-MM-dd").parse("2017-10-11"),
                    "createNew", 1000, "dollar", clientRepository.getClientById(1)));
            expected = ordersRepository.approveOrdersByClient(1, 1, "done");
        } catch (ParseException pex) {
            System.out.println(pex.getStackTrace());
        }
        assertEquals(expected.getStatus(), "done");
    }

    @Test
    public void getAllOrdersByClient() {
        ordersRepository.clearTable();
        try {
            clientRepository.createClient(new Client("Dima", "Pupkin", new SimpleDateFormat("yyyy-MM-dd").parse("2000-12-10"), "men"));
            clientRepository.createClient(new Client("Vova", "Sidorov", new SimpleDateFormat("yyyy-MM-dd").parse("1999-01-12"), "men"));
            ordersRepository.createOrdersByClient(new Orders(new SimpleDateFormat("yyyy-MM-dd").parse("2017-10-11"),
                    "createNew", 1000, "dollar", clientRepository.getClientById(1)));
            ordersRepository.createOrdersByClient(new Orders(new SimpleDateFormat("yyyy-MM-dd").parse("2017-10-11"),
                    "createNew", 2000, "dollar", clientRepository.getClientById(1)));
            ordersRepository.createOrdersByClient(new Orders(new SimpleDateFormat("yyyy-MM-dd").parse("2017-09-30"),
                    "createNew", 9999, "euro", clientRepository.getClientById(2)));
        } catch (ParseException pex) {
            System.out.println(pex.getStackTrace());
        }
        assertEquals(ordersRepository.getAllOrdersByClient(1).size(), 2);
    }
}
