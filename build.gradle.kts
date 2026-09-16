plugins {
    java
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

application {
    mainClass.set("biblioteca.Main")
}

tasks.named<JavaExec>("run") {
    // Sem isto, o app não consegue ler o que você digita no terminal.
    standardInput = System.`in`
}

tasks.test {
    useJUnitPlatform()
}
