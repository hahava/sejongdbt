package menu;

import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MenuSelector {

	private static MenuSelector menuSelector;

	public static MenuSelector getInstance() {
		if (menuSelector == null) {
			menuSelector = new MenuSelector();
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
		List<Method> list = new LinkedList<>();
		for (Method method : declaredMethods) {
			if (method.getAnnotation(MenuMapping.class) != null) {
				list.add(method);
			}
		}
		return list;
	}

	private Set<Class<?>> getMenuMapperAnnotatedClasses() {
		Reflections reflections = new Reflections();
		return reflections.getTypesAnnotatedWith(MenuMapper.class);
	}

}
