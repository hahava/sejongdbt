package kr.ac.sejong.menu;

import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class MenuSelector {

	private static MenuSelector menuSelector;

	public static MenuSelector getInstance() {
		if (menuSelector == null) {
			synchronized (MenuSelector.class) {
				menuSelector = new MenuSelector();
			}
		}
		return menuSelector;
	}

	public void select(String requestMenu) {
		for (Class cls : getMenuMapperAnnotatedClasses()) {
			for (Method method : getMenuMappingMethods(cls.getDeclaredMethods())) {
				String annotationValue = method.getAnnotation(MenuMapping.class).value();
				if (StringUtils.equals(annotationValue, requestMenu)) {
					try {
						method.invoke(cls.newInstance());
					} catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private List<Method> getMenuMappingMethods(Method[] declaredMethods) {
		return Arrays.stream(declaredMethods)
			.filter(method -> method.getAnnotation(MenuMapping.class) != null)
			.collect(toList());
	}

	private Set<Class<?>> getMenuMapperAnnotatedClasses() {
		Reflections reflections = new Reflections();
		return reflections.getTypesAnnotatedWith(MenuMapper.class);
	}
}
