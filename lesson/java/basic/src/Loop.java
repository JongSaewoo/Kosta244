public class Loop {
	public static void main(String[] args) {
		int score1 = 10;
		int score2 = 20;
		int score3 = 30;
		int score4 = 10;
		int totalScore = score1+score2+score3+score4;
		System.out.println(totalScore);
		
		int num;
//		num=1;
//		System.out.println(num);
//		
//		num=2;
//		System.out.println(num);
//		
//		num=3;
//		System.out.println(num);
//
//		num=4;
//		System.out.println(num);
//		
//		num=5;
//		System.out.println(num);
		
//		num=0;
//		while(num<5) {
//			num++;
//			System.out.println(num);
//		}		
//		num=1;
//		while(num<=5) {
//			System.out.println(num);
//			num++;
//		}
		
		int sum=0; //합
//		num=1;
//		sum+=num;//sum=sum+num;
//		
//		num=2;
//		sum+=num;
//		
//		num=3;
//		sum+=num;
//		
//		num=4;
//		sum+=num;
//		
//		num=5;
//		sum+=num;
		
//		num=0;
//		while(num < 100) { //1~100까지의 합:5050
//			num++;
//			sum+=num;
//		}
//		System.out.println(sum);
		
//		num=1;
//		System.out.println(num);
//		
//		num=3;
//		System.out.println(num);
//		
//		num=5;
//		System.out.println(num);
//		
//		num=7;
//		System.out.println(num);
		
//		//숫자1부터 총4개의 홀수들을 출력하시오
//		int cnt=0;//출력횟수
//		num=1;
//		while(cnt < 4) {
//			System.out.println(num);
//			num+=2;//num=num+2;
//			cnt++;
//		}
		
		//피보나치 수열 9개 출력하시오 : 1, 1, 2, 3, 5, 8, 13, 21, 34 
//		int bb=1; //이전이전수 1
//		int b=0; //이전수 0
//		int current = bb+b;	//현재수 1	
//		System.out.println(current); //1
//		
//		bb=b; //이전수를 이전이전수에 대입 bb=0과 같음
//		b=current;//현재수를 이전수에 대입 b=1
//		current = bb+b; //현재수 1
//		System.out.println(current); //1
//		
//		bb=b; //이전수를 이전이전수에 대입 bb=1과 같음
//		b=current;//현재수를 이전수에 대입 b=1
//		current = bb+b; //현재수 2
//		System.out.println(current); //2
//		
//		
//		bb=b; //이전수를 이전이전수에 대입 bb=1과 같음
//		b=current;//현재수를 이전수에 대입 b=2
//		current = bb+b; //현재수 3
//		System.out.println(current); //3
			
		for(int bb=1, b=0, cnt=0; cnt<9; cnt++) {
			int current = bb+b;
			System.out.println(current);
			bb=b;
			b=current;
		}
		
		System.out.println("중복되지 않는 로또숫자[1~45]를 6개 출력하기");
		int []lotto = new int [6];
		for(int cnt=0; cnt<lotto.length; cnt++) {
			lotto[cnt] = (int)(Math.random()*45)+1; // 0.0<= r <1를   1<= r <46로 바꾸기
			//System.out.println(rand);
			for(int index=0; index<cnt; index++) {
				if(lotto[index] == lotto[cnt]) { //중복
					cnt--;
					break;
				}
			}
		}
		for(int i=0; i<lotto.length; i++) {
			System.out.println(lotto[i]);
		}
	}
}