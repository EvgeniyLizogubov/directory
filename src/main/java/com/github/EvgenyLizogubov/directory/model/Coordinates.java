package com.github.EvgenyLizogubov.directory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "coordinates")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Coordinates {
    @Column(name = "latitude", nullable = false, updatable = false)
    @NotNull
    private Integer latitude;
    
    @Column(name = "longitude", nullable = false, updatable = false)
    @NotNull
    private Integer longitude;
    
    @Id
    @OneToOne
    @JoinColumn(name = "building_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Building building;
    
    public Coordinates(Integer latitude, Integer longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
