# Proyecto IBK

Proyecto modeular sobre prueba técnica para IBK.

## Funcionalidades:

- Se debe crear una API que devuelva la información del cliente y sus productos financieros (cuentas de ahorro, tarjetas de crédito, entre otros).
- La API debe recibir el valor `codigoUnico` y devolver los nombres, apellidos, tipo de documento, número de documento y todos sus productos financieros (tipo de productos, nombre y saldo).
- El uso de la API debe ser mostrado desde Postman.
- Para acceder a la API, se debe tener un mecanismo de autenticación.
- El `codigoUnico` que se envía debe estar encriptado.
- Se debe crear un microservicio que tenga la información del cliente.
- Se debe crear un microservicio que tenga la información de los productos financieros.
- Se debe crear un microservicio (Backend For Frontend) que integre a los microservicios de información de cliente y productos financieros.
- Debe existir un identificador para realizar el tracking desde el BFF hasta el último microservicio.

## Tecnologías implementadas:

- Uso de patrones de diseño y principios SOLID.
- Uso de Base de datos SQL Server.
- Los microservicios deben estar implementados en Java 11 (Functional Interface, Predicate, Stream, Optional, Default, Lambda, Date Time) con el framework Spring WebFlux, JUnit 5 y AOP (Aspect-Oriented Programming).
- La capa de seguridad se debe implementar con OAuth.
- Dockerización de los microservicios.
- Configuración de logback.
- Creación de un starter básico en Spring.
- Uso de MapStruct, Lombok, Gson.



Estructura del Proyecto
------------------------
![image](https://github.com/panoshi13/proyecto-ibk/assets/60948575/0358f524-2a7f-412c-b0e2-40f8e3ad3628)

