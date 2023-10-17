/*package com.example.filter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class CBFilterController {

    private static final String UPLOAD_DIR = "uploads/";

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("colorBlindTypes", CBFilterSimulation.ColorBlindType.values());
        return "index";
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file,
                              @RequestParam("colorBlindType") CBFilterSimulation.ColorBlindType colorBlindType,
                              RedirectAttributes redirectAttributes, Model model) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "파일을 선택하세요.");
            return "redirect:/";
        }

        try {
            // 업로드 디렉토리 생성
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // 파일 저장
            Path filePath = Path.of(uploadDir.getPath(), file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // 업로드한 이미지 읽기
            List<BufferedImage> frames = GifDecoder.readFrames(filePath.toFile());

            // 색맹 시뮬레이션 적용
            for (int i = 0; i < frames.size(); i++) {
                BufferedImage frame = frames.get(i);
                BufferedImage simulatedFrame = CBFilterSimulation.simulateColorBlindness(frame, colorBlindType);
                frames.set(i, simulatedFrame);
            }

            // 적용된 이미지를 모델에 추가
            model.addAttribute("frames", frames);

        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message", "파일 업로드 중 오류가 발생했습니다.");
        }

        return "result";
    }
}
*/