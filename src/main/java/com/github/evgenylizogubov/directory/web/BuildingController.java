package com.github.evgenylizogubov.directory.web;

import com.github.evgenylizogubov.directory.model.Building;
import com.github.evgenylizogubov.directory.repository.BuildingRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/building", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class BuildingController {
    private final BuildingRepository repository;
    
    @GetMapping
    @Operation(summary = "Get all buildings pageable and sorting by address")
    public List<Building> getAll(@PageableDefault(sort = {"address"}) Pageable pageable) {
        log.info("getAll");
        return repository.findAll(pageable).getContent();
    }
    
    @GetMapping("/by-address")
    @Cacheable("buildingsByAddress")
    @Operation(summary = "Get all buildings with like address")
    public List<Building> getAllByAddress(@RequestParam String address) {
        log.info("get buildings by name:{}", address);
        return repository.findAllByAddressLike(address);
    }
}
