# 예제 1 (ex1)
자신의 Github 접속 토큰을 이용하여 정보를 조회 수정 한다.  
Webflux 기반의 RESTful 서버가 기본이며,  
내부에서 Github으로 Webclient 방식으로 호출한다.

필요한 정보는 @EnableConfigurationProperties 를 이용하여 객체와 하여 사용  

참고 : 블로그를 잊어버림 하하 곧 업뎃하겠음

# 예제 2 (ex2)
OMDB API 를 사용한 동일한 예제 (https://omdbapi.com/)  

필요한 정보는 Environment 를 이용하여 사용  

참고 : https://springframework.guru/spring-5-webclient/

# 예제 3 (ex3)
redis도 비동방식이라 화면에서 block 현상없이 데이터를 받아온다. 
필요한 정보는 @Value("${spring.redis.port") 를 이용하여 사용

참고 : https://warpgate3.tistory.com/entry/Spring2xWebFlux-ReactiveRedis

# Swagger 2 추가
Webflux 에서는 @EnableSwagger2WebFlux 사용을 위해  
gradle repository 에 아래를 추가해야 함  
maven { url "http://oss.jfrog.org/artifactory/oss-snapshot-local"}  

http://localhost:8080/swagger-ui.html
