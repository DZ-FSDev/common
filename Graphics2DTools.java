/*  Original Licensing Copyright
 * 
 *  Image manipulation methods for financial dashboards and displays.
 *  Copyright (C) 2021  DZ-FSDev
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.dz_fs_dev.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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
 * Utility class containing different image manipulative methods for financial dashboards and displays.
 * 
 * @author DZ-FSDev
 * @since 17.0.1
 * @version 0.0.8
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
	 * @param images The list of buffered images to be compiled.
	 * @param frameDdelay The uniform delay in milliseconds between frames.
	 * @param outputFile The file to write the output.
	 * @throws IOException 
	 * @throws FileNotFoundException Thrown if file cannot be written to.
	 * @throws IIOException Thrown if the there are issues with reads or writes fail.
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
	 * @param images The list of buffered images be compiled.
	 * @param frameDelay The uniform delay in milliseconds between frames.
	 * @throws IOException 
	 * @throws IIOException Thrown if the there are issues with reads or writes fail.
	 * @since 0.0.6
	 */
	public static byte[] toAnimatedGifBytes(List<BufferedImage> images, int frameDelay) throws IIOException, IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageOutputStream mcos = new MemoryCacheImageOutputStream(baos);
		
		GifSequenceWriter gifWriter = new GifSequenceWriter(
				mcos, BufferedImage.TYPE_INT_ARGB, frameDelay, true);
		
		for(BufferedImage image : images) {
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
	
	/**
	 * Utility method for drawing text to the client. Functionality limited to fonts with uniform character width.
	 * 
	 * @param g The Graphics object tied to the image to draw the text on.
	 * @param str The text to be drawn.
	 * @param font The font that will be used to draw the text.
	 * @param w The width of the boundaries for the text to be drawn on.
	 * @param h The height of the boundaries for the text to be drawn on.
	 * @param ox The offset in the x direction of the text to be drawn on.
	 * @param oy The offset in the y direction of the text to be drawn on.
	 * @return The height of the text drawn.
	 * @since 0.0.7
	 */
	public static int drawText(Graphics g, String str, Font font, int w, int h, int ox, int oy){
		int init_oy = oy;
		//Build the str
		g.setFont(font);
		FontMetrics metrics = g.getFontMetrics(font);
		int fh = metrics.getHeight(), fw = metrics.stringWidth("ABC abc")/7, cpw = w/fw - 1;

		//Debugging
		//g.drawRect(ox, oy, w, h);

		for(int i = 0; i < str.length();){
			String seg = str.substring(i, Math.min(i+cpw,str.length()));
			if(seg.contains("\n")){
				i += seg.indexOf("\n")+1;
				continue;
			}else if(seg.length() == cpw){
				int ii = Math.max(Math.max(Math.max(seg.lastIndexOf(" "), seg.lastIndexOf(";")), seg.lastIndexOf(",")), seg.lastIndexOf("."));
				if(ii > 0){
					i += ii;
					str = str.substring(0,i) + "\n" + str.substring(i+1,str.length());
				}
			}
			i++;
		}

		//Draw the str
		String[] lines = str.split("\n");
		for(String l : lines){
			g.setColor(Color.WHITE);
			g.drawString(l, ox, oy += fh);
			//System.out.println(l);
		}

		return oy - init_oy;
	}
	
	/**
	 * Rotates an image retaining lines and parallelism at the cost of distances and angles between objects.
	 * 
	 * @param bi The specified buffered image to undergo rotation.
	 * @param degrees The specified rotation to be applied.
	 * @since 0.0.8
	 */
	public static void rotateImageEuclidianAffine(BufferedImage bi, double degrees) {
		double rotationRequired = Math.toRadians(degrees);
		double locationX = bi.getWidth() / 2;
		double locationY = bi.getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BICUBIC);
		
		bi.getGraphics().drawImage(op.filter(bi, null), 0, 0, null);
	}
}
