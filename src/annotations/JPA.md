# ๐ [ JPA Annotations ]
***
<br>

> โญ @Id
- ์ํฐํฐ์ ๊ณ ์  ์๋ณ์ ์ญํ , DB์ Primary Key๋ก ์ง์ ํ ์ปฌ๋ผ์ ํด๋นํจ

<br>

> โญ @Entity
- JPA ๊ด๋ฆฌ๋์ ์ํฐํฐ ํด๋์ค ์ง์ ,
- ์ต์ = name
- name ์ ํธ๋ฆฌ๋ทฐํธ๋ฅผ ์ค์ ํ์ง ์์ผ๋ฉด ๊ธฐ๋ณธ๊ฐ์ผ๋ก ํด๋์ค ์ด๋ฆ์ ํ์ด๋ธ ์ด๋ฆ์ผ๋ก ์ฌ์ฉ

<br>

> โญ @Table
- ํ์ด๋ธ ์ง์ , ๋ฏธ ์ง์ ์ ํด๋์ ์ด๋ฆ์ด ํ์ด๋ธ๋ช์ด ๋จ, Optional,
- ์ต์ = name
- name ์ ํธ๋ฆฌ๋ทฐํธ๋ฅผ ์ค์ ํ์ง ์์ผ๋ฉด ๊ธฐ๋ณธ๊ฐ์ผ๋ก ํด๋์ค ์ด๋ฆ์ ํ์ด๋ธ ์ด๋ฆ์ผ๋ก ์ฌ์ฉ
- @Table ์ ๋ํ์ด์์ ์ต์์ด๋ฉฐ, ์ถ๊ฐํ์ง ์์ ๊ฒฝ์ฐ ํด๋์ค ์ด๋ฆ์ ํ์ด๋ธ ์ด๋ฆ์ผ๋ก ์ฌ์ฉ

<br>

> โญ @GeneratedValue
- ๋ฉค๋ฒ ๋ณ์์ ์ถ๊ฐํ๋ฉด ํ์ด๋ธ์์ ๊ธฐ๋ณธํค๊ฐ ๋๋ ์๋ณ์๋ฅผ ์๋ ์ค์ 
- Attr๋ก strategy = GenerationType.IDENTITY ์ฌ์ฉ ์ DB์ pk ์์ฑ ์์
- Attr๋ก strategy = GenerationType.SEQUENCE ์ฌ์ฉ ์ DB ์ํ์ค ์ฌ์ฉ

<br>

> โญ @Column
- Field <-> Column ๋งคํ
- ์ต์ = nullable (default = true) - Null๊ฐ ํ์ฉ ์ฌ๋ถ
- ์ต์ = updatable (default = true) - ์ปฌ๋ผ ์์  ์ฌ๋ถ
- ์ต์ = unique (default = false) - ๊ณ ์  ๊ฐ ์ค์  ์ฌ๋ถ(์ ์ฝ์กฐ๊ฑด ์ค์ )
- ์ต์ = length (default = 255) - ์ปฌ๋ผ์ ์ ์ฅํ  ๋ฌธ์ ๊ธธ์ด

<br>

> โญ @Transient
- JPA ๊ด๋ฆฌ๋ฒ์์์ ์ ์ธ

<br>

> โญ @Enumerated
- Enum ํ์๊ณผ ๋งคํ
- ์ต์ = EnumType.STRING - enum์ ์ด๋ฆ์ ํ์ด๋ธ์ ์ ์ฅ (๊ถ์ฅ)
- ์ต์ = EnumType.ORDINAL - enum์ ์์๋ฅผ ๋ํ๋ด๋ ์ซ์๋ฅผ ํ์ด๋ธ์ ์ ์ฅ

<br>

> โญ @JoinColumn
- Attr name ์ฌ์ฉ ์ ํด๋น ํ์ด๋ธ์ ์ธ๋ํค์ ํด๋นํ๋ ์ปฌ๋ผ๋ช ์ง์ 

<br>

