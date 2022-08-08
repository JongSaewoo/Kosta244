--��������
  --��ġ��
    --Scalar Query : SELECT���� SubQuery
    --Inline View : FROM���� SubQuery
    --SubQuery : WHERE���� SubQuery
    --ex) SELECT (SELECT ~)
    --    FROM (SELECT ~)
    --    WHERE �÷� �񱳿�����(SELECT ~)
    --���������� ��ȯ����� ����
      --�����༭������ : ���������� �񱳽� �Ϲݺ񱳿����� ���
      --�����༭������ : ���������� �񱳽� ANY, ALL, IN������ ���
      
  --�ִ�޿��� ����Ͻÿ�
  SELECT MAX(salary)
  FROM employees;
  
  --�ִ�޿��� �޴� ����� ���, �޿��� ����Ͻÿ�
  SELECT employee_id, MAX(salary)
  FROM employees
  GROUP BY employee_id; --X
  --1)�ִ�޿����� ����Ѵ�
  --2)1)�� ���� �޿��� �޴� ����� ���, �޿��� ����Ͻÿ�
  SELECT employee_id, salary
  FROM employees
  WHERE salary = (SELECT MAX(salary) FROM employees);
  
  --�μ��� �ִ�޿��� ����Ͻÿ�
  SELECT MAX(salary) 
  FROM employees 
  GROUP BY department_id;
  
  --�μ��� �ִ�޿��� �޴� ����� �μ���ȣ, ���, �޿��� ����Ͻÿ�
  SELECT department_id, employee_id, MAX(salary)
  FROM employees
  GROUP BY department_id;--X
  --1)�μ��� �ִ�޿� ���
  --2)1)�ǰ��� ���� �޿��� �޴� ����� ���, �޿��� ���
  SELECT employee_id, salary
  FROM employees
  WHERE salary = (SELECT MAX(salary) 
                  FROM employees 
                  GROUP BY department_id);
                  --������ �������� X
  SELECT employee_id, salary
  FROM employees
  WHERE salary  IN (SELECT MAX(salary) 
                    FROM employees 
                    GROUP BY department_id);
                    --������ �������� O
  SELECT employee_id, salary
  FROM employees
  WHERE salary  = ANY (SELECT MAX(salary) 
                       FROM employees 
                       GROUP BY department_id);
                       --������ �������� O
  SELECT employee_id, salary
  FROM employees
  WHERE salary  > ANY (SELECT MAX(salary) 
                       FROM employees 
                       GROUP BY department_id);
  SELECT employee_id, salary
  FROM employees
  WHERE salary  < ANY (SELECT MAX(salary) 
                       FROM employees 
                       GROUP BY department_id);
------------------------------------------------------------------
  --�μ��� �ִ�޿��� �޴� ����� �μ���ȣ, ���, �޿��� ����Ͻÿ�
  SELECT employee_id, department_id, salary
  FROM employees
  WHERE salary IN (SELECT MAX(salary) 
                    FROM employees 
                    GROUP BY department_id)
  ORDER BY department_id, salary; --������ �������� O --���X
  
  SELECT employee_id, department_id, salary
  FROM employees
  WHERE (department_id, salary) IN (SELECT department_id, MAX(salary) 
                                    FROM employees 
                                    GROUP BY department_id)
  ORDER BY department_id, salary; --�����࿩���÷� �������� O --���O
------------------------------------------------------------------
  --Inline View
    --�޿��� ���� ������� ���, �޿��� ����Ͻÿ�(107��)
    SELECT employee_id, salary
    FROM employees
    ORDER BY salary;

    --�޿��� ���� ������� ���, �޿��� ����Ͻÿ�(107��)
    --�޿��� ���� ������� 5������� ������� ���
    SELECT rownum, employee_id, salary
    FROM employees
    WHERE rownum <= 5
    ORDER BY salary; -- X
    
    SELECT rownum, employee_id, salary
    FROM (SELECT *
          FROM employees
          ORDER BY salary)
    WHERE rownum <= 5; -- O
    
    --�޿��� ���� ������� ���, �޿��� ����Ͻÿ�(107��)
    --�޿��� ���� ������� 11��° ������� 20��° ��������� ������� ���
    SELECT rownum, employee_id, salary
    FROM (SELECT *
          FROM employees
          ORDER BY salary)
    WHERE rownum BETWEEN 11 AND 20; --X
    
    SELECT *
    FROM (SELECT rownum r, employee_id, salary
          FROM (SELECT *
                FROM employees
                ORDER BY salary)
         )
    WHERE r BETWEEN 11 AND 20;
------------------------------------------------------------------
--��Į������
--����� ���, �μ���ȣ, �μ����� ����Ͻÿ�
SELECT employee_id, department_id, (SELECT department_name
                                    FROM departments d
                                    WHERE d.department_id = e.department_id)
FROM employees e;
------------------------------------------------------------------

  
  