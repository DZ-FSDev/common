package com.dz_fs_dev.common;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Non-contructable class containing different image manipulative methods for financial dashboards and displays.
 * 
 * @author DZ-FSDev
 * @since 17.0.1
 * @version 0.0.2
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
}
