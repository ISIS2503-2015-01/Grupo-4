#!/bin/sh
curl -v -H "Content-type: application/json" -X POST -d '{"idp": "26035704728", "id1": "1", "nombre": "Yuca", "cantidad": "2"}' http://localhost:9000/api/paciente/26035704728/episodio/1/alimento
curl -v -H "Content-type: application/json" -X POST -d '{"idp": "50832631970", "id1": "3", "nombre": "Papa", "cantidad": "4"}' http://localhost:9000/api/paciente/50832631970/episodio/3/alimento