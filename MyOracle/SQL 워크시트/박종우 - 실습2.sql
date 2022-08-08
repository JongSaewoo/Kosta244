--1. �޿��� 4000���� ���� ������� �μ��� �޿���ո� ����Ͻÿ�. �� �޿������ �Ҽ�������2�ڸ����� �ݿø��մϴ�.
SELECT department_id �μ���ȣ, ROUND(AVG(salary),1) �޿����
FROM employees
WHERE salary > 4000
GROUP BY department_id;

--2. �μ����� �μ���ȣ, ��ձ޿�, �ִ�޿�, �ּұ޿��� ����Ͻÿ�
--�� �μ����� ����� 30���μ����, 50���μ������ �����Ѵ�
SELECT department_id �μ���ȣ, AVG(salary) ��ձ޿�, MAX(salary) �ִ�޿�, MIN(salary) �ּұ޿�
FROM employees
WHERE department_id IS NOT NULL AND department_id NOT IN (30, 50) 
GROUP BY department_id;

--3. ������ ���� 20���μ� �ѱ޿�, 50���μ� �ѱ޿�, 80���μ� �ѱ޿�, 90���μ� �ѱ޿�, ����� �ѱ޿��� ��Ÿ������ 
--�׷��Լ��� DECODE�Լ��� ����Ͻÿ�.(����� �ϳ�)
--20���μ� �޿�         50���μ��ѱ޿�      80���μ��ѱ޿�       90���μ� �ѱ޿�       ����� �ѱ޿�
-----------------    ---------------   ---------------     ---------------     -------------
--  19000                156400            304500               58000              691416
SELECT SUM(DECODE(department_id, 20, salary)) "20���μ� �޿�", 
        SUM(DECODE(department_id, 50, salary)) "50���μ� �޿�",
        SUM(DECODE(department_id, 80, salary)) "80���μ� �޿�",
        SUM(DECODE(department_id, 90, salary)) "90���μ� �޿�",
        SUM(salary) "����� �ѱ޿�"
FROM employees;

--4.������ ������ȣ(location_id), �μ����� ����Ͻÿ�. �μ����� 10�� �̻��� ������ ����Ѵ�
SELECT location_id ������ȣ, COUNT(*) �μ���
FROM departments
GROUP BY location_id
HAVING COUNT(*) >= 10;

--5.�⵵�� �Ի��ڼ��� ����Ͻÿ�, �� �ֱٳ⵵���� ����Ѵ�
SELECT TO_CHAR(HIRE_DATE,'yyyy')||'��' �Ի�⵵, COUNT(*)||'��' �Ի��ڼ�
FROM EMPLOYEES
GROUP BY TO_CHAR(HIRE_DATE,'yyyy')
ORDER BY �Ի�⵵ DESC;

--6. �Ϲݱ�(7~12��) ���� �Ի��ڼ��� ����Ͻÿ�. �Ի��ڼ��� 5���̻��� ��츸 ����Ѵ�
SELECT TO_CHAR(HIRE_DATE,'mm')||'��' ��, COUNT(*)||'��' �Ի��ڼ�
FROM EMPLOYEES
WHERE TO_NUMBER(TO_CHAR(HIRE_DATE,'mm')) >= 7 
GROUP BY TO_CHAR(HIRE_DATE,'mm')
HAVING COUNT(*) >= 5
ORDER BY ��;