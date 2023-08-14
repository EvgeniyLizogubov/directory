package com.github.EvgenyLizogubov.directory.repository;

import com.github.EvgenyLizogubov.directory.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    @Query("SELECT c FROM Company c WHERE c.building.address=:address")
    List<Company> findAllByBuilding(String address);
    
    @Query("SELECT c FROM Company c JOIN FETCH c.headings h WHERE h.name=:headingName")
    List<Company> findAllByHeading(String headingName);
    
    @Query("SELECT c FROM Company c WHERE c.name=:name")
    Optional<Company> findByName(String name);
}
