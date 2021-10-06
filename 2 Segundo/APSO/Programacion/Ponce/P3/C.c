#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>


int main(){
	printf("Soy C, mi pid es: %d y el de mi padre es: %d\n", getpid(), getppid());

	srand(time(0));
	int n1 = (rand()%49)+1, n2 = (rand()%51)+50, i;
	for(i = n1; i < n2; i++)
		if(i % 5 == 0)
			printf("El numero %i es multiplo de 5\n", i);

	return 0;
}
