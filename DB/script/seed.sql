use litcodedb;
SET GLOBAL TIME_ZONE = 'Asia/Seoul';

INSERT INTO users (id,user_id,name,email,password,solved_id,created,updated) VALUE (1,'xymon2','성빈','test@email.com','$2y$10$aWO50OhBjHY2ePv5J47fWuxV8jZo0jntOssEsk5lmkUhU8Edr9s.S','[1]','2021-01-14 15:52:44','2021-01-14 15:52:44');

INSERT INTO problems (id,name,description,sample_input) VALUE (1,'deepcopy','deepcopyproblem','{"a":1,"b":2}');
INSERT INTO skeleton_code(id,problem_id,language,code) VALUE (1,1,'JavaScript','JS solution');
INSERT INTO skeleton_code(id,problem_id,language,code) VALUE (2,1,'Python3','Python3 solution');
INSERT INTO skeleton_code(id,problem_id,language,code) VALUE (3,1,'Java','Python3 solution');
INSERT INTO test_cases(id,problem_id,input,output,score) VALUE (1,1,'1','1',5);

INSERT INTO problems (id,name,description,sample_input) VALUE (2,'sort','sortproblem','[1,5,2]');
INSERT INTO skeleton_code(id,problem_id,language,code) VALUE (4,2,'JavaScript','JS solution');
INSERT INTO skeleton_code(id,problem_id,language,code) VALUE (5,2,'Python3','Python3 solution');
INSERT INTO skeleton_code(id,problem_id,language,code) VALUE (6,2,'Java','JAVAsolution');
INSERT INTO test_cases(id,problem_id,input,output,score) VALUE (2,2,'[1,4,2]','[1,2,4]',10);

