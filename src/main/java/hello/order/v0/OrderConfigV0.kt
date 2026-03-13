package hello.order.v0

import hello.order.OrderService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * OrderService 빈 직접 등록하는 설정 클래스
 */
@Configuration
class OrderConfigV0 {

    @Bean
    fun orderService(): OrderService = OrderServiceV0()
}