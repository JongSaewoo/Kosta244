--JOIN
 --����� ���, �̸�, �޿�, �μ���ȣ, �μ����� ����Ͻÿ�
SELECT employee_id, first_name, salary, department_id
FROM employees;

SELECT department_id, department_name
FROM departments;

 --īƼ��������Ʈ
SELECT employee_id, department_name
FROM employees, departments;

 --����Ŭ���� JOIN����
SELECT employee_id, department_name
FROM employees, departments
WHERE employees.department_id = departments.department_id
ORDER BY employee_id;

 --ǥ��ȭ�� ANSI JOIN����
SELECT employee_id, department_name
FROM employees JOIN departments 
    ON (employees.department_id = departments.department_id);

 --���̺� ��Ī�ֱ�
SELECT employee_id, first_name, salary, e.department_id, department_name
FROM employees e JOIN departments d
    ON (e.department_id = d.department_id);
--��������
  --����
    --EQUI JOIN : =�� ���ϴ� JOIN
    --NON-EQUI JOIN : =���� �����ڷ� ���ϴ� JOIN

  --����
    --INNER JOIN --����� ���, �̸�, �޿�, �μ���ȣ, �μ����� ����Ͻÿ�
      --NATURAL JOIN
        --����� ���, ������ȣ, �������� ����Ͻÿ�
        SELECT employee_id, job_id, job_title
        FROM employees NATURAL JOIN jobs;
        --����� ���, �μ���ȣ, �μ����� ����Ͻÿ�
        SELECT employee_id, department_id, department_name
        FROM employees NATURAL JOIN departments; 
            --������ǳ� �ùٸ�������, ON�� ��ߵ�
        
      --JOIN USING
        --����� ���, ������ȣ, �������� ����Ͻÿ�
        SELECT employee_id, job_id, job_name
        FROM employees JOIN jobs USING (job_id );
        --����� ���, �μ���ȣ, �μ���, �μ������� ����Ͻÿ�
        SELECT employee_id, department_id, department_name, d.manager_id
        FROM employees e JOIN departments d USING (department_id);
        --�μ��� �μ���ȣ, �μ���, �μ�����, �μ����̸��� ����Ͻÿ�
        SELECT e.department_id, department_name, manager_id, first_name
        FROM departments d JOIN employees e USING (manager_id);
             --������ǳ� �ùٸ�������, ON�� ��ߵ�
        
      --JOIN ON
        --����� ���, ������ȣ, �������� ����Ͻÿ�
        SELECT employee_id, e.job_id, job_title
        FROM employees e JOIN jobs j ON (e.job_id = j.job_id);
        --�μ��� �μ���ȣ, �μ���, �μ�����, �μ����̸��� ����Ͻÿ�
        SELECT d.department_id, department_name, d.manager_id, first_name
        FROM departments d JOIN employees e ON (d.manager_id = e.manager_id);
        --����� ���, �μ���ȣ, �μ���, ������ȣ, �������� ����Ͻÿ�
        SELECT e.employee_id, e.first_name, e.department_id,
               d.department_name,
               j.job_id, job_title
        FROM employees e JOIN departments d ON (d.department_id = e.department_id)
                         JOIN jobs j ON (e.job_id = j.job_id);
        --�μ��� �μ���ȣ, �μ��� ���� ������ ���ø�(city), ������(country_name)�� ����Ͻÿ�
        SELECT d.department_id, l.city, c.country_name
        FROM departments d JOIN locations l ON (d.location_id = l.location_id)
                           JOIN countries c ON (l.country_id = c.country_id);
        --����� ���, �μ���ȣ, �μ���, ������ȣ, �������� ����Ͻÿ�
        --��, ������ 'Manager'�� ������ ����鸸 ����Ͻÿ�
        --������ȣ��, �μ������� ���������Ͻÿ�(���������� ����Ѵ�)
        SELECT e.employee_id, e.first_name, e.department_id,
               d.department_name,
               j.job_id, job_title
        FROM employees e JOIN departments d ON (d.department_id = e.department_id)
                         JOIN jobs j ON (e.job_id = j.job_id)
        WHERE job_title LIKE '%Manager%'
        ORDER BY 5, 4;
        --�μ��� �μ���ȣ, �μ���, �����, ��ձ޿��� ����Ͻÿ�
        SELECT d.department_id �μ���ȣ, department_name �μ���, 
                COUNT(e.employee_id) �����, TRUNC(AVG(salary), 0) ��ձ޿�
        FROM departments d JOIN employees e ON (d.department_id = e.department_id)
        GROUP BY d.department_id, department_name
        ORDER BY �μ���ȣ;
        --�μ��� ������� 10���̻��� �μ�����  
        --�μ��� �μ���ȣ, �μ���, �����, ��ձ޿��� ����Ͻÿ�
        SELECT d.department_id �μ���ȣ, department_name �μ���, 
                COUNT(e.employee_id) �����, TRUNC(AVG(salary), 0) ��ձ޿�
        FROM departments d JOIN employees e ON (d.department_id = e.department_id)
        GROUP BY d.department_id, department_name
        HAVING COUNT(e.employee_id) >= 10
        ORDER BY �μ���ȣ;
        --����� ���, �̸�, �����ڹ�ȣ(manager_id), �������̸��� ����Ͻÿ�
        SELECT e.employee_id, e.first_name �̸�,
                m.employee_id �����ڹ�ȣ, m.first_name �������̸�
        FROM employees e JOIN employees m ON (e.manager_id = m.employee_id);
        --����� �μ���ȣ�� �������� �μ���ȣ�� ��ġ���� �ʴ� 
        --������� ���, �μ���ȣ�� ����Ͻÿ�
        --��������� ����
        SELECT e.employee_id ���, e.department_id �μ���ȣ
        FROM employees e JOIN employees m ON (e.manager_id = m.employee_id)
        WHERE e.department_id <> m.department_id
        ORDER BY ���;
        
    --OUTER JOIN 
      --����� ���, �̸�, �޿�, �μ���ȣ, �μ����� ����Ͻÿ�
      --��, �μ���ȣ�� ���� ����� ����Ѵ�
      SELECT employee_id, first_name, salary, e.department_id, d.department_name
      FROM employees e LEFT OUTER JOIN departments d ON (e.department_id = d.department_id);
      
      --�μ���ȣ, �μ���, �μ��� ���� ���ø� ����Ͻÿ�
      SELECT department_id, department_name, city
      FROM departments d JOIN locations l ON (d.location_id = l.location_id); 
      
      --�� ���ÿ� �ִ� �μ����� ����Ͻÿ�
      SELECT city ���ø�, COUNT(*) �μ���
      FROM locations l JOIN departments d ON (l.location_id = d.location_id)
      GROUP BY city;
      
      --�� ���ÿ� �ִ� �μ����� ����Ͻÿ�
      --��, �μ��� ���� ���õ� ��� ���
      SELECT city ���ø�, COUNT(department_id) �μ���
      FROM locations l LEFT OUTER JOIN departments d ON (l.location_id = d.location_id)
      GROUP BY city;
      
      --����� ���, �̸�, �����ڹ�ȣ, �������̸��� ����Ͻÿ�
      --��, �����ھ��� ����� ��� ��� --107��
      SELECT e.employee_id ���, e.first_name ����̸�, 
            m.employee_id �����ڹ�ȣ, m.first_name �������̸�
      FROM employees e LEFT OUTER JOIN employees m ON (e.manager_id = m.employee_id);
      
      --���, �̸�, �μ���ȣ, �μ����� ����Ͻÿ�
      --��, ����� ���� �μ��� ��� ��� --122��
      SELECT employee_id, d.department_id, department_name
      FROM departments d LEFT OUTER JOIN employees e ON (e.department_id = d.department_id); 
      
      --���, �̸�, �μ���ȣ, �μ����� ����Ͻÿ�
      --��, ����� ���� �μ��� ��� ���, �μ����� ����� ��� ��� --
      SELECT employee_id, d.department_id, department_name
      FROM departments d FULL OUTER JOIN employees e ON (e.department_id = d.department_id);
      