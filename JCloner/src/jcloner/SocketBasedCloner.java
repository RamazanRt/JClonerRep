package jcloner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This class is network socket based cloner. Data is sent to socket and than is
 * read from socket again. Default host is localhost(127.0.0.1) and default port
 * is 14725.
 * 
 * @author Faruk BOZAN
 * @since 15.4.7
 * @version 15.4.7
 *
 */
public class SocketBasedCloner implements ICloner {

	/**
	 * Default host, localhost.
	 */
	private final String HOST = "127.0.0.1";

	/**
	 * Default port for socket.
	 */
	private final int PORT = 14725;

	/**
	 * Writes source object to socket.
	 * 
	 * @param sourceObject
	 *            Source object to clone.
	 * @return Socket based on host and port.
	 * @throws IOException
	 *             Throws when IO error occurs.
	 * @throws UnknownHostException
	 *             Throws when host name is wrong or host is unreachable.
	 */
	private Socket writeToSocket(Object sourceObject) throws UnknownHostException, IOException {
		Socket socket = new Socket(HOST, PORT);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(sourceObject);
		oos.flush();
		byte[] byteArray = baos.toByteArray();
		oos.close();
		OutputStream os = socket.getOutputStream();
		DataOutputStream out = new DataOutputStream(os);
		out.write(byteArray);
		return socket;
	}

	/**
	 * Reads data from socket and creates new object.
	 * 
	 * @param socket
	 *            Socket instance to read data.
	 * @return New object.
	 * @throws IOException
	 *             Throws when IO error occurs.
	 * @throws ClassNotFoundException
	 *             Throws when an exception occurs during class stream
	 *             operation.
	 */
	private Object readFromSocket(Socket socket) throws IOException, ClassNotFoundException {
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		byte[] newByteData = new byte[dis.available()];
		dis.read(newByteData);
		ByteArrayInputStream bais = new ByteArrayInputStream(newByteData);
		ObjectInputStream ois = new ObjectInputStream(bais);
		Object newObject = ois.readObject();
		ois.close();
		return newObject;
	}

	@Override
	public Object clone(Object sourceObject) throws ClonerException {
		try {
			Socket socket = writeToSocket(sourceObject);
			Object newObject = readFromSocket(socket);
			return newObject;
		} catch (UnknownHostException e) {
			throw new ClonerException(e);
		} catch (IOException e) {
			throw new ClonerException(e);
		} catch (ClassNotFoundException e) {
			throw new ClonerException(e);
		}
	}

}
