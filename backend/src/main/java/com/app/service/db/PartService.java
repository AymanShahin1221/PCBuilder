package com.app.service.db;

import com.app.entity.model.CPU;
import com.app.repository.CPURepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartService {
    private final CPURepository cpuRepository;

    @Autowired
    public PartService(CPURepository cpuRepository) {
        this.cpuRepository = cpuRepository;
    }

    public List<CPU> getCPUs() {
        return cpuRepository.findAll();
    }
}
