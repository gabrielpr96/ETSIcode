CFLAGS=-Wall
CC=gcc

all: calculadora

calculadora: calculadora.o calculadora_clnt.o calculadora_xdr.o
	$(CC) -o $@ calculadora.o calculadora_clnt.o calculadora_xdr.o

calculadora.o: calculadora.h

clean:
	rm -f *.o calculadora