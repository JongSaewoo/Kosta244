public class Condition{
    public static void main(String[]args){
        java.util.Scanner sc = new java.util.Scanner(System.in);
        
        /*
        System.out.print("������ �Է��ϼ���:");
        
        //int score = 93;
        int score = sc.nextInt();
        if( score >= 60 ){
            System.out.println("�հ�");
            if( score >= 90){
                System.out.println("1���");
            }else if( score >= 70){
                System.out.println("2���");
            }else{ //if(score >= 60){
                System.out.println("3���");
            }
        }else{
            System.out.println("���հ�");
        }
        */
        
        int year = 2022;
        
        //int birthYear = 2021;
        /*System.out.print("����⵵�� �Է��ϼ���:");
        int birthYear = sc.nextInt();
        int age = year - birthYear; //1
        //7�� �̸��� ������,  7~13������ ���,  14~19������ û�ҳ�, 
        //20���̻��� ����
        if(age >= 20){
            System.out.println("����");
        }else if(age >= 14){
            System.out.println("û�ҳ�");
        }else if(age >= 7){
            System.out.println("���");
        }else{
            System.out.println("������");
        }

        if(age < 7){               
            System.out.println("������");        
        }else if(age<=13){      
            System.out.println("���");
        }else if(age <= 19){    
            System.out.println("û�ҳ�");
        }else{                       
            System.out.println("����");
        }
        
        //����⵵�� �ش��ϴ� 12���� ����Ͻÿ�. 
        //2022������ڴ� ȣ����, 2021��-��, 2020��-��
        //�⵵�� 12�� ���� �������� 0�̸� ������, 1�̸� ��, 2�̸� ��, 3�̸� ����,
        //                                    4�̸� ��,  5�̸� ��,  6�̸� ȣ����, 7�̸� �䳢,
        //                                    8�̸� ��,  9�̸� ��,  10�̸� ��,    11�̸� ��
        
        if(birthYear%12 == 0){
           System.out.println("�����̶�");
        }else if(birthYear%12 == 1){
           System.out.println("�߶�");
        }
        
        int ganzi= birthYear%12;
        /*if(ganzi == 0){
        }else if(ganzi == 1){
        }*/
        /*switch(ganzi){   //byte, short, char, int����(long, float, double�Ұ���), String����
        case 0:
           System.out.println("�����̶�");
        case 1:
           System.out.println("�߶�");
        case 2:
           System.out.println("��");
        case 3:
           System.out.println("����");
        case 4:
           System.out.println("��");
        case 5:
           System.out.println("��");
        case 6:
           System.out.println("ȣ����");
           break;
        default:
           System.out.println("�׿��� ����");
        }
        */
        /*
        ��������������: 
        ��ǻ�Ͱ� ������ ���� ����ڰ� ������ �� ��� "�̰���ϴ�"�� ��� 
                                                 ��                 "�����ϴ�"
                                                 ������ �� ��� "�����ϴ�"
         1:����, 2:����, 3:��
        */
         char c1 = 9996;
         int computer;
        
         computer = (int)(Math.random()*3)+1 ; //Math.random()�� ��ȯ���� 0.0<= r<1.0
                                                             //Math.random()*3       0.0<=r<3.0
                                                             //(int)                         0 <= r <3
         int user;
         System.out.print(c1 +"-1, ����-2, ��-3�� �Է��ϼ���:");
         user = sc.nextInt( );
         switch(computer){
         case 1:  System.out.println("��ǻ�Ͱ� ������ �½��ϴ�");
                    break;
         case 2: System.out.println("��ǻ�Ͱ� ������ �½��ϴ�");
                    break;
         case 3: System.out.println("��ǻ�Ͱ� ���� �½��ϴ�");
                    break;
         }
        
        if(user == computer) {
             System.out.println("�����ϴ�.");
        }else if((user==1 && computer==3) 
                ||(user==2 && computer==1)
	   ||(user==3 && computer==2)) {
             System.out.println("�����ϴ�.");
        }else {
	System.out.println("�̰���ϴ�.");
        }

        

         if(computer == user){
             System.out.println("�����ϴ�");
         }else {
              //�̱�� ���
               if( (user - computer%3) == 1 ) {
                 System.out.println("�����ϴ�");
             }else{
                 System.out.println("�̰���ϴ�");
             }
         }
    }
}