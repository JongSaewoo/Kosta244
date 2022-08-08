package inheritance;

public class Child extends Parent {
	private String c1;
	public Child() {}
	public Child(String c1){
		super("자식에서 설정한 부모변수", 10000);
		this.c1 = c1;
	}
	public void cm() {
		System.out.println("자식의 기능입니다");
		System.out.println("c1=" + c1);
		pm();
		//System.out.println("p1=" + p1); 
		//자식이 부모의 private에 직접적인 접근 불가
	}
}
