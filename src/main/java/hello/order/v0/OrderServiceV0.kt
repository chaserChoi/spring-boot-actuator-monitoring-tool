package hello.order.v0

import hello.order.OrderService
import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.concurrent.atomic.AtomicInteger

private val log = KotlinLogging.logger {  }

class OrderServiceV0 : OrderService {

    override val stock = AtomicInteger(100) // 재고 수량 100부터 시작

    override fun order() {
        log.info { "주문" }
        stock.decrementAndGet()
    }

    override fun cancel() {
        log.info { "취소" }
        stock.incrementAndGet()
    }
}