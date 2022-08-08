--DML(������ ���۾�)
  --������ �߰�(INSERT INTO)
  INSERT INTO customer ("id", pwd, "name", address, status) 
        VALUES ('id1', 'p1', 'n1', 'a1', 1); 
  INSERT INTO customer 
        VALUES ('id2', 'p2', 'n2', 'a2', 1);
  INSERT INTO customer("id", pwd, "name")
        VALUES ('id3', 'p3', 'n3'); -- address�� �ڵ����� NULL���� ���Ե�
                                    -- status�� �ڵ����� NULL���� ���Ե�
                                    -- (status�� CHECK(0,1)�̾����� �⺻���� NULL��
  INSERT INTO product(prod_no, prod_name, prod_price) 
        VALUES ('C0001', '�Ƹ޸�ī��', 1000);
  INSERT INTO product                                 
        VALUES ('C0002', '���̽��Ƹ޸�ī��', 1000, NULL, '');
  INSERT INTO product(prod_no, prod_name, prod_price, prod_mfd)
        VALUES ('G0001', '�Һ�', 3000, '22/01/01');
  INSERT INTO product(prod_no, prod_name, prod_price, prod_mfd)
        VALUES ('G0002', '���̾', 4000, SYSDATE);
  
  --������ ����(UPDATE)
  UPDATE customer SET "name" = '������' WHERE "id" = 'id1';
  UPDATE customer SET pwd = 'p22' WHERE "id" = 'id2';
  UPDATE customer SET status = 1; --WHERE���� ���� ���� ��� status���� 1�� ��. 
  
  UPDATE product SET prod_price = prod_price + (prod_price * 0.1);
  
  --������ ����(DELETE)
  DELETE customer; --WHERE�� ���� �����Ƿ� customer���̺��� ��� �����ͻ���
  DELETE customer WHERE id = 'id1';
  
  --��ü(��, �ε���, ������)
    --�� : �������̺� 
    --������ SQL������ ��� ����
    CREATE VIEW a_view
    AS
    SELECT d.department_id, d.department_name, j.job_id, j.job_title, count(employee_id) employee_cnt
    FROM employees e JOIN departments d ON (e.department_id = d.department_id)
                     JOIN jobs j ON (e.job_id = j.job_id)
    WHERE salary >= 3000
    GROUP BY d.department_id, d.department_name, j.job_id, j.job_title
    HAVING count(employee_id) >= 2
    ORDER BY count(employee_id);
    
    SELECT * FROM a_view;
    
    --�� ���뺯��
    CREATE OR REPLACE VIEW a_view
    AS
    SELECT d.department_id, d.department_name, j.job_id, j.job_title, count(employee_id) employee_cnt
    FROM employees e JOIN departments d ON (e.department_id = d.department_id)
                     JOIN jobs j ON (e.job_id = j.job_id)
    WHERE salary >= 5000
    GROUP BY d.department_id, d.department_name, j.job_id, j.job_title
    HAVING count(employee_id) >= 2
    ORDER BY count(employee_id);
    
    --�� ����
    DROP VIEW a_view;
    
    --���������� : �Ϸù�ȣ��
    CREATE SEQUENCE a_seq; --1���� 1������
    
    CREATE SEQUENCE b_seq --4���� 2������, �ִ�50���� �����ϰ� 50���� �����Ǹ� �ڵ����� �ּҰ� 3���� ���ư�
    START WITH 4
    INCREMENT BY 2
    MAXVALUE 50
    CYCLE 
    MINVALUE 3;
    
    --������ �������� �������Ϸù�ȣ�� ��� 
    SELECT b_seq.NEXTVAL FROM dual; --��� �����غ���
    --���� �������Ϸù�ȣ�� Ȯ��
    select b_seq.CURRVAL FROM dual;
    
    --�ֹ��� ��������ü
    CREATE SEQUENCE order_seq;
    INSERT INTO order_info(order_no, order_id, order_dt)
            VALUES (order_seq.NEXTVAL, 'id1', SYSDATE);
    INSERT INTO order_line(order_no, order_prod_no, order_quantity)
            VALUES (order_seq.CURRVAL, 'C0002', 7);
    INSERT INTO order_line(order_no, order_prod_no, order_quantity)
            VALUES (order_seq.CURRVAL, 'C0001', 1);
    SELECT * FROM order_info;
    SELECT * FROM order_line;
    
    --���������� : ������ ������ ALTER SEQUENCE ... �� �ϸ� ������
    --           �����ϸ� �������� ���� �� ���� ����°��� ������ �����Ѵ�.
    
    --����������
    DROP SEQUENCE b_seq;
    
    
    