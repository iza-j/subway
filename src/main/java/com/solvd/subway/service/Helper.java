package com.solvd.subway.service;

import java.lang.reflect.*;
	import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.stream.IntStream.range;
import static java.util.EnumSet.range;

public class Helper {

	public static <T> void viewDetails(T someObject) throws InvocationTargetException, IllegalAccessException {
		for (Method method : someObject.getClass().getDeclaredMethods()) {
			if (method.getName().toString().startsWith("get")) {
				if (method.getGenericReturnType().toString().contains("List")) {
					List list = new ArrayList<>(Arrays.asList(method.invoke(someObject)));
					viewDetails(list);
//					list.forEach(i -> System.out.println(i.getClass()));
				}
				System.out.print(new StringBuilder()
					.append(method.getName().toString().replace("get", ""))
					.append(" : ")
					.append(method.invoke(someObject))
				);
			}
		}
	}

	public static <T> void viewDetails(List<T> someList) throws InvocationTargetException, IllegalAccessException {
		for (Method method : someList.getClass().getDeclaredMethods()) {
			if (method.getName().toString().startsWith("get")) {
				viewDetails(someList);
			}
		}
	}

}