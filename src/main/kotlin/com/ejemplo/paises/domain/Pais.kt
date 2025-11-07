package com.ejemplo.paises.domain

import jakarta.persistence.*

@Entity
@Table(name = "paises")
data class Pais(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(length = 100, nullable = false)
    val nombre: String,

    @Column(length = 3, nullable = false, unique = true)
    val codigoIso: String,

    @Column(length = 100)
    val capital: String? = null,

    val poblacion: Long? = null,
    val superficieKm2: Double? = null,

    @Column(length = 50)
    val moneda: String? = null,

    @Column(length = 50)
    val idiomaPrincipal: String? = null,

    @Column(length = 30)
    val continente: String? = null
)