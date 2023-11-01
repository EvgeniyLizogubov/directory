package com.github.evgenylizogubov.directory.repository;

import com.github.evgenylizogubov.directory.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    @Query("SELECT c FROM Company c WHERE c.building.id=:buildingId ORDER BY c.name ASC")
    List<Company> findAllByBuildingId(Integer buildingId);
    
    @Query("SELECT c FROM Company c JOIN c.headings h WHERE h.name=:headingName ORDER BY c.name ASC")
    List<Company> findAllByHeading(String headingName);
    
    @Query("SELECT c FROM Company c WHERE c.name LIKE %:name%")
    List<Company> findByNameLike(String name);
}
