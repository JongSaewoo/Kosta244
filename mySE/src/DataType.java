/**
�ڹ��� �ڷ����� ���� �ǽ��ڵ��Դϴ�
*/
public class DataType{
    /**
      ���θ޼���� Ŭ���� ����� �������� ������ �ϴ� �޼����̴�
    */
    public static void main(String[] args){
        byte b;
        b = 1;
        System.out.println(b);
        //b = 128; //byte������ -128~127�����̴�
        
        // ���� -> ���� �ڵ�����ȯ
        short sh;
        sh = b;
        System.out.println(sh); //1

        //�Ǽ��ڷ���
        float f;
        //f = 1234.567;
        f = 1234.567F;

        long lon;
        //lon = 123456789123456789;
        lon = 123456789123456789L;

        //���� -> �Ǽ� �ڵ�����ȯ
        sh = 1234;
        f = sh;
        System.out.println(sh); // 1234
        System.out.println(f); // 1234.0

        //�Ǽ� -> ���� ����ȯ
        //sh = f; //�ڵ�����ȯ �ȵ�
        sh = (short)f; //��������ȯ
        System.out.println(sh); //1234
        b = (byte)f; //��������ȯ
        System.out.println(b); //-46

        /*
        //���Ϲ����� 2byteunicode
        char c = 'A';
        int i = c; //�ڵ�����ȯ
        System.out.println(i); //65

        //���� 1byte
        boolean flag;
        flag = true;
        //flag = 1; //�����Ͽ���      
        */  
    }
}















