package com.app.controller;

import com.app.entity.model.*;
import com.app.service.db.PartService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.app.controller.PartCategory.*;

@RestController
public class PartController {
    private final PartService partService;

    private final Map<PartCategory, Class<? extends PCPart>> categoryClassMap = new HashMap<>();

    @Autowired
    public PartController(PartService partService) {
        this.partService = partService;

        categoryClassMap.put(CASE, Case.class);
        categoryClassMap.put(COOLER, Cooler.class);
        categoryClassMap.put(CPU, CPU.class);
        categoryClassMap.put(GPU, GPU.class);
        categoryClassMap.put(KEYBOARD, Keyboard.class);
        categoryClassMap.put(MEMORY, Memory.class);
        categoryClassMap.put(MONITOR, Monitor.class);
        categoryClassMap.put(MOTHERBOARD, Motherboard.class);
        categoryClassMap.put(OS, OS.class);
        categoryClassMap.put(POWER_SUPPLY, PowerSupply.class);
        categoryClassMap.put(STORAGE, Storage.class);
    }

    private <T extends PCPart> Class<T> getClassInstance(String categoryName) {
        return (Class<T>) categoryClassMap.get(PartCategory.valueOf(categoryName.toUpperCase()));
    }

    @GetMapping("/api/v1/getAllParts/{categoryName}")
    public <T extends PCPart> String getAllParts(@PathVariable("categoryName") String categoryName) {
        Class<T> partClass = getClassInstance(categoryName);
        JSONArray jsonData =  partService.getAllPartsByCategory(partClass);

        return jsonData.toString();
    }
}
