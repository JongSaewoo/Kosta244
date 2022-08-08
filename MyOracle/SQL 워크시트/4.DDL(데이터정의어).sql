--DDL(���������Ǿ�)
  --�÷����� �������� ����
  --��ü ���� : 
    --���̺� ���Ž� �������� : ������ �ش� ���̺��� �ٸ� ���̺�� �ĺ��ڰ��谡 ���ִٸ�
    --�θ�, �ڽ� ���迡�� �ڽ��������� ���̺��� ���� �����ؾ� �θ��������� ���̺��� �����ɼ��ִ�
  DROP TABLE product; 
  
  --��ü ����
  CREATE TABLE product(
    prod_no VARCHAR2(5),
    prod_name VARCHAR2(30),
    prod_price NUMBER(6),
    prod_info VARCHAR2(100),
    prod_mfd DATE 
  );
  
  --��ü(�÷�) ������ ���� ���� �����غ���
  SELECT * FROM product;
  
  INSERT INTO product(prod_no, prod_name, prod_price) 
            VALUES ('C0001', '�Ƹ޸�ī��', 1000);
  INSERT INTO product(prod_no, prod_name, prod_price) 
            VALUES ('C0005', NULL, 2500);
  --��ü ����
    --�÷� �߰�
    DESC product; --�߰��� �÷� Ȯ���ϱ����� product ����Ȯ��
    
    ALTER TABLE product
    ADD a VARCHAR2(3); 
  
    ALTER TABLE product
    RENAME COLUMN a TO b; -- 'a'��� �̸��� �÷��� 'b'�� �ٲٱ�
  
    --�÷��ڷ���/�ڸ�������
    ALTER TABLE product
    MODIFY b VARCHAR2(4); -- 'b'��� �̸��� �÷��� VARCHAR(4)�� ����
  
    --�÷�����
    ALTER TABLE product
    DROP COLUMN b;
    
    --�������� �߰�
    ALTER TABLE product
    ADD CONSTRAINT prod_no_pk PRIMARY KEY (prod_no);
    
    ALTER TABLE product
    ADD CONSTRAINT prod_name_nn NOT NULL (prod_name); --���̺������� �����ؼ� x
    ALTER TABLE product
    MODIFY prod_name NOT NULL; --�÷��������� ������ ������ ����� ������
    
    --�������� ����
    ALTER TABLE product
    DROP CONSTRAINT prod_no_pk;
    
    ALTER TABLE product
    MODIFY prod_name NULL;
    
  --��ųʸ�
  SELECT * FROM USER_TABLES;
  SELECT * FROM USER_CONSTRAINTS;
  SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME='PRODUCT';
  --���Ἲ ��������
    --NOT NULL 
      CREATE TABLE a_tbl(a NUMBER CONSTRAINT atbl_a_nn NOT NULL, b NUMBER);
      INSERT INTO a_tbl(a, b) VALUES (null, 2);
      SELECT * FROM a_tbl;
      
    --UNIQUE : �ߺ��� �� ����
      CREATE TABLE b_tbl(a NUMBER CONSTRAINT btbl_a_uq UNIQUE, b NUMBER); 
      INSERT INTO b_tbl(a, b) VALUES (1, 1);
      SELECT * FROM b_tbl;
      
    --PRIMARY KEY : NOT NULL + UNIQUE
      CREATE TABLE c_tbl(a NUMBER CONSTRAINT ctbl_a_pk PRIMARY KEY, b number);
      INSERT INTO c_tbl(a,b) VALUES (1, 11);
      SELECT * FROM c_tbl;
      
    --CHECK : ���� A�ƴϸ� B, �ΰ��� ������ �÷��� ����
      CREATE TABLE d_tbl(a NUMBER CONSTRAINT dtbl_a_ck CHECK(MOD(a, 2) = 1),
                         b CHAR(1) CHECK(b IN ('M', 'F'))
                        );
      CREATE TABLE e_tbl(a NUMBER CONSTRAINT etbl_a_ck CHECK(MOD(a, 2) = 1)
                         NOT NULL,
                         b CHAR(1) CHECK(b IN ('M', 'F'))
                        );
                        
      INSERT INTO d_tbl(a, b) VALUES(1, 'M');
      INSERT INTO e_tbl(a, b) VALUES(null, 'M');
      SELECT * FROM d_tbl;
      SELECT * FROM e_tbl;
      
    --FOREIGN KEY : �ٸ� ���̺��� �⺻Ű�� �Ϲ�Ű�� �����ϴ� �÷��̴�
    --              ����, �ܷ�Ű�� �����ϴ� ���̺��� �ڽĿ���Ƽ, ������ ����̵Ǵ� Ű��
    --              �ִ� ���̺��� �θ���Ƽ�̴�. �� ����Ƽ���̿��� �����̼� ���� �׸�����
    --              ������ ����̵Ǵ� Ű�� �⺻Ű�� �Ǽ�(�ֽĺ��ڰ���)���� �׸���
    --                                  �ܷ�Ű�� ����(��ĺ��ڰ���)���� �׸���.
      --ī��ڸ�Ƽ : 1:1, 1:M, M:N ���踦 �ΰ��ִ� �� ��
      --ex) �� ����� ���� ����� ���´� - 1:M ���� - 1���� ���輱�� �� �ϳ�,
      --                                          M���� ���輱�� ��ġ�߷� �׷��ش�,
      CREATE TABLE parent_tbl(a VARCHAR2(2) PRIMARY KEY,
                          b NUMBER);
      CREATE TABLE child_tbl(c NUMBER PRIMARY KEY,
                             d VARCHAR2(2) CONSTRAINT childtbl_d_fk REFERENCES parent_tbl(a));
      INSERT INTO parent_tbl(a, b) VALUES ('fi', 1);
      INSERT INTO child_tbl(c, d) VALUES (10, 'fi');
      INSERT INTO child_tbl(c, d) VALUES (10, 'ss'); -- ss��� �÷��� parent_tbl�� �������� �����Ƿ� ��������.
      SELECT * FROM parent_tbl;
      SELECT * FROM child_tbl;
      
    --���̺��� �������� ���� : NOT NULL �������Ǹ�ŭ�� �÷����� ������������ �����Ѵ�.
      CREATE TABLE f_tbl(a NUMBER,
                         b VARCHAR2(2),
                         c DATE,
                         CONSTRAINT ftbl_a_pk PRIMARY KEY(a),
                         CONSTRAINT ftbl_b_ck CHECK(a >= 0),
                         CONSTRAINT ftbl_c_fk FOREIGN KEY(b) REFERENCES parent_tbl(a)
                        );