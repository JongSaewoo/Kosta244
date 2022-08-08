package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

import com.my.dto.Product;

public class ReflectionTest {
	public static void reflect(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class clazz = Class.forName(className);
		Object obj = clazz.newInstance();
		Method[] methodArr = clazz.getDeclaredMethods();
		for(int i=0; i<methodArr.length; i++) {
			Method m = methodArr[i];
			if(m.getName().equals("toString")) {
				Object returnValue = m.invoke(obj);
				System.out.println(returnValue);
			}
		}
	}
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		Product p = new Product("D0001", "아메리카노", 1500); 
//		System.out.println(p.toString());
		
		Scanner sc = new Scanner(System.in);
		System.out.print("클래스이름을 입력하세요ex)java.lang.String");
		String className = sc.nextLine();
		reflect(className);
		
	}

}
