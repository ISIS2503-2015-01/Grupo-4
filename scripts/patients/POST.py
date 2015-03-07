import random
import string
import json
import sys

names = []
last_names = []
digits = '0123456789'

def set_names():
	global names
	file_object = open('first.txt', 'r')
	names = [_.strip() for _ in file_object.readlines()]

def set_last_names():
	global last_names
	file_object = open('last.txt', 'r')
	last_names = [_.strip() for _ in file_object.readlines()]

def pick_name():
	name = random.choice(names).capitalize() + ' ' + random.choice(last_names)
	return name

def pick_email(name):
	name = name.split()
	email = name[0][0].lower() + name[1].lower()
	email += str(random.randrange(100, 999))
	email += '@gmail.com'
	return email

def pick_date():
	ans = ''
	ans += str(random.randrange(1930, 2010)) + '-' + '0' + str(random.randrange(1, 12)) + '-' + str(random.randrange(1, 26))
	return ans

def populate_patients(bound):
	file_object = open(sys.argv[0][:-2] + 'sh', 'w')
	documents = open('ids.txt', 'w')
	#seed = curl -v -H "Content-type: application/json" -X POST -d "fechaN" : "2000-09-09", "genero" : "1", "password" : "zadvczzvz"}' http://localhost:9000/api/paciente
	i = 0
	while i < bound:
		line = 'curl -v -H "Content-type: application/json" -X POST -d '
		dct = {}
		dct["nombre"] = pick_name() 
		dct["email"] = pick_email(dct["nombre"])
		dct["docIdentidad"] = ''.join(random.choice(string.digits) for _ in range(11))
		dct["fechaN"] = pick_date()
		dct["genero"] = random.choice(['0', '2'])
		dct["password"] = ''.join(random.choice(string.ascii_uppercase + string.digits) for _ in range(8))

		line += "'" + json.dumps(dct) + "'" + ' '
		
		line += 'http://localhost:9000/api/paciente'
		line += '\n'
		file_object.write(line)
		documents.write(dct["docIdentidad"] + '\n')

		i += 1

def main():
	set_names(); set_last_names()

main()
populate_patients(int(sys.argv[1]))