#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

int main(){
	printf("Soy pr33 y mi pid es: %d\n", getpid());
	sleep(2);

	int pid32, pid31c;
	int f = open("prac3.dat", O_RDONLY);
	read(f, (char*)&pid32, sizeof(int));
	read(f, (char*)&pid31c, sizeof(int));
	close(f);

	printf("Soy pr33 y el pid de 32 es: %d\n", pid32);
	printf("Soy pr33 y el pid de 31 copia es: %d\n", pid31c);

	printf("Fin de pr33\n");
	return 0;
}
