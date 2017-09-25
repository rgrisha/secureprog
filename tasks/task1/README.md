
# Task 1. Tracing application ABI calls using strace #

You have several applications that do not work on Docker image. You have to create conditions (folders, files, sockets, etc) for application to run. Application considered as "running" when it outputs to stdout: "Application terminated normally".

Do the tasks under user "magistras" (on login, perform `su magistras`)

How to run:
* Install Docker
* Checkout this repo, cd tasks, cd task1
* Run Dockerfile
** `docker build .`
* Run Docker image:
** `docker run --security-opt seccomp:unconfined <image ID> /sbin/init`
* On new terminal:
** `docker exec -it <container ID> /bin/bash
** Inside container:
** `su magistras`
cd app[1-4]


## Evaluation:
app1 and app2 are madatory - 8 points. App3 - +1 point, app4 - +1 point.

##Hints
* strace utility is most usable tool.
* nc is also usable
* one of apps receives input from command line
* /etc/hosts
* app3 uses gethostbyname to obtain IP address
* app4 uses realtime Mongolian Tugrik rate via webservice


