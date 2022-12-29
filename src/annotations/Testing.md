# 📌 [ Testing Annotations ]
***
<br>

> ⭐ @SpringBootTest
- 어플리케이션 테스트를 위한 Application Context 생성

<br>

> ⭐ @BeforeEach
- 테스트 메소드 실행 전 실행됨

<br>

> ⭐ @AfterEach
- 테스트 메소드 이후에 실행됨

<br>

> ⭐ @DisplayName("")
- 테스트 클래스or메소드의 이름 정의 가능

<br>

> ⭐ @Disabled("")
- 테스트 클래스or메소드를 비활성화

<br>

> ⭐ @AutoConfigure
- MockMvc	Controller 테스트를 위한 어플리케이션 자동 구성 작업

<br>

> ⭐ @MockBean
- Application Context에 등록된 Bean에 대한 Mock 객체 생성 & 주입

<br>

> ⭐ @ExtendWith
- Spring을 사용하지않고 순수 Mockito의 기능만 사용하기 위해 클래스레벨에 추가

<br>

> ⭐ @Mock
- 해당 필드의 객체를 Mock 객체로 만듬

<br>

> ⭐ @InjectMocks
- @Mock을 설정한 객체가 InjectMocks를 추가한 필드에 주입

<br>

> ⭐ @WebMvcTest()
- 컨트롤러를 테스트하기 위한 전용 어노테이션, 괄호 안에 테스트 대상 클래스 지정