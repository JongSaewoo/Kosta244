public class Array2 {
	public static void main(String[] args) {
		int [][]arr = new int[3][4];
		
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {	
				arr[i][j] = j+1;
			}
		}
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {	
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
		System.out.println("------");
		
		//1  2  3  4
		//5  6  7  8
		//9 10 11 12
		int arr2[][] = new int[3][4];
		int num2 = 1;
		for (int i=0; i<arr2.length; i++) {
			for (int j=0; j<arr2[0].length; j++, num2++) {
				arr2[i][j] = num2;
			}
		}
		for (int i=0; i<arr2.length; i++) {
			for (int j=0; j<arr2[0].length; j++) {
				System.out.print(arr2[i][j]+" "); 
			}
			System.out.println();
		}
		System.out.println("------");	
		
		//1  4  7  10
		//2  5  8  11 
		//3  6  9  12
		int arr3[][] = new int[3][4];
		int num3 = 1;
		for (int col=0; col<arr3[0].length; col++) {
			for (int row=0; row<arr3.length; row++, num3++) {
				arr3[row][col] = num3;
			}
		}
		for (int row=0; row<arr3.length; row++) {
			for (int col=0; col<arr3[0].length; col++) {
				System.out.print(arr3[row][col]+" "); 
			}
			System.out.println();
		}
		System.out.println("------");	
		
		//A B C D
		//E F G H
		//I J K L
		char arr4[][] = new char[3][4];
		char alpha = 'A';
		for (int i=0; i<arr4.length; i++) {
			for (int j=0; j<arr4[0].length; j++, alpha++) {
				arr4[i][j] = alpha;
			}
		}
		for (int i=0; i<arr4.length; i++) {
			for (int j=0; j<arr4[0].length; j++) {
				System.out.print(arr4[i][j]+" "); 
			}
			System.out.println();
		}
		System.out.println("------");	
		
		//1 
		//2 3 
		//4 5 6 
		//7 8 9 10
		int [][]arr5 = new int[4][];
//		arr5[0] = new int[1];
//		arr5[1] = new int[2];
//		arr5[2] = new int[3];
//		arr5[3] = new int[4];
		
		int num5 = 1;
		for(int row=0; row<arr5.length; row++) {
			arr5[row] = new int[row+1]; //열 생성
			//값 대입
			for(int col=0; col<arr5[row].length; col++, num5++) {
				arr5[row][col] = num5;
			}
		}
		for(int row=0; row<arr5.length; row++) {
			for(int col=0; col<arr5[row].length; col++) {
				System.out.print(arr5[row][col]);
			}
			System.out.println();
		}
		System.out.println("------");	
		
//		int[][]scores = new int[5][3];
//		scores[0][0] = 9;
//		scores[0][1] = 8;
//		scores[0][2] = 5;
		int [][]scores = { {9, 8, 5},      //첫번째 학생의 점수
				           {10, 3, 5},     //두번째 학생의 점수
				           {7, 10, 8},     //세번째 학생의 점수
				           {3, 2, 1},      //네번째 학생의 점수
				           {5, 6, 7}       //다섯번째 학생의 점수
				        }; //배열초기화
		//각 학생의 총점과 평균을 계산하시오
		//int totalScore = scores[0][0] + scores[0][1] + scores[0][2];
		
//		int totalScore = 0;
//			for(int col = 0; col < scores[0].length col++) {
//				totalScore += scores[0][col];
//			}
//			float avg = (float)totalScore / scores[0].length;
		//바로 위 코드를 이용하여 총 5명의 총점과 평균을 구하면 아래와 같음
		
		for(int row = 0; row < scores.length; row++) {
			int totalScore = 0;
			for(int col = 0; col < scores[0].length; col++) {
				totalScore += scores[row][col];
			}
			float avg = (float)totalScore / scores[0].length;
			System.out.println((row + 1) + " 번 학생의 총점:" + totalScore + ", 평균:" + avg);
		}
		//1번학생의 총점은   이고 평균은    이다
		//2번학생의 총점은   이고 평균은    이다
		//3번학생의 총점은   이고 평균은    이다
		//4번학생의 총점은   이고 평균은    이다
		//5번학생의 총점은   이고 평균은    이다
		
//		국어과목의 평균은
//		영어과목의 평균은
//		수학과목의 평균은
		
//		String []subjects = {"국어", "영어", "수학"};
//		String []subjects = { "국어", "영어", "수학"};
//		int []subjectTotalScores = new int[subjectCnt];		
//		
//		for(int row=0; row<studentCnt; row++) {
//			for(int col=0; col < subjectCnt; col++) {
//				subjectTotalScores[col] += scores[row][col];
//			}
//		}
//		for(int i=0; i<subjectCnt; i++) {
//			float subjectAvg = (float)subjectTotalScores[i]/studentCnt;
//			System.out.println(subjects[i] + "과목의 평균은 " + subjectAvg);
//		}
//		
//	}
		
		for(int col = 0; col < scores[0].length; col++) {
			int totalScore = 0;
			for(int row = 0; row < scores.length; row++) {
				totalScore += scores[row][col];
			}
			float avg = (float)totalScore / scores.length;
			if(col==0) {
				System.out.println("국어과목의 평균은:" + avg);
			} else if(col==1) {
				System.out.println("영어과목의 평균은:" + avg);
			} else {
				System.out.println("수학과목의 평균은:" + avg);
			}
		}
			
			//arr6배열값을 회전시켜서 arr7배열에 저장하기
			int [][]arr6 = { {1,2,3,4}, 
					         {5,6,7,8}, 
					         {9,10,11,12}, 
					         {13,14,15,16} };
			int [][]arr7 = new int[4][4];
//			for(int i = 0; i < arr6.length; i++) {
//				for(int j = 0; j < arr6[i].length; j++) {
//					arr7[j][arr6[i].length-i-1] = arr6[i][j];
//				}
//			}
//			
//			for(int i = 0; i < arr6.length; i++) {
//				for(int j = 0; j < arr6[i].length; j++) {
//					System.out.print(arr7[i][j] + " ");
//				}
//				System.out.println();
//			}

		for(int i = 0; i < arr6.length; i++ ) {
			for(int j = 3; j > -1; j-- ) {
				arr7[i][j] =  arr6[j][i];
				System.out.print(arr7[i][j] + " ");
			}
			System.out.println();
				
		}
		
		
		
		
		
	}
}