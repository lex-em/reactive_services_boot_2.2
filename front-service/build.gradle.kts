import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val artifactId = "front-service"

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile(kotlin("reflect"))
    compile("org.springframework.boot:spring-boot-starter-webflux")
    compile("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    compile("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    compile("org.springframework.data:spring-data-commons")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin")
    compile("io.github.microutils:kotlin-logging:1.6.26")
    testCompile("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions{
        jvmTarget = "11"
        apiVersion = "1.3"
        languageVersion = "1.3"
    }
}

