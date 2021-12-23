package com.dz_fs_dev.common.io;

import java.io.File;
import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper; 
import com.fasterxml.jackson.databind.ObjectWriter; 

/**
 * Wraps an object to allow local persistence on demand.
 * 
 * @param <T> The type of the wrapped object. The type must implement 
 * 			Serializable.
 * 
 * @author DZ-FSDev
 * @since 17.0.1
 * @version 0.0.1
 */
public class LocalPersistance<T extends Serializable>{
	private T obj;
	private File file;
	
	private static String saveDirectory = "\\data\\";
	
	/**
	 * 
	 * @param obj
	 * @param filename
	 */
	public LocalPersistance(T obj, String filename){
		this.setObj(obj);
		this.setFile(new File(saveDirectory + filename));
	}

	/**
	 * 
	 * @return
	 */
	public T getObj() {
		return obj;
	}

	/**
	 * 
	 * @param obj
	 */
	private void setObj(T obj) {
		this.obj = obj;
	}
	
	/**
	 * 
	 * @throws JsonProcessingException
	 */
	public void persist() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(this.getObj());
		
		this.getFile().mkdirs();
	}

	/**
	 * @return the file
	 */
	private File getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	private void setFile(File file) {
		this.file = file;
	}
	
	/**
	 * 
	 * @param <T>
	 * @param clazz
	 * @param file
	 * @return
	 */
	public static <T extends Serializable> LocalPersistance<T> load(Class<T> clazz, File file) {
		return new LocalPersistance<T>(null, file.getName());
	}
}
