/*package com.example.filter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.FileImageOutputStream;

public class GifSequenceWriter {
    protected ImageWriter gifWriter;
    protected ImageWriteParam imageWriteParam;
    protected IIOMetadata imageMetaData;

    public GifSequenceWriter(File outputFile, int timeBetweenFramesMS) throws IOException {
        // GIF 이미지 작성자 생성
        this.gifWriter = ImageIO.getImageWritersBySuffix("gif").next();
        this.imageWriteParam = this.gifWriter.getDefaultWriteParam();
        this.imageMetaData = this.gifWriter.getDefaultImageMetadata(ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_ARGB), this.imageWriteParam);

        String metaFormatName = this.imageMetaData.getNativeMetadataFormatName();
        IIOMetadataNode root = (IIOMetadataNode) this.imageMetaData.getAsTree(metaFormatName);

        // 그래픽 컨트롤 확장 설정
        IIOMetadataNode graphicsControlExtensionNode = getNode(root, "GraphicControlExtension");
        graphicsControlExtensionNode.setAttribute("disposalMethod", "none");
        graphicsControlExtensionNode.setAttribute("userInputFlag", "FALSE");
        graphicsControlExtensionNode.setAttribute("transparentColorFlag", "TRUE");
        graphicsControlExtensionNode.setAttribute("delayTime", Integer.toString(timeBetweenFramesMS / 10));
        graphicsControlExtensionNode.setAttribute("transparentColorIndex", "0");

        // 주석 추가
        IIOMetadataNode commentsNode = getNode(root, "CommentExtensions");
        commentsNode.appendChild(createCommentNode("Created by GifSequenceWriter"));

        this.imageMetaData.setFromTree(metaFormatName, root);

        // 출력 스트림 설정
        FileImageOutputStream output = new FileImageOutputStream(outputFile);
        this.gifWriter.setOutput(output);
        this.gifWriter.prepareWriteSequence(null);
    }

    public void writeToSequence(BufferedImage img) throws IOException {
        // 이미지를 시퀀스에 작성
        this.gifWriter.writeToSequence(new IIOImage(img, null, this.imageMetaData), this.imageWriteParam);
    }

    public void close() throws IOException {
        // 시퀀스 작성 종료
        this.gifWriter.endWriteSequence();
    }

    private static IIOMetadataNode getNode(IIOMetadataNode rootNode, String nodeName) {
        int nNodes = rootNode.getLength();
        for (int i = 0; i < nNodes; i++) {
            if (rootNode.item(i).getNodeName().equalsIgnoreCase(nodeName)) {
                return (IIOMetadataNode) rootNode.item(i);
            }
        }
        IIOMetadataNode node = new IIOMetadataNode(nodeName);
        rootNode.appendChild(node);
        return node;
    }

    private static IIOMetadataNode createCommentNode(String comment) {
        IIOMetadataNode node = new IIOMetadataNode("Comment");
        node.setAttribute("commentText", comment);
        return node;
    }
}*/
