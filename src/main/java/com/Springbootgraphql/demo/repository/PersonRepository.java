package com.Springbootgraphql.demo.repository;


import com.Springbootgraphql.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
