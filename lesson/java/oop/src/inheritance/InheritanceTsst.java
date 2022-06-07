package inheritance;
public class InheritanceTsst {
	public static void main(String[] args) {
		Parent p = new Parent("부모변수", 1000);
		p.pm();
		
		Child c = new Child();
//		c.c1 = "자식변수";
//		System.out.println(c.p1);
//		System.out.println(c.money);
		System.out.println("-----");
		c.cm();
		System.out.println("-----");
		c.pm();
		
		c = new Child("자식변수");
		System.out.println("-----");
		c.cm();
	}

}
