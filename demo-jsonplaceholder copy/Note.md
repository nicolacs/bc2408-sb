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

# Step
- Controller call Service call Respository

## GlobalExceptionalHandler
- 全局捕捉 ->統一攔截
  
## mvn
- mvn compile (main code)
- mvn test-compile (main compile + test compile)
- mvn test (main compile + test compile + test), test -> bean cycle + server starts (i.e. check autowired)
- mvn package (all the above + generate jar)
- mvn install (all the above + copy the jar from project to m2)

* JPHMapper -> object (bean) -> map()
  // UserDTO.class (Controller Layer)
  // User.class (Service Layer)
  // UserEntity.class (Repository Layer)

### API DESIGN
-GET , POST, PUT, PATCH, DELETE(小用)

## @OneToMany
- cascade = CascadeType.ALL =>> delete 
- fetch = FetchType.LAZY  =>> select post by id時-