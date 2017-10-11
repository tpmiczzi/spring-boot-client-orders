package aplication.controller;

import aplication.entity.Client;
import aplication.entity.Orders;
import aplication.service.ClientServicesImpl;
import aplication.service.OrdersServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
public class ControllerRest {

    @Autowired
    private ClientServicesImpl clientServices;
    @Autowired
    private OrdersServicesImpl ordersServices;

    @RequestMapping(value = "/client", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public ResponseEntity clientCreate(@RequestParam(value = "name") String name,
                                       @RequestParam(value = "surname") String surname,
                                       @RequestParam(value = "birthday") String birthday,
                                       @RequestParam(value = "sex") String sex) {
        Client client = new Client();

        client.setName(name);
        client.setSurname(surname);
        client.setBirthday(transformationDate(birthday));
        client.setSex(sex);
        Client clientRespone = clientServices.createClient(client);
        return new ResponseEntity<>(clientRespone, HttpStatus.OK);
    }

    @RequestMapping(value = "/client/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity getClientById(@PathVariable("id") long id) {
        return new ResponseEntity<>(clientServices.getClientById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/clients", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity getAllClient() {
        return new ResponseEntity<>(clientServices.getAllClient(), HttpStatus.OK);
    }

    @RequestMapping(value = "/client/edit/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity editClientById(@PathVariable("id") long id,
                                         @RequestParam(value = "name") String name,
                                         @RequestParam(value = "surname") String surname,
                                         @RequestParam(value = "birthday") String birthday,
                                         @RequestParam(value = "sex") String sex) {
        Client clientRespone = clientServices.editClientById(id, name, surname, transformationDate(birthday), sex);
        return new ResponseEntity<>(clientRespone, HttpStatus.OK);
    }

    @RequestMapping(value = "/order/client/{idClient}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity createOrdersByClient(@PathVariable("idClient") long idClient,
                                              @RequestParam(value = "date") String date,
                                              @RequestParam(value = "status") String status,
                                              @RequestParam(value = "sum") long sum,
                                              @RequestParam(value = "currency") String currency) {
        Orders orders = new Orders();
        orders.setDate(transformationDate(date));
        orders.setStatus(status);
        orders.setSum(sum);
        orders.setCurrency(currency);
        Client clientTmp = clientServices.getClientById(idClient);
        orders.setClient(clientTmp);
        return new ResponseEntity<>(ordersServices.createOrdersByClient(orders), HttpStatus.OK);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity getAllOrders() {
        return new ResponseEntity<>(ordersServices.getAllOrders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/order/{idOrders}/client/{idClient}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity approveOrdersByClient(@PathVariable("idClient") long idClient,
                                                @PathVariable("idOrders") long idOrders,
                                                @RequestParam(value = "status") String status) {
        return new ResponseEntity<>(ordersServices.approveOrdersByClient(idClient, idOrders, status), HttpStatus.OK);
    }

    @RequestMapping(value = "/orders/client/{idClient}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity getAllOrdersByClient(@PathVariable("idClient") long idClient) {
        return new ResponseEntity<>(ordersServices.getAllOrdersByClient(idClient), HttpStatus.OK);
    }

    private Date transformationDate(String dateString){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
}
