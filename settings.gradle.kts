plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "diploma"
include("HTTPScheduler")
include("PeriodTask")
include("migrationExcel")
