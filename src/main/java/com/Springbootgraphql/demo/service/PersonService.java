package com.Springbootgraphql.demo.service;

import com.Springbootgraphql.demo.model.Person;
import com.Springbootgraphql.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }

    public Person createPerson(String name, int age) {
        Person person = new Person();
        person.setName(name);
        person.setAge(age);
        return personRepository.save(person);
    }

    public Person updatePerson(Long id, String name, Integer age) {
        Optional<Person> optionalPerson = personRepository.findById(id);

        if (optionalPerson.isPresent()) {
            Person existingPerson = optionalPerson.get();

            if (name != null) {
                existingPerson.setName(name);
            }

            if (age != null) {
                existingPerson.setAge(age);
            }

            return personRepository.save(existingPerson);
        } else {
            // Handle not found scenario
            return null;
        }
    }

    public boolean deletePerson(Long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);

        if (optionalPerson.isPresent()) {
            personRepository.deleteById(id);
            return true;
        } else {
            // Handle not found scenario
            return false;
        }
    }


}
