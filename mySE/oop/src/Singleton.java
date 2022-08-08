class Single{
	private static Single s = new Single();
	private Single() {}
	public static Single getInstance(){
		//return new Single();
		return s;
	}
}

public class Singleton {
	public static void main(String[] args) {
//		Single s1 = new Single();
		Single s1 = Single.getInstance();
		Single s2 = Single.getInstance();
		System.out.println(s1 == s2); //true
	}
}