package com.github.evgenylizogubov.directory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "building", uniqueConstraints = @UniqueConstraint(columnNames = {"address", "latitude", "longitude"}, name = "uk_building"))
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Building extends AbstractBaseEntity {
    @Column(name = "address", nullable = false, unique = true, updatable = false)
    @NotBlank
    private String address;
    
    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Company> companies;
    
    @Column(name = "latitude", nullable = false)
    @NotNull
    private Integer latitude;
    
    @Column(name = "longitude", nullable = false)
    @NotNull
    private Integer longitude;
}
