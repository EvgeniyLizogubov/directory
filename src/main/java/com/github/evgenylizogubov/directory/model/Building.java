package com.github.evgenylizogubov.directory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "building")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Building extends AbstractBaseEntity {
    @Column(name = "address", nullable = false, unique = true, updatable = false)
    @NotBlank
    private String address;
    
    @OneToMany(mappedBy = "building")
    @JsonIgnore
    private List<Company> companies;
    
    @Column(name = "latitude", nullable = false)
    @NotNull
    private Integer latitude;
    
    @Column(name = "longitude", nullable = false)
    @NotNull
    private Integer longitude;
}
