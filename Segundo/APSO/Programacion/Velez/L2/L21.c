#include <unistd.h>
#include <stdio.h>
#include <sys/msg.h>
#include <sys/wait.h>

void continuar(){
	printf("L21 con pid %d. Mensaje 6\n", getpid());
}

int main(){
	int pid22, pid23, tub2[2], cola;
	key_t clave;

	signal(12, continuar);

	pipe(tub2);
	clave = ftok("Makefile", 144);
	cola = msgget(clave, 0600 | IPC_CREAT);

	printf("L21 con pid %d. Mensaje 1\n", getpid());

	pid22 = fork();
	if(pid22 == 0){
		close(2);
		dup(tub2[1]);
		execl("L22", "L22", NULL);
	}

	pid23 = fork();
	if(pid23 == 0){
		close(2);
		dup(tub2[0]);
		execl("L23", "L23", NULL);
	}

	close(tub2[0]);
	close(tub2[1]);

	pause();

	wait(NULL);
	wait(NULL);

	msgctl(cola, IPC_RMID, (struct msqid_ds *)NULL);

	return 0;
}
