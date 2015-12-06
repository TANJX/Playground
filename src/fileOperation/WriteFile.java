package fileOperation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteFile {
	public static void write(String address, String fileName, String text) {

		File file = new File(address + fileName);
		try (FileOutputStream fop = new FileOutputStream(file)) {

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// get the content in bytes
			byte[] contentInBytes = text.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
