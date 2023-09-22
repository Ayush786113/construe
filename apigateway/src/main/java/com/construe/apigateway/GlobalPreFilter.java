package com.construe.apigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GlobalPreFilter implements GlobalFilter {
    @Autowired
    private Security security;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange
                .getRequest()
                .mutate()
                        .headers(headers -> {
                            if(headers.containsKey("Api-Key")
                                    && headers.get("Api-Key").get(0) != null
                                    && security.validateAPIKey(headers.get("Api-Key").get(0)))
                                headers.add("AUTHORIZED", "True");
                            else
                                headers.add("AUTHORIZED", "False");
                        });
        return chain.filter(exchange);
    }
}
