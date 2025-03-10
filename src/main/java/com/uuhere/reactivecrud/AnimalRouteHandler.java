package com.uuhere.reactivecrud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class AnimalRouteHandler {
    private final AnimalService service;

    public AnimalRouteHandler(AnimalService service) {
        this.service = service;
    }

    public Mono<ServerResponse> getAllAnimals(ServerRequest request) {
        return ok().body(service.getAllAnimals(), Animal.class);
    }

    public Mono<ServerResponse> getAnimalById(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return service.getAnimalById(id)
                .flatMap(animal -> ok().bodyValue(animal))
                .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> createAnimal(ServerRequest request) {
        return request.bodyToMono(Animal.class)
                .flatMap(service::createAnimal)
                .flatMap(animal -> ok().bodyValue(animal));
    }

    public Mono<ServerResponse> deleteAnimal(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return service.deleteAnimal(id).then(ok().build());
    }
}
