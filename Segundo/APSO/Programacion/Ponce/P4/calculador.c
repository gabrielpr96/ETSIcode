#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <sys/types.h>
#include <time.h>
#include <fcntl.h>

int main(int argc, char *argv[]){
	if(argc == 2){
		srand(time(0));

		int n, f;
		sscanf(argv[1], "%d", &n);

		n = rand()%(n+1);

		f = creat("secreto", 0600);
		write(f, (char*) &n, sizeof(int));
		close(f);

		sleep(2);
		printf("Número calculado, intente adivinarlo en 10 intentos.\n");

		exit(0);
	}

	return 0;
}
