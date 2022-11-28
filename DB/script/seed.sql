use litcodedb;
SET GLOBAL TIME_ZONE = 'Asia/Seoul';

INSERT INTO users (id,user_id,name,email,password,solved_id,created,updated) VALUE (1,'xymon2','성빈','test@email.com','$2y$10$aWO50OhBjHY2ePv5J47fWuxV8jZo0jntOssEsk5lmkUhU8Edr9s.S','[1]','2021-01-14 15:52:44','2021-01-14 15:52:44');

INSERT INTO problems (id,name,description,sample_input) VALUE (1,'deepcopy','
## Deepcopy

There are parsed Json objects.  
Implement a function deepcopying the objects.  
Input object should not be changed even if output object has changed.

```js
const input = {
    "a" = 1;
};

const output = deepcopy(input);
output.a = 0;

console.log(input.a); // should be 1
console.log(output.a) // should be 0
```

**Sample inputs**

```bash
# sample input 1
3

# sample input 2
["3", 1, "Hello", null]

# sample input 3
{"key" : [{"foo" : "bar"}]}
```
','{"a":1}');

INSERT INTO skeleton_code(id,problem_id,language,code) VALUE (1,1,'javascript',
'function solution(input){
    return input;
}');
INSERT INTO skeleton_code(id,problem_id,language,code) VALUE (2,1,'python',
'def solution(input):
    return input');
INSERT INTO test_cases(id,problem_id,input,py_script,js_script,score) VALUE (1,1,'1312414',
'test_input = "test"
result = res_run == 1312414',
'testInput = "test"
const result = resRun === 1312414',5);
INSERT INTO test_cases(id,problem_id,input,py_script,js_script,score) VALUE (2,1,'cxzcxzcczzx',
'test_input = "test"
result = res_run == "cxzcxzcczzx"',
'testInput = "test"
const result = resRun === "string"',5);
INSERT INTO test_cases(id,problem_id,input,py_script,js_script,score) VALUE (3,1,'[1, "dascxzc"]',
'test_input[0] = "test"
result = res_run[0] == "dascxzc"',
'testInput[0] = "test"
const result = resRun[0] === "dascxzc"',5);
INSERT INTO test_cases(id,problem_id,input,py_script,js_script,score) VALUE (4,1,'{141 : "xczc", "azc" : "dasdas"}',
'test_input[141] = "test"
result = res_run[141] == "xczc"',
'testInput[141] = "test"
const result = resRun[141] === "xczc"',5);
INSERT INTO test_cases(id,problem_id,input,py_script,js_script,score) VALUE (5,1,'{142 : [1,"aaa"], "azc" : "dasdas}',
'test_input[142][1] = "test"
result = res_run[142][1] == "aaa"',
'testInput[142][1] = "test"
const result = resRun[142][1] === "aaa"',5);

INSERT INTO problems (id,name,description,sample_input) VALUE (2,'sort','sortproblem','[1,5,2]');
INSERT INTO skeleton_code(id,problem_id,language,code) VALUE (4,2,'javascript',
'function solution(input){
    return input;
}');
INSERT INTO skeleton_code(id,problem_id,language,code) VALUE (5,2,'python',
'def solution(input):
    return input');
INSERT INTO test_cases(id,problem_id,input,py_script,js_script,score) VALUE (6,2,'[1,4,2,9,-3]',
'result = res_run = [-3,1,2,4,9]',
'const result = resRun === [-3,1,2,4,9]',100);

