public class StringEx {
	public static void main(String[] args) {
		String s1 = new String("가나다");
		String s2 = new String("가나다");
		String s3 = "가나다";
		String s4 = "가나다";
		System.out.println(s1 == s2); //false
		System.out.println(s1 == s3); //false
		System.out.println(s3 == s4); //true
		
		System.out.println(s1.equals(s2));//true
		System.out.println(s1.equals(s3));//true
		System.out.println(s3.equals(s4));//true
		
		System.out.println(s1+"라마"); //가나다라마
		System.out.println(s1); //가나다
		System.out.println(s1.substring(1, 3));
		s1 += "라마"; //s1 = s1 + "라마"; //가나다라마
		char c = s1.charAt(0);
		System.out.println(c); //가
		//System.out.println(s1.charAt(-1));
		int size = s1.length(); //배열의 길이 : 참조변수명.length
		System.out.println(size); //5
		
		//Palindrome문자열 : 가나다나가  ABBA 
		String palindrome = "가나다나가";
		//TODO
		//palindrome 문자열입니다가 출력
		
		//palindrome = "ABBA";
		//TODO
		//palindrome 문자열입니다가 출력
		
		//palindrome = "ABCD";
		int length = palindrome.length();
		int half = length / 2;
		int i = 0;
		for (int j = length - 1; i < half; i++, j--) {
			if (palindrome.charAt(i) != palindrome.charAt(j)) {
				break;
			}
		}
		if (i == half) {
			System.out.println("palindrome 문자열입니다.");
		}else {
			System.out.println("palindrome 문자열이아닙니다.");
		}
		
		String str = "https://news.naver.com/main/clusterArticles.naver?id=c_202205031110_00000070&mode=LSD&mid=shm&sid1=105&oid=018&aid=0005206604";
		//str = "https://www.naver.com";
		String hostName; //str의 host : news.naver.com
		String path; //str의 path : main/clusterArticles.naver
		String queryString; //str의 querystring : id=c_202205031110_00000070&mode=LSD&mid=shm&sid1=105&oid=018&aid=0005206604
		
		String regex = "\\?";
		int limit = 2;
		String[]arr = str.split(regex, limit); //?
		System.out.println("arr[0]:" + arr[0]);
		if(arr.length >= 2) {
			System.out.println("arr[1]:" + arr[1]);
		}		
		
		String hostNameNPath = arr[0];  // https://news.naver.com/main/clusterArticles.naver
		                                // https://www.naver.com
		int beginIndex = hostNameNPath.indexOf(":")+3;//5+3
		int endIndex = hostNameNPath.indexOf("/", beginIndex);//22,  -1
		if(endIndex != -1) {
			hostName = hostNameNPath.substring(beginIndex, endIndex);
					//hostNameNPath.substring(endIndex+1, hostNameNPath.length());
			path = hostNameNPath.substring(endIndex+1);
		}else {
			hostName = hostNameNPath;
			path = "";
		}				
		System.out.println("호스트명:" + hostName);
		System.out.println("패스:" + path);
		
		if(arr.length >= 2) {
			queryString = arr[1];
			String[] queryStringArr = queryString.split("&");
			for(int index=0; index<queryStringArr.length; index++) {
				String q = queryStringArr[index];
				String []qArr = q.split("=",2);
				String qName = qArr[0];
				String qValue = qArr[1];
				System.out.println("쿼리스트링 이름은" + qName + ", 값은" + qValue); 
				/*
				쿼리스트링 이름은id, 값은c_202205031110_00000070
				쿼리스트링 이름은mode, 값은LSD
				쿼리스트링 이름은mid, 값은shm
				쿼리스트링 이름은sid1, 값은105
				쿼리스트링 이름은oid, 값은018
				쿼리스트링 이름은aid, 값은0005206604*/			
			}
		}
	}

}
