package com.example.demo.repository;

import com.example.demo.entities.PhoneNumber;
import org.springframework.data.repository.CrudRepository;


public interface PhoneNumberRepository extends CrudRepository<PhoneNumber, Integer> {
}
