spring:

h2:
console:
enabled: true
path: /h2
datasource:
url: jdbc:h2:mem:test

jpa:
hibernate:
ddl-auto: create
show-sql: true
properties:
hibernate:
format_sql: true
logging:
level:
org:
springframework:
orm:
jpa: DEBUG
open-in-view: false

server:
servlet:
encoding:
force-response: true
charset: UTF-8

email:
address:
admin: admin@admin.com

jwt:
key:
secret: $skw12341234123412341234123412341234123412341234               # 민감한 정보는 시스템 환경 변수에서 로드한다.
access-token-expiration-minutes: 30
refresh-token-expiration-minutes: 420