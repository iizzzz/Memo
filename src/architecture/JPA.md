# π [ Member ]

### [Entity]
> @Getter, @Setter, @Entity, @NoArgsConstructor
* Long memberId -> Wrapper νμ
* String email
* String name
* String phone
* createdAt - LocalDateTime
* modifiedAt - LocalDateTime ("LAST_MODIFIED_AT")
* age - @Transient
* MemberStatus - MemberStatus.MEMBER_ACTIVE μ΄κΈ° μν
* Mapping
    * List<Order>
        * OneToMany(mappedBy = "member")  ## Nμͺ½μ μΈλν€ μ­ν μ νλ 'νλλͺ' κΈ°μ¬ (μ»¬λΌλͺμ΄ μλ)
        * setOrder -> orderμμ κ°μ Έμ¨ λ©€λ²κ° Memberκ° μλλ©΄ setMember(this)
    * Stamp
        * OneToOne(mappedBy = "member", cascade = {CascadeType.PERSIST, CASCADEType.REMOVE}) <- νΈλμ­μ μ ν μ΅μ
        * setStamp -> stampμμ κ°μ Έμ¨ λ©€λ²κ° Memberκ° μλλ©΄ setMember(this)
* μμ±μ(email)
* μμ±μ(email,name,phone)
* enum MemberStatus

<br>

***
# π [ Dto ] (Use static inner classes)

<br>

> Post

* @Getter __νμ λ±λ‘μ νμν νλλ§ μλ ₯__
* String email -> @NotBlank
* String name -> @NotBlank
* String phone -> @Pattern

<br>

> Patch

* @Getter, @AllArgs __νμ μμ μ μλ³μ μ§μ μ μν΄ memberId μ¬μ©__
* long memberId
* String name -> @NotSpace
* String phone -> @NotSpace, Pattern
* Member.MemberStatus memberStatus
* void setMemberId(long memberId) { this.memberId = memberId }

<br>

> Response @Getter, @AllArgs __νμμ λ³΄ μλ΅μ νμνλ―λ‘ λͺ¨λ  νλ λ€ νμ__

* long memberId
* String email
* String name
* String phone
* String memberStatus
* int stampCount
* stampCount
    * μλ΅μΌλ‘ μ€λ stampλ₯Ό countνλ©°, μ»€νΌ μ£Όλ¬Έ λ±λ‘μ μ€ν¬νμ λ§€ν + νΈλμ­μ λ³ν©μ ν΄μ,
    * μ£Όλ¬Έμ λ€μ΄κ°λλ° μ€ν¬νκ° μμ¬λΌκ°λ νμ λ°©μ§

<br>

***
# π [ Repository ]

<br>

> Annotation

* JpaRepository<Entity, Long>

> Funtions

* Optional<Member> -> emailλ‘ νμμ μ°Ύκ³  NullPointerException λ°©μ§

<br>

***
> # π [ Service ]

<br>

> Annotations
* @Service
* @Transactional
* @RequiredArgsConstructor

<br>

> DI
* MemberRepository
* CustomBeanUtils<Member>

<br>

> Funtions

* #### createMember
    * emailκ²μ¦
* #### update
    * Idκ²μ¦ + createOrderμ νΈλμ­μ λ³ν©, μμ νλ €κ³  νλ κ°μ΄ null μ΄λ©΄ member.getName / μ‘΄μ¬νλ©΄ setName
    * Optional.ofNullableμ μ¬μ©νμ¬ Nullμ΄ λ  κ°λ₯μ±μ΄ μλ νλ μΆκ°
    * Optional.ofNullableμ μ¬μ© μνλ €λ©΄ CustomBeanUtilsλ₯Ό λ§λ€μ΄μ Null λ°©μ§νκΈ°
* #### find
    * μ‘°νλ©μλλ readOnlyλ₯Ό μ£Όλκ² μ’λ€, idκ²μ¦
* #### Page<Member> findMembers
    * repo.findAll(PageRequest.of(page,size,Sort))
* #### delete
    * idκ²μ¦

* #### findVerifiedMember(long memberId)
    * Optional<Member> opm -> repo.findById -> id κ²μ¦
    * Member result -> opm.orElseThrow(() -> BusinessException λμ§)  _service κ³μΈ΅μ business exceptionλ§ λμ§

* #### verifyExistEmail(String email)
    * Optional<Member> = repo.findByEmail -> email κ²μ¦
    * if (member.μ‘΄μ¬νλ©΄) -> throw businessException

<br>

***
> # π [ Controller ] (CSR λ°©μ)

<br>

> Annotations
* @RestController
* @RequiredArgsConstructor
* @RequestMapping
* @Validated

<br>

> DI
* final static String [SET_URI] = "/v10/members"; -> UriCreatorμ Default Uri μ§μ 
* MemberService
* MemberMapper

<br>

> Function

* #### Post
    * Request -> MemberDto.Post
    * Dto -> Entity λ³ν (λ³νμμμ Mapperμμ ν΄μ€)
    * setStamp(new Stamp()) -> μλ‘μ΄ λ©€λ²λ₯Ό μμ±νλκΉ μ€ν¬νλ μλ‘ μμ±
    * memService.createMember -> λ©€λ² μμ±
    * return -> singleDto(Entity -> ResponseDto, HttpStatus)

<br>

* #### Patch
    * Request -> μλ³μ, MemberDto.Patch
    * setmemberId -> μλ³μλ₯Ό μ§μ ν΄μΌ μ΄λ€ λ©€λ²λ₯Ό μμ ν μ§ νΉμ  ν  μ μμ
    * memService.updateMember
    * return -> singleDto(Entity -> ResponseDto, HttpStatus)

<br>

* #### Get
    * Request -> μλ³μ
    * memService.findMember(id)
    * return singleDto, HttpStatus

<br>

* #### Gets
    * Request -> Param page,size
    * Page<Entity> pageEntities -> service.finds(page-1, size)  
      μλ΅μΌλ‘ λμ§λ +1 ν΄μ€¬λ pageλ₯Ό λ€μ -1 ν΄μ€μ μ»¨νΈλ‘€λ¬κ° μ²λ¦¬ ν  μ μκ² ν¨
    * List<Entity> entitys = pageEntities.getContent()
    * return MultiDto(mapper, pageEntities), HttpStatus)

<br>

* #### Delete
    * Request -> μλ³μ
    * service.delete(id)
    * return ResponseEntity<>(HttpStatus)