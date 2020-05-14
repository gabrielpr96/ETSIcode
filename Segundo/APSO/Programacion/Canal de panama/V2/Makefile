all: servidor_ncurses canalpanama barcoeste borcooeste

servidor_ncurses: servidor_ncurses.c definiciones.h comun.h
	cc -o servidor_ncurses servidor_ncurses.c -lncurses

canalpanama: canalpanama.c
	cc -o canalpanama canalpanama.c

barcoeste: barcoeste.c comunbarcos.h comunbarcos.c comun.h
	cc -o barcoeste barcoeste.c comunbarcos.c

borcooeste: barcooeste.c comunbarcos.h comunbarcos.c comun.h
	cc -o barcooeste barcooeste.c comunbarcos.c

clean:
	rm servidor_ncurses canalpanama barcoeste barcooeste

remake: clean all
