package com.app.service.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * Utility class for downloading images
 */
public class ImageDownloader {

    private static final String baseImagesDirectory = "frontend\\public\\images\\";

    /**
     *
     * @param imgUrl url for image to be downloaded
     * @param fname file name the image should be named when downloaded
     * @param categoryDirectoryName category directory where image should be stored in
     */
    public static void downloadImage(String imgUrl, String fname, String categoryDirectoryName) {
        try
        {
            if(imgUrl == null)
                return;

            String destination = baseImagesDirectory + categoryDirectoryName + "\\";
            File file = new File(destination + fname);

            if(file.exists())
                return;

            URL url = new URL(imgUrl);
            InputStream inputStream = url.openStream();
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            byte[] bytes = new byte[1024];
            int len;
            while((len = inputStream.read(bytes)) != -1)
                fileOutputStream.write(bytes, 0, len);

            fileOutputStream.close();
            inputStream.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
