all: servidor_ncurses canalpanama barcoeste barcooeste gesclusae gesclusao glago cierraexclusas

servidor_ncurses: servidor_ncurses.c definiciones.h comun.h
	cc servidor_ncurses.c -o servidor_ncurses -lncurses

canalpanama: canalpanama.c
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

cierraexclusas: cierraexclusas.c
	cc cierraexclusas.c -o cierraexclusas


clean:
	rm servidor_ncurses canalpanama barcoeste barcooeste glago gesclusae gesclusao cierraexclusas

repair: remake
	ipcrm --all=msg
	rm lago esclusao esclusae colalago colaesclusao colaesclusae

remake: clean all
