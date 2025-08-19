plugins {
    alias(libs.plugins.es.rudo.application.compose)
    alias(libs.plugins.es.rudo.compose.ui)
    alias(libs.plugins.es.rudo.dependency.injection)
}

/** Common Android configurations are already handled by the convention plugins.
 * If module-specific configuration is needed, for example, for app signing (signingConfigs),
 * add it here. */
android {
    namespace = "com.rudo.rickAndMorty" // Replace with your actual namespace
}

dependencies {
    implementation(libs.retrofit.v230)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor.v4120)
    implementation(libs.coil.compose)
}
