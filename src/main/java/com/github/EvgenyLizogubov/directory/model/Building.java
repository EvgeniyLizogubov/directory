package com.github.EvgenyLizogubov.directory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "building")
@Getter
@Setter
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "building_address", nullable = false, unique = true, updatable = false)
    @NotBlank
    private String address;
    
    @OneToMany(mappedBy = "building")
    @JsonIgnore
    private List<Company> companies;
    
    @OneToOne(mappedBy = "building")
    @PrimaryKeyJoinColumn
    @NotNull
    private Coordinates coordinates;
}
