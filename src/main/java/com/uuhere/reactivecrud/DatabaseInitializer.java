package com.uuhere.reactivecrud;

import jakarta.annotation.PostConstruct;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {
    private final DatabaseClient databaseClient;

    public DatabaseInitializer(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    @PostConstruct
    public void initializeDatabase() {

        databaseClient.sql("""
                CREATE TABLE animals (
                    id SERIAL PRIMARY KEY,
                    type VARCHAR(50) ,
                    scientific_name VARCHAR(100) ,
                    diet VARCHAR(20) ,
                    lifespan INT CHECK (lifespan >= 0),
                    warm_blooded BOOLEAN
                );
                """).fetch().rowsUpdated().subscribe();


        databaseClient.sql("""
                INSERT INTO animals (type, scientific_name, diet, lifespan, warm_blooded)
                VALUES ('python', 'Pythonidae', 'Carnivore', 30, FALSE)
                  ON CONFLICT (type) DO NOTHING;
                """).fetch().rowsUpdated().subscribe();
    }
}
