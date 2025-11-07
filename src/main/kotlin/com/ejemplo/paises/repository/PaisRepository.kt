package com.ejemplo.paises.repository

import com.ejemplo.paises.domain.Pais
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PaisRepository : JpaRepository<Pais, Long>