package com.example.filter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

public class GifEncoder {
    public static void writeFrames(List<BufferedImage> frames, File outputFile) throws IOException {
        if (frames.isEmpty()) {
            throw new IllegalArgumentException("Frame list is empty.");
        }
        
        int width = frames.get(0).getWidth();
        int height = frames.size();
        
        BufferedImage gifImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        for (int i = 0; i < height; i++) {
            BufferedImage frame = frames.get(i);
            int[] pixels = new int[width];
            
            for (int j = 0; j < width; j++) {
                pixels[j] = frame.getRGB(j, 0);
            }
            
            gifImage.setRGB(0, i, width, 1, pixels, 0, width);
        }
        
        ImageIO.write(gifImage, "gif", outputFile);
    }
}

