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

    @RequestMapping(value = "/client/create", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
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

    @RequestMapping(value = "/client/{id}", produces = "application/json")
    public ResponseEntity getClientById(@PathVariable("id") long id) {
        return new ResponseEntity<>(clientServices.getClientById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/allclient", produces = "application/json")
    public ResponseEntity getAllClient() {
        return new ResponseEntity<>(clientServices.getAllClient(), HttpStatus.OK);
    }

    @RequestMapping(value = "/client/edit/{id}", produces = "application/json")
    public ResponseEntity editClientById(@PathVariable("id") long id,
                                         @RequestParam(value = "name") String name,
                                         @RequestParam(value = "surname") String surname,
                                         @RequestParam(value = "birthday") String birthday,
                                         @RequestParam(value = "sex") String sex) {
        Client clientRespone = clientServices.editClientById(id, name, surname, transformationDate(birthday), sex);
        return new ResponseEntity<>(clientRespone, HttpStatus.OK);
    }

    @RequestMapping(value = "/createOrdersByClient", produces = "application/json")
    public ResponseEntity createOrdersByClien(@RequestParam(value = "date") String date,
                                              @RequestParam(value = "status") String status,
                                              @RequestParam(value = "sum") long sum,
                                              @RequestParam(value = "currency") String currency,
                                              @RequestParam(value = "clientId") long clientId) {
        Orders orders = new Orders();
        orders.setDate(transformationDate(date));
        orders.setStatus(status);
        orders.setSum(sum);
        orders.setCurrency(currency);
        Client clientTmp = clientServices.getClientById(clientId);
        orders.setClient(clientTmp);
        return new ResponseEntity<>(ordersServices.createOrdersByClient(orders), HttpStatus.OK);
    }

    @RequestMapping(value = "/allorders", produces = "application/json")
    public ResponseEntity getAllOrders() {
        return new ResponseEntity<>(ordersServices.getAllOrders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/approveOrdersByClient/{idClient}/{idOrders}", produces = "application/json")
    public ResponseEntity approveOrdersByClient(@PathVariable("idClient") long idClient,
                                                @PathVariable("idOrders") long idOrders,
                                                @RequestParam(value = "status") String status) {
        return new ResponseEntity<>(ordersServices.approveOrdersByClient(idClient, idOrders, status), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllOrdersByClient/{id}", produces = "application/json")
    public ResponseEntity getAllOrdersByClient(@PathVariable("id") long id) {
        return new ResponseEntity<>(ordersServices.getAllOrdersByClient(id), HttpStatus.OK);
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
