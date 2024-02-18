package com.example.estpayments.domain.member

import java.time.LocalDateTime

data class Member(
    val id: Long? = null,
    val name: String,
    val createdAt: LocalDateTime? = null
) {
    init {
        require(name.isNotBlank()) { "이름은 빈 값이 올 수 없습니다." }
        require(name.length <= 20) { "이름은 20자를 넘을 수 없습니다." }
    }
}
