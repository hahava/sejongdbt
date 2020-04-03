package util;

import java.io.*;

public class ConsoleUtil {

	private static final BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));
	private static final BufferedWriter BUFFERED_WRITER = new BufferedWriter(new OutputStreamWriter(System.out));
	public static final String HORIZONTAL_RULE = "=================================";

	public static String readString() throws IOException {
		String input = BUFFERED_READER.readLine();
		return input;
	}

	public static String readString(String msg) throws IOException {
		printLn(msg);
		String input = BUFFERED_READER.readLine();
		return input;
	}

	public static int readInt(String msg) throws IOException {
		printLn(msg);
		int parseInt = 0;
		boolean success = false;
		while (success != true) {
			String input = BUFFERED_READER.readLine();
			try {
				parseInt = Integer.parseInt(input);
				success = true;
			} catch (NumberFormatException e) {
				printLn("숫자만 입력해주세요!");
			}
		}
		return parseInt;
	}

	public static void printLn(String msg) throws IOException {
		BUFFERED_WRITER.write(msg);
		BUFFERED_WRITER.newLine();
		BUFFERED_WRITER.flush();
	}

	public static void close() throws IOException {
		BUFFERED_READER.close();
		BUFFERED_WRITER.close();
	}
}
