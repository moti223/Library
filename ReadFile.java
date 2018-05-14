import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

// a class that enables reading from a file.
public class ReadFile {
	private String _path;

	public ReadFile(String path) {
		_path = path;
	}

	public String[] OpenFile() throws IOException {
		FileReader f = new FileReader(_path);
		BufferedReader textReader = new BufferedReader(f);

		String[] textData = new String[25];
		for (int i = 0; i < 25; i++)
			textData[i] = textReader.readLine();
		textReader.close();
		return textData;
	}
}
