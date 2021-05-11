plugins {
    kotlin("multiplatform") version "1.4.10"
    `maven-publish`
}
group = "io.stud.forest"
version = "0.0.7"

repositories {
    mavenCentral()
}


kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "13"
        }

        js {
            browser {
                testTask {
                    useKarma {
                        useChromeHeadless()
                        webpackConfig.cssSupport.enabled = true
                    }
                }
            }
        }
        val hostOs = System.getProperty("os.name")
        val isMingwX64 = hostOs.startsWith("Windows")
        val nativeTarget = when {
            hostOs == "Mac OS X" -> macosX64("native")
            hostOs == "Linux" -> linuxX64("native")
            isMingwX64 -> mingwX64("native")
            else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
        }


        sourceSets {
            val commonMain by getting{
                dependencies{
                    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
                }
            }
            val commonTest by getting {
                dependencies {
                    implementation(kotlin("test-common"))
                    implementation(kotlin("test-annotations-common"))
                }
            }
            val jvmMain by getting {
                dependencies {
                    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
                    implementation("org.jetbrains.kotlin:kotlin-reflect:1.3.41")
                }
            }
            val jvmTest by getting {
                dependencies {
                    implementation(kotlin("test-junit5"))
                }
            }
            val jsMain by getting
            val jsTest by getting {
                dependencies {
                    implementation(kotlin("test-js"))
                }
            }
            val nativeMain by getting
            val nativeTest by getting
        }
    }
}



