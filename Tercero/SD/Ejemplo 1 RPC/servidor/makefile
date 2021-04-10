CFLAGS=-Wall
CC=gcc

all: calculadorad

calculadorad: calculadorad.o calculadora_svc.o calculadora_xdr.o
	$(CC) -o $@ calculadorad.o calculadora_svc.o calculadora_xdr.o

calculadorad.o: calculadora.h

clean:
	rm -f *.o calculadorad