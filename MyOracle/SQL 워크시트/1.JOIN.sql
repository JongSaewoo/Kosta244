--JOIN
 --사원의 사번, 이름, 급여, 부서번호, 부서명을 출력하시오
SELECT employee_id, first_name, salary, department_id
FROM employees;

SELECT department_id, department_name
FROM departments;

 --카티션프러덕트
SELECT employee_id, department_name
FROM employees, departments;

 --오라클전용 JOIN문법
SELECT employee_id, department_name
FROM employees, departments
WHERE employees.department_id = departments.department_id
ORDER BY employee_id;

 --표준화된 ANSI JOIN문법
SELECT employee_id, department_name
FROM employees JOIN departments 
    ON (employees.department_id = departments.department_id);

 --테이블 별칭주기
SELECT employee_id, first_name, salary, e.department_id, department_name
FROM employees e JOIN departments d
    ON (e.department_id = d.department_id);
--조인종류
  --조건
    --EQUI JOIN : =로 비교하는 JOIN
    --NON-EQUI JOIN : =외의 연산자로 비교하는 JOIN

  --형태
    --INNER JOIN --사원의 사번, 이름, 급여, 부서번호, 부서명을 출력하시오
      --NATURAL JOIN
        --사원의 사번, 직무번호, 직무명을 출력하시오
        SELECT employee_id, job_id, job_title
        FROM employees NATURAL JOIN jobs;
        --사원의 사번, 부서번호, 부서명을 출력하시오
        SELECT employee_id, department_id, department_name
        FROM employees NATURAL JOIN departments; 
            --출력은되나 올바르지않음, ON절 써야됨
        
      --JOIN USING
        --사원의 사번, 직무번호, 직무명을 출력하시오
        SELECT employee_id, job_id, job_name
        FROM employees JOIN jobs USING (job_id );
        --사원의 사번, 부서번호, 부서명, 부서장사번을 출력하시오
        SELECT employee_id, department_id, department_name, d.manager_id
        FROM employees e JOIN departments d USING (department_id);
        --부서의 부서번호, 부서명, 부서장사번, 부서장이름을 출력하시오
        SELECT e.department_id, department_name, manager_id, first_name
        FROM departments d JOIN employees e USING (manager_id);
             --출력은되나 올바르지않음, ON절 써야됨
        
      --JOIN ON
        --사원의 사번, 직무번호, 직무명을 출력하시오
        SELECT employee_id, e.job_id, job_title
        FROM employees e JOIN jobs j ON (e.job_id = j.job_id);
        --부서의 부서번호, 부서명, 부서장사번, 부서장이름을 출력하시오
        SELECT d.department_id, department_name, d.manager_id, first_name
        FROM departments d JOIN employees e ON (d.manager_id = e.manager_id);
        --사원의 사번, 부서번호, 부서명, 직무번호, 직무명을 출력하시오
        SELECT e.employee_id, e.first_name, e.department_id,
               d.department_name,
               j.job_id, job_title
        FROM employees e JOIN departments d ON (d.department_id = e.department_id)
                         JOIN jobs j ON (e.job_id = j.job_id);
        --부서의 부서번호, 부서가 속한 지역의 도시명(city), 국가명(country_name)을 출력하시오
        SELECT d.department_id, l.city, c.country_name
        FROM departments d JOIN locations l ON (d.location_id = l.location_id)
                           JOIN countries c ON (l.country_id = c.country_id);
        --사원의 사번, 부서번호, 부서명, 직무번호, 직무명을 출력하시오
        --단, 직무명에 'Manager'를 포함한 사원들만 출력하시오
        --직무번호순, 부서명으로 오름차순하시오(사전순으로 출력한다)
        SELECT e.employee_id, e.first_name, e.department_id,
               d.department_name,
               j.job_id, job_title
        FROM employees e JOIN departments d ON (d.department_id = e.department_id)
                         JOIN jobs j ON (e.job_id = j.job_id)
        WHERE job_title LIKE '%Manager%'
        ORDER BY 5, 4;
        --부서별 부서번호, 부서명, 사원수, 평균급여를 출력하시오
        SELECT d.department_id 부서번호, department_name 부서명, 
                COUNT(e.employee_id) 사원수, TRUNC(AVG(salary), 0) 평균급여
        FROM departments d JOIN employees e ON (d.department_id = e.department_id)
        GROUP BY d.department_id, department_name
        ORDER BY 부서번호;
        --부서별 사원수가 10명이상인 부서들의  
        --부서별 부서번호, 부서명, 사원수, 평균급여를 출력하시오
        SELECT d.department_id 부서번호, department_name 부서명, 
                COUNT(e.employee_id) 사원수, TRUNC(AVG(salary), 0) 평균급여
        FROM departments d JOIN employees e ON (d.department_id = e.department_id)
        GROUP BY d.department_id, department_name
        HAVING COUNT(e.employee_id) >= 10
        ORDER BY 부서번호;
        --사원의 사번, 이름, 관리자번호(manager_id), 관리자이름을 출력하시오
        SELECT e.employee_id, e.first_name 이름,
                m.employee_id 관리자번호, m.first_name 관리자이름
        FROM employees e JOIN employees m ON (e.manager_id = m.employee_id);
        --사원의 부서번호와 관리자의 부서번호가 일치하지 않는 
        --사원들의 사번, 부서번호를 출력하시오
        --사번순으로 정렬
        SELECT e.employee_id 사번, e.department_id 부서번호
        FROM employees e JOIN employees m ON (e.manager_id = m.employee_id)
        WHERE e.department_id <> m.department_id
        ORDER BY 사번;
        
    --OUTER JOIN 
      --사원의 사번, 이름, 급여, 부서번호, 부서명을 출력하시오
      --단, 부서번호가 없는 사원도 출력한다
      SELECT employee_id, first_name, salary, e.department_id, d.department_name
      FROM employees e LEFT OUTER JOIN departments d ON (e.department_id = d.department_id);
      
      --부서번호, 부서명, 부서가 속한 도시를 출력하시오
      SELECT department_id, department_name, city
      FROM departments d JOIN locations l ON (d.location_id = l.location_id); 
      
      --각 도시에 있는 부서수를 출력하시오
      SELECT city 도시명, COUNT(*) 부서수
      FROM locations l JOIN departments d ON (l.location_id = d.location_id)
      GROUP BY city;
      
      --각 도시에 있는 부서수를 출력하시오
      --단, 부서가 없는 도시도 모두 출력
      SELECT city 도시명, COUNT(department_id) 부서수
      FROM locations l LEFT OUTER JOIN departments d ON (l.location_id = d.location_id)
      GROUP BY city;
      
      --사원의 사번, 이름, 관리자번호, 관리자이름을 출력하시오
      --단, 관리자없는 사원도 모두 출력 --107건
      SELECT e.employee_id 사번, e.first_name 사원이름, 
            m.employee_id 관리자번호, m.first_name 관리자이름
      FROM employees e LEFT OUTER JOIN employees m ON (e.manager_id = m.employee_id);
      
      --사번, 이름, 부서번호, 부서명을 출력하시오
      --단, 사원이 없는 부서도 모두 출력 --122건
      SELECT employee_id, d.department_id, department_name
      FROM departments d LEFT OUTER JOIN employees e ON (e.department_id = d.department_id); 
      
      --사번, 이름, 부서번호, 부서명을 출력하시오
      --단, 사원이 없는 부서도 모두 출력, 부서없는 사원도 모두 출력 --
      SELECT employee_id, d.department_id, department_name
      FROM departments d FULL OUTER JOIN employees e ON (e.department_id = d.department_id);
      