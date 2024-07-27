//package com.app.config;
//
//import com.app.service.db.PartImageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class PartImageServiceConfig {
//
//    private final PartImageService partImageService;
//
//    @Autowired
//    public PartImageServiceConfig(PartImageService partImageService) {
//        this.partImageService = partImageService;
//    }
//
//    @Bean
//    CommandLineRunner commandLineRunner() {
//        return args -> partImageService.updateImagesInTables();
//    }
//}
//
