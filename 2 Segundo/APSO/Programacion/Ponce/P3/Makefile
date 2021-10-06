all: A B C

A: A.c
	cc -o A A.c

B: B.c
	cc -o B B.c -lm

C: C.c
	cc -o C C.c

clean:
	rm A B C pb-error.out pc.out
