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
    
    @Query("""
            SELECT c
            FROM Company c JOIN c.building b
            WHERE FUNCTION('POWER', b.latitude - :latitude, 2) + FUNCTION('POWER', b.longitude - :longitude, 2) <= FUNCTION('POWER', :radius, 2)
            ORDER BY c.name
            """)
    List<Company> findAllInCircleArea(int latitude, int longitude, int radius);
    
    @Query("""
            SELECT c
            FROM Company c JOIN c.building b
            WHERE b.latitude >= :point1Latitude AND b.latitude <= :point2Latitude AND b.longitude >= :point1Longitude AND b.longitude <= :point2Longitude
            ORDER BY c.name
            """)
    List<Company> findAllInRectangleArea(int point1Latitude, int point1Longitude, int point2Latitude, int point2Longitude);
}
