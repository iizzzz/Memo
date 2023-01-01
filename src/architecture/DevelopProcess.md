# 기능 구현 프로세스

1. 엔티티 생성
- 필요한 필드가 뭐가 있는지 생각 후 필드 작성
- 어떤 엔티티와 연관관계를 맺을지 생각 후 1:N, N:1, N:N, 1:1 등 생각해서 Relation Mapping
* 빌더 패턴 등 디자인패턴에 대해 알아보고 편한 방식 적용

2. DTO 클래스 생성
- Request, Response에 필요한 필드 생각 후 작성

3. Mapper 인터페이스 생성
- Entity와 DTO의 타입이나 필드명이 다를 경우 @Mapping 어노테이션 이용해서 매핑

4. Controller 클래스 생성
- 단순히 DTO <-> Entity <-> Response를 전달하는 로직만 작성

5. Repository 인터페이스 생성
- 필요에 따른 JPQL 쿼리를 이용한 조회 메서드 작성

6. Service 클래스 생성
- Mapstruct를 이용한 매핑을 제외하고 필요한 비즈니스 로직 작성

7. ETC
- 그 외 Exception, Security, Auditable 등 적용