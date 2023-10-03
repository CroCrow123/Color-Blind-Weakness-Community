/*package com.example.filter;

import com.example.filter.CBFilterSimulation;
import com.example.filter.CBFilterSimulation.ColorBlindType;
import com.example.filter.GifSequenceWriter;
import com.example.filter.GifEncoder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CBFilterController {

	@PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                  @RequestParam("colorBlindType") ColorBlindType colorBlindType,
                                  RedirectAttributes redirectAttributes,
                                  Model model) {

        // 파일 확장자 확인
        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();

        try {
            if (fileExtension.equalsIgnoreCase("gif")) {
                BufferedImage[] gifFrames = extractGifFrames(file);
                BufferedImage[] convertedFrames = new BufferedImage[gifFrames.length];

                for (int i = 0; i < gifFrames.length; i++) {
                    BufferedImage frame = gifFrames[i];
                    convertedFrames[i] = CBFilterSimulation.simulateColorBlindness(frame, colorBlindType);
                }

                File outputGifFile = new File("converted.gif");
                saveAsGif(convertedFrames, outputGifFile);

                model.addAttribute("imageUrl", "/images/converted.gif"); // 이미지 URL을 모델에 추가
            } else if (isImageFile(fileExtension)) {
                BufferedImage image = ImageIO.read(file.getInputStream());
                BufferedImage convertedImage = CBFilterSimulation.simulateColorBlindness(image, colorBlindType);

                File outputImageFile = new File("converted_image.png");
                ImageIO.write(convertedImage, "png", outputImageFile);

                model.addAttribute("imageUrl", "/images/converted_image.png"); // 이미지 URL을 모델에 추가
            } else {
                redirectAttributes.addFlashAttribute("message", "지원하지 않는 파일 형식입니다.");
                return "redirect:/";
            }

            return "result"; // 결과 페이지로 이동
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "파일 업로드 및 변환 중 오류가 발생했습니다.");
            return "redirect:/";
        }
    }


    private BufferedImage[] extractGifFrames(MultipartFile gifFile) throws IOException {
        List<BufferedImage> frames = new ArrayList<>();
        BufferedImage sourceImage = ImageIO.read(gifFile.getInputStream());

        for (int i = 0; i < sourceImage.getWidth(); i++) {
            BufferedImage frame = sourceImage.getSubimage(i, 0, 1, sourceImage.getHeight());
            frames.add(frame);
        }

        return frames.toArray(new BufferedImage[0]);
    }

    private void saveAsGif(BufferedImage[] frames, File outputGifFile) throws IOException {
        GifSequenceWriter gifSequenceWriter = new GifSequenceWriter(outputGifFile, frames[0].getType(), 100, true); // Modify the delay as needed

        for (BufferedImage frame : frames) {
            gifSequenceWriter.writeToSequence(frame);
        }

        gifSequenceWriter.close();
    }
    
    private boolean isImageFile(String extension) {
        return extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png") || extension.equals("bmp");
    }
}*/