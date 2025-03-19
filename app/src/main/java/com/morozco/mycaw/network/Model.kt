package com.morozco.mycaw.network

data class ItemResponse(
    val capital: String? = null,
    val code: String? = null,
    val currency: Currency? = null,
    val flag: String? = null,
    val language: Language? = null,
    val name: String? = null,
    val region: String? = null
)

data class Currency(
    val code: String? = null,
    val name: String? = null,
    val symbol: String? = null
)

data class Language(
    val code: String? = null,
    val name: String? = null
)

//API Status
enum class Status {
    UNKNOWN,
    IN_PROGRESS,
    READY,
    FAIL
}