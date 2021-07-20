plugins {
    kotlin("multiplatform") version "1.4.10"
//    `maven-publish`
}
group = "io.stud.forest"
version = "0.0.9"

repositories {
    mavenCentral()
}


kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
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
                    implementation("org.jetbrains.kotlin:kotlin-stdlib")
                }
            }

            // According to documentation you can only use a dedicated library for testing common
            val commonTest by getting {
                dependencies {
                    implementation(kotlin("test-common"))
                    implementation(kotlin("test-annotations-common"))
                    implementation(kotlin("test-junit5"))
                }
            }
            val jvmMain by getting {
                dependencies {
                    implementation("org.jetbrains.kotlin:kotlin-stdlib")
                    implementation("org.jetbrains.kotlin:kotlin-reflect:1.3.41")
                }
            }
            val jvmTest by getting {

                dependencies {
                    implementation(kotlin("test-junit5"))
                    compileOnly("org.junit.jupiter:junit-jupiter-params:5.7.0")
                    compileOnly("org.assertj:assertj-core:3.11.1")

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



