CC = gcc
JC = javac
CFLAGS = -ansi -Wall -pedantic

all : DNSsimple DNSsimple.class DNS.class

DNSsimple : DNSsimple.c
	$(CC) $(CFLAGS) $? -o $@

DNSsimple.class : src/dns/DNSsimple.java
	$(JC) $?


DNS.class : src/dns/DNS.java
	$(JC) $?

clean :
	rm -f DNSsimple DNSsimple.class DNS.class
