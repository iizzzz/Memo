```java
@Configuration
public class FilterConfiguration {

    // 필터의 순서 지정
    @Bean
    public FilterRegistrationBean<FilterOne> filterOne() {

        FilterRegistrationBean<FilterOne> registrationBean = new FilterRegistrationBean<>(new FilterOne());
        registrationBean.setOrder(1);

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<FilterTwo> filterTwo() {
        FilterRegistrationBean<FilterTwo> registrationBean = new FilterRegistrationBean<>(new FilterTwo());
        registrationBean.setOrder(2);

        return registrationBean;
    }
}
```