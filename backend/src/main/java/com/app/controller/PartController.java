package com.app.controller;

import com.app.entity.model.CPU;
import com.app.service.db.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PartController {
    private final PartService partService;

    @Autowired
    public PartController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping("/")
    public List<CPU> getCpus() {
        return partService.getCPUs();
    }
}
