```java

@Configurations
public class SecurityConfiguration {

    private final JwtTokenizer jwtTokenizer;
    private final MemberService memberService;
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin() //i⭐H2 를 정상적으로 사용하기 위해 설정
                .and()
                .csrf().disable()        //i⭐csrf 공격 방지 옵션 Disable
                .cors(withDefaults())    //i⭐ corsConfiguraationSource Bean을 추가
                .formLogin().disable()   //i⭐ SSR 방식의 form-login Disable
                .httpBasic().disable()   //i⭐ HTTP Header에 ID,PW를 전송하지 않을것이므로 Disable
                .apply(new CustomFilterConfigurer())
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll() //i⭐ 모든 HTTP Request에 대해 허용
                );
        return http.build();
    }

    // HTTP 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .cors(withDefaults())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                // Exception Handling - EntryPoint, AccessDenied Handler 추가
                .exceptionHandling()
                .authenticationEntryPoint(new MemberAuthenticationEntryPoint())
                .accessDeniedHandler(new MemberAccessDeniedHandler())
                .and()
                // Custom Configuration 추가
                .apply(new CustomFilterConfigurer())
                .and()
                // URL 별로 접근 권한 부여
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers(HttpMethod.POST, "/*/members").permitAll()
                        .antMatchers(HttpMethod.PATCH, "/*/members").hasRole("USER")
                        .antMatchers(HttpMethod.GET, "/*/members").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET, "/*/members/**").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/*/members/**").hasRole("USER")
                        .anyRequest().permitAll()
                );
        return http.build();
    }
    
    // CORS 설정
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // TODO: 환경 변수로 변경할 필요 있음
        configuration.setAllowedOrigins(Arrays.asList("http://stack-overflow-clone.s3-website.ap-northeast-2.amazonaws.com"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PATCH", "DELETE"));
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
    
    // CORS Configuration Source
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // 모든 출처에 대해 스크립트 기반의 HTTP 통신 허용
        configuration.setAllowedOrigins(Arrays.asList("*"));
        // 파라미터로 지정한 HTTP Method에 대한 HTTP 통신 허용
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELEE", "GET"));

        // CorsConfigurationSource의 구현체인 UrlBasedCorsConfigurationSource 객체 생성
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 모든 URL에 앞에서 구성한 CORS 정책 적용
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    // UserDetailsService
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> MemberPrincipal.from(memberService.searchMember(username));
    }

    // Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordEncoder pwEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /** JWT AuthenticationFilter 등록
      * AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> 를 이용해 Custom Configurer 구현 가능
      * 파라미터로는 <상속하는타입, HttpSecurityBuilder를 상속하는 타입을 제네릭으로 지정>
      */
    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {

        // Configure Method 를 Override 함으로써 SecurityConfig 를 Customizing 할 수 있다
        @Override
        public void configure(HttpSecurity builder) throws Exception {

            // getSharedObject()로 인해 SecurityConfigurer 간에 공유되는 객체를 얻을 수 있음
            // AuthenticationManager 객체를 얻음
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            // JwtAuthenticationFilter 객체 생성과 동시에,
            // AuthenticationManager & JwtTokenizer를 DI 해줌
            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);

            // setFilterProccessUrl() 를 통해 로그인 URL Customizing (default url은 /login 이다)
            jwtAuthenticationFilter.setFilterProcessesUrl("/auth/login");
            // Success, Failure Handler 추가
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new MemberAuthenticationSuccessHandler());
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new MemberAuthenticationFailureHandler());

            // JWT를 생성할 때, JwtVerificationFilter 에서 사용되는 객체들을 DI
            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);

            // addFilter()를 통해 JWT 검증필터를 Spring Security Filter Chain에 추가
            // addFilter()를 JwtAuthenticationFilter가 수행된 바로 다음에 동작하도록 추가
            builder.addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
        }
    }
}
```