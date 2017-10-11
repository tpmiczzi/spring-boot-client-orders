package aplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @SequenceGenerator(name = "jpaSequenceOrders", sequenceName = "JPA_SEQUENCE_ORDERS", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpaSequenceOrders")
    @Column(name = "order_id")
    private long idOrders;

    @Column(name = "date")
    @Temporal(value = TemporalType.DATE)
    private Date date;

    @Column(name = "status")
    private String status;

    @Column(name = "sum")
    private long sum;

    @Column(name = "currency")
    private String currency;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "client_id")
    private Client client;

    public Orders() {
    }

    public Orders(Date date, String status, long sum, String currency, Client client) {
        this.date = date;
        this.status = status;
        this.sum = sum;
        this.currency = currency;
        this.client = client;
    }

    public long getIdOrders() {
        return idOrders;
    }

    public void setIdOrders(long idOrders) {
        this.idOrders = idOrders;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
