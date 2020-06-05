all: servidor_ncurses canalpanama barcoeste barcooeste gesclusae gesclusao glago avion

servidor_ncurses: servidor_ncurses.c definiciones.h comun.h
	cc servidor_ncurses.c -o servidor_ncurses -lncurses

canalpanama: canalpanama.c comun.h
	cc canalpanama.c -o canalpanama

barcoeste: barcoeste.c comun.h comunbarcos.c comunbarcos.h
	cc barcoeste.c comunbarcos.c -o barcoeste

barcooeste: barcooeste.c comun.h comunbarcos.c comunbarcos.h
	cc barcooeste.c comunbarcos.c -o barcooeste

gesclusae: gesclusae.c
	cc gesclusae.c -o gesclusae

gesclusao: gesclusao.c
	cc gesclusao.c -o gesclusao

glago: glago.c
	cc glago.c -o glago

avion: avion.c comun.h comunbarcos.c comunbarcos.h
	cc avion.c comunbarcos.c -o avion

clean:
	rm barcooeste barcoeste canalpanama gesclusae gesclusao glago servidor_ncurses avion
