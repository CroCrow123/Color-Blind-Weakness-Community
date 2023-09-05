package com.example.filter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

public class CBFilterFileType {
    
    // ... 이전에 작성한 이미지 변환 메소드들 ...

    // GIF 파일 변환 메소드
    public static void convertGifFile(File inputGif, File outputGif, CBFilterSimulation.ColorBlindType type) {
        try {
            GifDecoder gifDecoder = new GifDecoder();
            int status = gifDecoder.read(inputGif.getPath());

            if (status != GifDecoder.STATUS_OK) {
                System.out.println("GIF 파일을 읽어올 수 없습니다.");
                return;
            }

            GifEncoder gifEncoder = new GifEncoder();
            gifEncoder.start(outputGif.getPath());
            gifEncoder.setRepeat(0);

            int frameCount = gifDecoder.getFrameCount();
            for (int i = 0; i < frameCount; i++) {
                BufferedImage frameImage = gifDecoder.getFrame(i);
                BufferedImage convertedImage = CBFilterSimulation.simulateColorBlindness(frameImage, type);
                gifEncoder.addFrame(convertedImage);
            }

            gifEncoder.finish();
            System.out.println("GIF 변환 완료");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
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