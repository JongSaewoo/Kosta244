/*
산술연산자 : +, -, *, /, %
대입연산자 : =, +=, -=, *= /=, %=
비교연산자 : >, >=, <, <=, ==, !=, ===, !==
논리연산자 : &&, ||, !
삼항연산자 :  ? :
단항연산자 : ++, --
*/
//산술연산자
var a, b, c;
a=3;
b=4;
c=a/b; //0.75
console.log(c);

b=0;
c=a/b; //Infinity
console.log(typeof(c), c);
console.log(++c); //Infinity

b=undefined;
c=a/b; //NaN
console.log(b);
console.log(c);
console.log(++c); //NaN

//+ : 산술연산자, 문자열결합연산자 : 문자열결합우선순위가 더 높음, 
//숫자가 문자로 자동형변환
console.log(1+'2'); //'12'  
console.log('2'+1); //'21'

//자동형변환 :숫자, 문자형, 논리형
console.log(true +'2');//문자열결합연산자 'true2'
console.log(true + 2); //산술연산자 3
console.log(1 - '2'); //-1
console.log(1 - true); //0
console.log('2' - true); //1
//강제형변환 
console.log(typeof(Number('2')));
console.log(typeof(String(2)));
console.log(typeof(Number('2.345')), Number('2.345'));
console.log(typeof(parseInt('2')),   parseInt('2'));
console.log(typeof(parseFloat('2.345')),   parseFloat('2.345'));
console.log(typeof(parseInt('2.345')),   parseInt('2.345'));

//비교연산자 ===자료형까지 비교
console.log(2 == '2', 2 ==='2'); //true false
console.log(0 == false, 0===false);//true false
