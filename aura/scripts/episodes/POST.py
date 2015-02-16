import string
import random
import json
import sys

def set_dct(url = False):
	dct = {}
	dct['url'] = '-1'
	dct['intensidad'] = '-1'
	dct['horasSuenio'] = '-1'
	dct['regularidad'] = 'false'
	dct['localizacion'] = '-1'
	dct['estres'] = 'false'
	
	if url:
		dct['url'] = ''.join(random.choice(string.digits) for _ in range(5))
		dct['intensidad'] = '5'

	else:
		dct['url'] = '-1'
		dct['intensidad'] = random.choice(['1', '2', '3', '4', '5'])
		dct['horasSuenio'] = random.choice(map(str, range(6)))
		dct['regularidad'] = random.choice(['true', 'false'])
		dct['localizacion'] = random.choice(map(str, range(6)))
		dct['estres'] = random.choice(['true', 'false'])

	return dct

def set_command(dct, user):
	command = 'curl -v -H "Content-type: application/json" -X POST -d '
	command += '\'' + json.dumps(dct) + '\' '
	command += 'http://localhost:9000/api/paciente/' + str(user) + '/' + 'episodio'
	return command

def main():
	users = [_.strip() for _ in open('../patients/ids.txt', 'r').readlines()]
	file_object = open('POST.sh', 'w')
	i = 0
	while i <= int(sys.argv[1]):
		flag = random.choice([True, False])
		dct = set_dct(flag)
		user = random.choice(users)
		file_object.write(set_command(dct, user) + '\n')
		i += 1

main()