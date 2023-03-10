# π [ Spring MVC Annotations ]
***
<br>

> β­ @ComponentScan
- @Componentκ° λΆμ λμμ μ€νλ§ λΉμΌλ‘ λ±λ‘, λ³΄νΈμ μΌλ‘ νμ μμμμΉλ νλ‘μ νΈ μ΅μλ¨

### @ComponentScan μΆκ° μ λ³΄
### [Attribute]
- ### excludeFilters
    - @ComponentScan.Filter(type=FilterType.ANNOTATION, classes=Configuration.class) μ μΈ μ€μ 
    - βνν° μ¬μ©μ κΈ°λ³Έκ°μ typeμ΄ FilterType.ANNOTATION μ΄λΌμ typeμ μλ΅ κ°λ₯

- ### basePackages = "hello.core" μ»΄ν¬λνΈ μ€μΊ μμΉ μ§μ 
    - basePackages = {"hello.core", "hello.service"} μ¬λ¬κ³³ μ§μ  κ°λ₯
    - basePackageClasses = μ§μ ν ν΄λμ€μ ν¨ν€μ§λ₯Ό νμ μμ μλ‘ μ§μ 

<br>

> #### μ€μΊ λμ
1. @Component
2. @Controller
3. @Service
4. @Repository
5. @Configuration

<br>

> #### νν° μ΅μ
- #### _ANNOTATION_: κΈ°λ³Έκ°, μ λΈνμ΄μμ μΈμν΄μ λμνλ€.
    - ex) org.example.SomeAnnotation
- #### _ASSIGNABLE_TYPE_: μ§μ ν νμκ³Ό μμ νμμ μΈμν΄μ λμνλ€.
    - ex) org.example.SomeClass
- #### _ASPECTJ_: AspectJ ν¨ν΄ μ¬μ©
    - ex) org.example..*Service+
- #### _REGEX_: μ κ· ννμ
    - ex) org\.example\.Default.*
- #### _CUSTOM_: TypeFilter μ΄λΌλ μΈν°νμ΄μ€λ₯Ό κ΅¬νν΄μ μ²λ¦¬
    - ex) org.example.MyTypeFilter

<br>

> β­ @Autowired
- νμν μμ‘΄ κ°μ²΄μ 'νμ'μ ν΄λΉνλ λΉμ μ°Ύμ μμ‘΄μ± μ£Όμ,  getBean(.class)μ λμΌνλ€κ³  λ³΄λ©΄ λ¨

<br>

> β­ @Component
- μ€νλ§ λΉ λ±λ‘, μ΄λ¦μ μ§μ  νλ €λ©΄ ("")λ‘ μ§μ νλ, μ²«κΈμλ λ¬΄μ‘°κ±΄ μλ¬Έμ

<br>

> β­ @Configuration
μ€μ  μ λ³΄λ₯Ό μ€νλ§ μ»¨νμ΄λμ λ±λ‘, μ»΄ν¬λνΈ μ€μΊ λμμ (Why? Configuration μμ @Compoentκ° μμ)

<br>

> β­ @GetMapping
- ν΄λΌμ΄μΈνΈκ° μλ²μ λ¦¬μμ€λ₯Ό μ‘°νν λ μ¬μ©

<br>

> β­ @RequestMapping
- ν΄λΌμ΄μΈνΈ μμ²­ <-> νΈλ€λ¬ λ©μλ λ§€ν, ν΄λμ€ μ μ²΄μ μ¬μ©λλ κ³΅ν΅ URL μ€μ 
- @RequestMapping μΆκ°μ λ³΄
- produces = μλ΅ λ°μ΄ν°μ νμ μ€μ 

<br>

> β­ @RestController
- μ΄κ±° λΆμ ν΄λμ€κ° Rest APIμ λ¦¬μμ€λ₯Ό μ²λ¦¬νκΈ°μν API μλν¬μΈνΈλ‘ λμν¨μ μ μν¨, BeanμΌλ‘ λ±λ‘λ¨

<br>

> β­ @PostMapping
- ν΄λΌμ΄μΈνΈμ μμ²­ λ°μ΄ν°λ₯Ό μλ²μ μμ± (HTTP Method νμ μΌμΉν΄μΌν¨)

<br>

> β­ @RequestParam
- νΈλ€λ¬ λ©μλμ νλΌλ―Έν° μ’λ₯ μ€ νλ, ν΄λΌμ΄μΈνΈμμ μ μ‘νλ μμ²­ λ°μ΄ν°λ₯Ό
- μΏΌλ¦¬νλΌλ―Έν°, νΌλ°μ΄ν° νμμΌλ‘ μ μ‘νλ©΄ μ΄λ₯Ό μλ² μͺ½μμ μ λ¬ λ°μλ μ¬μ©
- μΏΌλ¦¬ ννλ―Έν° = μμ²­ URLμμ ?λ₯Ό κΈ°μ€μΌλ‘ λΆμ ν€,κ° μμ λ°μ΄ν° ex)

<br>

> β­ @PathVariable()
- νΈλ€λ¬ λ©μλμ νλΌλ―Έν° μ’λ₯ μ€ νλ, κ΄νΈμμ λ¬Έμκ°μ @GetMapping("/{member-id}" μ
- μ€κ΄νΈ μμ λ¬Έμμ΄κ³Ό λμΌν΄μΌ λ§€νλ¨, λΆμΌμΉμ MissingPathVariableException λ°μ

<br>

> β­ @RequiredArgsConstructor
- private final λ©€λ²μ λν μμ±μλ₯Ό λ§λ€μ΄μ€

<br>

> β­ @NoArgsConstructor
- νλΌλ―Έν°κ° μλ μμ±μ μμ±

<br>

> β­ @AllArgsConstructor
- λͺ¨λ  νλκ°μ νλΌλ―Έν°λ‘ λ°λ μμ±μ μμ±

<br>

> β­ @Qualifier
- Lombok κ΄λ ¨ μΆκ° κ΅¬λΆμ μ§μ , λΉμ΄λ¦ λ³κ²½X

<br>

> β­ @Primary
- μ°μ μμ μ€μ 

<br>

> β­ @RequestBody
- JSON νμμ Requestλ₯Ό DTO ν΄λμ€ κ°μ²΄λ‘ λ³νμμΌμ€

<br>

> β­ @ResponseBody
- JSON νμμ Requestλ₯Ό ν΄λΌμ΄μΈνΈμκ² μ λ¬νκΈ° μν΄ DTOν΄λμ€ κ°μ²΄λ₯Ό Response Bodyλ‘ λ³ν

<br>

> β­ @Email
- μ΄λ©μΌ μ ν¨μ± κ²μ¦

<br>

> β­ @NotBlank("")
- κ°μ΄ λΉμ΄μλ€λ©΄ λ©μμ§ μΆλ ₯

<br>

> β­ @Pattern
- μ΅μ: regexp(μ κ·ννμ), message(λ©μΈμ§)

<br>

> β­ @PostConstruct
- μ΄κΈ°ν μ΄λΈνμ΄μ

<br>

> β­ @PreDestroy
- μ’λ£ μ΄λΈνμ΄μ

<br>

> β­ @Scope
- μ΅μ : proxyMode = λΉ μ€μ½νλ₯Ό νλ‘μλͺ¨λλ‘ λμνκ² ν¨ ScopedProxyMode.TARGET_CLASS

<br>

> β­ @Mapper
- MapStructμ Mapper Interfaceλ‘ μ§μ , componentModel = "spring"  <- μ€νλ§ λΉμΌλ‘ λ±λ‘