package com.github.EvgenyLizogubov.directory.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "company")
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "name", nullable = false, unique = true)
    @NotBlank
    @Size(min = 1, max = 200)
    private String name;
    
    @ElementCollection
    @CollectionTable(name = "company_phone_number", joinColumns = @JoinColumn(name = "company_id"))
    @Column(name = "phone_number")
    @JoinColumn(name = "company_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<String> phoneNumbers;
    
    @ManyToOne
    @JoinColumn(name = "building_id")
    @NotNull
    private Building building;
    
    @ManyToMany(mappedBy = "companies")
    @NotNull
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Heading> headings;
}