> โญ @OneToMany
- 1:N ๊ด๊ณ ๋งคํ
- ์ต์ = mappedBy
  - ๋งคํ์ฃผ์ฒด ์ค์  ์ธ๋ํค์ ์ญํ ์ ํ๋ ํ๋์ ์ง์ 
- ์ต์ = CascadeType
  - Persist = ์ฐ๊ด๊ด๊ณ ๋งคํ์ด ๋์ด์๋ ๊ฐ์ฒด๋ฅผ ๊ฐ์ด ์์ํ (์์์ฑ ์ ์ด)
  - Remove = ์ฐ๊ด๊ด๊ณ ๋งคํ์ด ๋์ด์๋ ๊ฐ์ฒด๋ฅผ ์ญ์ ํ๋ฉด ๊ฐ์ด ์ญ์ 
  - Add Method ์ถ๊ฐ
  
// ํด๋์ค์ํ๋.getQuestions ์ ํ๋ผ๋ฏธํฐ์ธ question ์ฝ์
// ํ๋ผ๋ฏธํฐ์ member์ this(ํด๋์ค์ ํ๋ ์ฝ์)
public void setQuestion(Question question) {
  this.getQuestions().add(question);
  question.setMember(this);
}

<br>

> โญ @ManyToOne
- N:1 ๊ด๊ณ ๋งคํ
- ์ต์ = FetchType.Eager 
  - ์ฆ์ ๋ก๋ฉ ์ ๋ต
- ์ต์ = FetchType.Lazy
  - ์ง์ฐ ๋ก๋ฉ ์ ๋ต (ํ๋ก์ ๊ฐ์ฒด ์์ฑ)
- ์ต์ = Optional (Default = true)
  - false๋ก ์ค์  ์, ์ฐ๊ด๊ด๊ณ๊ฐ ํญ์ ๋งคํ์ด ๋์ด ์์ด์ผํจ

// ์ํฐํฐ์ ํ๋์ธ ๋ฉค๋ฒ์ ์ง๋ฌธ์ด ์ํฐํฐ๊ฐ ์๋๋ฉด
// ์ํฐํฐ์ ํ๋์ธ ๋ฉค๋ฒ์ ์ํฐํฐ ์ฝ์
void addMember(Member member) {
    this.member = member;
    if (!this.member.getQuestions().contains(this))
        this.member.getQuestions().add(this);
}

<br>

> โญ @OneToOne
- 1:1 ๋งคํ

<br>

> โญ @Query("")
- ๊ฐ๋ฐ์๊ฐ ์ง์  ์ฟผ๋ฆฌ๋ฌธ ์์ฑ, ex) @Query("SELECT * FROM USER WHERE USER_ID = :userId")
- ์์ ์์ ์ฝ๋์์ :userId๋ ๋ฉ์๋์ ๋ณ์๊ฐ์ ์ฑ์์ฃผ๋ ๋์  ์ฟผ๋ฆฌ ํ๋ผ๋ฏธํฐ์

<br>

> โญ @Transaction
- ํธ๋์ญ์ ์ ์ฉ,
- ์ต์ = readOnly = true ์ค์  ์ ์ฝ๊ธฐ์ ์ฉ ํธ๋์ญ์ ์ ์ฉ
- ์ต์ = propagation = Propagation.REQUIRED ์ค์  ์ ํ์ฌ ์งํ์ค ํธ๋์ญ์์ด ์์ผ๋ฉด ํด๋น ํธ๋์ญ์์ ์ฌ์ฉ
- ์กด์ฌํ์ง ์์ผ๋ฉด ์ ํธ๋์ญ์ ์์ฑ

<br>

> โญ @EnableJpa
- Repositories	DB๋ฅผ ์ฌ์ฉํ๊ธฐ ์ํ JpaRepository๊ฐ ์์นํ ํจํค์ง์ entityManaferFactory ๋น์ ๋ํ ์ฐธ์กฐ ์ค์ 
- Attr Backpackages = ๊ธฐ์กด Jpa Repo๋ฅผ ๊ทธ๋๋ก ์ฌ์ฉํ๋๋ก ํด๋น Repo ํจํค์ง ๊ฒฝ๋ก ์ฝ์
- Attr entityManagerFactoryRef = Bean ์์ฑ ๋ฉ์๋ ๋ช ์ฝ์

