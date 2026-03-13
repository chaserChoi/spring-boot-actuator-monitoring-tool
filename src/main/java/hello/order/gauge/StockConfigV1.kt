package hello.order.gauge

import hello.order.OrderService
import io.github.oshai.kotlinlogging.KotlinLogging
import io.micrometer.core.instrument.Gauge
import io.micrometer.core.instrument.MeterRegistry
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * 메트릭 등록 - 게이지(Gauge)
 * 임의로 오르내릴 수 있는 단일 숫자 값
 * 값 현재 상태 보는데 사용 ex) 차량 속도, CPU 사용량, 메모리 사용량
 *
 * 재고 수량을 통한 게이지 등록 방법
 */
@Configuration
class StockConfigV1 {

    @Bean
    fun myStockMetric(orderService: OrderService, registry: MeterRegistry): MyStockMetric =
        MyStockMetric(orderService, registry)

    class MyStockMetric(
        private val orderService: OrderService,
        private val registry: MeterRegistry
    ) {
        private val log = KotlinLogging.logger {  }

        @PostConstruct
        fun init() {
            Gauge.builder("my.stock", orderService) { service ->
                log.info { "stock gauge call" }
                service.stock.get().toDouble()
            }.register(registry)
        }
    }
}