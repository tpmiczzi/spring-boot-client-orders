package aplication.dao;

import aplication.entity.Client;

import java.util.Date;
import java.util.List;

public interface ClientRepository {
    Client createClient(Client client);

    Client getClientById(long id);

    List<Client> getAllClient();

    Client editClientById(long id, String name, String surname, Date birthday, String sex);
}
