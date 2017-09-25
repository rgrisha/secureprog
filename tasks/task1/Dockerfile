FROM centos:7

RUN apt-get install -y strace nc openssl-devel openssl wget
RUN adduser magistras
RUN su magistras
RUN cd
RUN wget https://github.com/rgrisha/secureprog/tree/master/tasks/task1/setup.sh
RUN sh setup

