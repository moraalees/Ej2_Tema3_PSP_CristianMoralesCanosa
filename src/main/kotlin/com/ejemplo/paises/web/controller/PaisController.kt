package com.ejemplo.paises.web.controller

import com.ejemplo.paises.service.PaisService
import com.ejemplo.paises.web.dto.PaisRequest
import com.ejemplo.paises.web.dto.PaisResponse
import com.ejemplo.paises.web.mapper.PaisMapper
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/paises")
class PaisController(
    private val service: PaisService
) {

    @GetMapping
    fun getAll(): List<PaisResponse> =
        service.findAll().map { PaisMapper.toResponse(it) }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): PaisResponse =
        PaisMapper.toResponse(service.findById(id))

    @PostMapping
    fun create(@RequestBody request: PaisRequest): PaisResponse =
        PaisMapper.toResponse(service.create(PaisMapper.toEntity(request)))

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: PaisRequest): PaisResponse {
        val existing = service.findById(id)
        val merged = PaisMapper.merge(existing, request)
        return PaisMapper.toResponse(service.update(id, merged))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) =
        service.delete(id)
}