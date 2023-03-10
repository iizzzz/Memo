# โญ [ Testing ]

***


> ### ๐ Packages

  * import static org.assertj.core.api.Assertions.*; [assertj]
  * import static org.junit.jupiter.api.Assertions.*; [junit]
  * import static org.junit.jupiter.api.Assumptions.*; [Assumptions]
  * import static org.hamcrest.MatcherAssert.*; [Hamcrest]
  * import static org.hamcrest.Matchers.*; [Hamcrest Matcher]
  * import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; [Controller ํ์คํธ]
  * import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*; [Controller ํ์คํธ]
  * import org.mockito.Mockito; [Mockito]
  * import static org.mockito.BDDMockito.given; [Mockito_given]

<br>

***
> ### ๐ Junit 5

* assertEquals(a,b) 
  * *๊ฐ๋น๊ต*
* assertNotNull(๋์, ์คํจ์ ๋ฉ์์ง)
  * *Null ์ฌ๋ถ ์ฒดํฌ*
* assertThrows(Exception.class, () -> ํ์คํธ๋์ ๋ฉ์๋)
  * *์์ธ ๋ฐ์ ํ์คํธ*
* assertDoesNotThrow(() -> Do) 
  * *์์ธ ๋ฐ์ X ํ์คํธ*

<br>

***
> ### ๐ AssertJ

* None

<br>

***
> ### ๐ Assumption

* #### assumeTrue()
  * ํ๋ผ๋ฏธํฐ์ ๊ฐ์ด true์ด๋ฉด, ์๋ ๋ก์ง ์คํ

<br>

***
> ### ๐ Hamcrest
* #### asserThat(a, is(equalTo(b)))
  * ๋น๊ต
* #### asserThat(a, is(notNullValue()))
  * Null ๊ฒ์ฆ
* #### asserThat(๋์.class, is(์์Exception.class))
  * ์์ธ ๊ฒ์ฆ

<br>

***
# โญ [ URI ]

<br>

* #### UriComponentBuilder.newInstance().path().buildAndExpand().toUri
  * Build Request URI

* #### ResultActions - ๊ธฐ๋ HTTP Status, Content ๊ฒ์ฆ
  * mockMvc.perform(get & post ๋ฑ๋ฑ)

* #### MvcResult - Response Body์ HTTP Status, ํ๋กํผํฐ ๊ฒ์ฆ
  * ResultActions์ ๊ฐ์ฒด๋ฅผ ์ด์ฉ

<br>

***
# โญ [ Anotations ]

<br>

* #### @BeforeEach 
  * *init()* ์ฌ์ฉ, ํ์คํธ ์คํ ์  ์ ์ฒ๋ฆฌ
* #### @BeforeAll
  * *initAll()* ์ฌ์ฉ, ํ์คํธ ์ผ์ด์ค๊ฐ ์คํ๋๊ธฐ์  1๋ฒ๋ง ์คํ
* #### @DisplayName
  * ํ์คํธ์ ์ด๋ฆ ์ง์ 

<br>

> ### ๐ Controller 

* #### @SpringBootTest
  * Spring Boot ๊ธฐ๋ฐ Application Context ์์ฑ
* #### @AutoConfigureMockMvc
  * Controller๋ฅผ ์ํ ์ฑ์ ์๋ ๊ตฌ์ฑ ์์, MockMvc๋ฅผ ์ด์ฉํ๋ ค๋ฉด ํ์๋ก ์ถ๊ฐํด์ผํจ

> ### ๐ Data
* @DataJpaTest
  * @Transactional์ ํฌํจํ๊ณ  ์์ด์, ํ๋์ ํ์คํธ์ผ์ด์ค ์ข๋ฃ์ ์ ์ฅ๋ ๋ฐ์ดํฐ RollBack

> ### ๐ Mockito
* @MockBean
  * Application Context์ ์๋ Bean์ Mockito Mock ๊ฐ์ฒด๋ฅผ ์์ฑ & ์ฃผ์
* @ExtendWith
  * Spring์ ์ฌ์ฉํ์ง์๊ณ  Junit์์ Mockito์ ๊ธฐ๋ฅ์ ์ฌ์ฉํ๊ธฐ ์ํด ์ถ๊ฐ
* @Mock
  * ํด๋น ํ๋์ ๊ฐ์ฒด๋ฅผ Mock ๊ฐ์ฒด๋ก ์์ฑ
* @InjectMocks
  * @InjectMocks๋ฅผ ์ค์ ํ ํ๋์ @Mock์ผ๋ก ์์ฑํ ๊ฐ์ฒด๋ฅผ ์ฃผ์

<br>

---
# โญ[์ฌ๋ผ์ด์ค ํ์คํธ] API & Data ๊ณ์ธต ํ์คํธ

<br>

> ### ๐ given, when, then

* given - ํ์คํธ์ฉ Request Body ์ค์ 
* when - MockMvc ๊ฐ์ฒด๋ก ํ์คํธ ๋์ Controller ํธ์ถ(์์ฒญURI, HTTP๋ฉ์๋ ๋ฑ ์ง์ )
* then - Controller ํธ๋ค๋ฌ ๋ฉ์๋์์ ์๋ต์ผ๋ก ์์ ํ HTTP Status ๋ฐ response body ๊ฒ์ฆ


* Post - Request Body ์์ฑ
* Gson - Json ๋ณํ
* ResultActions - URI(post), MediaType, content ์ง์ 
* actions.andExpect() - Resonse Body <-> ์๋ต์ ํ๋กํผํฐ์ ์ผ์นํ๋์ง ๊ฒ์ฆ

<br>

---
> ###  ๐ ์ปจํธ๋กค๋ฌ ํ์คํธ 

* Post - Request Body ์์ฑ
* Gson - Json ๋ณํ
* ResultActions - URI(post), MediaType, content ์ง์ 
* actions.andExpect() - Resonse Body <-> ์๋ต์ ํ๋กํผํฐ์ ์ผ์นํ๋์ง ๊ฒ์ฆ


<br>
---

> ### ๐ ๋ฐ์ดํฐ๊ณ์ธต ํ์คํธ

* @DataJpaTest ์ฌ์ฉ
* Entity ๊ฐ์ฒด ์์ฑ - ๋ฐ์ดํฐ ์์ฑ
* ์ ์ฅ & ์กฐํ ๋ฑ ๋์ ์ํ
* ๊ฒ์ฆ
