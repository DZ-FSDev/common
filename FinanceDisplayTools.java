package com.dzappz.common;

/**
 * @author DzAppz
 * @version 0.1
 */

public final class FinanceDisplayTools {
	
	/**
	 * Calculates the point on a line segment which a value exists given the terminal ends representing a min and max value. Used for linear scaled data in 1D.
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
	
	/**
	 * Calculates the point on a line segment which a value exists given the terminal ends representing a min and max value. Used for logarithmic scaled data in 1D.
	 * @param value	The value that should be placed between the linear line segment. 
	 * @param min_value The value that corresponds to the start of the line segment.
	 * @param max_value The value that corresponds to the end of the line segment.
	 * @param startPixel The start of the line segment.
	 * @param endPixel The end of the line segment.
	 * @return The point on the line segment which corresponds to the logarithmic position of the value passed.
	 */
	public static int logSlider(double value, double min_value, double max_value, double startPixel, double endPixel){
		value=Math.log(value);min_value=Math.log(min_value);max_value=Math.log(max_value);
		return (int)(startPixel + (endPixel-startPixel)*(value-min_value)/(max_value-min_value));
	}
	
	/**
	 * Formats a double precision value into a string up to a specified precision. Rounds down.
	 * @param d The double precision value to be formatted.
	 * @param prec The number of decimal precision to return.
	 * @return The formatted string representation.
	 */
	public static String formatDbl(double d, int prec) {
		return String.format("%." + prec + "f",d);
	}
}
