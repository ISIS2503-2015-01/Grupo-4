#!/bin/sh
curl -v -H "Content-type: application/json" -X POST -d '{"genero": "2", "fechaN": "1965-09-3", "nombre": "Kataryna Flank", "password": "2TNQMON4", "docIdentidad": "50832631970", "email": "kflank744@gmail.com"}' http://localhost:9000/api/paciente
curl -v -H "Content-type: application/json" -X POST -d '{"genero": "0", "fechaN": "1997-010-25", "nombre": "Saleh Hildebrand", "password": "L4F6EIVG", "docIdentidad": "90315908328", "email": "shildebrand419@gmail.com"}' http://localhost:9000/api/paciente
curl -v -H "Content-type: application/json" -X POST -d '{"genero": "0", "fechaN": "1991-010-7", "nombre": "Parasili Amiri", "password": "XYTEY6M8", "docIdentidad": "26035704728", "email": "pamiri944@gmail.com"}' http://localhost:9000/api/paciente
