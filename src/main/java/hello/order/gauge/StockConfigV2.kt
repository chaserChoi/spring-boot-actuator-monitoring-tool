package hello.order.gauge

import hello.order.OrderService
import io.github.oshai.kotlinlogging.KotlinLogging
import io.micrometer.core.instrument.Gauge
import io.micrometer.core.instrument.binder.MeterBinder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

private val log = KotlinLogging.logger {  }

@Configuration
class StockConfigV2 {

    @Bean
    fun stockSize(orderService: OrderService): MeterBinder =
        MeterBinder { registry ->
            Gauge.builder("my.stock", orderService) { service ->
                log.info { "stock gauge call" }
                service.stock.get().toDouble()
            }.register(registry)
        }
}