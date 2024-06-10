package ru.nanikon.diploma.PeriodTask.domain

import jakarta.persistence.MappedSuperclass

@MappedSuperclass
abstract class RecordEntity(
    val id: Int,
    var stackTrace: String,
    var isReady: Boolean,
    var attempt: Int
)
