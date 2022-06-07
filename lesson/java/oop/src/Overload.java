public class Overload {
	public static void plus(int a, int b, int c) {
		int result = a + b + c;
		System.out.println(result);
	}
	public static void plus(int a, int b) {
		int result = a + b;
		System.out.println(result);
	}
	public static void plus(double a, double b) {
		double result = a + b;
		System.out.println(result);
	}
	public static void main(String[] args) {
		System.out.println("ABC"); //ABC
		System.out.println(1+2);   //3 
		System.out.println(1>2);   //false
		
		plus(1,2,3); // 6(1+2+3)
		plus(1,2);   // 3(1+2)
		plus(1.2, 3.4); //4.6(1.2+3.4)
	}
}
