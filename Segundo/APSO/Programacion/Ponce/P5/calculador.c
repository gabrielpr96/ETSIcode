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

		int n;
		sscanf(argv[1], "%d", &n);

		n = rand()%(n+1);

		sleep(2);
		printf("Número calculado, intente adivinarlo en 10 intentos.\n");

		write(2, (char*) &n, sizeof(int));
		close(2);

		exit(0);
	}

	return 0;
}
