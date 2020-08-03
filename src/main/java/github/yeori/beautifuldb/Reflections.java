package github.yeori.beautifuldb;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reflections {

	public static <T> void callSetter(Class<T> clazz, T instance, String propName, Object value) {
		if (!clazz.isAssignableFrom(instance.getClass())) {
			throw new BeautDbException(500, "SERVR_ERROR", "type mismatch [%s] vs [%s]", clazz.getName(), instance.getClass().getName());
		}
		
		Method [] m = findAccessors(clazz, propName);
		if (m[1] == null) {
			throw new BeautDbException(400, "NO_SUCH_COLUMN_PROP", "cannot find setter for property [%s]", propName);
		}
		try {
			m[1].invoke(instance, value);
		} catch (Exception e) {
			throw new BeautDbException(500, Error.SERVER_ERROR.name(), "fail to execute call method [%s]", m[1].getName());
		}
	}
	
	static Method[] findAccessors(Class<?> clazz, String propName) {
		List<Field> fields = asList(clazz.getDeclaredFields());
		Map<String, Method> methods = asMap(clazz.getDeclaredMethods());
		Method [] m = {null, null}; // [getter, setter]
		for (Field f : fields) {
			String nm = f.getName();
			if (!nm.equals(propName)) {
				continue;
			}
			String Fname = nm.substring(0, 1).toUpperCase() + nm.substring(1); // name -> Name
			String getter0 = "get" + Fname;
			String getter1 = "is" + Fname;
			String setter0 = "set" + Fname;
			Method getter = methods.get(getter0);
			if (getter == null) {
				getter = methods.get(getter1);
			}
			if (getter != null) {
				m[0] = getter;
			}
			Method setter = methods.get(setter0);
			if (setter != null) {
				m[1] = setter;
			}
			if (m[0] != null || m[1] != null) {
				break;
			}
		}
		return m;
	}
	
	private static Map<String, Method> asMap(Method[] methods) {
		if (methods == null) {
			return Collections.emptyMap();
		}
		Map<String, Method> map = new HashMap<>();
		for (Method m : methods) {
			String mname = m.getName();
			map.put(mname, m);
		}
		return map;
	}
	

	private static <T> List<T> asList(T[] fields) {
		return fields == null ? Collections.emptyList() : Arrays.asList(fields);
	}

}
