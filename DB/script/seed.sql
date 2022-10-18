use litcodedb;
SET GLOBAL TIME_ZONE = 'Asia/Seoul';

INSERT INTO users (id,user_id,email,password,solved_id,created,updated) VALUE (1,'xymon2','test@email.com','aaa','[1]','2021-01-14 15:52:44','2021-01-14 15:52:44');
INSERT INTO problems (id,name,description,sample_input) VALUE (1,'deepcopy','deepcopyproblem','{"a":1,"b":2}');
INSERT INTO skeleton_code(id,problem_id,language,code) VALUE (1,1,'JS','function solution');