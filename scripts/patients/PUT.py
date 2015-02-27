# python PUT.py name pass YYYY-MM-DD email gender ID
import subprocess
import json
import sys

keys = ['nombre', 'password', 'fechaNacimiento', 'email', 'genero']
tpl = [(keys[i], sys.argv[i + 1]) for i in range(5)]
dct = dict(tpl)

command = 'curl -v -H "Content-type: application/json" -X PUT -d ' + '\'' + json.dumps(dct) + '\'' + ' http://localhost:9000/api/paciente/' + sys.argv[-1]
print command

p = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
for line in p.stdout.readlines():
    print line,
retval = p.wait()