--���տ����� : ������ UNION(�ڵ����ĵ�) - �ߺ��� ������ ���� ���
--                  UNION ALL(���������ؾߵ�) - �ߺ��� ������ ���� ��� 
--                  ���� �÷��� ��ȸ�ؾߵ�.
--            ������ INTERSECT - �ߺ��� ���� ���
--            ������ MINUS - A-B = A�������� A��B�� �ߺ��Ȱ�(INTERSECT)�� ������ ���� ���
             
  --������������ <A>
  SELECT employee_id, job_id
  FROM job_history
  ORDER BY employee_id;
  
  --������������ <B>
  SELECT employee_id, job_id
  FROM employees
  ORDER BY employee_id;
  
  --����� ��������<A>�� ��������<B>�� ��� ����Ͻÿ�
  SELECT employee_id ���, job_id, TO_CHAR(start_date), end_date
  FROM job_history
  UNION
  SELECT employee_id, job_id, '��������', null
  FROM employees
  ORDER BY ���, 3;
  
  --���������� ���������� ����ϴ� ������� ����Ͻÿ�
  SELECT employee_id, job_id
  FROM job_history
  INTERSECT
  SELECT employee_id, job_id
  FROM employees
  ORDER BY employee_id;
  
  --���������� ���������� ������� �ʴ� ������� ����Ͻÿ�
  SELECT employee_id, job_id
  FROM employees
  MINUS
  SELECT employee_id, job_id
  FROM job_history;
  
  --����� ���� �μ���ȣ�� ����Ͻÿ�
    --���տ����ڸ� �̿�
      SELECT department_id
      FROM departments
      MINUS
      SELECT department_id
      FROM employees;
      
    --SubQuery�� �̿�
      SELECT * 
      FROM departments
      WHERE NOT EXISTS (SELECT * 
                        FROM employees
                        WHERE department_id = departments.department_id);
        

  