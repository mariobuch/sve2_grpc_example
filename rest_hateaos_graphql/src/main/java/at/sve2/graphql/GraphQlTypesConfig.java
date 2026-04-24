package at.sve2.graphql;

import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.time.LocalDate;

@Configuration
public class GraphQlTypesConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder.scalar(graphQLLocalDate());
    }

    private GraphQLScalarType graphQLLocalDate() {
        return GraphQLScalarType.newScalar().name("Date").coercing(new Coercing<LocalDate, String>() {
            @Override
            public String serialize(Object input) {
                return (input != null) ? input.toString() : null;
            }

            @Override
            public LocalDate parseValue(Object input) {
                return (input != null) ? LocalDate.parse(input.toString()) : null;
            }
        }).build();
    }
}