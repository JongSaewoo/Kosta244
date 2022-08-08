public class Condition{
    public static void main(String[]args){
        java.util.Scanner sc = new java.util.Scanner(System.in);
        
        /*
        System.out.print("점수를 입력하세요:");
        
        //int score = 93;
        int score = sc.nextInt();
        if( score >= 60 ){
            System.out.println("합격");
            if( score >= 90){
                System.out.println("1등급");
            }else if( score >= 70){
                System.out.println("2등급");
            }else{ //if(score >= 60){
                System.out.println("3등급");
            }
        }else{
            System.out.println("불합격");
        }
        */
        
        int year = 2022;
        
        //int birthYear = 2021;
        /*System.out.print("출생년도를 입력하세요:");
        int birthYear = sc.nextInt();
        int age = year - birthYear; //1
        //7세 미만은 영유아,  7~13세까지 어린이,  14~19세까지 청소년, 
        //20세이상은 성인
        if(age >= 20){
            System.out.println("성인");
        }else if(age >= 14){
            System.out.println("청소년");
        }else if(age >= 7){
            System.out.println("어린이");
        }else{
            System.out.println("영유아");
        }

        if(age < 7){               
            System.out.println("영유아");        
        }else if(age<=13){      
            System.out.println("어린이");
        }else if(age <= 19){    
            System.out.println("청소년");
        }else{                       
            System.out.println("성인");
        }
        
        //출생년도에 해당하는 12지를 출력하시오. 
        //2022년출생자는 호랑이, 2021년-소, 2020년-쥐
        //년도를 12로 나눈 나머지가 0이면 원숭이, 1이면 닭, 2이면 개, 3이면 돼지,
        //                                    4이면 쥐,  5이면 소,  6이면 호랑이, 7이면 토끼,
        //                                    8이면 용,  9이면 뱀,  10이면 말,    11이면 양
        
        if(birthYear%12 == 0){
           System.out.println("원숭이띠");
        }else if(birthYear%12 == 1){
           System.out.println("닭띠");
        }
        
        int ganzi= birthYear%12;
        /*if(ganzi == 0){
        }else if(ganzi == 1){
        }*/
        /*switch(ganzi){   //byte, short, char, int가능(long, float, double불가능), String가능
        case 0:
           System.out.println("원숭이띠");
        case 1:
           System.out.println("닭띠");
        case 2:
           System.out.println("개");
        case 3:
           System.out.println("돼지");
        case 4:
           System.out.println("쥐");
        case 5:
           System.out.println("소");
        case 6:
           System.out.println("호랑이");
           break;
        default:
           System.out.println("그외의 동물");
        }
        */
        /*
        가위바위보게임: 
        컴퓨터가 가위를 내고 사용자가 바위를 낸 경우 "이겼습니다"를 출력 
                                                 보                 "졌습니다"
                                                 가위를 낸 경우 "비겼습니다"
         1:가위, 2:바위, 3:보
        */
         char c1 = 9996;
         int computer;
        
         computer = (int)(Math.random()*3)+1 ; //Math.random()의 반환값이 0.0<= r<1.0
                                                             //Math.random()*3       0.0<=r<3.0
                                                             //(int)                         0 <= r <3
         int user;
         System.out.print(c1 +"-1, 바위-2, 보-3를 입력하세요:");
         user = sc.nextInt( );
         switch(computer){
         case 1:  System.out.println("컴퓨터가 가위를 냈습니다");
                    break;
         case 2: System.out.println("컴퓨터가 바위를 냈습니다");
                    break;
         case 3: System.out.println("컴퓨터가 보를 냈습니다");
                    break;
         }
        
        if(user == computer) {
             System.out.println("비겼습니다.");
        }else if((user==1 && computer==3) 
                ||(user==2 && computer==1)
	   ||(user==3 && computer==2)) {
             System.out.println("졌습니다.");
        }else {
	System.out.println("이겼습니다.");
        }

        

         if(computer == user){
             System.out.println("비겼습니다");
         }else {
              //이기는 경우
               if( (user - computer%3) == 1 ) {
                 System.out.println("졌습니다");
             }else{
                 System.out.println("이겼습니다");
             }
         }
    }
}