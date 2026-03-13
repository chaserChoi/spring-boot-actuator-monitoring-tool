package hello.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.sql.DataSource

private val log = KotlinLogging.logger {  }

@RestController
class TrafficController {

    /**
     * CPU 사용량 초과 모니터링
     * CPU에 간단한 부하 주는 코드
    */
    @GetMapping("/cpu")
    fun cpu(): String {
        log.info { "cpu" }
        var value = 0
        for (i in 0..<100000000000L) {
            value++
        }
        return "ok value=$value"
    }

    /**
     * JVM 메모리 사용량 초과
     * 메모리 사용 누적 코드
     */
    private val list = mutableListOf<String>()

    @GetMapping("/jvm")
    fun jvm(): String {
        log.info { "jvm" }
        for (i in 0..<1_000_000) {
            list.add("hello jvm!$i")
        }
        return "ok"
    }

    /**
     * 커넥션 풀 고갈
     */
    @Autowired lateinit var dataSource: DataSource

    @GetMapping("/jdbc")
    fun jdbc(): String {
        log.info { "jdbc" }
        val conn = dataSource.connection
        log.info { "connection info=$conn" }
        // conn.close() // 커넥션을 닫지 않는다.
        return "ok"
    }

    /**
     * 에러 로그 급증
     * 애플리케이션에서 ERROR 레벨 로그 급증
     */
    @GetMapping("/error-log")
    fun errorLog(): String {
        log.error { "error log" }
        return "error"
    }
}