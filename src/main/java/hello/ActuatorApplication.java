package hello;

import hello.order.v0.OrderConfigV0;
import hello.order.v1.OrderConfigV1;
import hello.order.v2.OrderConfigV2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

//@Import(OrderConfigV0.class)
//@Import(OrderConfigV1.class)
@Import(OrderConfigV2.class)
@SpringBootApplication(scanBasePackages = "hello.controller")
public class ActuatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActuatorApplication.class, args);
    }

    /**
     * HTTP 요청 & 응답의 과거 기록 확인(httpexchanges)을 위한 스프링 빈 등록
     * @return
     */
    @Bean
    public InMemoryHttpExchangeRepository httpExchangeRepository() {
        return new InMemoryHttpExchangeRepository();
    }

}
