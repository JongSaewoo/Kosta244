class A{
	int iv;
	static int sv;
	void m() {
		System.out.println("non-static method입니다 iv=" + this.iv);
		System.out.println("sv=" + this.sv);
		System.out.println("A.sv=" + A.sv);
		sm(); //static method 호출가능w
		}
	static void sm() {
		System.out.println("static method입니다 sv=" + A.sv);
//		System.out.println("iv=" + this.iv);
//		System.out.println("iv=" + iv);
//		m(); //this.m()
	}
}
public class StaticTest {
	public static void main(String[] args) {
		A a1, a2;
		a1 = new A();
		a2 = new A();
		a1.iv++;
		a1.sv++;
		
		System.out.println(a2.iv); //0
		System.out.println(a2.sv); //1
		System.out.println(A.sv);  //1
		
		a1.m();
		//A.sm();
		
		a1.sm();
		A.sm();
	}

}
