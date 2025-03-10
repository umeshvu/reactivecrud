package com.uuhere.reactivecrud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository repository;

    public AnimalService(AnimalRepository repository) {
        this.repository = repository;
    }

    public Flux<Animal> getAllAnimals() {
        return repository.findAll();
    }

    public Mono<Animal> getAnimalById(Long id) {
        return repository.findById(id);
    }

    public Mono<Animal> createAnimal(Animal animal) {
        return repository.save(animal);
    }

    public Mono<Void> deleteAnimal(Long id) {
        return repository.deleteById(id);
    }
}
