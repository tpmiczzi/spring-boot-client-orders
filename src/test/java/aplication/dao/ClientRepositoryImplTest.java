package aplication.dao;

import aplication.entity.Client;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ClientRepositoryImplTest {

    @Autowired
    private ClientRepositoryImpl clientRepository;

//    @Before
//    public void dropTable() {
//        clientRepository.;
//    }

    @Test
    public void createNewClient() throws Exception {
        Client client = getClient();
        long idTmp = client.getId();
        clientRepository.createClient(client);
        Client clientTmp = clientRepository.getClientById(idTmp);
        assertEquals(client, clientTmp);
    }

    private Client getClient() {
        Client client = new Client();
        client.setName("Pavel");
        client.setSurname("Tula");
        client.setBirthday(new Date());
        client.setSex("man");
        return client;
    }
}
