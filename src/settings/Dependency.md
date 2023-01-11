# 📌 [ Dependencies ]
***
<br>

> ⭐ [H2]

runtimeOnly 'com.h2database:h2'

<br>

> ⭐ [Lombok]

compileOnly 'org.projectlombok:lombok'
annotationProcessor 'org.projectlombok:lombok'
testCompileOnly 'org.projectlombok:lombok'
testAnnotationProcessor 'org.projectlombok:lombok'

<br>

> ⭐ [MapStruct]  __Processor__가 롬복보다 뒤에 있어야 함

implementation 'org.mapstruct:mapstruct:1.5.3.Final'
annotationProcessor 'org.mapstruct:mapstruct-processor:1.5.3.Final'

<br>

> ⭐ [JSR-330 Provider]

implementation 'javax.inject:javax.inject:1'

<br>

> ⭐ [Web] Scope 추가

implementation 'org.springframework.boot:spring-boot-starter-web'

<br>

> ⭐ [Validator]

implementation 'org.springframework.boot:spring-boot-starter-validation'

<br>

> ⭐ [Thymeleaf]

implementation 'org.springframework.boot:spring-boot-starter-thymeleaf
implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity5'

<br>

> ⭐ [Spring Data JDBC]

implementation 'org.springframework.boot:spring-boot-starter-data-jdbc'
runtimeOnly 'com.h2database:h2'

<br>

> ⭐ [Spring Data JPA]

implementation 'org.springframework.boot:spring-boot-starter-data-jpa'

<br>

> ⭐ [MySql]

implementation 'mysql:mysql-connector-java'
implementation 'org.springframework.boot:spring-boot-starter-jta-atomikos'

<br>

> ⭐ [Gson]

implementation group: 'com.google.code.gson', name: 'gson', version: '2.8.5'

<br>

> ⭐ [Apache HttpComponents]

implementation 'org.apache.httpcomponents:httpclient'

<br>

> ⭐ [Spring Security]

implementation 'org.springframework.boot:spring-boot-starter-security'
testImplementation 'org.springframework.security:spring-security-test'

<br>

> ⭐ [JJWT]

implementation 'org.springframework.boot:spring-boot-starter-web'
testImplementation 'org.springframework.boot:spring-boot-starter-test'
testImplementation 'org.springframework.security:spring-security-test'

implementation 'io.jsonwebtoken:jjwt-api:0.11.5'
runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.5'
runtimeOnly	'io.jsonwebtoken:jjwt-jackson:0.11.5'

<br>

> ⭐ [Mail]

implementation 'org.springframework.boot:spring-boot-starter-mail'

<br>

> ⭐ [JUnit5]

testImplementation 'org.junit.jupiter:junit-jupiter-api:5.3.1'
testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.3.1'

<br>

> ⭐ [OAuth2]

implementation 'org.springframework.boot:spring-boot-starter-oauth2-client'

<br>

> ⭐ [Swagger]

implementation group: 'io.springfox', name: 'springfox-swagger-ui', version: '2.9.2'
implementation group: 'io.springfox', name: 'springfox-swagger2', version: '2.9.2'

<br>

> ⭐ [WebFlux]

implementation 'org.springframework.boot:spring-boot-starter-webflux'

<br>

> ⭐ [Redis]

implementation 'org.springframework.boot:spring-boot-starter-data-redis'

<br>

> ⭐ [r2dbc]

implementation 'org.springframework.boot:spring-boot-starter-data-r2dbc'
//i H2 Non-Blocking Driver
runtimeOnly 'io.r2dbc:r2dbc-h2'

<br>

> ⭐ [Spring Rest Docs]

// [플러그인 추가]
id "org.asciidoctor.jvm.convert" version "3.3.2"

// [스니핏 생성 경로 지정]
ext {
set('snippetsDir', file("build/generated-snippets"))
}

// [AsciiDoctor에서 사용되는 의존그룹 지정]
configurations {
asciidoctorExtensions
}

// [Rest Docs 라이브러리]
testImplementation 'org.springframework.restdocs:spring-restdocs-mockmvc'  
asciidoctorExtensions 'org.springframework.restdocs:spring-restdocs-asciidoctor'

// [:test task 실행 시, 스니핏 디렉토리 경로 지정]
tasks.named('test') {
outputs.dir snippetsDir
useJUnitPlatform()
}

// [:asciidoctor 실행 시, 기능 사용을 위해 task에 asccidoctorExtensions 설정]

tasks.named('asciidoctor') {
configurations "asciidoctorExtensions"
inputs.dir snippetsDir
dependsOn test
}

// [:build 실행 전 실행되는 task,  :copyDocument 가 실행 되면 index.html이 static 경로에 copy되며,
그 파일은 API Docs를 파일로 외부 제공을 위한 용도로 사용 가능]
task copyDocument(type: Copy) {
dependsOn asciidoctor            // [:asciidoctor 실행 후 task 실행되도록 의존 설정]
from file("${asciidoctor.outputDir}")   // [asciidoc 경로에 생성되는 index.html copy]
into file("src/main/resources/static/docs")   // [static 경로로 index.html 추가]
}

build {
dependsOn copyDocument  // [:build 가 실행되기 전, :copyDocument 가 선행되도록 설정]
}

// [App 실행 파일이 생성하는 :bootJar 설정]
bootJar {
dependsOn copyDocument    // [:bootJar 실행 전, :copyDocument 가 선행되도록 의존설정]
from ("${asciidoctor.outputDir}") {  // [Asciidoctor로 생성되는 index.html을 Jar에 추가]
into 'static/docs'    
}
}