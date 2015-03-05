import subprocess
import sys

#         /api/paciente/:idp/episodio/:id1/alimento/:id2

user = sys.argv[1]
episode = sys.argv[2]
food = sys.argv[3]

command = "curl -i -H \"Accept: application/json\" -X DELETE http://localhost:9000/api/paciente/%s/episodio/%s/alimento/%s" % (user, episode, food)


p = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
for line in p.stdout.readlines():
    print line,
retval = p.wait()