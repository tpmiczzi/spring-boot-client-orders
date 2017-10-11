package aplication.controller;

import aplication.BaseTest;
import aplication.dao.ClientRepositoryImpl;
import aplication.dao.OrdersRepositoryImpl;
import org.junit.*;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.*;

@TestComponent
public class ControllerRestTest extends BaseTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Autowired
    ClientRepositoryImpl clientRepository;
    @Autowired
    OrdersRepositoryImpl ordersRepository;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

//    @After
//    public void clear(){
//        clientRepository.clearTable();
//        ordersRepository.clearTable();
//    }

    int idClient = 1;
    long idOrders = 1;

    @Test
    public void clientCreate() throws Exception {
        String defaultUrl = "/client?name=Pavel&surname=Tula&birthday=1988-03-10&sex=men";
        mockMvc.perform(get(defaultUrl))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name", is("Pavel")))
                .andExpect(jsonPath("$.surname", is("Tula")))
                .andExpect(jsonPath("$.sex", is("men")));
    }

    @Test
    public void getClientById() throws Exception {
        this.clientCreate();
        String defaultUrl = "/client/" + idClient;
        mockMvc.perform(get(defaultUrl))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(idClient)));
    }

    @Test
    public void getAllClient() throws Exception {
        this.clientCreate();
        String defaultUrl = "/clients";
        mockMvc.perform(get(defaultUrl))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));
    }

    @Test
    public void editClientById() throws Exception {
        String defaultUrl = "/client/edit/" + idClient + "?name=NewName&surname=NewSurname&birthday=2000-10-10&sex=women";
        mockMvc.perform(get(defaultUrl))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("NewName")))
                .andExpect(jsonPath("$.surname", is("NewSurname")))
                .andExpect(jsonPath("$.sex", is("women")));
    }

    @Test
    public void createOrdersByClient() throws Exception {
        String defaultUrl = "/order/client/" + idClient + "?date=1988-10-10&status=new&sum=100&currency=euro";
        mockMvc.perform(get(defaultUrl))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status", is("new")))
                .andExpect(jsonPath("$.sum", is(100)))
                .andExpect(jsonPath("$.currency", is("euro")));
    }

    @Test
    public void getAllOrders() throws Exception {
        this.createOrdersByClient();
        String defaultUrl = "/orders";
        mockMvc.perform(get(defaultUrl))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].idOrders", is(1)))
                .andExpect(jsonPath("$[1].idOrders", is(2)));
    }

    @Test
    public void approveOrdersByClient() throws Exception {
        String defaultUrl = "/order/" + idOrders + "/client/" + idClient + "?status=done";
        mockMvc.perform(get(defaultUrl))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.idOrders", is(1)))
                .andExpect(jsonPath("$.status", is("done")));
    }

    @Test
    public void getAllOrdersByClient() throws Exception {
        String defaultUrl = "/orders/client/" + idClient;
        mockMvc.perform(get(defaultUrl))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].idOrders", is(2)))
                .andExpect(jsonPath("$[1].idOrders", is(1)));
    }

    @Test
    public void validateBedAddress() throws Exception {
        String badUrl = "/other/url";
        mockMvc.perform(get(badUrl))
                .andExpect(status().isNotFound());
    }

}
