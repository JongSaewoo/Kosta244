CREATE TABLE lesson(
    lsn_no NUMBER(4),
    user_id VARCHAR2(20),
    lsn_title VARCHAR2(20) NOT NULL,
    lsn_lv VARCHAR2(20) NOT NULL,
    lsn_days NUMBER(3) NOT NULL,
    lsn_intro VARCHAR2(4000) NOT NULL,
    lsn_price NUMBER(8) NOT NULL,
    lsn_per_time NUMBER(3)NOT NULL,
    lsn_cnt_sum NUMBER(2) NOT NULL,
    lsn_star_sum NUMBER(4),
    lsn_star_ppl_cnt NUMBER(4),
    lsn_status NUMBER(1) NOT NULL
);
    
