```java
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/* Redis Template를 이용한 방식 */

@Configuration
public class RedisConfig {

    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private int redisPort;

    /* RedisConnectionFactory 인터페이스를 통해 LettuceConnectionFactory 반환 */

    @Bean
    public RedisConnectionFactory factory() {
        return new LettuceConnectionFactory(redisHost, redisPort);
    }

    @Bean
    public RedisTemplate<String, String> template() {
        /* RedisTemplate를 받아와 set, get, delete 사용 */
        RedisTemplate<String, String> template = new RedisTemplate<>();

        /* Key & Value Serializer, Factory 설정 */
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setConnectionFactory(factory());

        return template;
    }
}
```