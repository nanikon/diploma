plugins {
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
    kotlin("plugin.jpa") version "1.9.23"
}

group = "ru.nanikon"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    api("org.springframework.boot:spring-boot-starter-web")
    api("org.postgresql:postgresql")
    api("com.fasterxml.jackson.module:jackson-module-kotlin")
    api("io.github.oshai:kotlin-logging-jvm:5.1.0")
    api("org.jetbrains.kotlin:kotlin-reflect")
    api("org.apache.poi:poi-ooxml:5.2")
    api("org.apache.poi:poi:5.2")
    api("org.apache.poi:poi-ooxml-schemas")
    api("org.apache.commons:commons-lang3")

    api(project(":HTTPScheduler"))
    api(project(":PeriodTask"))

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(18)
}