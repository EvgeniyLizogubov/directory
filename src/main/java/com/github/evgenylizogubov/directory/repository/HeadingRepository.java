package com.github.evgenylizogubov.directory.repository;

import com.github.evgenylizogubov.directory.model.Heading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface HeadingRepository extends JpaRepository<Heading, Integer> {
    @Query("""
            SELECT h
            FROM Heading h
                JOIN TreePath t ON h.id = t.descendant.id
            WHERE t.ancestor.id = (
                SELECT id
                FROM Heading
                WHERE name = :headingName
            )
                        """)
    List<Heading> findByHeadingName(String headingName);
}
