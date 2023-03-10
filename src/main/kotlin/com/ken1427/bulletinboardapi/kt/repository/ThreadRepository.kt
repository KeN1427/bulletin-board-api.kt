package com.ken1427.bulletinboardapi.kt.repository

import com.ken1427.bulletinboardapi.kt.dao.Threads
import com.ken1427.bulletinboardapi.kt.entity.Thread
import com.ken1427.bulletinboardapi.kt.shared.config.DBConfig
import org.jetbrains.exposed.dao.flushCache
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

interface ThreadRepository {
    fun getThread(threadId: Int): Thread
    fun getOpenThreads(): List<Thread>
    fun createThread(userId: Int): Thread
    fun deleteThread(threadId: Int)
}

@Repository
class ThreadRepositoryImpl: ThreadRepository {
    @Autowired
    lateinit var dbConfig: DBConfig
    override fun getThread(threadId: Int): Thread {
        Database.connect(dbConfig.url, dbConfig.driverClassName, dbConfig.username, dbConfig.password)
        return transaction {
            addLogger(StdOutSqlLogger)

            Thread.find { Threads.id eq threadId }.single()
        }
    }

    override fun getOpenThreads(): List<Thread> {
        Database.connect(dbConfig.url, dbConfig.driverClassName, dbConfig.username, dbConfig.password)
        return transaction {
            addLogger(StdOutSqlLogger)

            Thread.find {
                Threads.status eq Thread.Companion.Status.OPEN.name
            }.map { it }
        }
    }

    override fun createThread(userId: Int): Thread {
        Database.connect(dbConfig.url, dbConfig.driverClassName, dbConfig.username, dbConfig.password)
        return transaction {
            addLogger(StdOutSqlLogger)

            val newThread = Thread.new {
                this.userId = userId
                this.status = Thread.Companion.Status.OPEN.name
            }
            flushCache()

            Thread.find { Threads.id eq newThread.id }.single()
        }
    }

    override fun deleteThread(threadId: Int) {
        Database.connect(dbConfig.url, dbConfig.driverClassName, dbConfig.username, dbConfig.password)
        transaction {
            addLogger(StdOutSqlLogger)

            Thread.find { Threads.id eq threadId }.single().also { thread ->
                thread.delete()
            }
        }
    }
}