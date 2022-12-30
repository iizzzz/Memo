# 📌 [ JPA Annotations ]
***
<br>

> ⭐ @Id
- 엔티티의 고유 식별자 역할, DB의 Primary Key로 지정한 컬럼에 해당함

<br>

> ⭐ @Entity
- JPA 관리대상 엔티티 클래스 지정,
- 옵션 = name
- name 애트리뷰트를 설정하지 않으면 기본값으로 클래스 이름을 테이블 이름으로 사용

<br>

> ⭐ @Table
- 테이블 지정, 미 지정시 클래시 이름이 테이블명이 됨, Optional,
- 옵션 = name
- name 애트리뷰트를 설정하지 않으면 기본값으로 클래스 이름을 테이블 이름으로 사용
- @Table 애너테이션은 옵션이며, 추가하지 않을 경우 클래스 이름을 테이블 이름으로 사용

<br>

> ⭐ @GeneratedValue
- 멤버 변수에 추가하면 테이블에서 기본키가 되는 식별자를 자동 설정
- Attr로 strategy = GenerationType.IDENTITY 사용 시 DB에 pk 생성 위임
- Attr로 strategy = GenerationType.SEQUENCE 사용 시 DB 시퀀스 사용

<br>

> ⭐ @Column
- Field <-> Column 매핑
- 옵션 = nullable (default = true) - Null값 허용 여부
- 옵션 = updatable (default = true) - 컬럼 수정 여부
- 옵션 = unique (default = false) - 고유 값 설정 여부(제약조건 설정)
- 옵션 = length (default = 255) - 컬럼에 저장할 문자 길이

<br>

> ⭐ @Transient
- JPA 관리범위에서 제외

<br>

> ⭐ @Enumerated
- Enum 타입과 매핑
- 옵션 = EnumType.STRING - enum의 이름을 테이블에 저장 (권장)
- 옵션 = EnumType.ORDINAL - enum의 순서를 나타내는 숫자를 테이블에 저장

<br>

> ⭐ @JoinColumn
- Attr name 사용 시 해당 테이블의 외래키에 해당하는 컬럼명 지정

<br>

> ⭐ @OneToMany
- 1:N 관계 매핑
- 옵션 = mappedBy
  - 매핑주체 설정 외래키의 역할을 하는 필드에 지정
- 옵션 = CascadeType
  - Persist = 연관관계 매핑이 되어있는 객체를 같이 영속화 (영속성 전이)
  - Remove = 연관관계 매핑이 되어있는 객체를 삭제하면 같이 삭제

<br>

> ⭐ @ManyToOne
- N:1 관계 매핑
- 옵션 = FetchType.Eager 
  - 즉시 로딩 전략
- 옵션 = FetchType.Lazy
  - 지연 로딩 전략 (프록시 객체 생성)
- 옵션 = Optional (Default = true)
  - false로 설정 시, 연관관계가 항상 매핑이 되어 있어야함

<br>

> ⭐ @OneToOne
- 1:1 매핑

<br>

> ⭐ @Query("")
- 개발자가 직접 쿼리문 작성, ex) @Query("SELECT * FROM USER WHERE USER_ID = :userId")
- 위의 예시 코드에서 :userId는 메소드의 변수값을 채워주는 동적 쿼리 파라미터임

<br>

> ⭐ @Transaction
- 트랜잭션 적용,
- 옵션 = readOnly = true 설정 시 읽기전용 트랜잭션 적용
- 옵션 = propagation = Propagation.REQUIRED 설정 시 현재 진행중 트랜잭션이 있으면 해당 트랜잭션을 사용
- 존재하지 않으면 새 트랜잭션 생성

<br>

> ⭐ @EnableJpa
- Repositories	DB를 사용하기 위한 JpaRepository가 위치한 패키지와 entityManaferFactory 빈에 대한 참조 설정
- Attr Backpackages = 기존 Jpa Repo를 그대로 사용하도록 해당 Repo 패키지 경로 삽입
- Attr entityManagerFactoryRef = Bean 생성 메소드 명 삽입

<br>

> ⭐ @EnableAsync
- 비동기 메소드 선언

<br>

> ⭐ @EventListener
- 이벤트 리스너 지정

<br>

> ⭐ @Data
- Getter, Setter, ToString, EqualsAndHashCode, RequiredArgsConstructor 를 합친 어노테이션

<br>

> ⭐ @RequiredArgsConstructor
- final 이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해줌

<br>

> ⭐ @ElementCollection
- 속성 fetch = FetchType.EAGER
- 유저 등록시, 유저의 권한 테이블 생성

<br>

> ⭐ @MappedCollection
- attr= idColumn, keyColumn  /  엔티티 클래스 간 연관관계를 맺어주는 정보를 의미함
- List일 경우 id,keyColumn 2개 다 필수로 입력해야 하지만
- Set일경우 keyColimn은 필수가 아님
- idColumn = 자식 테이블에 추가되는 외래키 컬럼명 지정,
- keyColumn = 외래키를 포함하는 자식테이블의 기본키 컬럼명 지정

> ⭐ @Mapping(source = "", target = "")
- MapStruct 사용 시, DTO와 Entity의 필드명이 다를시 필드 매핑

***
# 📌 [ JPA Auditing ]
***
<br>

> ⭐ @EnableJpaAuditing (스프링부트의 Entry포인트인 Application 클래스에 적용)
- JPA 시간 자동 저장 기능 활성화

<br>

> ⭐ @MappedSuperclass (클래스 적용)
- JPA Entity가 이 어노테이션이 붙은 추상 클래스를 상속할 경우, createDate, modifiedDate를 컬럼으로 인식

<br>

> ⭐ @EntityListeners(a.class) (클래스 적용)
- 해당 클래스에 괄호 안의 클래스의 기능 적용

<br>

> ⭐ @CreatedDate     (필드 적용)
- Entity가 생성되어 저장될 때 시간 자동 저장

<br>

> ⭐ @LastModifiedDate   (필드 적용)
- 조회한 Entity의 값을 변경할 때 시간 자동 저장

[위의 Auditing 기능을 정의한 클래스를 Entity에서 상속]