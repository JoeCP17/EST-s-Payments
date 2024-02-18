package com.example.estpayments.domain.remittance

import java.math.BigDecimal

@JvmInline
value class Money(
    val amount: BigDecimal
) {
    init {
        require(amount >= BigDecimal.ZERO) { "금액은 0보다 작을 수 없습니다." }
    }
}
