import random
import json
import sys

def set_command():
	command = 'curl -v -H "Content-type: application/json" -X POST -d '
	dct = {'sintoma' : random.choice(range(6))}
	command += '\'' + json.dumps(dct) + '\''
	command += ' http://localhost:9000/api/episodio/%s/sintoma' % sys.argv[1]

	return command

print set_command()