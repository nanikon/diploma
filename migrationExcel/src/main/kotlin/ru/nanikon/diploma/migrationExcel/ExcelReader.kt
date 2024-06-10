package ru.nanikon.diploma.migrationExcel

import org.springframework.stereotype.Service
import java.io.FileInputStream
import org.apache.poi.xssf.usermodel.XSSFWorkbook

@Service
class ExcelReader {
    fun <I> read(migration: MigrationInstance<I, *>, currentRow: Int): I {
        val book = XSSFWorkbook(FileInputStream(migration.filePath))
        val sheet = book.getSheet(migration.sheetName)
        val row = sheet.getRow(currentRow)

        val excelClass = migration.excelEntity
        val result = excelClass.getDeclaredConstructor().newInstance()
        excelClass.fields.forEach {
            if (it.isAnnotationPresent(ExcelColumn::class.java)) {
                val columnNumber = it.getAnnotation(ExcelColumn::class.java).number
                val value = row.getCell(migration.firstColumn + columnNumber).getStringCellValue()
                it.set(result, value)
            }

        }
        migration.firstRow
        migration.excelEntity
        migration.lastRow
        migration.mapping(result)

        book.close()
        return result
    }
}
