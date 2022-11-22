개발 도구 Eclipse (java) Oracle (database)

********* 데이터 베이스 *********

CREATE SEQUENCE SEQ_NUM
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE STOCK_OTABLE(
    SEQ_NUM   NUMBER(4) PRIMARY KEY,
    시총순위    NUMBER(4),
    종목코드    VARCHAR2(20),
    종목명     VARCHAR2(50),
    현재가     NUMBER(8),
    전일비     VARCHAR2(20),
    등락률     VARCHAR2(20),
    거래량     NUMBER(10)
);

CREATE TABLE STOCK_TABLE(
    시총순위    NUMBER(4),
    종목코드    VARCHAR2(20),
    종목명     VARCHAR2(50),
    현재가     NUMBER(8),
    전일비     VARCHAR2(20),
    등락률     VARCHAR2(20),
    거래량     NUMBER(10)
);

CREATE TABLE STOCK_ITABLE(
    시총순위    NUMBER(4),
    종목코드    VARCHAR2(20),
    종목명     VARCHAR2(50),
    현재가     NUMBER(8),
    전일비     VARCHAR2(20),
    등락률     VARCHAR2(20),
    거래량     NUMBER(10)
);

CREATE TABLE STOCK_MTABLE(
    종목코드    VARCHAR2(20),
    종목명     VARCHAR2(50),
    매입가     NUMBER(8),
    평가손익   VARCHAR2(20),
    수익률     VARCHAR2(20),
    보유수량   NUMBER(8),
    현재가     NUMBER(8),
    전일비     VARCHAR2(20),
    등락률     VARCHAR2(20),
    매입금액   NUMBER(8),
    평가금액   NUMBER(8)
);
