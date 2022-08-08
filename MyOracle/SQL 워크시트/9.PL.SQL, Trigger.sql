--Ʈ����
  --�� ���̺� DML(INSERT, UPDATE, DELETE)���� �� �ٸ� ���̺� �ڵ�DML ����ǵ��� �Ѵ�.
  --POINT ���̺�(����Ʈ �߰��� ����� ���� �߰��� > 1:1���� > �ֽĺ��ڰ��� > �������� ��)
  CREATE TABLE point(
    point_id VARCHAR2(5) PRIMARY KEY,
    point_score NUMBER(3),
    CONSTRAINT point_id_fk FOREIGN KEY (point_id) REFERENCES customer ("id")
  );
  drop table point;
  delete from order_info;
  delete from customer;
  --�� 1���� �߰��� �� ����Ʈ 1�� �ڵ� �߰�
  --(AFTER, :NEW) / (BEFORE, :OLD) 
  CREATE OR REPLACE TRIGGER point_trig
  AFTER insert ON customer
  FOR EACH ROW
  BEGIN
    INSERT INTO point(point_id, point_score) VALUES (:NEW."id", 1);
  END;
  /
  INSERT INTO CUSTOMER("id", pwd, "name", address, status) VALUES('id1', 'p1', 'n1', 'a1', 1);
  
  --�ֹ�1���� �߰��� �� ����Ʈ 1���� ������ �ڵ�1���� �߰�
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
  
  --���� �����Ǳ� ���� ����Ʈ�൵ �ڵ�����
  --���� ���� �� ... -X : ��(customer)�� point�� �θ�Ű�� ������ �����Ƿ�
  --                      ���� �������� ����
  CREATE OR REPLACE TRIGGER point2_trig
  BEFORE delete ON customer
  FOR EACH ROW
  BEGIN
    DELETE FROM point WHERE point_id =:OLD."id";
  END;
  /
  DELETE FROM order_info;
  DELETE FROM customer;
  COMMIT; --Ʈ����ǿϷ�
  
  SELECT * FROM point;
  SELECT * FROM customer; 
  
  --������ : Ʈ�����
  --Ʈ����� �ڵ�����(tx-1 : oracleDB)
  INSERT INTO customer("id", pwd, "name", address, status) VALUES ('id1', 'p1', 'n1', 'a1', 1);
              -->Ʈ���� point_trig(tx-1 : oracleDB)
  --���� COMMIT�����ʾ� Ʈ����� �ڵ��Ϸ�ȵ�
  --tx-2�� SQLĿ�ǵ忡�� point���̺�� customer���̺��� ��ȸ�ص� �ƹ����� �ȳ���
  --���� tx-1�� ����.
  
  COMMIT;
  --���� ����(tx-1�� Ʈ����� �ڵ��Ϸ�)