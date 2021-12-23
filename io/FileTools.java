package com.dz_fs_dev.common.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * 
 * @author DZ-FSDev
 * @since 17.0.1
 * @version 0.0.1
 */
public final class FileTools {
	private FileTools() {}

	/**
	 * 
	 * 
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("finally")
	public static ArrayList<String> loadFile(String path){
		ArrayList<String> ret = new ArrayList<String>();

		try (
			FileReader myReader = new FileReader(path);
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
}
