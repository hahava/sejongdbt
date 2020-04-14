package learningtest;

import menu.MenuMapper;
import org.junit.Test;
import org.reflections.Reflections;

import java.util.Set;

/**
 *
 * 	<h1>org.reflection 패키지의 사용용도와 방법을 위한 테스트 클래스</h1>
 *
 *	<p>
 *		애플리케이션 실행 시, Annotation 이 선언된 클래스의 정보를 가져오기 위해 사용했습니다.
 *		Class 객체를 이용시 현재 실행하고 있는 객체의 정보만을 가져오지만, 본 라이브러리를 이용시
 *		동적으로 클래스 정보를 가져 올 수 있습니다.
 *  </p>
 *
 *  <b>
 *      주의 : 하기 테스트 이외에 메서드를 사용하지 않았습니다.
 *  </b>
 *
 * @see org.reflections
 * @see menu.MenuSelector
 * @see menu.MenuMapper
 */
public class ReflectionTest {

	/**
	 * 탐색을 원하는 Annotation 타입을 given에 설정합니다.
	 * 이후 클래스 정보를 출력할 때, 매칭 되는 결과 값을 확인합니다.
	 */
	@Test
	public void getAnnotatedClasses() {

		//given
		Class annotation = MenuMapper.class;

		//when
		Reflections reflections = new Reflections();
		Set<Class<?>> cls = reflections.getTypesAnnotatedWith(annotation);

		//then
		cls.stream().forEach(clz -> System.out.println(clz.getName()));
	}

}
