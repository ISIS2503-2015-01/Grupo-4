curl -v -H "Content-type: application/json" -X POST -d '{"email":"sc@sada.com", "docIdentidad" : "10", "nombre" : "Ernestina", "fechaN" : "2000-09-09", "genero" : "1", "password" : "zadvczzvz"}' http://localhost:9000/api/paciente

curl -i -H "Accept: application/json" -X DELETE http://localhost:9000/api/paciente/10

curl -v -H "Content-type: application/json" -X PUT -d '{"email":"sc@sada.com", "docIdentidad" : "10", "nombre" : "Camilo", "fechaN" : "2000-09-09", "genero" : "1", "password" : "zadvczzvz"}' http://localhost:9000/api/paciente