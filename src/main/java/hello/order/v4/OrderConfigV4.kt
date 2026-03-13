package hello.order.v4

import hello.order.OrderService
import io.micrometer.core.aop.TimedAspect
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * OrderService 빈 직접 등록하는 설정 클래스
 */
@Configuration
class OrderConfigV4 {

    @Bean
    fun orderService(): OrderService = OrderServiceV4()

    /**
     * TimedAspect 적용해야 @Timed 사용 가능
     */
    @Bean
    fun timedAspect(registry: MeterRegistry): TimedAspect = TimedAspect(registry)
}