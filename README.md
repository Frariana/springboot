API REST
----------------
CREACIÓN DE USUARIOS

    Validaciones
        Campos importantes requeridos
        Email único
        Email validado por medio de expresión regular 

MANEJO DE EXCEPCIONES

BASE DE DATOS: H2

PERSISTENCIA: Hibernate

MAVEN

JAVA 17 

TEST
----------------
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



        