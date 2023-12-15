package com.Springbootgraphql.demo.graphql;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class GraphQLConfig {
    @Bean
    public GraphQL graphQL(GraphQLSchema graphQLSchema) {
        return GraphQL.newGraphQL(graphQLSchema).build();
    }

    @Bean
    public GraphQLSchema graphQLSchema() throws IOException {
        SchemaParser schemaParser = new SchemaParser();
        ClasspathResourceSchemaSource schemaSource = new ClasspathResourceSchemaSource("graphql/schema.graphqls");
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        TypeDefinitionRegistry typeRegistry = schemaParser.parse(schemaSource);
        RuntimeWiring runtimeWiring = buildRuntimeWiring();
        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
        return graphQLSchema;
    }

    private RuntimeWiring buildRuntimeWiring() {
        GraphQLDataFetchers graphQLDataFetchers;
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring ->
                        typeWiring
                                .dataFetcher("getAllPersons", graphQLDataFetchers.getAllPersonsDataFetcher()))
                .type("Query", typeWiring ->
                        typeWiring
                                .dataFetcher("getPersonById", graphQLDataFetchers.getPersonByIdDataFetcher()))
                .type("Mutation", typeWiring ->
                        typeWiring
                                .dataFetcher("createPerson", graphQLDataFetchers.createPersonDataFetcher()))
                .type("Mutation", typeWiring ->
                        typeWiring
                                .dataFetcher("updatePerson", graphQLDataFetchers.updatePersonDataFetcher()))
                .type("Mutation", typeWiring ->
                        typeWiring
                                .dataFetcher("deletePerson", graphQLDataFetchers.deletePersonDataFetcher()))
                .build();
    }

}
