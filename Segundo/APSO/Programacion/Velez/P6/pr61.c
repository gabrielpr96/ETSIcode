#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/msg.h>
#include <sys/wait.h>
#include <errno.h>
#include <unistd.h>

struct Mensaje1{
	long id;
	char dato;
};
struct Mensaje2{
	long id;
	int dato;
};


int main(){
	key_t clave;
	int cola, pid62, pid63, i;
	struct Mensaje1 mensaje1;
	struct Mensaje2 mensaje2;

	clave = ftok("Makefile", 144);
	cola = msgget(clave, 0600 | IPC_CREAT);

	pid62 = fork();
	if(pid62 == 0)
		execl("pr62", "pr62", NULL);

	pid63 = fork();
	if(pid63 == 0)
		execl("pr63", "pr63", NULL);

	for(i = 0; i < 10; i++){
		msgrcv(cola,  (struct msgbuf *) &mensaje1, sizeof(struct Mensaje1)-sizeof(long), 1, 0);
		printf("Recivido char: %c\n", mensaje1.dato);
		msgrcv(cola,  (struct msgbuf *) &mensaje2, sizeof(struct Mensaje2)-sizeof(long), 2, 0);
		printf("Recivido int: %d\n", mensaje2.dato);
	}

	wait(NULL);
	wait(NULL);

	msgctl(cola, IPC_RMID, (struct msqid_ds *)NULL);

	return 0;
}
