
public class Java100_method_Exam004 {
	
	public static String capitalMethod(String str) {
		String ret = str.toUpperCase();
		return ret;
	}
	
	public static void main(String[] args) {
		
		String rst;
		
		rst = capitalMethod("korea");
		System.out.println("입력한 소문자의 대문자는 =" + rst);
		
	}

}
