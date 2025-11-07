package com.ejemplo.paises.web.dto

data class PaisRequest(
    val nombre: String,
    val codigoIso: String,
    val capital: String,
    val poblacionMillones: Double,
    val superficieKm2: Double,
    val moneda: String,
    val idiomaPrincipal: String,
    val continente: String
)