package com.example.estpayments.infrastructure.jpa.remittance.repository

import com.example.estpayments.infrastructure.jpa.remittance.entity.RemittanceJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface RemittanceJpaRepository: JpaRepository<RemittanceJpaEntity, Long> {
}
