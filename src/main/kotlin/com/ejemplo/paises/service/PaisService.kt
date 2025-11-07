package com.ejemplo.paises.service

import com.ejemplo.paises.domain.Pais
import com.ejemplo.paises.repository.PaisRepository
import org.springframework.stereotype.Service

@Service
class PaisService(private val paisRepository: PaisRepository) {

    fun findAll(): List<Pais> = paisRepository.findAll()

    fun findById(id: Long): Pais =
        paisRepository.findById(id).orElseThrow { NotFoundException("País con id $id no encontrado") }

    fun create(pais: Pais): Pais = paisRepository.save(pais)

    fun update(id: Long, updated: Pais): Pais {
        val existing = findById(id)
        val merged = existing.copy(
            nombre = updated.nombre,
            codigoIso = updated.codigoIso,
            capital = updated.capital,
            poblacion = updated.poblacion,
            superficieKm2 = updated.superficieKm2,
            moneda = updated.moneda,
            idiomaPrincipal = updated.idiomaPrincipal,
            continente = updated.continente
        )
        return paisRepository.save(merged)
    }

    fun delete(id: Long) {
        if (!paisRepository.existsById(id))
            throw NotFoundException("País con id $id no encontrado")
        paisRepository.deleteById(id)
    }
}