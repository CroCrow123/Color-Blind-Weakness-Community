package com.example.filter;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;


public class CBFilterMain {
	public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            // 사용자가 이미지 파일을 선택한 경우
            File selectedFile = fileChooser.getSelectedFile();

            try {
                // 선택한 이미지 파일 로드
                BufferedImage originalImage = ImageIO.read(selectedFile);

                // 색맹 시뮬레이션 타입 선택 (PROTANOPIA, DEUTERANOPIA)
                CBFilterSimulation.ColorBlindType type = CBFilterSimulation.ColorBlindType.PROTANOPIA;

                // 색맹 시뮬레이션 적용
                BufferedImage simulatedImage = CBFilterSimulation.simulateColorBlindness(originalImage, type);

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
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // 사용자가 이미지 파일을 선택하지 않은 경우
            System.out.println("이미지 선택이 취소되었습니다.");
        }
    }
}
