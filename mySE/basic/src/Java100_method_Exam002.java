
public class Java100_method_Exam002 {
	
	public static void plusMethod(int a, int b) {
		System.out.printf("인자로 넘겨받은 2개의 값은 %d과 %d입니다%n",a,b);
	
	int rst = a + b;
	System.out.println("두 수를 더한값은 =" + rst);
	}
	public static void main(String[] args) {
		
		int a = 100, b = 200;
		plusMethod(a,b);
	}
}
