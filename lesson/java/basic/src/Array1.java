import javax.sound.midi.SysexMessage;

public class Array1 {

	public static void main(String[] args) {
		java.util.Scanner sc = new java.util.Scanner(System.in);
//		int []score; //배열선언
//		score = new int[7]; //배열생성
//		
//		for(int index=0; index<score.length; index++) {
//			System.out.println("점수를 입력하세요:");
//			score[index] = sc.nextInt();
//		}
//		
//		int totalScore; //총점
//
//		totalScore = 0;
//		for(int index=0; index<score.length; index++) {
//			totalScore += score[index];
//		}
//		System.out.println(totalScore);
//		System.out.println((float)totalScore/score.length); //평균값은 정확한 실수값이 되도록 출력하세요

		//12지 동물등
//		String []zodiacSign; 
//		zodiacSign = new String[12];
//		zodiacSign[0] = "원숭이";
//		zodiacSign[1] = "닭";
//		zodiacSign[2] = "개";
//		zodiacSign[3] = "돼지";
//		//:
//		zodiacSign[11] = "양";
		
		//배열 초기화: 선언 + 생성 + 대입
		//String []zodiaSign = {"원숭이", "닭", "개", "돼지", "쥐", "소", "호랑이", "토끼", "용", "뱀", "말", "양"};
		
		//배열초기화
		String []zodiacSign; //선언
		//생성+대입
		zodiacSign = new String[] {"원숭이", "닭", "개", "돼지", "쥐", "소", "호랑이", "토끼", "용", "뱀", "말", "양"};
		
//		int year = 2022;
//		System.out.println(zodiacSign[year%12]);
		
		int year = 2022;
		int ganzi = year%zodiacSign.length;
		System.out.println(zodiacSign[ganzi]);
		
		//nums배열의 값은 1부터 10사이의 숫자들이다.
		//숫자들의 출현횟수를 출력하시오
		//1의 출현횟수: 3회
		//2의 출현횟수: 1회
		//3의 출현횟수: 2회
		//4의 출현횟수: 0회
		//5의 출현횟수: 0회
		//6의 출현횟수: 1회
		//7의 출현횟수: 1회
		//8의 출현횟수: 0회
		//9의 출현횟수: 1회
		//10의 출현횟수: 1회
		int []nums = {1, 10, 3, 3, 1, 2, 7, 1, 6, 9};
		int []cnts; //출현횟수 cnts[0]는 숫자1의 출현횟수가 저장될 공간
					//         cnts[1]는 숫자2의 "
		        	//		   cnts[2]는 숫자3의 "
		cnts = new int[10];
		for(int index=0; index<nums.length; index++) {
			cnts[nums[index]-1]++;
		}
		
		for(int index=0; index<cnts.length; index++) {
			System.out.println( (index+1)+ "의 출현횟수 :" + cnts[index] + "회");
		}
		
		int []nums1 = {9, 3, 1, 4, 5};
		//num1배열의 최소값을 찾아 출력하시오
		int min = nums1[0];
		
		for(int i=1; i<nums1.length; i++) {
			if(min>nums1[i]) {
				min = nums1[i];
			}
		}
		System.out.println("최소값은?" + min);
		
	}

}
