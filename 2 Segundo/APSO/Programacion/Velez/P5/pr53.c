#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <signal.h>
#include <unistd.h>

int main(){
	int pid54;
	read(2, (char*)&pid54, sizeof(int));
	close(2);
	printf("pr53 con pid %d. Mensaje 3\n", getpid());
	kill(pid54, 12);
	return 0;
}
