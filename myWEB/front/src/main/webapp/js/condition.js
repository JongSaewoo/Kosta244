var num=15;
if(num%2==0){
    console.log('짝수');
}else{
    console.log('홀수');
}
if(num>=10){
    console.log('10이상의 숫자');
}else if(num >= 5){
    console.log('5이상의 숫자');
}else{
    console.log('5미만의 숫자');
}

var today = new Date();
console.log(today);
var month = today.getMonth(); //1월이 0, 2월이 1
console.log(month+1+'월'); //'6월'
switch(month+1){
case 1:
case 2:
case 3:
case 4:
case 5:
case 6:
    console.log('상반기');
    break;
case 7:
case 8:
case 9:
case 10:
case 11:
case 12:
    console.log('하반기');
    break;
}
var hour = today.getHours(); //today.getFullYear()
switch(parseInt(hour/12)){
    case 0:
        console.log('오전');
        break;
    case 1:
        console.log('오후');
        break;
}