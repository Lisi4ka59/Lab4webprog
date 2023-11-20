package com.lisi4ka.lab4webdb.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public abstract class ImageToByteArray {
    public static byte[] imageToByteArray(BufferedImage image, String format) {
        byte[] bytes = null;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, format, outputStream);
            outputStream.flush();
            bytes = outputStream.toByteArray();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
