import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.6"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
}

group = "com.ken1427"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	val exposedVersion = "0.41.1"
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// Open api swagger
	implementation("org.springdoc:springdoc-openapi-data-rest:1.6.14")
	implementation("org.springdoc:springdoc-openapi-ui:1.6.14")
	implementation("org.springdoc:springdoc-openapi-kotlin:1.6.14")

	// Exposed
	implementation("org.jetbrains.exposed:exposed-spring-boot-starter:$exposedVersion")
	implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
	implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
	implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
	implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")
	implementation("mysql:mysql-connector-java:8.0.30")

	// Test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
