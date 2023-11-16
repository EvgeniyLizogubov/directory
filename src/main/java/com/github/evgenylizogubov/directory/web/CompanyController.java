package com.github.evgenylizogubov.directory.web;

import com.github.evgenylizogubov.directory.model.Company;
import com.github.evgenylizogubov.directory.model.Heading;
import com.github.evgenylizogubov.directory.repository.CompanyRepository;
import com.github.evgenylizogubov.directory.repository.HeadingRepository;
import jakarta.transaction.Transactional;
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
@RequestMapping(value = "api/company", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class CompanyController {
    private final CompanyRepository companyRepository;
    private final HeadingRepository headingRepository;
    
    @GetMapping("/{id}")
    @Cacheable("companies")
    public ResponseEntity<Company> get(@PathVariable int id) {
        log.info("get id = {}", id);
        return ResponseEntity.of(companyRepository.findById(id));
    }
    
    @GetMapping("/by-name")
    @Cacheable("companies")
    public List<Company> getByNameLike(@RequestParam String name) {
        log.info("getByName for name = {}", name);
        return companyRepository.findByNameLike(name);
    }
    
    @GetMapping("/by-building-id")
    @Cacheable("companiesByBuildingId")
    public List<Company> getAllByBuildingId(@RequestParam Integer buildingId) {
        log.info("getAllByBuildingId for buildingId = {}", buildingId);
        return companyRepository.findAllByBuildingId(buildingId);
    }
    
    @GetMapping("/by-heading")
    @Cacheable("companiesByHeading")
    public List<Company> getAllByHeading(@RequestParam String heading) {
        log.info("getAllByHeading for {}", heading);
        return companyRepository.findAllByHeading(heading);
    }
    
    @GetMapping("/in-circle-area")
    @Cacheable("companiesInCircleArea")
    public List<Company> getAllInCircleArea(@RequestParam int latitude,
                                            @RequestParam int longitude,
                                            @RequestParam int radius) {
        log.info("getAllInArea for x = {}, y = {}, radius = {}", latitude, longitude, radius);
        return companyRepository.findAllInCircleArea(latitude, longitude, radius);
    }
    
    @GetMapping("/in-rectangle-area")
    @Cacheable("companiesInRectangleArea")
    public List<Company> getAllInRectangleArea(@RequestParam int point1Latitude,
                                               @RequestParam int point1Longitude,
                                               @RequestParam int point2Latitude,
                                               @RequestParam int point2Longitude) {
        log.info("getAllInRectangleArea for point1 = ({}, {}), point2 = ({}, {})",
                point1Latitude, point1Longitude, point2Latitude, point2Longitude);
        return companyRepository.findAllInRectangleArea(point1Latitude, point1Longitude, point2Latitude, point2Longitude);
    }
    
    @GetMapping("/by-name-and-heading")
    @Cacheable("companiesByNameAndHeading")
    @Transactional
    public Set<Company> getAllByNameAndHeading(@RequestParam String companyName,
                                               @RequestParam String headingName) {
        log.info("getAllByNameAndHeading for companyName = {} and headingName = {}", companyName, headingName);
        List<Heading> headingsWithChildren = headingRepository.findByHeadingName(headingName);
        Set<Company> result = new HashSet<>();
        headingsWithChildren.forEach(heading -> heading.getCompanies().forEach(company -> {
            if (company.getName().contains(companyName)) {
                result.add(company);
            }
        }));
        return result;
    }
}
