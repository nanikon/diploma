package ru.nanikon.diploma.HTTPScheduler.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class TaskEntity(
    @Id
    val id: String,
    val logTag: String
) {
    var isWorked: Boolean = false
}
