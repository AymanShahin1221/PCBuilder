package com.app.controller;

import com.app.entity.model.GPU;
import com.app.service.db.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PartController {
    private final PartService partService;

    @Autowired
    public PartController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping("/")
    public Object getAllParts() {
        return partService.getAllParts(GPU.class);
    }
}
