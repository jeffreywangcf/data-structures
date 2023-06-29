plugins {
    id("java")
    id("application")
    id("jacoco")
}

application {
    mainClass.set("datastructures.Driver")
}

jacoco {
    toolVersion = "0.8.7"
}

tasks.named("test") {
    finalizedBy("jacocoTestReport")
}

tasks.named("jacocoTestReport") {
    dependsOn("test")
}

group = "net.jeffreywang.datastructures"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}