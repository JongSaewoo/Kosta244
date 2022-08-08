package inheritance;

public class Parent {
	private String p1;
	private int money;
	public Parent(){}
	public Parent(String p1, int money){
		this.p1 = p1;
		this.money = money;
	}
	public void pm() {
		System.out.println("부모의 기능입니다");
		System.out.println("p1=" + p1 + ", money=" + money);
	}
}
