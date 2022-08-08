--DDL(데이터정의어)
  --컬럼레벨 제약조건 설정
  --객체 제거 : 
    --테이블 제거시 주의할점 : 제거할 해당 테이블이 다른 테이블과 식별자관계가 되있다면
    --부모, 자식 관계에서 자식포지션인 테이블을 먼저 삭제해야 부모포지션의 테이블이 삭제될수있다
  DROP TABLE product; 
  
  --객체 생성
  CREATE TABLE product(
    prod_no VARCHAR2(5),
    prod_name VARCHAR2(30),
    prod_price NUMBER(6),
    prod_info VARCHAR2(100),
    prod_mfd DATE 
  );
  
  --객체(컬럼) 생성한 곳에 값을 저장해보기
  SELECT * FROM product;
  
  INSERT INTO product(prod_no, prod_name, prod_price) 
            VALUES ('C0001', '아메리카노', 1000);
  INSERT INTO product(prod_no, prod_name, prod_price) 
            VALUES ('C0005', NULL, 2500);
  --객체 변경
    --컬럼 추가
    DESC product; --추가한 컬럼 확인하기위해 product 구조확인
    
    ALTER TABLE product
    ADD a VARCHAR2(3); 
  
    ALTER TABLE product
    RENAME COLUMN a TO b; -- 'a'라는 이름의 컬럼을 'b'로 바꾸기
  
    --컬럼자료형/자릿수변경
    ALTER TABLE product
    MODIFY b VARCHAR2(4); -- 'b'라는 이름의 컬럼을 VARCHAR(4)로 변경
  
    --컬럼제거
    ALTER TABLE product
    DROP COLUMN b;
    
    --제약조건 추가
    ALTER TABLE product
    ADD CONSTRAINT prod_no_pk PRIMARY KEY (prod_no);
    
    ALTER TABLE product
    ADD CONSTRAINT prod_name_nn NOT NULL (prod_name); --테이블레벨에서 정의해서 x
    ALTER TABLE product
    MODIFY prod_name NOT NULL; --컬럼레벨에서 정의한 것으로 제대로 수정됨
    
    --제약조건 삭제
    ALTER TABLE product
    DROP CONSTRAINT prod_no_pk;
    
    ALTER TABLE product
    MODIFY prod_name NULL;
    
  --딕셔너리
  SELECT * FROM USER_TABLES;
  SELECT * FROM USER_CONSTRAINTS;
  SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME='PRODUCT';
  --무결성 제약조건
    --NOT NULL 
      CREATE TABLE a_tbl(a NUMBER CONSTRAINT atbl_a_nn NOT NULL, b NUMBER);
      INSERT INTO a_tbl(a, b) VALUES (null, 2);
      SELECT * FROM a_tbl;
      
    --UNIQUE : 중복된 값 제약
      CREATE TABLE b_tbl(a NUMBER CONSTRAINT btbl_a_uq UNIQUE, b NUMBER); 
      INSERT INTO b_tbl(a, b) VALUES (1, 1);
      SELECT * FROM b_tbl;
      
    --PRIMARY KEY : NOT NULL + UNIQUE
      CREATE TABLE c_tbl(a NUMBER CONSTRAINT ctbl_a_pk PRIMARY KEY, b number);
      INSERT INTO c_tbl(a,b) VALUES (1, 11);
      SELECT * FROM c_tbl;
      
    --CHECK : 값이 A아니면 B, 두개가 나오는 컬럼에 설정
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
      
    --FOREIGN KEY : 다른 테이블의 기본키나 일반키를 참조하는 컬럼이다
    --              또한, 외래키가 존재하는 테이블이 자식엔터티, 참조의 대상이되는 키가
    --              있는 테이블이 부모엔터티이다. 두 엔터티사이에서 릴레이션 선을 그릴때는
    --              참조의 대상이되는 키가 기본키면 실선(주식별자관계)으로 그리고
    --                                  외래키면 점선(비식별자관계)으로 그린다.
      --카디넬리티 : 1:1, 1:M, M:N 관계를 맺고있는 행 수
      --ex) 한 사원은 여러 경력을 갖는다 - 1:M 관계 - 1쪽의 관계선은 선 하나,
      --                                          M쪽의 관계선은 까치발로 그려준다,
      CREATE TABLE parent_tbl(a VARCHAR2(2) PRIMARY KEY,
                          b NUMBER);
      CREATE TABLE child_tbl(c NUMBER PRIMARY KEY,
                             d VARCHAR2(2) CONSTRAINT childtbl_d_fk REFERENCES parent_tbl(a));
      INSERT INTO parent_tbl(a, b) VALUES ('fi', 1);
      INSERT INTO child_tbl(c, d) VALUES (10, 'fi');
      INSERT INTO child_tbl(c, d) VALUES (10, 'ss'); -- ss라는 컬럼은 parent_tbl에 존재하지 않으므로 오류난다.
      SELECT * FROM parent_tbl;
      SELECT * FROM child_tbl;
      
    --테이블레벨 제약조건 설정 : NOT NULL 제약조건만큼은 컬럼레벨 제약조건으로 설정한다.
      CREATE TABLE f_tbl(a NUMBER,
                         b VARCHAR2(2),
                         c DATE,
                         CONSTRAINT ftbl_a_pk PRIMARY KEY(a),
                         CONSTRAINT ftbl_b_ck CHECK(a >= 0),
                         CONSTRAINT ftbl_c_fk FOREIGN KEY(b) REFERENCES parent_tbl(a)
                        );