package com.github.evgenylizogubov.directory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "company", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "building_id"}, name = "uk_company"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "name", nullable = false, unique = true)
    @NotBlank
    @Size(min = 1, max = 200)
    private String name;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "company_phone_number", joinColumns = @JoinColumn(name = "company_id"))
    @Column(name = "phone_number")
    @JoinColumn(name = "company_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<String> phoneNumbers;
    
    @ManyToOne
    @JoinColumn(name = "building_id")
    @NotNull
    private Building building;
    
    @ManyToMany(mappedBy = "companies", fetch = FetchType.EAGER)
    @NotNull
    private List<Heading> headings;
}
