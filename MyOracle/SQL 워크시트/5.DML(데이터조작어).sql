--DML(데이터 조작어)
  --데이터 추가(INSERT INTO)
  INSERT INTO customer ("id", pwd, "name", address, status) 
        VALUES ('id1', 'p1', 'n1', 'a1', 1); 
  INSERT INTO customer 
        VALUES ('id2', 'p2', 'n2', 'a2', 1);
  INSERT INTO customer("id", pwd, "name")
        VALUES ('id3', 'p3', 'n3'); -- address는 자동으로 NULL값이 삽입됨
                                    -- status는 자동으로 NULL값이 삽입됨
                                    -- (status는 CHECK(0,1)이었지만 기본값은 NULL임
  INSERT INTO product(prod_no, prod_name, prod_price) 
        VALUES ('C0001', '아메리카노', 1000);
  INSERT INTO product                                 
        VALUES ('C0002', '아이스아메리카노', 1000, NULL, '');
  INSERT INTO product(prod_no, prod_name, prod_price, prod_mfd)
        VALUES ('G0001', '텀블러', 3000, '22/01/01');
  INSERT INTO product(prod_no, prod_name, prod_price, prod_mfd)
        VALUES ('G0002', '다이어리', 4000, SYSDATE);
  
  --데이터 수정(UPDATE)
  UPDATE customer SET "name" = '박종우' WHERE "id" = 'id1';
  UPDATE customer SET pwd = 'p22' WHERE "id" = 'id2';
  UPDATE customer SET status = 1; --WHERE절이 없이 쓰면 모든 status값이 1이 됨. 
  
  UPDATE product SET prod_price = prod_price + (prod_price * 0.1);
  
  --데이터 삭제(DELETE)
  DELETE customer; --WHERE절 없이 썼으므로 customer테이블의 모든 데이터삭제
  DELETE customer WHERE id = 'id1';
  
  --객체(뷰, 인덱스, 시퀀스)
    --뷰 : 가상테이블 
    --복잡한 SQL구문을 뷰로 저장
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
    
    --뷰 내용변경
    CREATE OR REPLACE VIEW a_view
    AS
    SELECT d.department_id, d.department_name, j.job_id, j.job_title, count(employee_id) employee_cnt
    FROM employees e JOIN departments d ON (e.department_id = d.department_id)
                     JOIN jobs j ON (e.job_id = j.job_id)
    WHERE salary >= 5000
    GROUP BY d.department_id, d.department_name, j.job_id, j.job_title
    HAVING count(employee_id) >= 2
    ORDER BY count(employee_id);
    
    --뷰 삭제
    DROP VIEW a_view;
    
    --시퀀스생성 : 일련번호값
    CREATE SEQUENCE a_seq; --1부터 1씩증가
    
    CREATE SEQUENCE b_seq --4부터 2씩증가, 최대50까지 증가하고 50에서 증가되면 자동으로 최소값 3으로 돌아감
    START WITH 4
    INCREMENT BY 2
    MAXVALUE 50
    CYCLE 
    MINVALUE 3;
    
    --생성한 시퀀스로 시퀀스일련번호값 얻기 
    SELECT b_seq.NEXTVAL FROM dual; --계속 실행해보기
    --얻은 시퀀스일련번호값 확인
    select b_seq.CURRVAL FROM dual;
    
    --주문용 시퀀스객체
    CREATE SEQUENCE order_seq;
    INSERT INTO order_info(order_no, order_id, order_dt)
            VALUES (order_seq.NEXTVAL, 'id1', SYSDATE);
    INSERT INTO order_line(order_no, order_prod_no, order_quantity)
            VALUES (order_seq.CURRVAL, 'C0002', 7);
    INSERT INTO order_line(order_no, order_prod_no, order_quantity)
            VALUES (order_seq.CURRVAL, 'C0001', 1);
    SELECT * FROM order_info;
    SELECT * FROM order_line;
    
    --시퀀스수정 : 시퀀스 수정은 ALTER SEQUENCE ... 를 하면 되지만
    --           가능하면 시퀀스를 제거 후 새로 만드는것을 강력히 권장한다.
    
    --시퀀스삭제
    DROP SEQUENCE b_seq;
    
    
    