<br>

> โญ @EnableAsync
- ๋น๋๊ธฐ ๋ฉ์๋ ์ ์ธ

<br>

> โญ @EventListener
- ์ด๋ฒคํธ ๋ฆฌ์ค๋ ์ง์ 

<br>

> โญ @Data
- Getter, Setter, ToString, EqualsAndHashCode, RequiredArgsConstructor ๋ฅผ ํฉ์น ์ด๋ธํ์ด์

<br>

> โญ @RequiredArgsConstructor
- final ์ด ๋ถ๊ฑฐ๋ @NotNull ์ด ๋ถ์ ํ๋์ ์์ฑ์๋ฅผ ์๋ ์์ฑํด์ค

<br>

> โญ @ElementCollection
- ์์ฑ fetch = FetchType.EAGER
- ์ ์  ๋ฑ๋ก์, ์ ์ ์ ๊ถํ ํ์ด๋ธ ์์ฑ

<br>

> โญ @MappedCollection
- attr= idColumn, keyColumn  /  ์ํฐํฐ ํด๋์ค ๊ฐ ์ฐ๊ด๊ด๊ณ๋ฅผ ๋งบ์ด์ฃผ๋ ์ ๋ณด๋ฅผ ์๋ฏธํจ
- List์ผ ๊ฒฝ์ฐ id,keyColumn 2๊ฐ ๋ค ํ์๋ก ์๋ ฅํด์ผ ํ์ง๋ง
- Set์ผ๊ฒฝ์ฐ keyColimn์ ํ์๊ฐ ์๋
- idColumn = ์์ ํ์ด๋ธ์ ์ถ๊ฐ๋๋ ์ธ๋ํค ์ปฌ๋ผ๋ช ์ง์ ,
- keyColumn = ์ธ๋ํค๋ฅผ ํฌํจํ๋ ์์ํ์ด๋ธ์ ๊ธฐ๋ณธํค ์ปฌ๋ผ๋ช ์ง์ 

> โญ @Mapping(source = "", target = "")
- MapStruct ์ฌ์ฉ ์, DTO์ Entity์ ํ๋๋ช์ด ๋ค๋ฅผ์ ํ๋ ๋งคํ

***
# ๐ [ JPA Auditing ]
***
<br>

> โญ @EnableJpaAuditing (์คํ๋ง๋ถํธ์ Entryํฌ์ธํธ์ธ Application ํด๋์ค์ ์ ์ฉ)
- JPA ์๊ฐ ์๋ ์ ์ฅ ๊ธฐ๋ฅ ํ์ฑํ

<br>

> โญ @MappedSuperclass (ํด๋์ค ์ ์ฉ)
- JPA Entity๊ฐ ์ด ์ด๋ธํ์ด์์ด ๋ถ์ ์ถ์ ํด๋์ค๋ฅผ ์์ํ  ๊ฒฝ์ฐ, createDate, modifiedDate๋ฅผ ์ปฌ๋ผ์ผ๋ก ์ธ์

<br>

> โญ @EntityListeners(a.class) (ํด๋์ค ์ ์ฉ)
- ํด๋น ํด๋์ค์ ๊ดํธ ์์ ํด๋์ค์ ๊ธฐ๋ฅ ์ ์ฉ

<br>

> โญ @CreatedDate     (ํ๋ ์ ์ฉ)
- Entity๊ฐ ์์ฑ๋์ด ์ ์ฅ๋  ๋ ์๊ฐ ์๋ ์ ์ฅ

<br>

> โญ @LastModifiedDate   (ํ๋ ์ ์ฉ)
- ์กฐํํ Entity์ ๊ฐ์ ๋ณ๊ฒฝํ  ๋ ์๊ฐ ์๋ ์ ์ฅ

[์์ Auditing ๊ธฐ๋ฅ์ ์ ์ํ ํด๋์ค๋ฅผ Entity์์ ์์]