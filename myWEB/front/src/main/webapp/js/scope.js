var a = 'window객체의 변수';
console.log(window.a);  //==console.log(a);

//if블럭, for문블럭등 블럭영역으로 변수구분
for(var i=0; i<3; i++){
  console.log(i); //0, 1, 2
}
console.log(i); //3

//함수영역으로 변수구분함
var b =function(){
  var lv1 = 'outer local variable';
  console.log(a);
  var lv2 = function(){
    var inner = 'inner local variable';
    console.log(lv1);  //outer function variable
  }
  // console.log(inner);  //error
}

// console.log(lv1); //error

b();
// console.log(lv1); //error