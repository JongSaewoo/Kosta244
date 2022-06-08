package world;
import world.asia.Korea;
import world.asia.Japan;
//import world.asia.*; 이렇게 하면 world.asia에 
//있는 클래스를 전체 가져온다
import java.util.Date;
import java.util.Scanner;
//import java.sql.Date;
//imort java.lang.String; //컴파일시에 자동포함

public class CountryTest {
	public static void main(String[] args) {
//		world.asia.Korea k;
//		k = new world.asia.Korea();
		
		Korea k;
		k = new Korea();
		
		Japan j = new Japan();
		
		Date d1 = new Date();
		Scanner sc = new Scanner(System.in);
		
		java.sql.Date d2;
		
//		k.language = "일본어";
//		k.capital = "평양";
//		k.population = -1;
		k.setPopulation(-1);
	}
}
