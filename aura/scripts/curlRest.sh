curl -v -H "Content-type: application/json" -X PUT -d '{"email":"sc@sada.com", "docIdentidad" : "10", "nombre" : "Camilo", "fechaN" : "2000-09-09", "genero" : "1", "password" : "zadvczzvz"}' http://localhost:9000/api/paciente

curl -v -H "Content-type: application/json" -X POST -d '{"URL" : "0", "intensidad" : "1", "horasSuenio" : "3", "regularidad" : "true", "localizacion" : "4", "estres" : "false"}' http://localhost:9000/api/paciente/10/episodio

