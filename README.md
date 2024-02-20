# API REST

### CREACIÓN DE USUARIOS
* Validaciones:
  * Campos importantes requeridos
  * Email único
  * Email validado por medio de expresión regular 
  * Clave regida bajo expresion regular configurable
### CARACTERÍSTICAS
* Manejo de excepciones
* Base de datos H2: http://localhost:8080/h2-ui
* Persistencia con hibernate
* Token JWT
* Maven
* Java 17 
* Swagger con springdoc http://localhost:8080/swagger-ui 
### INSTRUCCIONES
1. Crear formato de password: POST http://localhost:8080/api/users/password
```json
{
   "opcionalMayusculas":"false",
   "opcionalNumeros":"false",
   "opcionalSignos":"false",
   "longitudMinima":"4",
   "longitudMaxima":"50"
}
```
2. Crear usuarios: POST http://localhost:8080/api/users
```json
{
    "name": "Frariana Castro",
    "email": "frarianacastro@gmail.cl",
    "password": "clavesupersecretaysupersegura",
    "phones": [{
        "number": "972263805",
        "citycode":"0",
        "contrycode": "56"
    },{
        "number": "972222222",
        "citycode":"1",
        "contrycode": "56"
    }]
}

```
3. Iniciar sesión: GET http://localhost:8080/api/users/login
```json
{
    "email": "frarianacastro@gmail.com",
    "password":"clavesupersecretaysupersegura"
}
```