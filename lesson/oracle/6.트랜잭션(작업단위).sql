--트랜잭션(ACID : Atomicity(원자성), Consistency(일관성),
--              Isolation(독립성), Durability(지속성)
  --계좌테이블
  CREATE TABLE account (no CHAR(3) PRIMARY KEY, balance NUMBER(10));
  INSERT INTO account(no, balance) VALUES ('101', 1000); 
  --트랜잭션시작(다른 섹션에서 똑같은 자료를 INSERT하려하면 먼저 실행된 섹션에서
  --           다른 섹션에게 블러핑을 발생시킴) ex) 교착상태 Dead Lock
  INSERT INTO account(no, balance) VALUES ('102', 1000);
  COMMIT; 
  --COMMIT을 통해 수동완료시켰기 때문에 다른 섹션에서 자료를 INSERT 할 경우
  --INSERT가 잘 수행되거나 오류처리됨, 즉 블러핑이 발생하지않고 어떤 반응이라도 하게됨.
  
  SELECT * FROM account;
  --계좌이체트랜잭션 시작
    --101계좌 1000출금
    UPDATE account SET balance = balance - 1000 WHERE no = '101';
    --102계좌 1000입금
    UPDATE account SET balance = balance + 1000 WHERE no = '102';
  --계좌이체트랜잭션 종료
  
  --DML(INSERT. UPDATE. DELETE)실행시 트랜잭션 자동시작, 트랜잭션 자동완료안됨
  --트랜잭션완료 명령어 : COMMIT. ROLLBACK.
  
  --DDL(CREATE, ALTER, DROP)실행시 트랜잭션 자동시작, 트랜잭션 자동완료됨
  --하지만 DML처럼 COMMIT. ROLLBACK.을 할 수 없어 실제 데이터에 반영은 안됨.
  
  --없는계좌로 계좌이체하는 경우
    --101계좌 100출금 
    UPDATE account SET balance = balance - 100 WHERE no = '101';            
    UPDATE account SET balance = balance + 100 WHERE no = '999';
    --999계좌 100입금 : 999계좌는 존재하지않아 과정자체가 성사되지않지만
    --                 실행은 됨.
    ROLLBACK; --잘못된 과정이므로 rollback해보자
    -- ROLLBACK to SAVEPOINT A : 바로 전 과정 ROLLBACK이 아닌 특정지점으로 ROLLBACK됨.
    
    
    
    
    