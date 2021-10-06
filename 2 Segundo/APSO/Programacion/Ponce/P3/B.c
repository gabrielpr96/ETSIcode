#include <unistd.h>
#include <stdio.h>
#include <math.h>


int main(int argc, char *argv[]){
	printf("Soy B, mi pid es: %d y el de mi padre es: %d\n", getpid(), getppid());

	if(argc != 2){
		printf("Error, se esperaba un parametro\n");
	}else{
		int n;
		sscanf(argv[1], "%d", &n);
		printf("Seno: %.2f Coseno: %.2f Tangente: %.2f\n", sin(n), cos(n), tan(n));
	}

	return 0;
}
