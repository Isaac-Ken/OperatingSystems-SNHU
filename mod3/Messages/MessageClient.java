/**
 * Message client.
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Eighth Edition
 * Copyright John Wiley & Sons - 2010.
 */
 
 
import java.net.*;
import java.io.*;

public class MessageClient
{
	public static final int PORT = 6100;
	public static final String host = "127.0.0.1";

	public static void main(String[] args) throws IOException {
		Socket sock = null;
		
		if (args.length != 1) {
			System.err.println("Usage: java MessageClient <message>");
			System.exit(0);
		}
		
		try {
         /* Open a port and stream output  */
			sock = new Socket(host, PORT);

			PrintWriter pout = new PrintWriter(sock.getOutputStream(),true);

			pout.println(args[0]);

			/* Input data and print charter,digit count */
			ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());

			Message message = (Message) ois.readObject();
			System.out.println(message.getCharacterCount());
			System.out.println(message.getDigitCount());
		}
		catch (IOException ioe) {
			System.err.println(ioe);
		}
		catch (ClassNotFoundException cnfe) {
			System.err.println(cnfe);
		}
          finally {
          	sock.close();
          }
	}
}
