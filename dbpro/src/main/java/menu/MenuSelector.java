package menu;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MenuSelector {
	public static void select(String requestMenu) {
		Method[] methods = MenuSelector.class.getMethods();
		for (Method method : methods) {
			if (method.getAnnotation(MenuMapping.class) != null) {
				String annotationValue = method.getAnnotation(MenuMapping.class).value();
				if (StringUtils.equals(annotationValue, requestMenu)) {
					Class cls = MenuSelector.class;
					try {
						method.invoke(cls.newInstance());
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
