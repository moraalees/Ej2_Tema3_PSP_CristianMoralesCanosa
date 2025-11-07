package com.ejemplo.paises.web.mapper

import com.ejemplo.paises.domain.Pais
import com.ejemplo.paises.web.dto.PaisRequest
import com.ejemplo.paises.web.dto.PaisResponse

object PaisMapper {
    fun toEntity(req: PaisRequest): Pais = Pais(
        nombre = req.nombre.trim(),
        codigoIso = req.codigoIso.trim().uppercase(),
        capital = req.capital.trim(),
        poblacion = (req.poblacionMillones * 1_000_000).toLong(),
        superficieKm2 = req.superficieKm2,
        moneda = req.moneda.trim(),
        idiomaPrincipal = req.idiomaPrincipal.trim(),
        continente = req.continente.trim()
    )

    fun toResponse(p: Pais): PaisResponse = PaisResponse(
        id = p.id!!,
        nombre = p.nombre,
        codigoIso = p.codigoIso,
        capital = p.capital ?: "",
        poblacionMillones = (p.poblacion ?: 0L) / 1_000_000.0,
        superficieKm2 = p.superficieKm2 ?: 0.0,
        moneda = p.moneda ?: "",
        idiomaPrincipal = p.idiomaPrincipal ?: "",
        continente = p.continente ?: ""
    )

    fun merge(entity: Pais, req: PaisRequest): Pais = entity.copy(
        nombre = req.nombre.trim(),
        codigoIso = req.codigoIso.trim().uppercase(),
        capital = req.capital.trim(),
        poblacion = (req.poblacionMillones * 1_000_000).toLong(),
        superficieKm2 = req.superficieKm2,
        moneda = req.moneda.trim(),
        idiomaPrincipal = req.idiomaPrincipal.trim(),
        continente = req.continente.trim()
    )
}