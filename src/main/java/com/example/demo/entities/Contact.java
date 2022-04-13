package com.example.demo.entities;

import com.example.demo.components.ServiceClass;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
//@Data
//@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String surname;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    @OneToMany( targetEntity = PhoneNumber.class,
                mappedBy = "contact",
                cascade = CascadeType.ALL/*,
                fetch = FetchType.EAGER*/)
    private List<PhoneNumber> phoneNumberList;

    public Contact() {
    }

    public Contact(String surname, String middleName, String lastName, Date birthday) {
        this.surname = surname;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        var sc = new ServiceClass();
        if(sc.isValidName(surname)) { this.surname = surname; }
        else throw new IllegalArgumentException();
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        var sc = new ServiceClass();
        if(sc.isValidName(middleName)) { this.middleName = middleName; }
        else throw new IllegalArgumentException();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        var sc = new ServiceClass();
        if(sc.isValidName(lastName)) { this.lastName = lastName; }
        else throw new IllegalArgumentException();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        if(birthday != null) {this.birthday = birthday;}
        else throw new IllegalArgumentException();
    }

    public List<PhoneNumber> getPhoneNumberList() {
        return phoneNumberList;
    }

    public void setPhoneNumberList(List<PhoneNumber> phoneNumberList) {
        this.phoneNumberList = phoneNumberList;
    }

}