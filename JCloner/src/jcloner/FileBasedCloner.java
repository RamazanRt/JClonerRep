package jcloner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class is file based object cloner. Source object is written to a file
 * and then is read from file again.
 * 
 * @author Faruk BOZAN
 * @since 15.4.5
 * @version 15.4.5
 *
 */
public class FileBasedCloner implements ICloner {

	/**
	 * File name for clone.
	 */
	private final String FILE_NAME = "cloner.jcloner";

	/**
	 * Writes source object to file.
	 * 
	 * @param sourceObject
	 *            Source object to clone.
	 * @return File that source object is written.
	 * @throws IOException
	 *             Throws when an exception occurs during stream operation.
	 */
	private File writeToFile(Object sourceObject) throws IOException {
		File cloneFile = new File(FILE_NAME);
		cloneFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(cloneFile);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(sourceObject);
		oos.flush();
		oos.close();
		return cloneFile;
	}

	/**
	 * Reads source object from file to clone.
	 * 
	 * @param cloneFile
	 *            File that source object is written.
	 * @return New object based on file data.
	 * @throws IOException
	 *             Throws when an exception occurs during stream operation.
	 * @throws ClassNotFoundException
	 *             Throws when an exception occurs during class stream
	 *             operation.
	 */
	private Object readFromFile(File cloneFile) throws IOException, ClassNotFoundException {
		if (cloneFile.exists() == false)
			throw new FileNotFoundException(FILE_NAME.concat(" not found!"));
		FileInputStream fis = new FileInputStream(cloneFile);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object newObject = ois.readObject();
		ois.close();
		return newObject;
	}

	@Override
	public Object clone(Object sourceObject) throws ClonerException {
		try {
			File cloneFile = writeToFile(sourceObject);
			Object newObject = readFromFile(cloneFile);
			return newObject;
		} catch (IOException e) {
			throw new ClonerException(e);
		} catch (ClassNotFoundException e) {
			throw new ClonerException(e);
		}
	}

}
