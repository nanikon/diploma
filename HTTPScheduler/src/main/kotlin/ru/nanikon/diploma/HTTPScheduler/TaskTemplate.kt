package ru.nanikon.diploma.HTTPScheduler

import ru.nanikon.diploma.HTTPScheduler.domain.TaskRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.transaction.Transactional
import org.slf4j.MDC
import org.springframework.data.repository.findByIdOrNull
import ru.nanikon.diploma.HTTPScheduler.domain.TaskEntity
import java.util.UUID

private val logger = KotlinLogging.logger { }

abstract class TaskTemplate(
    private val taskRepository: TaskRepository,
    private val type: String
) {
    @Transactional
    fun job() {
        MDC.put("traceId", UUID.randomUUID().toString())

        taskRepository.findByIdOrNull(type)?.let {
            logger.debug("${it.logTag} job triggered")
            if (it.isWorked) {
                logger.debug("$it.logTag} job is turned on")
                usefulWork(it)
            } else {
                logger.debug("${it.logTag} job is turned off")
            }
            logger.debug("${it.logTag} job ended")
        } ?: logger.error("$type not found in database")

        MDC.clear()
    }

    abstract fun usefulWork(task: TaskEntity)
}
