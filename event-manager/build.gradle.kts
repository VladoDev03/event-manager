plugins {
	java
	id("org.springframework.boot") version "3.4.0"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
//	Hibernate
	implementation("org.hibernate:hibernate-core:6.5.2.Final")
	implementation("mysql:mysql-connector-java:8.0.18")
	implementation("org.hibernate.validator:hibernate-validator:8.0.1.Final")
	implementation("org.hibernate.validator:hibernate-validator-annotation-processor:8.0.1.Final")

//	JWTs
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
	implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")

//	Spring Boot
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.security:spring-security-crypto")

//	Cloudinary
	implementation("com.cloudinary:cloudinary-http5:2.0.0")
	implementation("com.cloudinary:cloudinary-taglib:2.0.0")

// QR code generating
	implementation("com.google.zxing:core:3.4.1")
	implementation("com.google.zxing:javase:3.4.1")

//	testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
