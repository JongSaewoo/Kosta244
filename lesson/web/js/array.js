var arr = [1, 10, 3];
console.log(arr.length); //3
console.log(arr[0]); //1

arr.push(7); //배열의 끝에 추가 1, 10, 3, 7
for(var i=0; i<arr.length; i++){
    console.log('인덱스'+i, arr[i]);
}
var last = arr.pop(); //배열의 마지막요소 제거
console.log('제거된 마지막요소값', last); //7

arr.forEach(function(item, index){
    console.log('인덱스'+index, item);
});

var leng = arr.unshift(9);//배열의 앞에 추가 9,1,10,3
console.log('추가된 후의 배열길이', leng);//4
arr.forEach(function(item, index){
    console.log('인덱스'+index, item);
});

var first = arr.shift(); //배열의 앞요소 제거
console.log('제거된 처음요소값', first);//9

arr.splice(2, 0, 8); //배열의 index에 추가 2번 index에 8을 추가
arr.forEach(function(item, index){
    console.log('인덱스' + index, item);
});

var index = arr.indexOf(1);  //arr.indexof(99);  //반환값 -1
console.log('값1의 인덱스위치', index);

var arr2 = ['JAVA', 'HTML', 'CSS', 'JS'];  //배열요소들을 문자열로 만듦 'JAVA,HTML,CSS,JS'
var str = arr2.join();
console.log(str);

var str1 = 'HELLO';
var arr3 = str1.split('');  //문자열을 배열로 만듦
console.log(arr3);

var arr4 = str.split(',');  //['JAVA', 'HTML', 'CSS', 'JS']
console.log(arr4);