package de.hdmstuttgart.wetter

class Configuration {

    companion object {
        var BASE_LINK: String = "https://api.openweathermap.org/data/2.5/"
        var API_KEY: String = "cc5ef9e3576dc1e8bc30087dae5ee9ca"
        const val PERMISSION_CODE = 130
        //London: https://api.openweathermap.org/data/2.5/weather?q=London&APPID=cc5ef9e3576dc1e8bc30087dae5ee9ca
    }
}