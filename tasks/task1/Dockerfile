FROM centos:7

RUN yum install -y strace nc openssl-devel openssl wget
RUN adduser magistras
RUN su magistras
RUN cd
RUN wget https://rawgit.com/rgrisha/secureprog/master/tasks/task1/setup1.sh
RUN /bin/sh setup.sh

