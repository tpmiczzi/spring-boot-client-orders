package aplication.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @SequenceGenerator( name = "jpaSequenceClient", sequenceName = "JPA_SEQUENCE_CLIENT", allocationSize = 1, initialValue = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "jpaSequenceClient")
    @Column(name = "client_id")
    private long idClient;

    @Column(name = "first_name")
    private String name;

    @Column(name = "last_name")
    private String surname;

    @Column(name = "birthday")
    @Temporal(value = TemporalType.DATE)
    private Date birthday;

    @Column(name = "sex")
    private String sex;

    public Client() {
    }

    public Client(String name, String surname, Date birthday, String sex) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.sex = sex;
    }

    public long getId() {
        return idClient;
    }

    public void setId(long id) {
        this.idClient = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
