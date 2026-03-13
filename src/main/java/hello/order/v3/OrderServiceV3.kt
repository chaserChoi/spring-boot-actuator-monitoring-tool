package hello.order.v3

import hello.order.OrderService
import io.github.oshai.kotlinlogging.KotlinLogging
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.Timer
import java.util.concurrent.atomic.AtomicInteger
import kotlin.random.Random

private val log = KotlinLogging.logger {  }

/**
 * 메트릭 등록: Timer
 * 실행 시간 측정
 * - seconds_count: 누적 실행 수 (카운터)
 * - seconds_sum: 실행 시간 합 (sum)
 * - seconds_max: 최대 실행 시간 (게이지)
 */
open class OrderServiceV3(private val registry: MeterRegistry) : OrderService {

    override val stock = AtomicInteger(100) // 재고 수량 100부터 시작

    override fun order() {
        val timer = Timer.builder("my.order") // 타이머 생성, name에 메트릭 이름 지정
            .tag("class", this.javaClass.name) // 프로메테우스에서 필터할 수 있는 레이블 사용
            .tag("method", "order") // order, cancel 메트릭 이름 구분
            .description("order")
            .register(registry) // MeterRegistry 등록

        // 타이머 사용
        timer.record(Runnable {
            log.info { "주문" }
            stock.decrementAndGet()
            sleep(500)
        })
    }

    override fun cancel() {
        val timer = Timer.builder("my.order")
            .tag("class", this.javaClass.name)
            .tag("method", "cancel")
            .description("order")
            .register(registry)


        timer.record(Runnable {
            log.info { "취소" }
            stock.incrementAndGet()
            sleep(200)
        })
    }

    private fun sleep(l: Int) {
        try {
            Thread.sleep(l + Random.nextInt(200).toLong())
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }
    }
}
