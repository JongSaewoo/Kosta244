public class Operator{
    public static void main(String[] args){
        //���������
        int a,b,c;
        a=10; b=3;
        c=a+b;
        System.out.println(c); //13
        c=a%b;
        System.out.println(c); //1

        short s1,s2,s3;
        s1=10; s2=3;
        s3 = (short)(s1+s2); //������ ����

        float f;
        a=10;
        b=3;
        f = a+b;
        System.out.println(f); //13.0
        a=10;
        b=3;
        f = a/b;
        System.out.println(f); //3.0

        f = (float)a/b;
        System.out.println(f); //3.333333

        //���Կ�����
        a+=10;
        System.out.println(a); //20

        //�񱳿�����
        a=10;
        System.out.println(a%2 == 0); //true
        a=8;
        System.out.println(a%2 == 0); //true
        b=3;
        System.out.println(b%2 == 1); //true
        System.out.println(b%2 != 0); // true
        System.out.println(a>b); //true

        //��������
        System.out.println(true && true); //true
        System.out.println(true &   true); //true
        System.out.println(false && true); //false
        System.out.println(false &   true); //false

        //��Ʈ������
        System.out.println(0 & 1); //0

        //���׿�����
        a=10;
        a++;
        System.out.println(a); //11
        
        a=10;
        a--;
        System.out.println(a); //9

        a=10;
        b=a++;
        System.out.println(b); //10
        System.out.println(a); //11

        a=10;
        b=++a;
        System.out.println(b); //11
        System.out.println(a); //11

        byte b1;
        b1=127;
        b1++; 
        //b1=128; //�����Ͽ���
        System.out.println(b1); //-128

        //���׿�����
        a=10;
        System.out.println(a%2==0?"¦��":"Ȧ��");
        

    }
}










