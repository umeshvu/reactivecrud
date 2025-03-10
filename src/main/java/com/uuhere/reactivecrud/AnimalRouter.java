package com.uuhere.reactivecrud;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AnimalRouter {
    @Bean
    public RouterFunction<ServerResponse> routes(AnimalRouteHandler handler) {
        return route(GET("/animals"), handler::getAllAnimals)
                .andRoute(GET("/animals/{id}"), handler::getAnimalById)
                .andRoute(POST("/animals"), handler::createAnimal)
                .andRoute(DELETE("/animals/{id}"), handler::deleteAnimal);
    }
}
