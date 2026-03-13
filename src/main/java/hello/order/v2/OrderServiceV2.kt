package hello.order.v2

import hello.order.OrderService
import io.github.oshai.kotlinlogging.KotlinLogging
import io.micrometer.core.annotation.Counted
import java.util.concurrent.atomic.AtomicInteger

private val log = KotlinLogging.logger {  }

/**
 * 메트릭 적용: @Counted
 * 스프링 AOP 활용
 * @Counted("value"): 측정을 원하는 메서드에 적용 & 이룸 지정 (tag에 method 기준 분류해서 적용)
 */
open class OrderServiceV2 : OrderService {

    override val stock = AtomicInteger(100) // 재고 수량 100부터 시작

    @Counted("my.order")
    override fun order() {
        log.info { "주문" }
        stock.decrementAndGet()
    }

    @Counted("my.order")
    override fun cancel() {
        log.info { "취소" }
        stock.incrementAndGet()
    }
}