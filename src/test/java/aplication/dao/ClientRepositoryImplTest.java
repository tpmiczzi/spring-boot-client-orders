package aplication.dao;

import aplication.BaseTest;
import aplication.entity.Client;
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
public class ClientRepositoryImplTest extends BaseTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Autowired
    ClientRepositoryImpl clientRepository;


    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void createAndGetClient() {
        Client expected = null;
        try {
            expected = clientRepository.createClient(new Client("Dima", "Pupkin", new SimpleDateFormat("yyyy-MM-dd").parse("2000-12-10"), "men"));
        } catch (ParseException pex) {
            System.out.println(pex.getStackTrace());
        }
        Client actual = clientRepository.getClientById(1);
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getSurname(), actual.getSurname());
        assertEquals(expected.getBirthday(), actual.getBirthday());
        assertEquals(expected.getSex(), actual.getSex());
    }

    @Test
    public void getAllClient() {
        clientRepository.clearTable();
        initTestDataClient();
        int expected = clientRepository.getAllClient().size();
        assertEquals(expected, 2);
    }


    @Test
    public void editClientById() {
        clientRepository.clearTable();
        initTestDataClient();
        Client expected = null;
        try {
            expected = clientRepository.editClientById(1, "Dmitriy", "BigPupkin", new SimpleDateFormat("yyyy-MM-dd").parse("2000-12-10"), "men");
        } catch (ParseException pex) {
            System.out.println(pex.getStackTrace());
        }
        assertEquals(expected.getName(), "Dmitriy");
        assertEquals(expected.getSurname(), "BigPupkin");
    }


    public void initTestDataClient() {
        try {
            clientRepository.createClient(new Client("Dima", "Pupkin", new SimpleDateFormat("yyyy-MM-dd").parse("2000-12-10"), "men"));
            clientRepository.createClient(new Client("Vova", "Sidorov", new SimpleDateFormat("yyyy-MM-dd").parse("1999-01-12"), "men"));
        } catch (ParseException pex) {
            System.out.println(pex.getStackTrace());
        }
    }
}
