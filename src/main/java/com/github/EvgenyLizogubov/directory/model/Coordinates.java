package com.github.EvgenyLizogubov.directory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "coordinates")
@Setter
@Getter
public class Coordinates {
    @Column(name = "x", nullable = false, updatable = false)
    @NotNull
    private Integer x;
    
    @Column(name = "y", nullable = false, updatable = false)
    @NotNull
    private Integer y;
    
    @Id
    @OneToOne
    @JoinColumn(name = "building_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Building building;
}
