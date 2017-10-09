package aplication.controller;

import aplication.BaseTest;
import aplication.dao.ClientRepositoryImpl;
import aplication.dao.OrdersRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ControllerRestTest extends BaseTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Autowired
    private ClientRepositoryImpl clientRepository;

    @Autowired
    private OrdersRepositoryImpl ordersRepository;

    @MockBean
    private ControllerRest controllerRest;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void clientCreate() throws Exception{
        String defaultUrl = "/client/create?name=Pavel&surname=Tula&birthday=1988-03-10&sex=man";
        System.out.println("_______________________");
        MvcResult result =mockMvc.perform(get(defaultUrl).contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();
        System.out.println(result.getResponse());
        System.out.println("_______________________");
        String expected = "{name:Pavel,surname:Tula,birthday:1506718800000,sex:man,id:1}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);



//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
//                .andDo(mvcResult -> {});
//                .andExpect(jsonPath("$.name").value("Pavel"))
//                .andExpect(jsonPath("$.surname").value("Tula"))
//                .andExpect(jsonPath("$.birthday").value("1988-03-10"))
//                .andExpect(jsonPath("$.sex").value("man"));

    }

//
//    @Test
//    public void validate_default_get_address() throws Exception {
//        String defaultUrl = "/userservice/register";
//        mockMvc.perform(get(defaultUrl))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(jsonPath("$.id").value("1"))
//                .andExpect(jsonPath("$.firstName").value("Some first name"))
//                .andExpect(jsonPath("$.lastName").value("The last name"))
//                .andExpect(jsonPath("$.userName").value("The user name"));
//    }
//
//    @Test
//    public void validate_user_get_address() throws Exception {
//        String userUrl = "/userservice/register?firstName=Pavel&lastName=Tula&userName=tpm&plainTextPassword=12345";
//        mockMvc.perform(get(userUrl))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(jsonPath("$.id").value("1"))
//                .andExpect(jsonPath("$.firstName").value("Pavel"))
//                .andExpect(jsonPath("$.lastName").value("Tula"))
//                .andExpect(jsonPath("$.userName").value("tpm"));
//    }
//
//    @Test
//    public void validate_bed_address() throws Exception{
//        String badUrl = "/userservice/badregister";
//        mockMvc.perform(get(badUrl))
//                .andExpect(status().isNotFound());
//    }
}
