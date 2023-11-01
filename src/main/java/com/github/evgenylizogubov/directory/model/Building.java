package com.github.evgenylizogubov.directory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "building")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "address", nullable = false, unique = true, updatable = false)
    @NotBlank
    private String address;
    
    @OneToMany(mappedBy = "building")
    @JsonIgnore
    private List<Company> companies;
    
    @OneToOne(mappedBy = "building")
    @PrimaryKeyJoinColumn
    @NotNull
    private Coordinates coordinates;
    
    public Building(Integer id, String address, Coordinates coordinates) {
        this.id = id;
        this.address = address;
        this.coordinates = coordinates;
    }
}
