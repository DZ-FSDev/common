package com.dz_fs_dev.common;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;

import jesino.GifSequenceWriter;

/**
 * Non-contructable class containing different image manipulative methods for financial dashboards and displays.
 * 
 * @author DZ-FSDev
 * @since 17.0.1
 * @version 0.0.5
 */
public final class Graphics2DTools {
	private Graphics2DTools() {}
	
	/**
	 * Scales an image retaining lines and parallelism at the cost of distances and angles between objects.
	 * 
	 * @param sourceImg The image to be scaled.
	 * @param scaledWidth The width of scaled image.
	 * @param scaledHeight The height of scaled image.
	 * @return The affine scaled image.
	 * @since 0.0.1
	 */
	public static BufferedImage scaleImageEuclidianAffine(BufferedImage sourceImg, int scaledWidth, int scaledHeight) {
		BufferedImage scaledImg = null;
		scaledImg = new BufferedImage(scaledWidth, scaledHeight, sourceImg.getType());
		Graphics2D g2d = scaledImg.createGraphics();
		AffineTransform affineTransformer = AffineTransform.getScaleInstance(
				(double)scaledWidth / sourceImg.getWidth(), (double)scaledHeight / sourceImg.getHeight());
		g2d.drawRenderedImage(sourceImg, affineTransformer);
		g2d.dispose();
		return scaledImg;
	}

	/**
	 * Scales an image quickly at the cost of output quality.
	 * 
	 * @param sourceImg The image to be scaled.
	 * @param scaledWidth The width of scaled image.
	 * @param scaledHeight The height of scaled image.
	 * @return The scaled image.
	 * @since 0.0.1
	 */
	public static BufferedImage scaleImageFast(BufferedImage sourceImg, int scaledWidth, int scaledHeight) {
		BufferedImage scaledImg = new BufferedImage(scaledWidth, scaledHeight, sourceImg.getType());
		Graphics2D g2d = scaledImg.createGraphics();
		g2d.drawImage(sourceImg, 0, 0, scaledWidth, scaledHeight, null);
		g2d.dispose();
		return scaledImg;
	}
	
	/**
	 * Saves a BufferedImage to a specified file as a portable network graphic.
	 * 
	 * @param bufferedImage The image to be saved.
	 * @param fileName The name of the file to be saved.
	 * @throws IOException Thrown if the file cannot be written.
	 * @since 0.0.2
	 */
	public static void saveAsPNG(BufferedImage bufferedImage, String fileName) throws IOException {
		File outputfile = new File(fileName + ".png");
		ImageIO.write(bufferedImage, "png", outputfile);
	}
	
	/**
	 * Saves a BufferedImage to a specified file as a Joint Photographic Experts Group digital image.
	 * 
	 * @param bufferedImage The image to be saved.
	 * @param fileName The name of the file to be saved.
	 * @throws IOException Thrown if the file cannot be written.
	 * @since 0.0.2
	 */
	public static void saveAsJPG(BufferedImage bufferedImage, String fileName) throws IOException {
		File outputfile = new File(fileName + ".jpg");
		ImageIO.write(bufferedImage, "jpg", outputfile);
	}
	
	/**
	 * Saves a BufferedImage to a specified file as a portable network graphic.
	 * 
	 * @param bufferedImage The image to be saved.
	 * @param outputFile The file to save the BufferedImage to.
	 * @throws IOException Thrown if the file cannot be written.
	 * @since 0.0.3
	 */
	public static void saveAsPNG(BufferedImage bufferedImage, File outputFile) throws IOException {
		if(!outputFile.getName().endsWith(".png"))outputFile = new File(outputFile.getPath() + ".png");
		ImageIO.write(bufferedImage, "png", outputFile);
	}
	
	/**
	 * Saves a list of BufferedImages as a single animated GIF. The frame delay will be constant through the animation.
	 * 
	 * @param images The list of buffered images to be 
	 * @param frameDdelay
	 * @param outputFile
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws IIOException 
	 * @since 0.0.5
	 */
	public static void saveAsAnimatedGif(List<BufferedImage> images, int frameDdelay, File outputFile) throws IIOException, FileNotFoundException, IOException {
		FileImageOutputStream fios;
		
		GifSequenceWriter gifWriter = new GifSequenceWriter(
				fios = new FileImageOutputStream(outputFile),
				BufferedImage.TYPE_INT_ARGB, frameDdelay, true);
		
		for(BufferedImage image : images) {
			gifWriter.writeToSequence(image);;
		}
		
		gifWriter.close();
		fios.close();
	}
	
	/**
	 * Returns a byte array representing an animated GIF with frames from a list of BufferedImages.
	 * The frame delay will be constant through the animation.
	 * 
	 * @param images The list of buffered images to be 
	 * @param frameDelay
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws IIOException 
	 * @since 0.0.5
	 */
	public static byte[] toAnimatedGifBytes(List<BufferedImage> images, int frameDelay) throws IIOException, IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageOutputStream mcos = new MemoryCacheImageOutputStream(baos);
		
		GifSequenceWriter gifWriter = new GifSequenceWriter(
				mcos, BufferedImage.TYPE_INT_ARGB, frameDelay, true);
		
		for(BufferedImage image : images) {
			System.out.println(image);
			gifWriter.writeToSequence(image);
			
		}
		
		gifWriter.close();
		
		mcos.close();
		return baos.toByteArray();
	}
	
	/**
	 * Saves a BufferedImage to a specified file as a Joint Photographic Experts Group digital image.
	 * 
	 * @param bufferedImage The image to be saved.
	 * @param outputFile The file to save the BufferedImage to.
	 * @throws IOException Thrown if the file cannot be written.
	 * @since 0.0.3
	 */
	public static void saveAsJPG(BufferedImage bufferedImage, File outputFile) throws IOException {
		if(!outputFile.getName().endsWith(".jpg"))outputFile = new File(outputFile.getPath() + ".jpg");
		ImageIO.write(bufferedImage, "jpg", outputFile);
	}
	
	/**
	 * Returns a png byte array containing the data from a specified buffered image.
	 * 
	 * @param bufferedImage The buffered image to be converted to a byte array.
	 * @return The png byte array.
	 * @throws IOException Thrown if the file cannot be written.
	 * @since 0.0.4
	 */
	public static byte[] toPNGbytes(BufferedImage bufferedImage) throws IOException {
		ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "png", byteArrayStream);
		return byteArrayStream.toByteArray();
	}
}
