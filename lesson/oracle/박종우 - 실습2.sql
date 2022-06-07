--1. 급여가 4000보다 많은 사원들의 부서별 급여평균를 출력하시오. 단 급여평균은 소숫점이하2자리에서 반올림합니다.
SELECT department_id 부서번호, ROUND(AVG(salary),1) 급여평균
FROM employees
WHERE salary > 4000
GROUP BY department_id;

--2. 부서들의 부서번호, 평균급여, 최대급여, 최소급여를 출력하시오
--단 부서없는 사원과 30번부서사원, 50번부서사원은 제외한다
SELECT department_id 부서번호, AVG(salary) 평균급여, MAX(salary) 최대급여, MIN(salary) 최소급여
FROM employees
WHERE department_id IS NOT NULL AND department_id NOT IN (30, 50) 
GROUP BY department_id;

--3. 다음과 같은 20번부서 총급여, 50번부서 총급여, 80번부서 총급여, 90번부서 총급여, 전사원 총급여가 나타나도록 
--그룹함수와 DECODE함수를 사용하시오.(행수는 하나)
--20번부서 급여         50번부서총급여      80번부서총급여       90번부서 총급여       전사원 총급여
-----------------    ---------------   ---------------     ---------------     -------------
--  19000                156400            304500               58000              691416
SELECT SUM(DECODE(department_id, 20, salary)) "20번부서 급여", 
        SUM(DECODE(department_id, 50, salary)) "50번부서 급여",
        SUM(DECODE(department_id, 80, salary)) "80번부서 급여",
        SUM(DECODE(department_id, 90, salary)) "90번부서 급여",
        SUM(salary) "전사원 총급여"
FROM employees;

--4.지역별 지역번호(location_id), 부서수를 출력하시오. 부서수가 10개 이상인 지역만 출력한다
SELECT location_id 지역번호, COUNT(*) 부서수
FROM departments
GROUP BY location_id
HAVING COUNT(*) >= 10;

--5.년도별 입사자수를 출력하시오, 단 최근년도부터 출력한다
SELECT TO_CHAR(HIRE_DATE,'yyyy')||'년' 입사년도, COUNT(*)||'명' 입사자수
FROM EMPLOYEES
GROUP BY TO_CHAR(HIRE_DATE,'yyyy')
ORDER BY 입사년도 DESC;

--6. 하반기(7~12월) 월별 입사자수를 출력하시오. 입사자수가 5명이상인 경우만 출력한다
SELECT TO_CHAR(HIRE_DATE,'mm')||'달' 월, COUNT(*)||'명' 입사자수
FROM EMPLOYEES
WHERE TO_NUMBER(TO_CHAR(HIRE_DATE,'mm')) >= 7 
GROUP BY TO_CHAR(HIRE_DATE,'mm')
HAVING COUNT(*) >= 5
ORDER BY 월;