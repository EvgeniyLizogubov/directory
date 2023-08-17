package com.github.EvgenyLizogubov.directory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "heading")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Heading {
    @Id
    @Column(name = "heading_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "name", nullable = false, unique = true)
    @NotBlank
    private String name;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Heading parent;
    
    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    private List<Heading> children;
    
    @ManyToMany
    @JoinTable(
            name = "heading_company",
            joinColumns = @JoinColumn(name = "heading_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id")
    )
    @JsonIgnore
    private List<Company> companies;
    
    @JsonIgnore
    public List<Heading> getAllChildren() {
        return getAllChildren(this);
    }

    private List<Heading> getAllChildren(Heading heading) {
        List<Heading> allChildren = new ArrayList<>();

        for (Heading child : heading.getChildren()) {
            allChildren.add(child);
            allChildren.addAll(getAllChildren(child));
        }

        return allChildren;
    }
    
    public Heading(Integer id, String name, Heading parent) {
        this.id = id;
        this.name = name;
        this.parent = parent;
    }
}
