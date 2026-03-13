package hello.order

import java.util.concurrent.atomic.AtomicInteger

/**
 * 주문 서비스 인터페이스
 */
interface OrderService {
    fun order() // 주문
    fun cancel() // 취소

    val stock: AtomicInteger // 재고 수량
}