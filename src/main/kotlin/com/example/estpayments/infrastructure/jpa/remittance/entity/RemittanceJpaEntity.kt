package com.example.estpayments.infrastructure.jpa.remittance.entity

import com.example.estpayments.infrastructure.jpa.base.BaseJpaEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "remittance")
class RemittanceJpaEntity(
    senderId: Long,
    receiverId: Long,
    money: BigDecimal
): BaseJpaEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    var senderId: Long = senderId
        protected set

    var receiverId: Long = receiverId
        protected set

    var money: BigDecimal = money
        protected set

}
