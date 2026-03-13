package hello.order.v1

import hello.order.OrderService
import io.github.oshai.kotlinlogging.KotlinLogging
import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import java.util.concurrent.atomic.AtomicInteger

private val log = KotlinLogging.logger {  }

/**
 * 주문수 취소수 서비스에 메트릭 적용
 * MeterRegistry: 마이크로미터 기능 제공 핵심 컴포넌트
 */
class OrderServiceV1(private val registry: MeterRegistry) : OrderService {

    override val stock = AtomicInteger(100) // 재고 수량 100부터 시작

    override fun order() {
        log.info { "주문" }
        stock.decrementAndGet()

        /**
         * 카운터 생성
         */
        Counter.builder("my.order") // 메트릭 이름 지정
            .tag("class", this.javaClass.name) // 프로메테우스에서 필터할 수 있는 레이블 사용
            .tag("method", "order")
            .description("order")
            .register(registry).increment() // MeterRegistry에 등록 & increment(): 카운터 값 하나 증가
    }

    override fun cancel() {
        log.info { "취소" }
        stock.incrementAndGet()

        Counter.builder("my.order")
            .tag("class", this.javaClass.name)
            .tag("method", "cancel")
            .description("order")
            .register(registry).increment()
    }
}