package com.example.estpayments.infrastructure.redis.annotations

import java.util.concurrent.TimeUnit

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class DistributionLock(
    val lockKey: String,
    val timeUnit: TimeUnit = TimeUnit.SECONDS,
    val leaseTime: Long = 5,
    val waitTime: Long = 5
)
