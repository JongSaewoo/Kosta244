package inheritance;
class PP{
	int ppv;
	String sv;
}
class P extends PP{
	int pv;
}
class C extends P{
	int cv;
	int sv;
	void c() {
		System.out.println(super.sv); //null
		System.out.println(super.pv); //0
	}
}
public class VariableDup {
	public static void main(String[] args) {
		C c = new C();
		System.out.println(c.sv);
		
//		System.out.println(c.super.sv);
		c.c();
	}

}
