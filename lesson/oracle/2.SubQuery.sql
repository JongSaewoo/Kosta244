--서브쿼리
  --위치상
    --Scalar Query : SELECT절의 SubQuery
    --Inline View : FROM절의 SubQuery
    --SubQuery : WHERE절의 SubQuery
    --ex) SELECT (SELECT ~)
    --    FROM (SELECT ~)
    --    WHERE 컬럼 비교연산자(SELECT ~)
    --서브쿼리의 반환행수에 따라
      --단일행서브쿼리 : 메인쿼리와 비교시 일반비교연산자 사용
      --여러행서브쿼리 : 메인쿼리와 비교시 ANY, ALL, IN연산자 사용
      
  --최대급여를 출력하시오
  SELECT MAX(salary)
  FROM employees;
  
  --최대급여를 받는 사원의 사번, 급여를 출력하시오
  SELECT employee_id, MAX(salary)
  FROM employees
  GROUP BY employee_id; --X
  --1)최대급여값을 출력한다
  --2)1)과 같은 급여를 받는 사원의 사번, 급여를 출력하시오
  SELECT employee_id, salary
  FROM employees
  WHERE salary = (SELECT MAX(salary) FROM employees);
  
  --부서별 최대급여를 출력하시오
  SELECT MAX(salary) 
  FROM employees 
  GROUP BY department_id;
  
  --부서별 최대급여를 받는 사원의 부서번호, 사번, 급여를 출력하시오
  SELECT department_id, employee_id, MAX(salary)
  FROM employees
  GROUP BY department_id;--X
  --1)부서벌 최대급여 출력
  --2)1)의값과 같은 급여를 받는 사원의 사번, 급여를 출력
  SELECT employee_id, salary
  FROM employees
  WHERE salary = (SELECT MAX(salary) 
                  FROM employees 
                  GROUP BY department_id);
                  --여러행 서브쿼리 X
  SELECT employee_id, salary
  FROM employees
  WHERE salary  IN (SELECT MAX(salary) 
                    FROM employees 
                    GROUP BY department_id);
                    --여러행 서브쿼리 O
  SELECT employee_id, salary
  FROM employees
  WHERE salary  = ANY (SELECT MAX(salary) 
                       FROM employees 
                       GROUP BY department_id);
                       --여러행 서브쿼리 O
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
  --부서별 최대급여를 받는 사원의 부서번호, 사번, 급여를 출력하시오
  SELECT employee_id, department_id, salary
  FROM employees
  WHERE salary IN (SELECT MAX(salary) 
                    FROM employees 
                    GROUP BY department_id)
  ORDER BY department_id, salary; --여러행 서브쿼리 O --결과X
  
  SELECT employee_id, department_id, salary
  FROM employees
  WHERE (department_id, salary) IN (SELECT department_id, MAX(salary) 
                                    FROM employees 
                                    GROUP BY department_id)
  ORDER BY department_id, salary; --여러행여러컬럼 서브쿼리 O --결과O
------------------------------------------------------------------
  --Inline View
    --급여가 적은 사원부터 사번, 급여를 출력하시오(107건)
    SELECT employee_id, salary
    FROM employees
    ORDER BY salary;

    --급여가 적은 사원부터 사번, 급여를 출력하시오(107건)
    --급여가 적은 사원들을 5명까지만 순서대로 출력
    SELECT rownum, employee_id, salary
    FROM employees
    WHERE rownum <= 5
    ORDER BY salary; -- X
    
    SELECT rownum, employee_id, salary
    FROM (SELECT *
          FROM employees
          ORDER BY salary)
    WHERE rownum <= 5; -- O
    
    --급여가 적은 사원부터 사번, 급여를 출력하시오(107건)
    --급여가 적은 사원들을 11번째 사원부터 20번째 사원까지만 순서대로 출력
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
--스칼라쿼리
--사원의 사번, 부서번호, 부서명을 출력하시오
SELECT employee_id, department_id, (SELECT department_name
                                    FROM departments d
                                    WHERE d.department_id = e.department_id)
FROM employees e;
------------------------------------------------------------------

  
  