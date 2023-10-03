package com.example.filter;

import com.example.filter.CBFilterSimulation.ColorBlindType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class CBFilterMain {
	public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            // 사용자가 이미지 파일을 선택한 경우
            File selectedFile = fileChooser.getSelectedFile();

            try {
                // 파일 확장자를 확인하여 GIF 파일 여부를 판단
                String fileExtension = getFileExtension(selectedFile);
                if ("gif".equalsIgnoreCase(fileExtension)) {
                    // GIF 파일인 경우 GIF 변환 수행
                    convertGifFile(selectedFile, CBFilterSimulation.ColorBlindType.DEUTERANOPIA);
                } else {
                    // GIF 파일이 아닌 경우 이미지 변환 수행
                    BufferedImage originalImage = ImageIO.read(selectedFile);
                    BufferedImage simulatedImage = CBFilterSimulation.simulateColorBlindness(originalImage, CBFilterSimulation.ColorBlindType.PROTANOPIA);

                    // 결과 이미지를 화면에 보여주기 위한 Swing 창 생성
                    JFrame frame = new JFrame("Simulated Image");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    JPanel panel = new JPanel();
                    JLabel label = new JLabel(new ImageIcon(simulatedImage));
                    panel.add(label);

                    frame.add(panel);
                    frame.pack();
                    frame.setVisible(true);

                    System.out.println("색맹 시뮬레이션이 완료되었습니다.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // 사용자가 이미지 파일을 선택하지 않은 경우
            System.out.println("이미지 선택이 취소되었습니다.");
        }
    }
	// GIF 파일 변환 메소드
	public static void convertGifFile(File inputGif, ColorBlindType type) {
        try {
            // GIF 파일을 프레임 목록으로 읽어옴
            List<BufferedImage> frames = GifDecoder.readFrames(inputGif);

            if (frames.isEmpty()) {
                System.out.println("GIF 파일을 읽어올 수 없습니다.");
                return;
            }

            // JFrame을 생성하여 변환된 GIF 프레임을 보여줌
            JFrame frame = new JFrame("Simulated GIF");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            JLabel label = new JLabel();
            panel.add(label);
            frame.add(panel);

            for (int i = 0; i < frames.size(); i++) {
                BufferedImage frameImage = frames.get(i);
                BufferedImage convertedImage = CBFilterSimulation.simulateColorBlindness(frameImage, type);

                // JLabel에 변환된 이미지를 설정하여 JFrame에 표시
                label.setIcon(new ImageIcon(convertedImage));

                // JFrame을 갱신하여 이미지를 보여줌
                frame.pack();
                frame.setVisible(true);

                // 잠시 대기하면서 이미지를 보여줄 수 있도록
                try {
                    Thread.sleep(100); // 밀리초 단위, 필요에 따라 조절
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("GIF 변환 완료");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 파일 확장자 추출
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0) {
            return fileName.substring(lastDotIndex + 1);
        }
        return "";
    }

    
}