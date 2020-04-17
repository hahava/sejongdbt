package util;

import java.io.*;
import java.lang.reflect.Field;

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

	public static void printObject(Object instance) throws IllegalAccessException, IOException {
		Field[] fields = instance.getClass().getDeclaredFields();
		print("[");
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			String fieldName = fields[i].getName();
			Object value = fields[i].get(instance);
			print(fieldName + " : " + value);
			if (i != fields.length - 1) {
				print(", ");
			}
		}
		printLn("]");
	}

	public static void printLn(String msg) throws IOException {
		BUFFERED_WRITER.write(msg);
		BUFFERED_WRITER.newLine();
		BUFFERED_WRITER.flush();
	}

	private static void print(String msg) throws IOException {
		BUFFERED_WRITER.write(msg);
		BUFFERED_WRITER.flush();
	}

	public static void close() throws IOException {
		BUFFERED_READER.close();
		BUFFERED_WRITER.close();
	}
}