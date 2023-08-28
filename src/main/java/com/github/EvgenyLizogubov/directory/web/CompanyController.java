package com.github.EvgenyLizogubov.directory.web;

import com.github.EvgenyLizogubov.directory.error.NotFoundException;
import com.github.EvgenyLizogubov.directory.model.Company;
import com.github.EvgenyLizogubov.directory.model.Heading;
import com.github.EvgenyLizogubov.directory.repository.CompanyRepository;
import com.github.EvgenyLizogubov.directory.repository.HeadingRepository;
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
    public ResponseEntity<Company> getByName(@RequestParam String name) {
        log.info("getByName for name = {}", name);
        return ResponseEntity.of(companyRepository.findByName(name));
    }
    
    @GetMapping("/building")
    @Cacheable("companies")
    public List<Company> getAllByBuilding(@RequestParam String address) {
        log.info("getAllByBuilding for address = {}", address);
        return companyRepository.findAllByBuilding(address);
    }
    
    @GetMapping("/heading")
    @Cacheable("companies")
    public List<Company> getAllByHeading(@RequestParam String heading) {
        log.info("getAllByHeading for {}", heading);
        return companyRepository.findAllByHeading(heading);
    }
    
    @GetMapping("/in-area")
    @Cacheable("companies")
    public List<Company> getAllInArea(@RequestParam int xCoor,
                                      @RequestParam int yCoor,
                                      @RequestParam int radius) {
        log.info("getAllInArea for x = {}, y = {}, radius = {}", xCoor, yCoor, radius);
        List<Company> companies = companyRepository.findAll(Sort.by("name"));
        return companies.stream().filter(company -> {
            int companyX = company.getBuilding().getCoordinates().getX();
            int companyY = company.getBuilding().getCoordinates().getY();
            return Math.pow(companyX - xCoor, 2) + Math.pow(companyY - yCoor, 2) <= Math.pow(radius, 2);
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
