package hello.order.v3

import hello.order.OrderService
import io.micrometer.core.aop.CountedAspect
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * OrderService 빈 직접 등록하는 설정 클래스
 */
@Configuration
class OrderConfigV3 {

    @Bean
    fun orderService(registry: MeterRegistry): OrderService = OrderServiceV3(registry)
}