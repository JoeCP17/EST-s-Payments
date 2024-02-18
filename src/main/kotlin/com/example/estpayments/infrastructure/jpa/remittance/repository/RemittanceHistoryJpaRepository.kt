package com.example.estpayments.infrastructure.jpa.remittance.repository

import com.example.estpayments.infrastructure.jpa.remittance.entity.RemittanceHistoryJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface RemittanceHistoryJpaRepository: JpaRepository<RemittanceHistoryJpaEntity, Long> {
}
