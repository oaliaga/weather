package com.oso.weather.common.utils

object Constants {
    const val NAV_WEATHER = "nav_weather"
    const val NAV_CITIES = "nav_cities"

    const val PARAM_API_KEY = "key"
    const val VAL_API_KEY = "c9d3259a3e7c4ec18b7211106262409"

    const val BASE_URL = "https://api.weatherapi.com"

    const val PATH_V1 = "/v1/current.json"

    const val PARAM_QUERY = "q"

    const val PARAM_LANGUAGE = "lang"
    const val VAL_LANGUAGE = "es"

    const val DURATION_SHORT = 3000L
    const val DURATION_LONG = 8000L


    //Room
    const val DB_NAME = "db_weather"
    const val DB_INIT_VERSION = 1

    //E = Entitiy
    const val E_CITY = "city_entity"
    const val E_WEATHER = "weather_entity"

    //P = Property
    const val P_NAME = "name"
    const val P_COUNTRY = "country"
    const val P_CITY_ID = "cityId"

}