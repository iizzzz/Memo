```java
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
//                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("arthur.deliveryapi"))
                // 스웨거가 RestController를 전부 스캔을 한다.
                // basePackage => 어디를 범위로 스캔을 할 것인지 작성
                // basePackage 부분은 프로젝트에 맞게 수정해야함
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    // API 정보를 작성하는 곳
    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("결제 API 서비스")
                .description("결제 API 서비스 입니다")
                .version("0.8.0")
                .termsOfServiceUrl("https://antstudy.tistory.com/")
                .license("LICENSE")
                .licenseUrl("")
                .build();
    }

    // 완료가 되었으면 오른쪽 URL 로 접속 => http://localhost:8080/swagger-ui.html
}
```