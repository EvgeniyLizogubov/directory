package com.github.evgenylizogubov.directory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "heading")
@Setter
@Getter
@NoArgsConstructor
public class Heading extends AbstractBaseEntity {
    @Column(name = "name", nullable = false, unique = true)
    @NotBlank
    private String name;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "descendant")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<TreePath> parents = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "ancestor")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<TreePath> children = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(
            name = "heading_company",
            joinColumns = @JoinColumn(name = "heading_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id")
    )
    @JsonIgnore
    private List<Company> companies;
    
    public Heading(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
