sqlplus -S test/test << EOF
CREATE TABLE "test_docker"
   (    "a" VARCHAR2(10 BYTE),
        "b" NUMBER(10),
     CONSTRAINT "test_docker_a_PK" PRIMARY KEY ("a")
   );
exit
EOF