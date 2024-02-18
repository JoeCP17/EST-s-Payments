package com.example.estpayments.domain.remittance

import java.time.LocalDateTime

class RemittanceHistory(
    val id: Long? = null,
    val remittances: Remittances,
    val createdAt: LocalDateTime? = null
) {
}
