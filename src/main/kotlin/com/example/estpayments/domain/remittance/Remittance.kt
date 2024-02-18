package com.example.estpayments.domain.remittance

import java.time.LocalDateTime

data class Remittance(
    val id: Long? = null,
    val senderId: Long,
    val receiverId: Long,
    val money: Money,
    val createdAt: LocalDateTime? = null
) {
    init {
        require(senderId != receiverId) { "송금자와 수금자는 같을 수 없습니다." }
    }

}
