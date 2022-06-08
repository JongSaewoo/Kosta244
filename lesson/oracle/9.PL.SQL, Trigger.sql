--트리거
  --한 테이블에 DML(INSERT, UPDATE, DELETE)실행 시 다른 테이블에 자동DML 실행되도록 한다.
  --POINT 테이블(포인트 추가는 사람당 각자 추가됨 > 1:1관계 > 주식별자관계 > 유무관계 ㅡ)
  CREATE TABLE point(
    point_id VARCHAR2(5) PRIMARY KEY,
    point_score NUMBER(3),
    CONSTRAINT point_id_fk FOREIGN KEY (point_id) REFERENCES customer ("id")
  );
  drop table point;
  delete from order_info;
  delete from customer;
  --고객 1행이 추가된 후 포인트 1행 자동 추가
  --(AFTER, :NEW) / (BEFORE, :OLD) 
  CREATE OR REPLACE TRIGGER point_trig
  AFTER insert ON customer
  FOR EACH ROW
  BEGIN
    INSERT INTO point(point_id, point_score) VALUES (:NEW."id", 1);
  END;
  /
  INSERT INTO CUSTOMER("id", pwd, "name", address, status) VALUES('id1', 'p1', 'n1', 'a1', 1);
  
  --주문1행이 추가된 후 포인트 1행의 점수가 자동1점씩 추가
  CREATE OR REPLACE TRIGGER point1_trig
  AFTER insert ON order_info
  FOR EACH ROW
  BEGIN
    UPDATE point SET point_score = point_score + 1 WHERE point_id = :NEW.order_id;
  END;
  /
  INSERT INTO order_info (order_no, order_id, order_dt)
            VALUES (order_seq.NEXTVAL, 'id1', SYSDATE);
            
  SELECT * FROM customer;
  SELECT * FROM order_info;
  SELECT * FROM point;
  
  --고객이 삭제되기 직전 포인트행도 자동삭제
  --고객을 삭제 후 ... -X : 고객(customer)은 point의 부모키로 가지고 있으므로
  --                      먼저 삭제되지 않음
  CREATE OR REPLACE TRIGGER point2_trig
  BEFORE delete ON customer
  FOR EACH ROW
  BEGIN
    DELETE FROM point WHERE point_id =:OLD."id";
  END;
  /
  DELETE FROM order_info;
  DELETE FROM customer;
  COMMIT; --트랜잭션완료
  
  SELECT * FROM point;
  SELECT * FROM customer; 
  
  --주의점 : 트랜잭션
  --트랜잭션 자동시작(tx-1 : oracleDB)
  INSERT INTO customer("id", pwd, "name", address, status) VALUES ('id1', 'p1', 'n1', 'a1', 1);
              -->트리거 point_trig(tx-1 : oracleDB)
  --아직 COMMIT하지않아 트랜잭션 자동완료안됨
  --tx-2인 SQL커맨드에서 point테이블과 customer테이블을 조회해도 아무값도 안나옴
  --현재 tx-1의 블러핑.
  
  COMMIT;
  --블러핑 해제(tx-1의 트랜잭션 자동완료)