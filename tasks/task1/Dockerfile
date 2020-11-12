FROM centos:7

RUN yum install -y strace nc openssl-devel openssl wget
RUN adduser magistras
RUN wget https://cdn.jsdelivr.net/gh/rgrisha/secureprog/tasks/task1/setup.sh
RUN su -c "/bin/sh setup.sh" magistras
