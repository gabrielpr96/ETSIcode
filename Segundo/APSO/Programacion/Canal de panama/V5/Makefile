all: servidor_ncurses canalpanama barcoeste borcooeste glago gesclusae gesclusao

servidor_ncurses: servidor_ncurses.c definiciones.h comun.h
	cc -o servidor_ncurses servidor_ncurses.c -lncurses

canalpanama: canalpanama.c
	cc -o canalpanama canalpanama.c

barcoeste: barcoeste.c comunbarcos.h comunbarcos.c comun.h
	cc -o barcoeste barcoeste.c comunbarcos.c

borcooeste: barcooeste.c comunbarcos.h comunbarcos.c comun.h
	cc -o barcooeste barcooeste.c comunbarcos.c

glago: glago.c
	cc -o glago glago.c

gesclusao: gesclusao.c
	cc -o gesclusao gesclusao.c

gesclusae: gesclusae.c
	cc -o gesclusae gesclusae.c

clean:
	rm servidor_ncurses canalpanama barcoeste barcooeste glago gesclusae gesclusao

repair: remake
	rm /home/apso/lago /home/apso/esclusao /home/apso/esclusae /home/apso/colalago /home/apso/colaesclusao /home/apso/colaesclusae
	ipcrm --all=msg

remake: clean all
