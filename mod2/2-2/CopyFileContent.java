import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyFileContent {

	public static void main(String[] args) {

		/*  Original File */
		File sourceFile = new File("file1.txt");

		/* Copy File */
		File destFile = new File("file2.txt");
		
		/* If Copy File does not exist make one */
		if (!destFile.exists()) {
			try {
				destFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		InputStream input = null;
		OutputStream output = null;

		try {

			/* Input Orignal File */
			input = new FileInputStream(sourceFile);

			/* Write Origanl File to Copy */
			output = new FileOutputStream(destFile);

			byte[] buf = new byte[1024];
			int bytesRead;

			while ((bytesRead = input.read(buf)) > 0) {
				output.write(buf, 0, bytesRead);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			try {

				if (null != input) {
					input.close();
				}
				
				if (null != output) {
					output.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}