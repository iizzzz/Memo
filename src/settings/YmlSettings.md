# ๐ [ Application.yml ]
***
<br>

> โญ [์ธํ๋ฆฌ์ ์ด ๋ก๊ทธ ํ๊ธ ๊นจ์ง๋]

server:
  servlet:
    encoding:
      force-response: true
      charset: UTF-8

<br>

> โญ [H2 Database]

h2:
console:
enabled: true
path: /h2
datasource:
url: jdbc:h2:mem:test

<br>

> โญ [JPA]

jpa:
  hibernate:
    ddl-auto: create  # (1) ์คํค๋ง ์๋ ์์ฑ
  show-sql: true      # (2) SQL ์ฟผ๋ฆฌ ์ถ๋ ฅ
  properties:
    hibernate:
      format_sql: true  # (3) SQL pretty print
//  sql:
//    init:
//      data-locations: classpath*:db/h2/data.sql

<br>

> โญ [JPA Log Level ์ค์ ]

logging:
  level:
    org:
      springframework:
        orm:
          jpa: DEBUG

<br>

> โญ [HTTP Encoding CharSet ์ค์ ]

server:
  servlet:
    encoding:
      charset: UTF-8
      force-response: true

<br>

> โญ [Mail ์ค์ ]

mail:
  address:
    admin: admin@gmail.com

<br>

> โญ [JWT ์ค์ ]

jwt:
  key:
    secret: ${JWT_SECRET_KEY}               # ๋ฏผ๊ฐํ ์ ๋ณด๋ ์์คํ ํ๊ฒฝ ๋ณ์์์ ๋ก๋ํ๋ค.
  access-token-expiration-minutes: 30
  refresh-token-expiration-minutes: 420

<br>

> โญ [OAuth2 Client ์ค์ ]

spring:
  security:
    oauth2:
      client:
        registration:
          google:
            clientId: xxxxx
            client-secret: xxxxx
            scope: // ์ค์ฝํ๊ฐ์ ์ง์ ํ๋ฉด ํด๋น ๋ฒ์๋งํผ์ Resourse๋ฅผ Client(๋ฐฑ์๋ ์ดํ๋ฆฌ์ผ์ด์)์๊ฒ ์ ๊ณต
            - email
            - profile