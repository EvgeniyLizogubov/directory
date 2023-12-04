package com.github.evgenylizogubov.directory.repository;

import com.github.evgenylizogubov.directory.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    @Query("SELECT c FROM Company c JOIN FETCH c.phoneNumbers WHERE c.building.id=:buildingId ORDER BY c.name ASC")
    List<Company> findAllByBuildingId(Integer buildingId);
    
    @Query("SELECT c FROM Company c JOIN c.headings h JOIN FETCH c.phoneNumbers WHERE h.name=:headingName ORDER BY c.name ASC")
    List<Company> findAllByHeading(String headingName);
    
    @Query("SELECT c FROM Company c JOIN FETCH c.phoneNumbers WHERE c.name LIKE %:name%")
    List<Company> findByNameLike(String name);
    
    @Query("""
            SELECT c
            FROM Company c JOIN c.building b JOIN FETCH c.phoneNumbers
            WHERE FUNCTION('POWER', b.latitude - :latitude, 2) + FUNCTION('POWER', b.longitude - :longitude, 2) <= FUNCTION('POWER', :radius, 2)
            ORDER BY c.name
            """)
    List<Company> findAllInCircleArea(int latitude, int longitude, int radius);
    
    @Query("""
            SELECT c
            FROM Company c JOIN c.building b JOIN FETCH c.phoneNumbers
            WHERE b.latitude >= :latitude - :radius AND b.latitude <= :latitude + :radius AND b.longitude >= :longitude - :radius AND b.longitude <= :longitude + :radius
            ORDER BY c.name
            """)
    List<Company> findAllInRectangleArea(int latitude, int longitude, int radius);
}
