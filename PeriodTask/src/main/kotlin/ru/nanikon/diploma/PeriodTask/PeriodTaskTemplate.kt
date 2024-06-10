package ru.nanikon.diploma.PeriodTask

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.transaction.Transactional
import ru.nanikon.diploma.HTTPScheduler.TaskTemplate
import ru.nanikon.diploma.HTTPScheduler.domain.TaskEntity
import ru.nanikon.diploma.PeriodTask.domain.RecordEntity
import ru.nanikon.diploma.PeriodTask.domain.RecordRepository
import ru.nanikon.diploma.HTTPScheduler.domain.TaskRepository

private val logger = KotlinLogging.logger { }

abstract class PeriodTaskTemplate<E : RecordEntity, in R : RecordRepository<E>>(
    private val recordRepository: R,
    type: String,
    taskRepository: TaskRepository,
) : TaskTemplate(taskRepository, type) {
    abstract var maxAttempt: Int
    abstract fun processing(record: E)

    @Transactional
    override fun usefulWork(task: TaskEntity) {
        recordRepository.findForProcessing()?.let { record ->
            runCatching {
                logger.info { "${task.logTag} Start processed record with id=${record.id}" }
                processing(record)
                record.isReady = false
                logger.info { "${task.logTag} Record processed successfully" }
            }.onFailure {
                logger.error(it) { "${task.logTag} Error occurred" }
                record.stackTrace = it.stackTrace.toString()
                record.attempt++
                if (record.attempt >= maxAttempt) {
                    record.isReady = false
                }
            }
            recordRepository.save(record)
        } ?: logger.trace { "${task.logTag} Not found record for processing" }
    }
}
