package com.example.estpayments.infrastructure.jpa.member.repository

import com.example.estpayments.infrastructure.jpa.member.entity.MemberJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberJpaRepository: JpaRepository<MemberJpaEntity, Long> {
}
