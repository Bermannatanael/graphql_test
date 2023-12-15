package com.Springbootgraphql.demo.graphql;

import com.Springbootgraphql.demo.model.Person;
import com.Springbootgraphql.demo.service.PersonService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional

@Component
public class GraphQLDataFetchers {
    @Autowired
    private PersonService personService;

    public DataFetcher<List<Person>> getAllPersonsDataFetcher() {
        return dataFetchingEnvironment -> personService.getAllPersons();
    }

    public DataFetcher<Optional<Person>> getPersonByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            Long id = Long.parseLong(dataFetchingEnvironment.getArgument("id"));
            return personService.getPersonById(id);
        };
    }

    public DataFetcher<Person> createPersonDataFetcher() {
        return dataFetchingEnvironment -> {
            Map<String, Object> personInput = dataFetchingEnvironment.getArgument("personInput");
            String name = (String) personInput.get("name");
            int age = (int) personInput.get("age");
            return personService.createPerson(name, age);
        };
    }

    public DataFetcher<Person> updatePersonDataFetcher() {
        return dataFetchingEnvironment -> {
            Long id = Long.parseLong(dataFetchingEnvironment.getArgument("id"));
            Map<String, Object> personInput = dataFetchingEnvironment.getArgument("personInput");
            String name = (String) personInput.get("name");
            Integer age = (Integer) personInput.get("age");
            return personService.updatePerson(id, name, age);
        };
    }

    public DataFetcher<Boolean> deletePersonDataFetcher() {
        return dataFetchingEnvironment -> {
            Long id = Long.parseLong(dataFetchingEnvironment.getArgument("id"));
            return personService.deletePerson(id);
        };
    }
}
