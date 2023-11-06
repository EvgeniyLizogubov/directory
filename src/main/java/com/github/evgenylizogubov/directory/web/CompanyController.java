package com.github.evgenylizogubov.directory.web;

import com.github.evgenylizogubov.directory.error.NotFoundException;
import com.github.evgenylizogubov.directory.model.Company;
import com.github.evgenylizogubov.directory.model.Heading;
import com.github.evgenylizogubov.directory.repository.CompanyRepository;
import com.github.evgenylizogubov.directory.repository.HeadingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "api/company", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
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
    @Cacheable("companies")
    public List<Company> getAllByBuildingId(@RequestParam Integer buildingId) {
        log.info("getAllByBuildingId for buildingId = {}", buildingId);
        return companyRepository.findAllByBuildingId(buildingId);
    }
    
    @GetMapping("/by-heading")
    @Cacheable("companies")
    public List<Company> getAllByHeading(@RequestParam String heading) {
        log.info("getAllByHeading for {}", heading);
        return companyRepository.findAllByHeading(heading);
    }
    
    @GetMapping("/in-circle-area")
    @Cacheable("companies")
    public List<Company> getAllInCircleArea(@RequestParam int latitude,
                                            @RequestParam int longitude,
                                            @RequestParam int radius) {
        log.info("getAllInArea for x = {}, y = {}, radius = {}", latitude, longitude, radius);
        List<Company> companies = companyRepository.findAll(Sort.by("name"));
        return companies.stream().filter(company -> {
            int companyLatitude = company.getBuilding().getLatitude();
            int companyLongitude = company.getBuilding().getLongitude();
            return Math.pow(companyLatitude - latitude, 2) + Math.pow(companyLongitude - longitude, 2) <= Math.pow(radius, 2);
        }).toList();
    }
    
    @GetMapping("/in-rectangle-area")
    @Cacheable("companies")
    public List<Company> getAllInRectangleArea(@RequestParam int point1Latitude,
                                               @RequestParam int point1Longitude,
                                               @RequestParam int point2Latitude,
                                               @RequestParam int point2Longitude) {
        log.info("getAllInRectangleArea for point1 = ({}, {}), point2 = ({}, {})",
                point1Latitude, point1Longitude, point2Latitude, point2Longitude);
        return companyRepository.findAll(Sort.by("name")).stream().filter(company -> {
            int companyLatitude = company.getBuilding().getLatitude();
            int companyLongitude = company.getBuilding().getLongitude();
            return companyLatitude >= point1Latitude && companyLatitude <= point2Latitude
                    && companyLongitude >= point1Longitude && companyLongitude <= point2Longitude;
        }).toList();
    }
    
    @GetMapping("/by-name-and-heading")
    @Cacheable("companies")
    public Set<Company> getAllByNameAndHeading(@RequestParam String companyName,
                                               @RequestParam String headingName) {
        log.info("getAllByNameAndHeading for companyName = {} and headingName = {}", companyName, headingName);
        Heading selectedHeading = headingRepository.findByHeadingName(headingName);
        if (selectedHeading == null)
            throw new NotFoundException("Heading \"" + headingName + "\" not found");
        
        Set<Company> result = new HashSet<>(selectedHeading.getCompanies().stream().filter(company -> company.getName().contains(companyName)).toList());
        selectedHeading.getAllChildren().forEach(heading -> heading.getCompanies().forEach(company -> {
            if (company.getName().contains(companyName)) {
                result.add(company);
            }
        }));
        return result;
    }
}
