/*
package com.example.filter;
 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.example.filter.CBFilterSimulation.ColorBlindType;


public class CBFilterFileType {
    // GIF 파일 변환 메소드
    public static void convertGifFile(File inputGif, File outputGif, ColorBlindType type) {
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
}
    
/*   
    // 동영상 파일 변환 메소드
    public static void convertVideoFile(File inputVideo, File outputVideo, CBFilterSimulation.ColorBlindType type) {
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputVideo);
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputVideo, grabber.getImageWidth(), grabber.getImageHeight());

        try {
            grabber.start();
            recorder.setFormat("mp4");
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
            recorder.setFrameRate(grabber.getFrameRate());
            recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);

            recorder.start();

            Frame frame;
            while ((frame = grabber.grabFrame()) != null) {
                BufferedImage image = Java2DFrameConverter.convert(frame);
                BufferedImage convertedImage = CBFilterSimulation.simulateColorBlindness(image, type);
                Frame convertedFrame = Java2DFrameConverter.convertToFrame(convertedImage);
                recorder.record(convertedFrame);
            }

            recorder.stop();
            recorder.release();
            grabber.stop();
            System.out.println("동영상 변환 완료");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/