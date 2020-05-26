package com.jaden.webflux.ex3;

import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Service
public class BasicService {
    private final ReactiveRedisConnectionFactory factory;
    private final ReactiveRedisOperations<String, String> reactiveRedisOperations;
    private final RedisTemplate<String , String> stringStringRedisTemplate;
    private static final AtomicInteger count = new AtomicInteger(0);

    public BasicService(ReactiveRedisConnectionFactory factory,
                        ReactiveRedisOperations<String, String> reactiveRedisOperations,
                        RedisTemplate<String, String> stringStringRedisTemplate) {
        this.factory = factory;
        this.reactiveRedisOperations = reactiveRedisOperations;
        this.stringStringRedisTemplate = stringStringRedisTemplate;
    }

    void loadData() {
        List<String> data = new ArrayList<>();
        IntStream.range(0, 100000).forEach(i -> data.add(UUID.randomUUID().toString()));
        Flux<String> stringFlux = Flux.fromIterable(data);
        factory.getReactiveConnection()
                .serverCommands()
                .flushAll()
                .thenMany(stringFlux.flatMap(uid ->
                        reactiveRedisOperations.opsForValue().set(String.valueOf(count.getAndAdd(1)), uid)))
                .subscribe();
    }

    Flux<String> findReactorList() {
        return reactiveRedisOperations
                .keys("*")
                .flatMap(key -> reactiveRedisOperations.opsForValue().get(key));
    }

    Flux<String> findNormalList() {
        return Flux.fromIterable(Objects.requireNonNull(stringStringRedisTemplate.keys("*"))
                .stream()
                .map(key -> stringStringRedisTemplate.opsForValue().get(key))
                .collect(Collectors.toList()));
    }

}
