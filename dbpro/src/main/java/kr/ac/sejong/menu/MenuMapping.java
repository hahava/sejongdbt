package kr.ac.sejong.menu;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 사용자 입력에 대한 응답을 반환하는 어노테이션
 *
 * @author hahava
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MenuMapping {
	String value();

	boolean onlyForAdmin() default false;
}
