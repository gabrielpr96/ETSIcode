#include <unistd.h>

int main(){
	char c;
	while(read(0, &c, sizeof(c)) > 0)
		write(1, &c, sizeof(c));

	return 0;
}
