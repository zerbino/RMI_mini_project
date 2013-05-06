.PHONY: server client clean

server:
	clear
	javac server/Chat.java
	java -Djava.rmi.server.codebase=file:/tmp/chat/                  \
       -Djava.security.policy=file:/tmp/chat/server/server.policy  \
       -Djava.rmi.server.hostname=127.0.0.1                        \
       server.Chat

client:
	clear
	javac client/Client.java
	java -Djava.rmi.server.codebase=file:/tmp/chat/                  \
       -Djava.security.policy=file:/tmp/chat/client/client.policy  \
       -Djava.rmi.server.hostname=127.0.0.1                        \
       client.Client

clean:
	-rm server/*.class
	-rm client/*.class
	-rm server/*~
	-rm client/*~
