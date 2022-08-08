--PL/SQL (PROCEDURE, FUNCTION, TRIGGER)
  --SQL�δ� ��������, ����ó��, �ݺ�ó���Ұ�
  --PL/SQL�� SQL, ��������, ����ó��, �ݺ�ó�� ����
  --ex) INSERT --�۽�--> DMBS
  --    UPDATE --�۽�-->
  --    DELETE --�۽�-->
  
--���ν���
  --����1(IS�� �����)
  CREATE OR REPLACE PROCEDURE a_proc(num1 NUMBER, num2 NUMBER)
  IS num3 NUMBER := 0; --num3 ������ ����� �ʱⰪ�� 0���� �����Ѵ�.
  BEGIN
    num3 := num1 + num2;
    DBMS_OUTPUT.PUT_LINE('������ : ' || num3);
  END;
  /
  
  EXEC a_proc(1, 2); -- ���� : DMBS��� -> ��Ŭ����ó�� �ܼ�â�� ����
  --                   �����ϸ� �ܼ�â�� '������ : 3'�� ��µ�
  
  --����2(IF THEN ~ ELSE/ELSIF ~ END IF)
  CREATE OR REPLACE PROCEDURE b_proc(num NUMBER)
  IS
  BEGIN
    IF MOD(num, 2) = 1 THEN
        DBMS_OUTPUT.PUT_LINE('Ȧ���Դϴ�');
    ELSE
        DBMS_OUTPUT.PUT_LINE('¦���Դϴ�');
    END IF;
    
    IF num > 10 THEN
        DBMS_OUTPUT.PUT_LINE('10���� Ů�ϴ�');
    ELSIF num > 5 THEN
        DBMS_OUTPUT.PUT_LINE('5���� Ů�ϴ�');
    ELSE
        DBMS_OUTPUT.PUT_LINE('5�����Դϴ�');
    END IF;
  END;
  /
  
  EXEC b_proc(15);

  --����3(FOR IN LOOP ~ END LOOP)
  CREATE OR REPLACE PROCEDURE c_proc --�Ű������� �������� ��ȣ�� ����
  IS
  BEGIN
    FOR i IN 1..10 LOOP --1���� 10����
        DBMS_OUTPUT.PUT_LINE(i);
    END LOOP;
  END;
  /
  
  EXEC c_proc;
  
  --����4(SELECT���� v_sum�� INTO�����ϱ�)
  CREATE OR REPLACE PROCEDURE d_proc(v_department_id employees.department_id%TYPE)
  IS v_sum NUMBER;
     v_department_name departments.department_name%TYPE;
  BEGIN
    SELECT SUM(salary) INTO v_sum
    FROM employees
    WHERE department_id = v_department_id;
    DBMS_OUTPUT.PUT_LINE(v_department_id || '�� �μ��� �޿����� ' || v_sum);
    
    SELECT department_name INTO v_department_name
    FROM departments
    WHERE department_id = v_department_id;
    DBMS_OUTPUT.PUT_LINE(v_department_id || '�� �μ��̸��� ' || v_department_name);
    
    INSERT INTO a_tbl(a) VALUES (v_department_id);
  END;
  /
  
  EXEC d_proc(50);
  SELECT * FROM a_tbl; --�ٷ� �� ���ν����� INSERT�� �� ��������� Ȯ��
  
  --����5(CURSOR ~ IS ~)
  CREATE OR REPLACE PROCEDURE e_proc(v_salary employees.salary%TYPE)
  IS v_salary2 employees.salary%TYPE;
  BEGIN
    SELECT salary INTO v_salary2
    FROM employees
    WHERE salary > v_salary;
    DBMS_OUTPUT.PUT_LINE(v_salary2);
  END;
  /
  
  EXEC e_proc(5000); --X : �� ���ν����� �������� ��ȯ�ϱ� ������ ����ȵ�
  
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
  
  EXEC e_proc(5000); --O : CURSOR�� �̿��� ������ ���߰�� ���