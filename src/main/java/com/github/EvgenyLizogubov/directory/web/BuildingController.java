package com.github.EvgenyLizogubov.directory.web;

import com.github.EvgenyLizogubov.directory.model.Building;
import com.github.EvgenyLizogubov.directory.repository.BuildingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/building", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class BuildingController {
    private final BuildingRepository repository;
    
    @GetMapping
    public List<Building> getAll() {
        log.info("getAll");
        return repository.findAll(Sort.by("address"));
    }
}
