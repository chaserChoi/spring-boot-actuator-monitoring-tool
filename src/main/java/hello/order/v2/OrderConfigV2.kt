package hello.order.v2

import hello.order.OrderService
import io.micrometer.core.aop.CountedAspect
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * OrderService 빈 직접 등록하는 설정 클래스
 */
@Configuration
class OrderConfigV2 {

    @Bean
    fun orderService(): OrderService = OrderServiceV2()

    /**
     * @Counted 인지해서 Counter 사용하는 AOP 적용
     */
    @Bean
    fun countedAspect(registry: MeterRegistry): CountedAspect = CountedAspect(registry)
}