package com.app.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

/**
 * Utility class for downloading images
 */
public class ImageDownloader
{

    private static final String BASE_IMAGES_DIRECTORY = "frontend\\public\\images\\";

    private static final Logger logger = LoggerFactory.getLogger(ImageDownloader.class);

    private ImageDownloader()
    {
        throw new IllegalStateException("Utility class");
    }

    /**
     * @param imgUrl                url for image to be downloaded
     * @param fname                 file name the image should be named when downloaded
     * @param categoryDirectoryName category directory where image should be stored in
     */
    public static void downloadImage(String imgUrl, String fname, String categoryDirectoryName)
    {
        try
        {
            if (imgUrl == null)
            {
                logger.info("Image URL is null. Skipping download.");
                return;
            }

            String destination = BASE_IMAGES_DIRECTORY + categoryDirectoryName + "\\";
            File file = new File(destination + fname);

            if (file.exists())
            {
                logger.info("File already exists. Skipping download.");
                return;
            }

            URL url = new URI(imgUrl).toURL();
            InputStream inputStream = url.openStream();
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            byte[] bytes = new byte[1024];
            int len;
            while ((len = inputStream.read(bytes)) != -1)
                fileOutputStream.write(bytes, 0, len);

            fileOutputStream.close();
            inputStream.close();
        } catch (Exception e)
        {
            logger.error("Could not download image.");
        }
    }
}
