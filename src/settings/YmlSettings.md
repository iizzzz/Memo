# 📌 [ Application.yml ]
***
<br>

> ⭐ [인텔리제이 로그 한글 깨질때]

server:
  servlet:
    encoding:
      force-response: true
      charset: UTF-8

<br>

> ⭐ [H2 Database]

h2:
console:
enabled: true
path: /h2
datasource:
url: jdbc:h2:mem:test

<br>

> ⭐ [JPA]

jpa:
  hibernate:
    ddl-auto: create  # (1) 스키마 자동 생성
  show-sql: true      # (2) SQL 쿼리 출력
  properties:
    hibernate:
      format_sql: true  # (3) SQL pretty print
//  sql:
//    init:
//      data-locations: classpath*:db/h2/data.sql

<br>

> ⭐ [JPA Log Level 설정]

logging:
  level:
    org:
      springframework:
        orm:
          jpa: DEBUG

<br>

> ⭐ [HTTP Encoding CharSet 설정]

server:
  servlet:
    encoding:
      charset: UTF-8
      force-response: true

<br>

> ⭐ [Mail 설정]

mail:
  address:
    admin: admin@gmail.com

<br>

> ⭐ [JWT 설정]

jwt:
  key:
    secret: ${JWT_SECRET_KEY}               # 민감한 정보는 시스템 환경 변수에서 로드한다.
  access-token-expiration-minutes: 30
  refresh-token-expiration-minutes: 420

<br>

> ⭐ [OAuth2 Client 설정]

spring:
  security:
    oauth2:
      client:
        registration:
          google:
            clientId: xxxxx
            client-secret: xxxxx
            scope: // 스코프값을 지정하면 해당 범위만큼의 Resourse를 Client(백엔드 어플리케이션)에게 제공
            - email
            - profile