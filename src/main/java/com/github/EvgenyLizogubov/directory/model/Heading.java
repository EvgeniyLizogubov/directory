package com.github.EvgenyLizogubov.directory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "heading")
@Setter
@Getter
public class Heading {
    @Id
    @Column(name = "heading_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "name", nullable = false, unique = true)
    @NotBlank
    private String name;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_heading_id")
    @JsonIgnore
    private Heading parentHeading;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "root_heading_id")
    @JsonIgnore
    private Heading rootHeading;
    
    @Transient
    private List<Heading> children = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(
            name = "heading_company",
            joinColumns = @JoinColumn(name = "heading_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id")
    )
    private List<Company> companies;
}
