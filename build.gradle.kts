plugins {
    id("org.jetbrains.intellij") version "1.17.4"
    kotlin("jvm") version "2.0.20"
}

group = "com.optimizer"
version = "1.0.0"

repositories {
    mavenCentral()
    maven {
        url = uri("https://cache-redirector.jetbrains.com/intellij-repository/releases")
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("junit:junit:4.13.2")
}

intellij {
    version.set("2023.2.1")
    type.set("CL") // Тип IDE: CLion
    plugins.set(listOf("com.intellij.clion")) // Установка зависимостей от CLion
}

tasks {
    patchPluginXml {
        sinceBuild.set("232") // Укажите минимальную версию вашей IDE.
        untilBuild.set("233.*") // Укажите максимальную версию IDE.
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}