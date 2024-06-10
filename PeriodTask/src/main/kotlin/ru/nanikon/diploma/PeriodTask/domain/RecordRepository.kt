package ru.nanikon.diploma.PeriodTask.domain

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface RecordRepository<E : RecordEntity> : CrudRepository<E, Int> {
    fun findForProcessing(): E?
}
