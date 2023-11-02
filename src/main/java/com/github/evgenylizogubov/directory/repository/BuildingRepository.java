package com.github.evgenylizogubov.directory.repository;

import com.github.evgenylizogubov.directory.model.Building;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BuildingRepository extends PagingAndSortingRepository<Building, Integer> {
}
