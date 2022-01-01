/*  Licensing Copyright
 * 
 *  Utility class containing common file IO and manipulation tools.
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
package com.dz_fs_dev.common.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Utility class containing common file IO and manipulation tools.
 * 
 * @author DZ-FSDev
 * @since 17.0.1
 * @version 0.0.2
 */
public final class FileTools {
	private FileTools() {}

	/**
	 * Attempts to read a specified file and returns a list containing what was
	 * read.
	 * 
	 * @param path The specified path to read the file from.
	 * @return A list containing the read lines from the file.
	 * @since 0.0.2
	 */
	public static ArrayList<String> tryLoadFile(String path){
		return tryLoadFile(new File(path));
	}
	
	/**
	 * Attempts to read a specified file and returns a list containing what was
	 * read.
	 * 
	 * @param file The specified file to read.
	 * @return A list containing the read lines from the file.
	 * @since 0.0.1
	 */
	@SuppressWarnings("finally")
	public static ArrayList<String> tryLoadFile(File file){
		ArrayList<String> ret = new ArrayList<String>();

		try (
			FileReader myReader = new FileReader(file);
			BufferedReader myBufferedReader = new BufferedReader(myReader);){
			String data = myBufferedReader.readLine();
			while(data != null){
				ret.add(data);
				data = myBufferedReader.readLine();
			}
		}finally {
			return ret;
		}
	}
	
	/**
	 * Attempts to read a specified file and returns a String containing what
	 * was read.
	 * 
	 * @param file The specified file to read.
	 * @return A String containing the read lines from the file.
	 * @since 0.0.1
	 */
	@SuppressWarnings("finally")
	public static String tryReadFile(File file){
		StringBuilder ret = new StringBuilder();

		try (
			FileReader myReader = new FileReader(file);
			BufferedReader myBufferedReader = new BufferedReader(myReader);){
			String data = myBufferedReader.readLine();
			while(data != null){
				ret.append(data);
				ret.append('\n');
				data = myBufferedReader.readLine();
			}
		}finally {
			return ret.toString();
		}
	}
	
	/**
	 * Attempts to read a specified file and returns a String containing what
	 * was read.
	 * 
	 * @param path The specified path to read the file from.
	 * @return A String containing the read lines from the file.
	 * @since 0.0.2
	 */
	public static String tryReadFile(String path){
		return tryReadFile(new File(path));
	}
	
	/**
	 * Attempts to write contents to a specified file replacing existing
	 * contents; returning true on success. Missing directories will also be
	 * created.
	 * 
	 * @param file The specified file to write to.
	 * @param data The specified data to write to the file.
	 * @return True if file write was successful.
	 */
	public static boolean tryWriteToFile(File file, String data) {
		//TODO
		return true;
	}
	
	/**
	 * Attempts to write contents to a specified file appending to existing
	 * contents; returning true on success. Missing directories will also be
	 * created.
	 * 
	 * @param file The specified file to append to.
	 * @param data The specified data to append to the file.
	 * @return True if file write was successful; false otherwise.
	 */
	public static boolean appendToFile(File file, String data) {
		//TODO
		return true;
	}
}
