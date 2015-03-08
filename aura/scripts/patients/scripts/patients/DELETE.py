import subprocess
import sys

command = "curl -i -H \"Accept: application/json\" -X DELETE http://localhost:9000/api/paciente/" + sys.argv[1]


p = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
for line in p.stdout.readlines():
    print line,
retval = p.wait()