package com.solvd.subway.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Helper {

	public static <T> void viewDetails(T someObject) throws InvocationTargetException, IllegalAccessException {
		if (someObject != null) {
			for (Method method : someObject.getClass().getDeclaredMethods()) {
				if (method.getName().toString().startsWith("get")) {
					if (method.getGenericReturnType().toString().contains("List")) {
						List list = new ArrayList<>(Arrays.asList(method.invoke(someObject)));
						viewDetails(list);
					}
					System.out.println(new StringBuilder()
						.append(method.getName().toString().replace("get", ""))
						.append(" : ")
						.append(method.invoke(someObject))
					);
				}
			}
		}
	}

	public static <T> void viewDetails(List<T> someList) throws InvocationTargetException, IllegalAccessException {
		for (Method method : someList.getClass().getDeclaredMethods()) {
			if (method.getName().toString().startsWith("get")) {
				someList.forEach(i -> {
					try {
						viewDetails(i);
					} catch (InvocationTargetException e) {
						throw new RuntimeException(e);
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					}
				});
			}
		}
	}
}