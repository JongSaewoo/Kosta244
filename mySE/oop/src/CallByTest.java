public class CallByTest {
	public static int m(int i) {
//		System.out.println(i); // 10
		i = 99;
		return i;
	}
	public static void m(int[]arr) {
		arr[0] = 99;
		
	}
	public static void main(String[] args) {
		int i = 10;
		int j = m(i);
		m(i);
		System.out.println(i); //10
		System.out.println(j); //99
		
		int[] arr = {10, 20, 30};
		m(arr);
		System.out.println(arr[0]);

	}

}
