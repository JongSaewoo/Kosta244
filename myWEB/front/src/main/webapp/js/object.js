var customer = {
  id: 'id1',
  name: '박종우',
  info: function(){
    console.log(this.id, this.name);
  }
};

customer.id = 'id9';
customer.info();

var customerArr = [];
customerArr[0] = customer;
customerArr.push({
  id: 'id1',
  name: '박종우'
},{
  id: 'id2',
  name: '홍길동'
},{
  id: 'id3',
  name: '강민주'
});
console.log(customerArr);
console.log(customerArr[2]);