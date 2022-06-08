--Function(프로시져와 다른점은 return값을 갖는것인가
  --예제1
    CREATE OR REPLACE FUNCTION a_func(num1 NUMBER, num2 NUMBER)
    RETURN NUMBER
    IS
      num3 NUMBER;
    BEGIN
      num3 := num1 + num2; 
      RETURN num3;
    END;
    /
  
    SELECT a_func(1, 2) FROM dual;
    
  --예제2(start_row)
  CREATE OR REPLACE FUNCTION start_row (current_page NUMBER, cnt_per_page NUMBER)
  RETURN NUMBER
  IS
    result NUMBER;
  BEGIN
    result := 1 + (cnt_per_page * (current_page - 1));
    RETURN result;
  END;
  /
  
  SELECT start_row(1, 10), -- 1 = 1 + 10*0
         start_row(2, 10), --11 = 1 + 10*1
         start_row(3, 10), --21 = 1 + 10*2
         start_row(1, 4), --1 
         start_row(2, 4), --5 
         start_row(3, 4)  --9 
  FROM dual;
  
  --예제3(end_row)
  CREATE OR REPLACE FUNCTION end_row (current_page NUMBER, cnt_per_page NUMBER)
  RETURN NUMBER
  IS
    result NUMBER;
  BEGIN
    result := cnt_per_page * current_page;
    RETURN result;
  END;
  /
  
  SELECT end_row(1, 10), -- 10 = 10*1 = cnt_per_page*current_page 
         end_row(2, 10), --20 = 10*2
         end_row(3, 10), --30 = 10*3
         end_row(1, 4), --4 = 4*1
         end_row(2, 4), --8 = 4*2
         end_row(3, 4)  --12 = 4*3
  FROM dual;
  