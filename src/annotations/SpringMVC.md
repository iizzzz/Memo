# 📌 [ Spring MVC Annotations ]
***
<br>

> ⭐ @ComponentScan
- @Component가 붙은 대상을 스프링 빈으로 등록, 보편적으로 탐색 시작위치는 프로젝트 최상단

### @ComponentScan 추가 정보
### [Attribute]
- ### excludeFilters
    - @ComponentScan.Filter(type=FilterType.ANNOTATION, classes=Configuration.class) 제외 설정
    - ↑필터 사용시 기본값의 type이 FilterType.ANNOTATION 이라서 type은 생략 가능

- ### basePackages = "hello.core" 컴포넌트 스캔 위치 지정
    - basePackages = {"hello.core", "hello.service"} 여러곳 지정 가능
    - basePackageClasses = 지정한 클래스의 패키지를 탐색 시작 위로 지정

<br>

> #### 스캔 대상
1. @Component
2. @Controller
3. @Service
4. @Repository
5. @Configuration

<br>

> #### 필터 옵션
- #### _ANNOTATION_: 기본값, 애노테이션을 인식해서 동작한다.
    - ex) org.example.SomeAnnotation
- #### _ASSIGNABLE_TYPE_: 지정한 타입과 자식 타입을 인식해서 동작한다.
    - ex) org.example.SomeClass
- #### _ASPECTJ_: AspectJ 패턴 사용
    - ex) org.example..*Service+
- #### _REGEX_: 정규 표현식
    - ex) org\.example\.Default.*
- #### _CUSTOM_: TypeFilter 이라는 인터페이스를 구현해서 처리
    - ex) org.example.MyTypeFilter

<br>

> ⭐ @Autowired
- 필요한 의존 객체의 '타입'에 해당하는 빈을 찾아 의존성 주입,  getBean(.class)와 동일하다고 보면 됨

<br>

> ⭐ @Component
- 스프링 빈 등록, 이름을 지정 하려면 ("")로 지정하되, 첫글자는 무조건 소문자

<br>

> ⭐ @Configuration
설정 정보를 스프링 컨테이너에 등록, 컴포넌트 스캔 대상임 (Why? Configuration 안에 @Compoent가 있음)

<br>

> ⭐ @GetMapping
- 클라이언트가 서버의 리소스를 조회할때 사용

<br>

> ⭐ @RequestMapping
- 클라이언트 요청 <-> 핸들러 메소드 매핑, 클래스 전체에 사용되는 공통 URL 설정
- @RequestMapping 추가정보
- produces = 응답 데이터의 타입 설정

<br>

> ⭐ @RestController
- 이거 붙은 클래스가 Rest API의 리소스를 처리하기위한 API 엔드포인트로 동작함을 정의함, Bean으로 등록됨

<br>

> ⭐ @PostMapping
- 클라이언트의 요청 데이터를 서버에 생성 (HTTP Method 타입 일치해야함)

<br>

> ⭐ @RequestParam
- 핸들러 메소드의 파라미터 종류 중 하나, 클라이언트에서 전송하는 요청 데이터를
- 쿼리파라미터, 폼데이터 형식으로 전송하면 이를 서버 쪽에서 전달 받을때 사용
- 쿼리 파타미터 = 요청 URL에서 ?를 기준으로 붙은 키,값 쌍의 데이터 ex)

<br>

> ⭐ @PathVariable()
- 핸들러 메소드의 파라미터 종류 중 하나, 괄호안의 문자값은 @GetMapping("/{member-id}" 의
- 중괄호 안의 문자열과 동일해야 매핑됨, 불일치시 MissingPathVariableException 발생

<br>

> ⭐ @RequiredArgsConstructor
- private final 멤버에 대한 생성자를 만들어줌

<br>

> ⭐ @NoArgsConstructor
- 파라미터가 없는 생성자 생성

<br>

> ⭐ @AllArgsConstructor
- 모든 필드값을 파라미터로 받는 생성자 생성

<br>

> ⭐ @Qualifier
- Lombok 관련 추가 구분자 지정, 빈이름 변경X

<br>

> ⭐ @Primary
- 우선순위 설정

<br>

> ⭐ @RequestBody
- JSON 형식의 Request를 DTO 클래스 객체로 변환시켜줌

<br>

> ⭐ @ResponseBody
- JSON 형식의 Request를 클라이언트에게 전달하기 위해 DTO클래스 객체를 Response Body로 변환

<br>

> ⭐ @Email
- 이메일 유효성 검증

<br>

> ⭐ @NotBlank("")
- 값이 비어있다면 메시지 출력

<br>

> ⭐ @Pattern
- 옵션: regexp(정규표현식), message(메세지)

<br>

> ⭐ @PostConstruct
- 초기화 어노테이션

<br>

> ⭐ @PreDestroy
- 종료 어노테이션

<br>

> ⭐ @Scope
- 옵션 : proxyMode = 빈 스코프를 프록시모드로 동작하게 함 ScopedProxyMode.TARGET_CLASS

<br>

> ⭐ @Mapper
- MapStruct의 Mapper Interface로 지정, componentModel = "spring"  <- 스프링 빈으로 등록