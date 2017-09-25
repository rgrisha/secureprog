FROM centos:7

RUN yum install -y strace nc openssl-devel openssl wget
RUN adduser magistras
RUN wget https://rawgit.com/rgrisha/secureprog/master/tasks/task1/setup.sh
RUN su -u magistras /bin/sh setup.sh

