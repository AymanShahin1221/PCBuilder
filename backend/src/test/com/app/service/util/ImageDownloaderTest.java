package com.app.service.util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ImageDownloaderTest {

    private static final String BASE_IMAGES_DIRECTORY = "frontend\\public\\images\\";
    private static final String TEST_DIRECTORY = "test_images\\";
    private static final String TEST_CATEGORY = "test_category\\";

    private static final String BASE_TEST_DIRECTORY = BASE_IMAGES_DIRECTORY + TEST_DIRECTORY;
    private static final String TESTING_IMAGES_DIRECTORY = BASE_IMAGES_DIRECTORY + TEST_DIRECTORY + TEST_CATEGORY;

    @BeforeAll
    static void setUp() {
        new File(TESTING_IMAGES_DIRECTORY).mkdirs();
    }

    private static void deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null)
        {
            for (File file : allContents)
                deleteDirectory(file);
        }
        directoryToBeDeleted.delete();
    }

    @AfterAll
    static void cleanUp() {
        deleteDirectory(new File(BASE_TEST_DIRECTORY));
    }

    // download test image, check if it exists after downloading it
    @Test
    void testIfImageExists() {
        String url = "https://m.media-amazon.com/images/I/61DLYxmpZlL._SX522_.jpg";
        ImageDownloader.downloadImage(url, "af6588c0-7384-43a5-b0a8-645c2634edce.jpg", "test_images\\test_category");

        File file = new File(TESTING_IMAGES_DIRECTORY + "af6588c0-7384-43a5-b0a8-645c2634edce.jpg");
        assertTrue(file.exists());
    }

    // ensure image does NOT get downloaded again if it already exists
    @Test
    void checkIfImageDoesNotGetDownloaded() {
        String url = "https://m.media-amazon.com/images/I/61DLYxmpZlL._SX522_.jpg";
        ImageDownloader.downloadImage(url, "af6588c0-7384-43a5-b0a8-645c2634edce.jpg", "test_images\\test_category");

        File image_dir = new File(TESTING_IMAGES_DIRECTORY);

        File[] imageFiles = image_dir.listFiles();

        int count = 0;
        for(File file : imageFiles)
        {
            if(file.getName().equals("af6588c0-7384-43a5-b0a8-645c2634edce.jpg"))
                count++;
        }
        assertTrue(count < 2);
    }
}