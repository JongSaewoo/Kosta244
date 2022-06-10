/*
var로 선언하면 재선언가능, window객체 Scope에 선언, 동시에 함수 scope에 선언, hoisting됨
var없이 선언하면 재선언가능, window객체 Scope에 선언
let로 선언하면 재선언불가능, block scope에 선언, hoisting 안됨.
*/
var a = 10;
var a = 'hello';
console.log(window.a); //hello
var f1 = function(){
    var lv = true;  //함수scope에 선언
    aa = true;      //window객체 Scope에 선언
    a = true;       //window객체 Scope에 재선언
    var f2 = function(){
        aaa = true;
    }
    f2();
}
f1();

// console.log(lv);
console.log(window.aa, aa);    //true, true
console.log(window.aaa, aaa);  //true, true
console.log(window.a, a);      //true, true




// let b = 10;
// let b = 'hello;'  
// console.log(b);
let b = 10;
console.log(window.b);  //let은 window의 내장객체가 아니므로 결과는 undefined
let f2 = function(){
    let bb = false; //함수 scope에 선언
    let b = false;  //함수 scope에 선언
}
f2();

// console.log(bb); 
console.log(b); // 10

//Hoisting : 사용먼저하고 선언을 나중에 하는 방법
aaaa = new Date();
console.log(aaa);
var aaaa;

//let은 Hoisting을 지원하지 않음
bb = new Date();
console.log(bb);
let bb;