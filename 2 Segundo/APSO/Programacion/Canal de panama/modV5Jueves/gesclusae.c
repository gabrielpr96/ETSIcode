#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>

int fesclusae,fcolaeste;

//Modificacion
int abierta = 1;

void R12(){
	abierta = abierta?0:1;
}

void R10()
{
  close(fesclusae);
  close(fcolaeste);
  exit(0);
}

int main()
{
 int testigo, pidbarco;

 //Modificacion
  signal(12,R12);

 signal(10,R10);
 fesclusae=open("esclusae",O_RDWR);
 fcolaeste=open("colaeste",O_RDWR);

 while(1)
 {
	//Modificacion
    if(abierta){
		read(fesclusae,&testigo,sizeof(testigo));
		read(fcolaeste,&pidbarco,sizeof(pidbarco));

		//Si acabamos de conseguir el testigo pero la eclusa esta cerrada, no se lo damos de momento
		while(!abierta) sleep(1);

		kill(pidbarco,16);
	}else{
		//Si la exclusa no esta abierta, duerme hasta que se abra
		sleep(1);
	}
 }
}
