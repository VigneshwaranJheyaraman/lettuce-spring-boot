package com.vicky.lettuce_spring.configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class ClusterConfiguration {
    // This will automatically parse the spring.redis.cluster.nodes
    // CSV into List of String i.e., the List<RedisClusterHostWithPort>
    // eg: [127.0.0.1:6001, 127.0.0.1:6002, ...]
    private List<String> nodes;

    public List<String> getNodes(){
        return this.nodes;
    }

    public void setNodes(List<String> newNodes){
        this.nodes = newNodes;
    }
}