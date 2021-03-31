package com.lpras.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.WeightedResponseTimeRule;

@Configuration
public class MyRibbonConfig {
    
    
    @Autowired 
    IClientConfig iClientConfig;
     

    @Bean
    public IPing ribbonPing(IClientConfig iClientConfig) {
	return new PingUrl();
    }

    @Bean
    public IRule ribbonRule(IClientConfig iClientConfig) {
	return new WeightedResponseTimeRule();
    }
    
}
