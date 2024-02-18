package com.example.estpayments.infrastructure.jpa.base

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseJpaEntity {
    @CreationTimestamp
    var createdAt: LocalDateTime? = null
        protected set
}
