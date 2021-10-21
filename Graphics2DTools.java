package com.dz_fs_dev.common;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Non-contructable class containing different image manipulative methods for financial dashboards and displays.
 * 
 * @author DZ-FSDev
 * @since 16.0.1
 * @version 0.0.1
 */
public final class Graphics2DTools {
	/**
	 * Scales an image retaining lines and parallelism at the cost of distances and angles between objects.
	 * 
	 * @param sourceImg The image to be scaled.
	 * @param scaledWidth The width of scaled image.
	 * @param scaledHeight The height of scaled image.
	 * @return The affine scaled image.
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
	 */
	public static BufferedImage scaleImageFast(BufferedImage sourceImg, int scaledWidth, int scaledHeight) {
		BufferedImage scaledImg = new BufferedImage(scaledWidth, scaledHeight, sourceImg.getType());
		Graphics2D g2d = scaledImg.createGraphics();
		g2d.drawImage(sourceImg, 0, 0, scaledWidth, scaledHeight, null);
		g2d.dispose();
		return scaledImg;
	}
}
