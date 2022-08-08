public class Struct {
	public static void main(String[] args) {
		//배열: 동일자료형의 데이터묶음, 크기 고정, 길이얻기length
		int[] arr = new int[10];
		arr[0] = 5;
		arr[1] = 8;
//		arr[2] = "HELLO"; 
//		arr[10] = 999;
		
		//맵: 키와 값을 저장할 수 있고 크기가 자동변경, 길이얻기 size()
		java.util.Map map;
		map = new java.util.HashMap();
		map.put("name", "김준용");
		map.put("addr", "광주");
		map.put("sal", 3000);
		System.out.println(map.size()); //3
		System.out.println(map.get("name")); //김준용
		System.out.println(map.get("sal"));  //3000
		
	}

}
