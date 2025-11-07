package com.ejemplo.paises

import com.ejemplo.paises.domain.Pais
import com.ejemplo.paises.repository.PaisRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DataInitializer {

    @Bean
    fun initDatabase(paisRepository: PaisRepository) = CommandLineRunner {
        if (paisRepository.count() == 0L) {
            val paises = listOf(
                Pais(nombre = "España", codigoIso = "ESP", capital = "Madrid", poblacion = 48000000, superficieKm2 = 505990.0,
                    moneda = "Euro (EUR)", idiomaPrincipal = "Español", continente = "Europa"),
                Pais(nombre = "Francia", codigoIso = "FRA", capital = "París", poblacion = 67000000, superficieKm2 = 551695.0,
                    moneda = "Euro (EUR)", idiomaPrincipal = "Francés", continente = "Europa"),
                Pais(nombre = "Japón", codigoIso = "JPN", capital = "Tokio", poblacion = 125000000, superficieKm2 = 377975.0,
                    moneda = "Yen (JPY)", idiomaPrincipal = "Japonés", continente = "Asia"),
                Pais(nombre = "Brasil", codigoIso = "BRA", capital = "Brasilia", poblacion = 214000000, superficieKm2 = 8515767.0,
                    moneda = "Real (BRL)", idiomaPrincipal = "Portugués", continente = "América"),
                Pais(nombre = "Australia", codigoIso = "AUS", capital = "Canberra", poblacion = 26000000, superficieKm2 = 7692024.0,
                    moneda = "Dólar australiano (AUD)", idiomaPrincipal = "Inglés", continente = "Oceanía")
            )
            paisRepository.saveAll(paises)
            println("Datos iniciales cargados (${paises.size} países)")
        }
    }
}