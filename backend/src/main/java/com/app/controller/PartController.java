package com.app.controller;

import com.app.entity.model.*;
import com.app.service.db.PartService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.app.service.util.DBUtils.getClassInstance;

@RestController
public class PartController {
    private final PartService partService;

    @Autowired
    public PartController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping("/api/v1/getAllParts/{categoryName}")
    public <T extends PCPart> String getAllParts(@PathVariable("categoryName") String categoryName) {
        Class<T> partClass = getClassInstance(categoryName);
        JSONArray jsonData =  partService.getAllPartsByCategory(partClass, List.of("pid"));

        return jsonData.toString();
    }
}
