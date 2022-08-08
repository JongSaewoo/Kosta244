--1. �޿��� 4000 ���� ���� ������� �μ��� �޿���ո� ����Ͻÿ�. �� �޿������
--�Ҽ������� 2 �ڸ����� �ݿø��մϴ�.
--SubQuery
--�ڡڛ����� �̰� �� �ȵǴ��������غ���
SELECT department_id, ROUND(AVG(SELECT salary
                                FROM employees
                                WHERE salary > 4000), 1)
FROM employees
GROUP BY department_id;
-----------
SELECT department_id, ROUND(AVG(salary), 1)
FROM employees
WHERE salary IN (SELECT salary
                 FROM employees
                 WHERE salary > 4000)
GROUP BY department_id;
--JOIN
SELECT e.department_id, ROUND(AVG(e.salary), 1)
FROM employees e LEFT JOIN departments d ON (e.department_id = d.department_id)
WHERE e.salary > 4000
GROUP BY e.department_id;

--2. �μ���ġ�� ���� ���� ����� �����ϰ� �μ��� �޿������ ����Ͻÿ�.�� �޿������
--�Ҽ������� 2 �ڸ����� �ݿø��մϴ�.
--SubQuery
SELECT department_id, ROUND(AVG(salary), 1)
FROM employees
WHERE salary IN (SELECT salary
                 FROM employees
                 WHERE salary > 4000) 
                 AND department_id IS NOT NULL
GROUP BY department_id;
--JOIN
SELECT e.department_id, ROUND(AVG(e.salary), 1)
FROM employees e JOIN departments d ON (e.department_id = d.department_id)
WHERE e.salary > 4000
GROUP BY e.department_id;

--3. �μ���ġ�� ���� ���� ����� �����ϰ� �޿������ 10000 �̻��� �μ��� �޿������
--����Ͻÿ�.�� �޿������ �Ҽ������� 2 �ڸ����� �ݿø��մϴ�.
--�Ϲ�
SELECT department_id �μ���ȣ, ROUND(AVG(salary), 2) �޿����
FROM employees
WHERE department_id IS NOT NULL
GROUP BY department_id
HAVING AVG(salary) > 10000;

--4. ��å(job_title)�� 'President'�� ����� ���, �̸��� ����Ͻÿ�: (NaturalJOIN ���� ǥ��)
--SubQuery�� Ǯ �ʿ䰡 ����
--NATURAL JOIN
SELECT employee_id ���, first_name || ' ' || last_name �̸�
FROM employees NATURAL JOIN jobs
WHERE job_title = 'President';

--5. ��å(job_title)�� 'President'�� �ƴ� ����� ���, �̸��� ����Ͻÿ�(JOIN USING ���� ǥ��)
--SubQuery�� Ǯ �ʿ䰡 ����
--JOIN USING
SELECT employee_id ���, first_name || ' ' || last_name �̸�
FROM employees JOIN jobs USING (job_id) 
WHERE job_title <> 'President';

--6. ��å(job_title)�� 'President'�� �ƴ� ����� ���, �̸��� ����Ͻÿ� (SubQuery ���)
--SubQuery
SELECT employee_id ���, first_name || ' ' || last_name �̸�
FROM employees, jobs
WHERE job_title <> (SELECT job_title
                    FROM jobs
                    WHERE job_title = 'President');
                    
SELECT employee_id, first_name
FROM employee e
WHERE e.job_id IN (SELECT job_id
                   FROM jobs
                   WHERE job_title <> 'President');

--JOIN ON
 SELECT employee_id ���, first_name || ' ' || last_name �̸�
 FROM employees e JOIN jobs j ON (e.job_id = j.job_id)
 WHERE job_title <> 'President';

--7. �μ���ȣ, �μ���, �μ��� �޿��Ѿװ� ��ձ޿��� ����Ͻÿ�.
--�μ����� ����� ��� ����Ͻÿ�.
--��, �޿��Ѿ��� ���� ������ �����ϰ�,
--��ձ޿��� �Ҽ��� ���� 1 �ڸ����� ��Ÿ������ �ݿø��ؾ� �Ѵ� (JOIN ���)
--�ڡ�SubQuery�� �����ִ��� �����
--JOIN
SELECT d.department_id �μ���ȣ, department_name �μ���, SUM(salary) �޿��Ѿ�, ROUND(AVG(salary), 1) ��ձ޿�
FROM departments d  RIGHT JOIN employees e ON (d.department_id = e.department_id)
GROUP BY d.department_id, department_name
ORDER BY �޿��Ѿ�;

--8. �μ���ȣ, �μ���, �μ��� �޿��Ѿװ� ��ձ޿��� ����Ͻÿ�. (Scalar Query ���)
--��, �޿��Ѿ��� ���� ������ �����ϰ�,
--��ձ޿��� �Ҽ��� ���� 1 �ڸ����� ��Ÿ������ �ݿø��ؾ� �Ѵ�.
--SubQuery
SELECT d.department_id �μ���ȣ, department_name �μ���, SUM(salary) �޿��Ѿ�, ROUND(AVG(salary), 1) ��ձ޿�
FROM departments d JOIN employees e ON (d.department_id = e.department_id)
GROUP BY d.department_id, department_name
ORDER BY �޿��Ѿ�;

SELECT last_name
FROM employees
WHERE last_name = 'Davies';

--9. 'Seattle'�� 'Toronto'���ÿ�  �ٹ��ϴ�
--������� ���, �̸�, �μ�ID, �μ����� ����Ͻÿ�
SELECT e.employee_id ���, e.first_name||' '|| e.last_name �̸�, 
        e.department_id �μ���ȣ, d.department_name �μ���
FROM employees e JOIN departments d ON (e.department_id = d.department_id)
                 JOIN locations l ON (d.location_id = l.location_id)
WHERE city IN ('Seattle', 'Toronto');

--10.���� 'Davies'�� ����� ���� �μ��� �ٹ��ϴ� ������� ���, ��, �̸��� ����Ͻÿ�(SUBQUERY)
--SubQuery
SELECT employee_id ���, last_name ��, first_name || ' ' || last_name �̸�
FROM employees 
WHERE department_id = (SELECT department_id
                       FROM employees
                       WHERE last_name = 'Davies');

--11.���� 'Davies'�� ����� ���� �μ��� �ٹ��ϸ鼭 
--�μ���ձ޿����� ���� �޿��� �޴� ����� ���, ��, �̸�, �޿��� ����Ͻÿ�(SUBQUERY)
--SubQuery
--11-1. ���� 'Davies'�� ����� ���� �μ��� �ٹ��ϴ� �����
SELECT department_id
FROM employees
WHERE last_name = 'Davies';

--11-2. 'Davies'�� ���� �ٹ��ϴ� ������� �μ� ��ձ޿�
SELECT department_id, ROUND(AVG(salary), 0)
FROM employees
GROUP BY department_id
HAVING department_id = (SELECT department_id
                        FROM employees
                        WHERE last_name = 'Davies');
--11-3. ���� 'Davies'�� ����� ���� �μ��� �ٹ��ϸ鼭 
--      �μ���ձ޿����� ���� �޿��� �޴� ����� ���, ��, �̸�, �޿��� ����Ͻÿ�
SELECT employee_id ���, last_name ��, first_name || ' ' || last_name �̸�, salary �޿�
FROM employees
WHERE department_id = (SELECT department_id
                       FROM employees
                       WHERE last_name = 'Davies') 
                       AND salary > 
                       
                   


  

  
  
