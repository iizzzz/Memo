package com.server.seb41_main_11.redis;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.SocketOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/* Redis Template를 이용한 방식 */

@Configuration
@EnableRedisRepositories
@RequiredArgsConstructor
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    private final RedisProperties redisProperties;

    /* RedisConnectionFactory 인터페이스를 통해 LettuceConnectionFactory 반환 */

    @Bean
    public RedisConnectionFactory factory() {
        RedisClusterConfiguration configuration = new RedisClusterConfiguration();
        configuration.clusterNode(redisHost, redisPort);

        LettuceClientConfiguration clientConfiguration = LettuceClientConfiguration.builder()
                .clientOptions(ClientOptions.builder()
                        .socketOptions(SocketOptions.builder()
                                .connectTimeout(Duration.ofMillis(redisProperties.getTimeout())).build())
                        .build())
                .commandTimeout(Duration.ofSeconds(redisProperties.getTimeout())).build();

        return new LettuceConnectionFactory(configuration, clientConfiguration);
    }

    @Bean
    public RedisTemplate<String, String> template() {
        /* RedisTemplate를 받아와 set, get, delete 사용 */
        RedisTemplate<String, String> template = new RedisTemplate<>();

        /* Key & Value Serializer, Factory 설정 */
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setConnectionFactory(factory());
//        template.afterPropertiesSet();

        return template;
    }
}
