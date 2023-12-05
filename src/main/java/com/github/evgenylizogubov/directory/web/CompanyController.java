package com.github.evgenylizogubov.directory.web;

import com.github.evgenylizogubov.directory.model.Company;
import com.github.evgenylizogubov.directory.model.Heading;
import com.github.evgenylizogubov.directory.repository.CompanyRepository;
import com.github.evgenylizogubov.directory.repository.HeadingRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/company", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class CompanyController {
    private final CompanyRepository companyRepository;
    private final HeadingRepository headingRepository;
    
    @GetMapping("/{id}")
    @Cacheable("companiesByName")
    @Operation(summary = "Get company by id")
    public ResponseEntity<Company> get(@PathVariable int id) {
        log.info("get id = {}", id);
        return ResponseEntity.of(companyRepository.findById(id));
    }
    
    @GetMapping("/by-name")
    @Cacheable("companiesByName")
    @Operation(summary = "Get company or companies like name")
    public List<Company> getByName(@RequestParam String name) {
        log.info("getByName for name = {}", name);
        return companyRepository.findByNameLike(name);
    }
    
    @GetMapping("/by-building-id")
    @Cacheable("companiesByBuildingId")
    @Operation(summary = "Get all companies in building with concrete id")
    public List<Company> getAllByBuildingId(@RequestParam Integer buildingId) {
        log.info("getAllByBuildingId for buildingId = {}", buildingId);
        return companyRepository.findAllByBuildingId(buildingId);
    }
    
    @GetMapping("/by-heading")
    @Cacheable("companiesByHeading")
    @Operation(summary = "Get all companies in heading with concrete name")
    public List<Company> getAllByHeading(@RequestParam String headingName) {
        log.info("getAllByHeading for {}", headingName);
        return companyRepository.findAllByHeadingName(headingName);
    }
    
    @GetMapping("/in-area")
    @Cacheable("companiesInArea")
    @Operation(summary = "Get all companies in circle or rectangle area")
    public List<Company> getAllInCircleOrRectangleArea(@RequestParam int latitude,
                                                       @RequestParam int longitude,
                                                       @RequestParam int radius,
                                                       @RequestParam boolean isCircleArea) {
        log.info("getAllInArea for latitude = {}, longitude = {}, radius = {}, circle area = {}",
                latitude, longitude, radius, isCircleArea);
        
        if (isCircleArea) {
            return companyRepository.findAllInCircleArea(latitude, longitude, radius);
        }
        return companyRepository.findAllInRectangleArea(latitude, longitude, radius);
    }
    
    @GetMapping("/by-name-and-heading")
    @Cacheable("companiesByNameAndHeading")
    @Operation(summary = "Get all companies by and heading including subheading")
    public Set<Company> getAllByNameAndHeading(@RequestParam String companyName,
                                               @RequestParam String headingName) {
        log.info("getAllByNameAndHeading for companyName = {} and headingName = {}", companyName, headingName);
        List<Heading> headingsWithChildren = headingRepository.findByHeadingNameAndCompanyName(headingName, companyName);
        Set<Company> result = new HashSet<>();
        headingsWithChildren.forEach(heading -> result.addAll(heading.getCompanies()));
        return result;
    }
}
