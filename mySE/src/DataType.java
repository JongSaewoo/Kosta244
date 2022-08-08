/**
자바의 자료형에 대한 실습코드입니다
*/
public class DataType{
    /**
      메인메서드는 클래스 실행시 진입점의 역할을 하는 메서드이다
    */
    public static void main(String[] args){
        byte b;
        b = 1;
        System.out.println(b);
        //b = 128; //byte범위는 -128~127까지이다
        
        // 정수 -> 정수 자동형변환
        short sh;
        sh = b;
        System.out.println(sh); //1

        //실수자료형
        float f;
        //f = 1234.567;
        f = 1234.567F;

        long lon;
        //lon = 123456789123456789;
        lon = 123456789123456789L;

        //정수 -> 실수 자동형변환
        sh = 1234;
        f = sh;
        System.out.println(sh); // 1234
        System.out.println(f); // 1234.0

        //실수 -> 정수 형변환
        //sh = f; //자동형변환 안됨
        sh = (short)f; //강제형변환
        System.out.println(sh); //1234
        b = (byte)f; //강제형변환
        System.out.println(b); //-46

        /*
        //단일문자형 2byteunicode
        char c = 'A';
        int i = c; //자동형변환
        System.out.println(i); //65

        //논리형 1byte
        boolean flag;
        flag = true;
        //flag = 1; //컴파일오류      
        */  
    }
}















