
public class Java100_method_Exam003 {
	
	public static int returnMethod() {
		int rst = 100;
		rst *= 100;
		return rst; //10000
	}
	
	public static void main(String[] args) {
		int rst;
		rst = returnMethod();
		
		System.out.println("메서드 호출에 따른 리턴된 값은=" + rst);
		
	}
}
