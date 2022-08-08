--PL/SQL (PROCEDURE, FUNCTION, TRIGGER)
  --SQL로는 변수선언, 조건처리, 반복처리불가
  --PL/SQL은 SQL, 변수선언, 조건처리, 반복처리 가능
  --ex) INSERT --송신--> DMBS
  --    UPDATE --송신-->
  --    DELETE --송신-->
  
--프로시져
  --예제1(IS는 선언부)
  CREATE OR REPLACE PROCEDURE a_proc(num1 NUMBER, num2 NUMBER)
  IS num3 NUMBER := 0; --num3 변수를 만들고 초기값을 0으로 세팅한다.
  BEGIN
    num3 := num1 + num2;
    DBMS_OUTPUT.PUT_LINE('숫자합 : ' || num3);
  END;
  /
  
  EXEC a_proc(1, 2); -- 보기 : DMBS출력 -> 이클립스처럼 콘솔창이 생김
  --                   실행하면 콘솔창에 '숫자합 : 3'이 출력됨
  
  --예제2(IF THEN ~ ELSE/ELSIF ~ END IF)
  CREATE OR REPLACE PROCEDURE b_proc(num NUMBER)
  IS
  BEGIN
    IF MOD(num, 2) = 1 THEN
        DBMS_OUTPUT.PUT_LINE('홀수입니다');
    ELSE
        DBMS_OUTPUT.PUT_LINE('짝수입니다');
    END IF;
    
    IF num > 10 THEN
        DBMS_OUTPUT.PUT_LINE('10보다 큽니다');
    ELSIF num > 5 THEN
        DBMS_OUTPUT.PUT_LINE('5보다 큽니다');
    ELSE
        DBMS_OUTPUT.PUT_LINE('5이하입니다');
    END IF;
  END;
  /
  
  EXEC b_proc(15);

  --예제3(FOR IN LOOP ~ END LOOP)
  CREATE OR REPLACE PROCEDURE c_proc --매개변수가 없을때는 괄호를 삭제
  IS
  BEGIN
    FOR i IN 1..10 LOOP --1부터 10까지
        DBMS_OUTPUT.PUT_LINE(i);
    END LOOP;
  END;
  /
  
  EXEC c_proc;
  
  --예제4(SELECT절을 v_sum에 INTO대입하기)
  CREATE OR REPLACE PROCEDURE d_proc(v_department_id employees.department_id%TYPE)
  IS v_sum NUMBER;
     v_department_name departments.department_name%TYPE;
  BEGIN
    SELECT SUM(salary) INTO v_sum
    FROM employees
    WHERE department_id = v_department_id;
    DBMS_OUTPUT.PUT_LINE(v_department_id || '번 부서의 급여합은 ' || v_sum);
    
    SELECT department_name INTO v_department_name
    FROM departments
    WHERE department_id = v_department_id;
    DBMS_OUTPUT.PUT_LINE(v_department_id || '번 부서이름은 ' || v_department_name);
    
    INSERT INTO a_tbl(a) VALUES (v_department_id);
  END;
  /
  
  EXEC d_proc(50);
  SELECT * FROM a_tbl; --바로 위 프로시져에 INSERT가 잘 실행됬는지 확인
  
  --예제5(CURSOR ~ IS ~)
  CREATE OR REPLACE PROCEDURE e_proc(v_salary employees.salary%TYPE)
  IS v_salary2 employees.salary%TYPE;
  BEGIN
    SELECT salary INTO v_salary2
    FROM employees
    WHERE salary > v_salary;
    DBMS_OUTPUT.PUT_LINE(v_salary2);
  END;
  /
  
  EXEC e_proc(5000); --X : 위 프로시져가 여러행을 반환하기 때문에 실행안됨
  
  CREATE OR REPLACE PROCEDURE e_proc(v_salary employees.salary%TYPE)
  IS 
    CURSOR c1 IS
        SELECT *
        FROM employees
        WHERE salary > v_salary;
  BEGIN
    FOR e IN c1 LOOP
        DBMS_OUTPUT.PUT_LINE(e.employee_id || '-' || e.salary);
    END LOOP;
  END;
  /
  
  EXEC e_proc(5000); --O : CURSOR를 이용한 다중행 다중결과 출력