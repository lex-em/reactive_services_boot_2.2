import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val artifactId = "database-service"

apply(plugin = "org.jetbrains.kotlin.plugin.jpa")

repositories {
    maven { url = uri("https://repo.spring.io/libs-milestone") }
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile(kotlin("reflect"))
    compile("org.springframework.boot:spring-boot-starter-webflux")
    compile("org.springframework.data:spring-data-r2dbc:1.0.0.RC1")
    compile("io.projectreactor.kotlin:reactor-kotlin-extensions")
    compile("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    compile("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin")
    compile("io.github.microutils:kotlin-logging:1.6.26")
//    compile("com.zaxxer:HikariCP")
    compile("org.flywaydb:flyway-core:5.2.4")
    runtime("org.postgresql:postgresql")
    compile("io.r2dbc:r2dbc-postgresql:0.8.0.RC2")
    compile("io.r2dbc:r2dbc-pool:0.8.0.RC2")
    testCompile("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions{
        jvmTarget = "11"
        apiVersion = "1.3"
        languageVersion = "1.3"
    }
}

