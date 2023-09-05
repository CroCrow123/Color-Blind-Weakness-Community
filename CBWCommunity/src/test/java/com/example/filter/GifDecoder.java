package com.example.filter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class GifDecoder {
    public static List<BufferedImage> readFrames(File gifFile) throws IOException {
        List<BufferedImage> frames = new ArrayList<>();

        BufferedImage originalImage = ImageIO.read(gifFile);
        int numFrames = originalImage.getHeight();
        
        for (int i = 0; i < numFrames; i++) {
            BufferedImage frame = originalImage.getSubimage(0, i, originalImage.getWidth(), 1);
            frames.add(frame);
        }
        
        return frames;
    }
}
