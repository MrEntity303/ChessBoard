plugins {
    id("java")
}

group = "it.unicam.cs.pa"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
tasks {
    val run by creating(JavaExec::class) {
        mainClass.set("it.unicam.cs.pa.Main")
        classpath = sourceSets.main.get().runtimeClasspath
    }
}