# 📌 [ Member ]

### [Entity]
> @Getter, @Setter, @Entity, @NoArgsConstructor
* Long memberId -> Wrapper 타입
* String email
* String name
* String phone
* createdAt - LocalDateTime
* modifiedAt - LocalDateTime ("LAST_MODIFIED_AT")
* age - @Transient
* MemberStatus - MemberStatus.MEMBER_ACTIVE 초기 상태
* Mapping
    * List<Order>
        * OneToMany(mappedBy = "member")  ## N쪽의 외래키 역할을 하는 '필드명' 기재 (컬럼명이 아님)
        * setOrder -> order에서 가져온 멤버가 Member가 아니면 setMember(this)
    * Stamp
        * OneToOne(mappedBy = "member", cascade = {CascadeType.PERSIST, CASCADEType.REMOVE}) <- 트랜잭션 전파 옵션
        * setStamp -> stamp에서 가져온 멤버가 Member가 아니면 setMember(this)
* 생성자(email)
* 생성자(email,name,phone)
* enum MemberStatus

<br>

***
# 📌 [ Dto ] (Use static inner classes)

<br>

> Post

* @Getter __회원 등록은 필요한 필드만 입력__
* String email -> @NotBlank
* String name -> @NotBlank
* String phone -> @Pattern

<br>

> Patch

* @Getter, @AllArgs __회원 수정은 식별자 지정을 위해 memberId 사용__
* long memberId
* String name -> @NotSpace
* String phone -> @NotSpace, Pattern
* Member.MemberStatus memberStatus
* void setMemberId(long memberId) { this.memberId = memberId }

<br>

> Response @Getter, @AllArgs __회원정보 응답에 필요하므로 모든 필드 다 필요__

* long memberId
* String email
* String name
* String phone
* String memberStatus
* int stampCount
* stampCount
    * 응답으로 줄때 stamp를 count하며, 커피 주문 등록시 스탬프와 매핑 + 트랜잭션 병합을 해서,
    * 주문은 들어갔는데 스탬프가 안올라가는 현상 방지

<br>

***
# 📌 [ Repository ]

<br>

> Annotation

* JpaRepository<Entity, Long>

> Funtions

* Optional<Member> -> email로 회원을 찾고 NullPointerException 방지

<br>

***
> # 📌 [ Service ]

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
    * email검증
* #### update
    * Id검증 + createOrder와 트랜잭션 병합, 수정하려고 하는 값이 null 이면 member.getName / 존재하면 setName
    * Optional.ofNullable을 사용하여 Null이 될 가능성이 있는 필드 추가
    * Optional.ofNullable을 사용 안하려면 CustomBeanUtils를 만들어서 Null 방지하기
* #### find
    * 조회메소드는 readOnly를 주는게 좋다, id검증
* #### Page<Member> findMembers
    * repo.findAll(PageRequest.of(page,size,Sort))
* #### delete
    * id검증

* #### findVerifiedMember(long memberId)
    * Optional<Member> opm -> repo.findById -> id 검증
    * Member result -> opm.orElseThrow(() -> BusinessException 던짐)  _service 계층은 business exception만 던짐

* #### verifyExistEmail(String email)
    * Optional<Member> = repo.findByEmail -> email 검증
    * if (member.존재하면) -> throw businessException

<br>

***
> # 📌 [ Controller ] (CSR 방식)

<br>

> Annotations
* @RestController
* @RequiredArgsConstructor
* @RequestMapping
* @Validated

<br>

> DI
* final static String [SET_URI] = "/v10/members"; -> UriCreator의 Default Uri 지정
* MemberService
* MemberMapper

<br>

> Function

* #### Post
    * Request -> MemberDto.Post
    * Dto -> Entity 변환 (변환작업은 Mapper에서 해줌)
    * setStamp(new Stamp()) -> 새로운 멤버를 생성하니까 스탬프도 새로 생성
    * memService.createMember -> 멤버 생성
    * return -> singleDto(Entity -> ResponseDto, HttpStatus)

<br>

* #### Patch
    * Request -> 식별자, MemberDto.Patch
    * setmemberId -> 식별자를 지정해야 어떤 멤버를 수정할지 특정 할 수 있음
    * memService.updateMember
    * return -> singleDto(Entity -> ResponseDto, HttpStatus)

<br>

* #### Get
    * Request -> 식별자
    * memService.findMember(id)
    * return singleDto, HttpStatus

<br>

* #### Gets
    * Request -> Param page,size
    * Page<Entity> pageEntities -> service.finds(page-1, size)  
      응답으로 던질때 +1 해줬던 page를 다시 -1 해줘서 컨트롤러가 처리 할 수 있게 함
    * List<Entity> entitys = pageEntities.getContent()
    * return MultiDto(mapper, pageEntities), HttpStatus)

<br>

* #### Delete
    * Request -> 식별자
    * service.delete(id)
    * return ResponseEntity<>(HttpStatus)