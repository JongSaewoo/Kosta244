public class HR {
	public static void main(String[] args) {
		Employee e1;
		e1 = new Employee();
		e1.no = "2201"; e1.name = "김준용"; e1.salary = 3000;
		
		Employee e2 = new Employee("1101", "나자바", 9000);
		// e2.no = "1101"; e2.name = "나자바"; e2.salary = 9000;
		
		Employee e3 = new Employee();
		e2.no = "1301"; e3.name = "홍길동"; e3.salary = 6000;
		
		Employee e4 = new Employee("1401", "김"); //사원의 급여는 1000
		

	}

}
