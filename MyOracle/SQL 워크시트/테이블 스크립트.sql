DROP TABLE product;

--PRODUCT 테이블()
CREATE TABLE product (
    prod_no VARCHAR2(5),
    prod_name VARCHAR2(30) NOT NULL,
    prod_price NUMBER(6),
    prod_info VARCHAR2(100),
    prod_mfd DATE,
    CONSTRAINT prod_no_pk PRIMARY KEY (prod_no),
    CONSTRAINT prod_price_ck CHECK (prod_price >= 0)
);

--CUSTOMER 테이블
CREATE TABLE customer (
    "id" VARCHAR2(5),
    pwd VARCHAR2(5) NOT NULL,
    "name" VARCHAR2(30) NOT NULL,
    address VARCHAR2(30),
    status NUMBER(1),
    CONSTRAINT id_pk PRIMARY KEY ("id"),
    CONSTRAINT status_ck CHECK (status IN (0, 1))
);

--ORDER_INFO 테이블
CREATE TABLE order_info (
    order_no NUMBER,
    order_id VARCHAR2(5) NOT NULL,
    order_dt DATE NOT NULL,
    CONSTRAINT order_no_pk PRIMARY KEY (order_no),
    CONSTRAINT order_id_fk FOREIGN KEY (order_id) REFERENCES customer ("id")
);

--ORDER_LINE 테이블
CREATE TABLE order_line (
    order_no NUMBER,
    order_prod_no VARCHAR2(5),
    order_quantity NUMBER(3),
    CONSTRAINT order_no2_pk PRIMARY KEY (order_no, order_prod_no),
    CONSTRAINT order_no_fk FOREIGN KEY (order_no) REFERENCES order_info (order_no),
    CONSTRAINT order_prod_no_fk FOREIGN KEY (order_prod_no) REFERENCES product (prod_no),
    CONSTRAINT order_quantity_ck CHECK (order_quantity >= 1)
);

--ORDER_LINE 테이블과 ORDER_INFO 테이블의 ORDER_NO컬럼의 제약조건 이름 구분을
--제대로 짓지 않아 다시 수정함
ALTER TABLE order_line
RENAME CONSTRAINT order_linesNo_pk TO "order_linesNo_pk";
ALTER TABLE order_info
RENAME CONSTRAINT order_no_pk TO order_infosNo_pk;


--POINT 테이블(포인트 추가는 사람당 각자 추가됨 > 1:1관계 > 주식별자관계 > 유무관계 ㅡ)
CREATE TABLE point(
    point_id VARCHAR2(5) PRIMARY KEY,
    point_score NUMBER(3),
    CONSTRAINT point_id_fk FOREIGN KEY (point_id) REFERENCES customer ("id")
);
