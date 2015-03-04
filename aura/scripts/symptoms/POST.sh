#!/bin/sh
curl -v -H "Content-type: application/json" -X POST -d '{"idp": "26035704728", "id1": "58"}' http://localhost:9000/api/paciente/26035704728/episodio/58/sintoma
curl -v -H "Content-type: application/json" -X POST -d '{"idp": "50832631970", "id1": "60"}' http://localhost:9000/api/paciente/50832631970/episodio/60/sintoma
