package com.example.estpayments.infrastructure.redis.config

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import redis.embedded.RedisServer
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

@Configuration
class EmbeddedRedisConfig {
    @Value("\${spring.data.redis.port}")
    private var redisPort: Int = 0

    private var redisServer: RedisServer? = null

    @PostConstruct
    @Throws(IOException::class)
    fun redisServer() {
        val port = if (isRedisRunning()) findAvailablePort() else redisPort
        redisServer = RedisServer(port)
        redisServer?.start()
    }

    @PreDestroy
    fun stopRedis() {
        redisServer?.stop()
    }

    @Throws(IOException::class)
    private fun isRedisRunning(): Boolean = isRunning(executeGrepProcessCommand(redisPort))

    @Throws(IOException::class)
    fun findAvailablePort(): Int {
        for (port in 10000..65535) {
            val process = executeGrepProcessCommand(port)
            if (!isRunning(process)) {
                return port
            }
        }
        throw IllegalArgumentException(NOT_FOUND_AVAILABLE_PORT)
    }

    @Throws(IOException::class)
    private fun executeGrepProcessCommand(port: Int): Process {
        val command = NETSTATS_COMMAND + port
        return Runtime.getRuntime().exec(arrayOf("/bin/sh", "-c", command))
    }

    private fun isRunning(process: Process): Boolean {
        val pidInfo = StringBuilder()
        BufferedReader(InputStreamReader(process.inputStream)).use { input ->
            var line: String?
            while (input.readLine().also { line = it } != null) {
                pidInfo.append(line)
            }
        }
        return pidInfo.toString().isNotEmpty()
    }

    companion object {
        private const val NOT_FOUND_AVAILABLE_PORT = "Not Found Available port: 10000 ~ 65535"
        private const val NETSTATS_COMMAND = "netstat -nat | grep LISTEN|grep "
    }

}
