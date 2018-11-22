FROM gradle:jdk8

#USER root
RUN git clone https://github.com/bizreach/massive_mailer.git $HOME/massive_mailer
RUN cd $HOME/massive_mailer

CMD ["gradle test"]