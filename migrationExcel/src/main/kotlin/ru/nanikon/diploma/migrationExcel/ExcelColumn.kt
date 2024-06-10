package ru.nanikon.diploma.migrationExcel

@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class ExcelColumn(
    val number: Int
)
