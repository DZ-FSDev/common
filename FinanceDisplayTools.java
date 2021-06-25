package com.dzappz.common;

/**
 * @author DzAppz
 * @version 0.1
 */

public final class FinanceDisplayTools {
	
	/**
	 * Calculates the point on a line segment which a value exists given the terminal ends representing a min and max value. Useful for linear scaled data.
	 * @param value	The value that should be placed between the linear line segment. 
	 * @param min_value The value that corresponds to the start of the line segment.
	 * @param max_value The value that corresponds to the end of the line segment.
	 * @param startPixel The start of the line segment.
	 * @param endPixel The end of the line segment.
	 * @return The point on the line segment which corresponds to the linear position of the value passed.
	 */
	public static int slider(double value, double min_value, double max_value, double startPixel, double endPixel){
		return (int)(startPixel + (endPixel-startPixel)*(value-min_value)/(max_value-min_value));
	}
}
