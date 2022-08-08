ALTER USER hr ACCOUNT UNLOCK;
ALTER USER hr IDENTIFIED BY hr;

--계정생성 : 계정을 생성했다고 해서 OracleDB의 접속탭에는 이제 막 만든 계정이 보이지않음
CREATE USER test IDENTIFIED BY test;
--계정 권한설정 : GRANT ~ TO ~
  --CREATE SESSION : 다른 섹션에서 계정 접속 허용
  --CREATE TABLE : 테이블 생성권한 허용
  --롤(ROLE) 사용 : CONNECT, RESOURCE, 권한 : CREATE VIEW
  --CONNECT : 왠만한 접속 권한 설정을 모두 허용
  --RESOURCE : 왠만한 소스 생성권한을 모두 허용
  --CREATE VIEW : 뷰 생성권한 허용
  GRANT CREATE SESSION, CREATE TABLE  TO test;
  GRANT CONNECT, RESOURCE, CREATE VIEW TO test;

--계정 권한설정 해제
  --REVOKE ~ FROM ~
  REVOKE CREATE VIEW FROM test;
