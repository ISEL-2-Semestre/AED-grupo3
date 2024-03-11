plugins {
    kotlin("jvm") version "1.9.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.github.palex65:CanvasLib-jvm:1.0.2") // Adicionar
}


tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}