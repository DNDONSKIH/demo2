package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String surname;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;
    private Date birthday;
    @OneToMany( targetEntity = PhoneNumber.class,
                mappedBy = "contact",
                cascade = CascadeType.ALL,
                fetch = FetchType.EAGER)
    private List<PhoneNumber> phoneNumberList;
}