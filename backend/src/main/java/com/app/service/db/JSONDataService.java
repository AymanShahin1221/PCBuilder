package com.app.service.db;

import org.springframework.web.client.RestTemplate;

public class JSONDataService {

    /**
     * Service class that fetches JSON data using RestTemplate
     */

    private static final RestTemplate restTemplate = new RestTemplate();

    public enum ProductCategory {

        /**
         * Enum representing all categories and their corresponding JSON data URLs
         */
        CPU("https://raw.githubusercontent.com/docyx/pc-part-dataset/main/data/json/cpu.json"),
        GPU("https://raw.githubusercontent.com/docyx/pc-part-dataset/main/data/json/video-card.json"),
        MOTHERBOARD("https://raw.githubusercontent.com/docyx/pc-part-dataset/main/data/json/motherboard.json"),
        MEMORY("https://raw.githubusercontent.com/docyx/pc-part-dataset/main/data/json/memory.json"),
        STORAGE("https://raw.githubusercontent.com/docyx/pc-part-dataset/main/data/json/internal-hard-drive.json"),
        COOLER("https://raw.githubusercontent.com/docyx/pc-part-dataset/main/data/json/cpu-cooler.json"),
        CASE("https://raw.githubusercontent.com/docyx/pc-part-dataset/main/data/json/case.json"),
        POWER_SUPPLY("https://raw.githubusercontent.com/docyx/pc-part-dataset/main/data/json/power-supply.json"),
        MONITOR("https://raw.githubusercontent.com/docyx/pc-part-dataset/main/data/json/monitor.json"),
        OS("https://raw.githubusercontent.com/docyx/pc-part-dataset/main/data/json/os.json"),
        KEYBOARD("https://raw.githubusercontent.com/docyx/pc-part-dataset/main/data/json/keyboard.json");

        private final String url;
        ProductCategory(String url) { this.url = url; }

        /**
         *
         * @param category category enum representing the specified product category
         * @return a string containing the JSON data for the specified category
         */
        public static String fetchJsonData(ProductCategory category) { return restTemplate.getForObject(category.url, String.class); }
    }
}
