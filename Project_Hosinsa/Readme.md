개발 도구
Eclipse (java)
Oracle (database)

********* 데이터 베이스 *********

create table MUSINSA (
    SEQ_NUM      NUMBER (4) NOT NULL PRIMARY KEY,     
    PRODUCT              VARCHAR2(200) ,
    PRODUCT_NUM          VARCHAR2(10)  ,
    BRAND                VARCHAR2(50)  ,
    CATEGORY             VARCHAR2(50)  ,
    PRICE                NUMBER(7)     ,
    STOCK                NUMBER(4) 
); // 데이터베이스 테이블

create SEQUENCE seq_num
    start with 1
    increment by 1;
  // 시퀀스 넘버
