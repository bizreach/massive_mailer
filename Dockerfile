FROM gradle:jdk8

RUN apt-get update && apt-get -y install git
RUN git clone https://github.com/bizreach/massive_mailer.git $HOME/massive_mailer
RUN cd $HOME/massive_mailer && gradle test