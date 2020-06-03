#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/msg.h>
#include <errno.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

#include "comun.h"
#include "comunbarcos.h"

extern int llega14;
extern int llega16;
int llega18 = 0;

void R18(){
	llega18 = 1;
}

int main()
{

 int colagrafica, lapipe;
 struct Parametros param;
 int fesclusao, fesclusae, flago, testigo=1, fcolaeste, fcolaoeste,fcolalago, fdocu, mipid;

 srand(getpid());
 signal(10,R10); // me preparo para la senyal 10
 signal(16,R16); // me preparo para la senyal 1
 signal(12,R12); // me preparo para la senyal 12
 signal(14,R14); // me preparo para la seyal de alarma
 signal(18,R18); // me preparo para la senyal 18

 // Creamos y abrimos la cola de mensajes
 colagrafica=crea_cola(ftok ("./fichcola.txt", 18));

 // Abrimos las fifos
 fesclusae=open("esclusae",O_WRONLY);
 fcolaeste=open("colaeste",O_WRONLY);
 fesclusao=open("esclusao",O_WRONLY);
 fcolaoeste=open("colaoeste",O_WRONLY);
 flago=open("lago",O_WRONLY);
 fcolalago=open("colalago",O_WRONLY);
 fdocu=open("coladocu",O_WRONLY);

 // Leemos de la pipe los par�metros
 read(2,&param,sizeof(param));

 //recuperamos la salida de errores
 lapipe=dup(2);
 close(2);
 open("/dev/tty",O_RDWR);

 //nos pintamos en la llegada
 visualiza(colagrafica, VESTEIN, PINTAR, TIPOESTE);

 alarm(rand()%(param.mevoymax-param.mevoymin+1)+param.mevoymin);

 mipid=getpid();
 // reservo sitio en el lago
 write(fcolalago,&mipid,sizeof(mipid));
 if(!llega16) pause();

 if(llega14) //me he aburrido de esperar
 {
	visualiza(colagrafica, VESTEIN, BORRAR, TIPOESTE);

	if(rand()%10 < 6){
		//Pide documentación
		visualiza(colagrafica, VHORNOS, PINTAR, BARCODOCUE);
		write(fdocu, &mipid,sizeof(int));
		if(!llega16) pause();
		if(!llega16) pause();
		write(flago,&testigo,sizeof(testigo));

		if(!llega18) pause();
		visualiza(colagrafica, VHORNOS, BORRAR, BARCODOCUE);
		visualiza(colagrafica, VOESTEOUT, PINTAR, BARCODOCUE);
		kill(getppid(), 16);
	}else{
		//No pide documentación
		visualiza(colagrafica, VHORNOS, PINTAR, TIPOESTE);
		if(!llega16) pause();
		write(flago,&testigo,sizeof(testigo));
		kill(getppid(), 16);
	}

 }
 else
 {
	llega16=0;
	alarm(0); //desactivo la alarma
	// reservo la esclusa este
	write(fcolaeste,&mipid,sizeof(mipid));
	if(!llega16) pause();
	llega16=0;
	//entro en la esclusa
	visualiza(colagrafica, VESTEIN, BORRAR, TIPOESTE);
	visualiza(colagrafica, VESCLUSAESTE, PINTAR, TIPOESTE);
	sleep(param.tesclusa);
	//salgo y voy al lago
	visualiza(colagrafica, VESCLUSAESTE, BORRAR, TIPOESTE);
	visualiza(colagrafica, VLAGOE, PINTAR, TIPOESTE);
	write(fesclusae,&testigo,sizeof(testigo));
	sleep((rand()%(param.lagomax-param.lagomin+1))+param.lagomin);
	// reservo la esclusa oeste
	write(fcolaoeste,&mipid,sizeof(mipid));
	if(!llega16) pause();
	llega16=0;
	// salgo del lago y voy a la esclusa
	visualiza(colagrafica, VLAGOE, BORRAR, TIPOESTE);
	visualiza(colagrafica, VESCLUSAOESTE, PINTAR, TIPOESTE);
	write(flago,&testigo,sizeof(testigo));
	sleep(param.tesclusa);
	//salgo de la esclusa y me marcho
	visualiza(colagrafica, VESCLUSAOESTE, BORRAR, TIPOESTE);
	visualiza(colagrafica, VOESTEOUT, PINTAR, TIPOESTE);
	write(fesclusao,&testigo,sizeof(testigo));
 }
 close(fesclusae);
 close(fesclusao);
 close(flago);
 close(fcolaeste);
 close(fcolaoeste);
 close(fcolalago);
 close(fdocu);
}
