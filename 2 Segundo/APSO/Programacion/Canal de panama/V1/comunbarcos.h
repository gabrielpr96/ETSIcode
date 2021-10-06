#include <unistd.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <stdlib.h>
#include <stdio.h>
#include "comun.h"

void R10();
void R12();

int crea_cola(key_t clave);
void visualiza(int cola, int donde, int que, int como);
