package com.example.estpayments.infrastructure.jpa.remittance.entity

import com.example.estpayments.infrastructure.jpa.base.BaseJpaEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class RemittanceHistoryJpaEntity(
    remittance: RemittanceJpaEntity
): BaseJpaEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set
    @ManyToOne
    @JoinColumn(name = "remittance_id")
    var remittances: RemittanceJpaEntity = remittance
        protected set
}
