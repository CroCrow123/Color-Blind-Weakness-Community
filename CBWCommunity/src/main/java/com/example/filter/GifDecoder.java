package com.example.filter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class GifDecoder {
    public static List<BufferedImage> readFrames(File gifFile) throws IOException {
        List<BufferedImage> frames = new ArrayList<>();

        ImageReader reader = ImageIO.getImageReadersByFormatName("gif").next();
        try (ImageInputStream input = ImageIO.createImageInputStream(gifFile)) {
            reader.setInput(input);

            int numFrames = reader.getNumImages(true);

            for (int i = 0; i < numFrames; i++) {
                BufferedImage frame = reader.read(i);

                // 프레임의 높이를 설정
                int frameHeight = frame.getHeight();
                frame = frame.getSubimage(0, 0, frame.getWidth(), frameHeight);

                frames.add(frame);
            }
        } finally {
            reader.dispose();
        }

        return frames;
    }
}