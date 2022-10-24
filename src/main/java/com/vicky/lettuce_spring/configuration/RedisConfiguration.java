package com.vicky.lettuce_spring.configuration;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.ReadFrom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConfiguration {

    private final String url;
    private final int port;
    private final String password;
    private final String userName;

    @Autowired
    private ClusterConfiguration clusterConfiguration;

    public RedisConfiguration(@Value("${spring.redis.host}") String url,
                              @Value("${spring.redis.port}") int port,
                              @Value("${spring.redis.password}") String password,
                              @Value("${spring.redis.username}") String userName) {
        this.url = url;
        this.port = port;
        this.password = password;
        this.userName = userName;
    }

    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(url, port);
        if((userName != null) && (userName.length() > 0)){
            redisStandaloneConfiguration.setUsername(userName);
        }
        redisStandaloneConfiguration.setPassword(password);
        return redisStandaloneConfiguration;
    }

    @Bean
    public RedisClusterConfiguration redisClusterConfiguration() {
        RedisClusterConfiguration clusterConfig = new RedisClusterConfiguration(
            this.clusterConfiguration.getNodes()
        );
        return clusterConfig;
    }
    
    @Bean
    public ClientOptions clientOptions() {
        return ClientOptions.builder()
                .disconnectedBehavior(ClientOptions.DisconnectedBehavior.REJECT_COMMANDS)
                .autoReconnect(true)
                .build();
    }

    // @Bean
    // commenting this part to always OPT for REDIS Cluster configuration
    public RedisConnectionFactory connectionFactory(RedisStandaloneConfiguration redisStandaloneConfiguration) {

        LettuceClientConfiguration configuration = LettuceClientConfiguration.builder()
                .clientOptions(clientOptions()).build();

        return new LettuceConnectionFactory(redisStandaloneConfiguration, configuration);
    }

    @Bean
    public RedisConnectionFactory clusterConnectionFactory(RedisClusterConfiguration redisClusterConfiguration) {
        LettuceClientConfiguration config = (
            LettuceClientConfiguration
            .builder()
            .readFrom(ReadFrom.REPLICA_PREFERRED)
            .build());
        return new LettuceConnectionFactory(redisClusterConfiguration, config);
    }

    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    @Primary
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

}