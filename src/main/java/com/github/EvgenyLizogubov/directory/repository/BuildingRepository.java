package com.github.EvgenyLizogubov.directory.repository;

import com.github.EvgenyLizogubov.directory.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BuildingRepository extends JpaRepository<Building, Integer> {
}
