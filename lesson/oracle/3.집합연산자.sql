--집합연산자 : 합집합 UNION(자동정렬됨) - 중복을 제외한 값을 출력
--                  UNION ALL(수동정렬해야됨) - 중복을 포함한 값을 출력 
--                  같은 컬럼을 조회해야됨.
--            교집합 INTERSECT - 중복된 값을 출력
--            차집합 MINUS - A-B = A쿼리에서 A와B의 중복된값(INTERSECT)을 제외한 값을 출력
             
  --이전직무정보 <A>
  SELECT employee_id, job_id
  FROM job_history
  ORDER BY employee_id;
  
  --현재직무정보 <B>
  SELECT employee_id, job_id
  FROM employees
  ORDER BY employee_id;
  
  --사원의 이전직무<A>와 현재직무<B>를 모두 출력하시오
  SELECT employee_id 사번, job_id, TO_CHAR(start_date), end_date
  FROM job_history
  UNION
  SELECT employee_id, job_id, '현재직무', null
  FROM employees
  ORDER BY 사번, 3;
  
  --이전직무를 현재직무로 담당하는 사원들을 출력하시오
  SELECT employee_id, job_id
  FROM job_history
  INTERSECT
  SELECT employee_id, job_id
  FROM employees
  ORDER BY employee_id;
  
  --이전직무를 현재직무로 담당하지 않는 사원들을 출력하시오
  SELECT employee_id, job_id
  FROM employees
  MINUS
  SELECT employee_id, job_id
  FROM job_history;
  
  --사원이 없는 부서번호를 출력하시오
    --집합연산자를 이용
      SELECT department_id
      FROM departments
      MINUS
      SELECT department_id
      FROM employees;
      
    --SubQuery를 이용
      SELECT * 
      FROM departments
      WHERE NOT EXISTS (SELECT * 
                        FROM employees
                        WHERE department_id = departments.department_id);
        

  