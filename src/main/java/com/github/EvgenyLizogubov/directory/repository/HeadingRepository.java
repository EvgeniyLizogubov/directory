package com.github.EvgenyLizogubov.directory.repository;

import com.github.EvgenyLizogubov.directory.model.Heading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface HeadingRepository extends JpaRepository<Heading, Integer> {
    @Query("SELECT h FROM Heading h WHERE h.name=:headingName")
    Heading findByHeadingName(String headingName);
}
