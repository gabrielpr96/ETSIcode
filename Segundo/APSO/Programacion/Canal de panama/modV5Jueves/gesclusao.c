#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>

int fesclusao, fcolaoeste;

//Modificacion
int abierta = 1;

void R12(){
	abierta = abierta?0:1;
}

void R10()
{
 close(fesclusao);
 close(fcolaoeste);
 exit(0);
}

int main()
{
 int testigo, pidbarco;

 //Modificacion
  signal(12,R12);

 signal(10,R10);
 fesclusao=open("esclusao",O_RDWR);
 fcolaoeste=open("colaoeste",O_RDWR);

 while(1)
 {
	//Modificacion
	if(abierta){
		read(fesclusao,&testigo,sizeof(testigo));
		read(fcolaoeste,&pidbarco,sizeof(pidbarco));

		//Si acabamos de conseguir el testigo pero la eclusa esta cerrada, no se lo damos de momento
		while(!abierta) sleep(1);

		kill(pidbarco,16);
	}else{
		//Si la exclusa no esta abierta, duerme hasta que se abra
		sleep(1);
	}
 }
}
