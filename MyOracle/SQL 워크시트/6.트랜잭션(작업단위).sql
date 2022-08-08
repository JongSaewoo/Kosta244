--Ʈ�����(ACID : Atomicity(���ڼ�), Consistency(�ϰ���),
--              Isolation(������), Durability(���Ӽ�)
  --�������̺�
  CREATE TABLE account (no CHAR(3) PRIMARY KEY, balance NUMBER(10));
  INSERT INTO account(no, balance) VALUES ('101', 1000); 
  --Ʈ����ǽ���(�ٸ� ���ǿ��� �Ȱ��� �ڷḦ INSERT�Ϸ��ϸ� ���� ����� ���ǿ���
  --           �ٸ� ���ǿ��� ������ �߻���Ŵ) ex) �������� Dead Lock
  INSERT INTO account(no, balance) VALUES ('102', 1000);
  COMMIT; 
  --COMMIT�� ���� �����Ϸ���ױ� ������ �ٸ� ���ǿ��� �ڷḦ INSERT �� ���
  --INSERT�� �� ����ǰų� ����ó����, �� ������ �߻������ʰ� � �����̶� �ϰԵ�.
  
  SELECT * FROM account;
  --������üƮ����� ����
    --101���� 1000���
    UPDATE account SET balance = balance - 1000 WHERE no = '101';
    --102���� 1000�Ա�
    UPDATE account SET balance = balance + 1000 WHERE no = '102';
  --������üƮ����� ����
  
  --DML(INSERT. UPDATE. DELETE)����� Ʈ����� �ڵ�����, Ʈ����� �ڵ��Ϸ�ȵ�
  --Ʈ����ǿϷ� ��ɾ� : COMMIT. ROLLBACK.
  
  --DDL(CREATE, ALTER, DROP)����� Ʈ����� �ڵ�����, Ʈ����� �ڵ��Ϸ��
  --������ DMLó�� COMMIT. ROLLBACK.�� �� �� ���� ���� �����Ϳ� �ݿ��� �ȵ�.
  
  --���°��·� ������ü�ϴ� ���
    --101���� 100��� 
    UPDATE account SET balance = balance - 100 WHERE no = '101';            
    UPDATE account SET balance = balance + 100 WHERE no = '999';
    --999���� 100�Ա� : 999���´� ���������ʾ� ������ü�� �������������
    --                 ������ ��.
    ROLLBACK; --�߸��� �����̹Ƿ� rollback�غ���
    -- ROLLBACK to SAVEPOINT A : �ٷ� �� ���� ROLLBACK�� �ƴ� Ư���������� ROLLBACK��.
    
    
    
    
    