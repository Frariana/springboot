API REST
----------------
CREACIÓN DE USUARIOS

    Validaciones
        Campos importantes requeridos
        Email único
        Email validado por medio de expresión regular 
        Clave regida bajo expresion regular configurable

MANEJO DE EXCEPCIONES

BASE DE DATOS: H2

PERSISTENCIA: Hibernate

TOKEN JWT

MAVEN

JAVA 17 

INSTRUCCIONES:
1. Crear formato de password
POST http://localhost:8080/api/users/password
raw:
```json
{
   "opcionalMayusculas":"true",
   "opcionalNumeros":"true",
   "opcionalSignos":"true",
   "longitudMinima":"4",
   "longitudMaxima":"10"
}
```
2. Crear usuarios
POST http://localhost:8080/api/users
   JSON

raw:
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
3. Iniciar sesión
GET http://localhost:8080/api/users/login
JSON
raw:
```json
{
    "email": "frarianacastro@gmail.com",
    "password":"clavesupersecretaysupersegura"
}
```