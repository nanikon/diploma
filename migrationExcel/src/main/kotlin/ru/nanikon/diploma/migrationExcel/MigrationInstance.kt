package ru.nanikon.diploma.migrationExcel

abstract class MigrationInstance<I, O>(
    val filePath: String,
    val sheetName: String,
    val firstRow: Int,
    val lastRow: Int,
    val firstColumn: Int,
    val excelEntity: Class<I>
) {
    abstract fun mapping(input: I): O
}
