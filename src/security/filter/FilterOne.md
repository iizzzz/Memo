```java
import javax.servlet.*;
import java.io.IOException;

public class FilterOne implements Filter {

    // 초기화
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("# First Filter 생성");
    }

    // ServletRequest를 이용해 다음 Filter로 넘어가기 위한 전처리 작업 수행
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("--- First Filter Start ---");
        chain.doFilter(request, response);
        System.out.println("--- First Filter End ---");
    }

    // Filter가 사용한 리소스 반납
    @Override
    public void destroy() {
        System.out.println("# First Filter Destroy");
        Filter.super.destroy();
    }
}

```