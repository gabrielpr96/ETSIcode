CFLAGS=-Wall
CC=gcc

all: vectord

vectord: vectord.o vector_svc.o vector_xdr.o
	$(CC) -o $@ vectord.o vector_svc.o vector_xdr.o

vectord.o: vector.h

clean:
	rm -f *.o vectord