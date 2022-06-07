import java.util.Calendar;
import java.util.Map;
public class Function {
//	public static void method6(zodiac함수) {
//		
//	}
	public static void method5(Map map) {}
	public static void method4(int []arr) {}
	public static void method3() {
		System.out.println("파일이 만들어집니다");
		
	}
	public static String method2() {
		Calendar c = Calendar.getInstance();
		switch(c.get(Calendar.AM_PM)) {
		case Calendar.AM:
			return "오전";
		default:
			return "오후";
		}
		
	}
	public static void method1(int i) {
		System.out.println(i%2==0?"짝수" : "홀수");
	}
	public static String zodiac(int year) {
		String []zodiacSign; //선언
		//생성+대입
		zodiacSign = new String[] {"원숭이", "닭", "개", "돼지", "쥐", "소", "호랑이", "토끼", "용", "뱀", "말", "양" };
		int ganzi = year%zodiacSign.length;
		//System.out.println(zodiacSign[ganzi]);
		return zodiacSign[ganzi];
	}
	
	public static void main(String[] args) {
		method3();
		
		String ampm = method2();
		System.out.println(ampm);
		
		method1(5);
		
		
		
		java.util.Scanner sc = new java.util.Scanner(System.in);
		System.out.println("출생년도를 입력하세요:");
		int year = sc.nextInt();
		String result;
		result = zodiac(year);
		System.out.println(result);
	}
}