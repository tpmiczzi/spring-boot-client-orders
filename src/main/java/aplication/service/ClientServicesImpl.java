package aplication.service;

import aplication.entity.Client;
import aplication.dao.ClientRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ClientServicesImpl implements ClientServices {
    @Autowired
    private ClientRepositoryImpl clientRepository;

    @Override
    public Client createClient(Client client) {
        return clientRepository.createClient(client);
    }

    @Override
    public Client getClientById(long id) {
        return clientRepository.getClientById(id);
    }

    @Override
    public List<Client> getAllClient() {
        return clientRepository.getAllClient();
    }

    @Override
    public Client editClientById(long id, String name, String surname, Date birthday, String sex) {
        return clientRepository.editClientById(id, name, surname, birthday, sex);
    }

}
