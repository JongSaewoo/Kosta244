--------------------------------------------------------
--  파일이 생성됨 - 수요일-5월-25-2022   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table A_TBL
--------------------------------------------------------

  CREATE TABLE "HR"."A_TBL" 
   (	"A" NUMBER, 
	"B" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into HR.A_TBL
SET DEFINE OFF;
Insert into HR.A_TBL (A,B) values (1,2);
Insert into HR.A_TBL (A,B) values (1,null);
Insert into HR.A_TBL (A,B) values (50,null);
Insert into HR.A_TBL (A,B) values (50,null);
--------------------------------------------------------
--  Constraints for Table A_TBL
--------------------------------------------------------

  ALTER TABLE "HR"."A_TBL" MODIFY ("A" CONSTRAINT "ATBL_A_NN" NOT NULL ENABLE);
