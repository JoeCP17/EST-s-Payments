package com.example.estpayments.infrastructure.jpa.member.entity

import com.example.estpayments.infrastructure.jpa.base.BaseJpaEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "member")
class MemberJpaEntity(
    name: String
): BaseJpaEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    var name: String = name
        protected set
}
