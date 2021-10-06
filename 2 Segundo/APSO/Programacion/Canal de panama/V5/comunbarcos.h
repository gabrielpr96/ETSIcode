#include <unistd.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <stdlib.h>
#include <stdio.h>
#include "comun.h"

struct Parametros{
	int tesclusa;
	int lagomin;
	int lagomax;
	int mevoymin;
	int mevoymax;
};

int llega14, llega16;

void R10();
void R12();
void R14();
void R16();

int crea_cola(key_t clave);
void visualiza(int cola, int donde, int que, int como);
