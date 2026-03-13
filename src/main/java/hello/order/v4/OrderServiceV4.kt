package hello.order.v4

import hello.order.OrderService
import io.github.oshai.kotlinlogging.KotlinLogging
import io.micrometer.core.annotation.Timed
import java.util.concurrent.atomic.AtomicInteger
import kotlin.random.Random

private val log = KotlinLogging.logger {  }

/**
 * 메트릭 등록: @Timed
 * @Timed 애노테이션으로 AOP 등록
 */
@Timed("my.order")
open class OrderServiceV4 : OrderService {

    override val stock = AtomicInteger(100) // 재고 수량 100부터 시작

    override fun order() {
        log.info { "주문" }
        stock.decrementAndGet()
        sleep(500)
    }

    override fun cancel() {
        log.info { "취소" }
        stock.incrementAndGet()
        sleep(200)
    }

    private fun sleep(l: Int) {
        try {
            Thread.sleep(l + Random.nextInt(200).toLong())
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }
    }
}
