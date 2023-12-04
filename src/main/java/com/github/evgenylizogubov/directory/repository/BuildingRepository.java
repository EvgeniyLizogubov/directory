package com.github.evgenylizogubov.directory.repository;

import com.github.evgenylizogubov.directory.model.Building;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface BuildingRepository extends PagingAndSortingRepository<Building, Integer> {
    @Query("SELECT b FROM Building b WHERE LOWER(b.address) LIKE LOWER(CONCAT('%', :address, '%'))")
    List<Building> findAllByAddressLike(String address);
}
