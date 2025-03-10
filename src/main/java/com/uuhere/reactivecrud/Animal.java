package com.uuhere.reactivecrud;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("animals")
@Setter
@Getter
public class Animal {
    @Id
    private Long id;

    @Column("type")  // Explicit mapping
    private String type;

    @Column("scientific_name") // Maps to scientific_name column
    private String scientificName;

    @Column("diet")
    private String diet;

    @Column("lifespan")
    private int lifespan;

    @Column("warm_blooded")
    private boolean warmBlooded;
}
