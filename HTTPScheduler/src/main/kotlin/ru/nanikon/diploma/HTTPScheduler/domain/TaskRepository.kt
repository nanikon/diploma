package ru.nanikon.diploma.HTTPScheduler.domain

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository : CrudRepository<TaskEntity, String> {
}
