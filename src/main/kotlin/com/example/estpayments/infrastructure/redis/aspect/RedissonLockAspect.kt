package com.example.estpayments.infrastructure.redis.aspect

import com.example.estpayments.infrastructure.redis.annotations.DistributionLock
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.redisson.api.RLock
import org.redisson.api.RedissonClient
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.expression.ExpressionParser
import org.springframework.expression.spel.standard.SpelExpressionParser
import org.springframework.expression.spel.support.StandardEvaluationContext
import org.springframework.stereotype.Component

@Aspect
@Order(1)
@Component
class RedissonLockAspect(
    private val redissonClient: RedissonClient
) {
    private val log = LoggerFactory.getLogger(RedissonLockAspect::class.java)
    @Around("@annotation(distributionLock)")
    @Throws(Throwable::class)
    fun lock(joinPoint: ProceedingJoinPoint, distributionLock: DistributionLock): Any? {
        val signature = joinPoint.signature as MethodSignature
        val LOCK_PREFIX = "lock:"
        val lockName = LOCK_PREFIX + createRedissonKey(
            signature.parameterNames,
            joinPoint.args,
            distributionLock.lockKey
        )

        val lock: RLock = redissonClient.getLock(lockName)

        try {
            val available =
                lock.tryLock(distributionLock.waitTime, distributionLock.leaseTime, distributionLock.timeUnit)

            if (!available) {
                log.error(
                    LOCK_NOT_GET_MESSAGE,
                    lockName,
                    Thread.currentThread().name
                )
                return false
            }

            log.error(
                LOCK_GET_MESSAGE,
                lockName,
                Thread.currentThread().name
            )
            return joinPoint.proceed()

        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
            throw e
        } finally {
            log.error(
                LOCK_RELEASE_MESSAGE,
                lockName,
                Thread.currentThread().name
            )
            lock.unlock()
        }
    }

    companion object {
        fun createRedissonKey(parameterNames: Array<String>, args: Array<Any>, key: String): Any {
            val expressionParser: ExpressionParser = SpelExpressionParser()
            val context = StandardEvaluationContext()

            parameterNames.forEachIndexed { index, paramName ->
                context.setVariable(paramName, args[index])
            }
            return expressionParser.parseExpression(key).getValue(context, Any::class.java)!!
        }

        private const val LOCK_GET_MESSAGE = "락을 획득하였습니다. key: {}, currentThreadName : {}"
        private const val LOCK_RELEASE_MESSAGE = "락을 해제하였습니다. key: {}, currentThreadName : {}"
        private const val LOCK_NOT_GET_MESSAGE = "락을 획득하지 못했습니다. key: {}, currentThreadName : {}"
    }

}
