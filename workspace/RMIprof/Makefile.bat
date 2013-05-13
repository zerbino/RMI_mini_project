# Hao ZHANG writes for RMI.
JC=javac
AR=jar
JFLAGS= -g
ARFLAGS= -cvmf

SRCDIR=src/
BINDIR=bin/
MFDIR=META-INF/

CLASSES = client/IClient \
	  client/Client \
	  server/IChat \
	  server/Chat


SRCS=$(addsuffix .java, $(addprefix $(SRCDIR), $(CLASSES)))

BINS=$(addsuffix .class, $(addprefix $(BINDIR), $(CLASSES)))

$(BINS): $(SRCS)
	$(JC) $^ -d $(BINDIR)

bins: $(BINS)
	@echo $(BINS)

rmireg:
	cd bin && rmiregistry &

server:
	cd bin && java -Djava.rmi.server.codebase=file:./ -Djava.security.policy=file:../policies/server.policy -Djava.rmi.server.hostname=127.0.0.1 server.Chat &

client:
	cd bin && java -Djava.rmi.server.codebase=file:./ -Djava.security.policy=file:../policies/client.policy -Djava.rmi.server.hostname=127.0.0.1 client.Client

clean:
	rm *.class

