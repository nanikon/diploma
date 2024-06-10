package ru.nanikon.diploma.HTTPScheduler

import ru.nanikon.diploma.HTTPScheduler.domain.TaskRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger { }

@RestController
@RequestMapping("/api/v1/taskWorker")
class SchedulerController(
    private val taskRepository: TaskRepository
) {
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["{type}/start"]
    )
    fun start(type: String): ResponseEntity<Boolean> {
        logger.info { "startJob: Request received: type=$type" }
        val result = taskRepository.findByIdOrNull(type)
            ?.let {
                taskRepository.save(it.apply { isWorked = true })
                ResponseEntity.ok(true)
            }
            ?: ResponseEntity.notFound().build()
        return result
    }

    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["{type}/stop"]
    )
    fun stop(type: String): ResponseEntity<Boolean> {
        logger.info { "startJob: Request received: type=$type" }
        val result = taskRepository.findByIdOrNull(type)
            ?.let {
                taskRepository.save(it.apply { isWorked = false })
                ResponseEntity.ok(true)
            }
            ?: ResponseEntity.notFound().build()
        return result
    }
}
