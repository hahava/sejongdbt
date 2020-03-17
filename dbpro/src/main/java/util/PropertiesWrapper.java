package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author hahava
 *
 * 프로파일별 환경변수를 java 에서 사용하기 위함
 * */
public final class PropertiesWrapper {

	private static final String RESOURCE_FILE = "/application.properties";
	private static final int ABNORMAL_EXIT = 1;

	private static Properties properties;

	private PropertiesWrapper() {
	}

	/**
	 * 싱글톤 사용을 위한 Properties 객체 생성 생성자
	 *
	 * @return resource 의 application.properties 를 스트림하는 properties 객체
	 * @throws IOException properties 가 존재하지 않거나 정상적으로 파싱이 되지 않는다면 프로그램을 종료
	 */
	public static Properties getInstance() {
		if (properties == null) {
			InputStream inputStream = PropertiesWrapper.class.getResourceAsStream(RESOURCE_FILE);
			properties = new Properties();
			try {
				properties.load(inputStream);
				inputStream.close();
			} catch (IOException ie) {
				ie.printStackTrace();
				System.exit(ABNORMAL_EXIT);
			}
		}
		return properties;
	}

}
