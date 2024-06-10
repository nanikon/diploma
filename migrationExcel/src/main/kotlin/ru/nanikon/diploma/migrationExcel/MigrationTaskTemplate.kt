package ru.nanikon.diploma.migrationExcel

import org.springframework.data.jpa.repository.JpaRepository
import ru.nanikon.diploma.HTTPScheduler.TaskTemplate
import ru.nanikon.diploma.HTTPScheduler.domain.TaskEntity
import ru.nanikon.diploma.HTTPScheduler.domain.TaskRepository

abstract class MigrationTaskTemplate<I, O : Any, ID>(
    val migration: MigrationInstance<I, O>,
    val entityRepository: JpaRepository<O, ID>,
    taskRepository: TaskRepository,
    taskType: String
) : TaskTemplate(taskRepository, taskType) {
    open fun start() {
        job()
    }

    override fun usefulWork(task: TaskEntity) {
        val record = ExcelReader().read(migration, 5)
        entityRepository.save(migration.mapping(record))
    }
}
