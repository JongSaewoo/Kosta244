ALTER USER hr ACCOUNT UNLOCK;
ALTER USER hr IDENTIFIED BY hr;

--�������� : ������ �����ߴٰ� �ؼ� OracleDB�� �����ǿ��� ���� �� ���� ������ ����������
CREATE USER test IDENTIFIED BY test;
--���� ���Ѽ��� : GRANT ~ TO ~
  --CREATE SESSION : �ٸ� ���ǿ��� ���� ���� ���
  --CREATE TABLE : ���̺� �������� ���
  --��(ROLE) ��� : CONNECT, RESOURCE, ���� : CREATE VIEW
  --CONNECT : �ظ��� ���� ���� ������ ��� ���
  --RESOURCE : �ظ��� �ҽ� ���������� ��� ���
  --CREATE VIEW : �� �������� ���
  GRANT CREATE SESSION, CREATE TABLE  TO test;
  GRANT CONNECT, RESOURCE, CREATE VIEW TO test;

--���� ���Ѽ��� ����
  --REVOKE ~ FROM ~
  REVOKE CREATE VIEW FROM test;
