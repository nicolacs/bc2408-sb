- 開mvn project
- 改application -> .yml
  - server:
  -   port:80xx
-   開folder 
    -   Create : controller + impl , service + impl , model
-   睇題目, 出JSON 一堆String -> 開個CalculateResponse class for response OBJ
    -   寫曬attribute , @...
-   睇題目, 2條Get, 1條Post
    -   In controller, 
-   
-   controller call service, service call db

***唔開咁多CLASS, 分開D CONTROLLER既話
-分離唔到你D CASE/PROJECTS
-方便management 
-恆生PROJECT 3WEEKS, GOV PROJECT 2MONTH, 如放同一個CLASS 又run唔到, 又test唔到
-分layer 就可以減testcase
    -eg: 10個if 既method, 一個method寫曬(testcase=2*2*2*2^10), 同分10個method(testcase = 2*10only)