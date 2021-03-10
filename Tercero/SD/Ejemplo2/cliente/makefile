CFLAGS=-Wall
CC=gcc

all: vector

vector: vector.o vector_clnt.o vector_xdr.o
	$(CC) -o $@ vector.o vector_clnt.o vector_xdr.o

vector.o: vector.h

clean:
	rm -f *.o vector