package com.github.evgenylizogubov.directory.web;

import com.github.evgenylizogubov.directory.model.Heading;
import com.github.evgenylizogubov.directory.repository.HeadingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/heading", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class HeadingController {
    private final HeadingRepository repository;
    
    @GetMapping
    @Cacheable("headingAllRoots")
    public List<Heading> getAllRoots() {
        log.info("getAllRoots");
        return repository.findAllRoot();
    }
    
    @GetMapping("/{id}")
    @Cacheable("headingAllChildrenByParentId")
    public List<Heading> getAllChildrenByParentId(@PathVariable int id) {
        log.info("getAllChildren for Heading with id:{}", id);
        return repository.findAllChildren(id);
    }
}
