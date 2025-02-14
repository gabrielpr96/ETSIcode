/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */

#include <malloc.h>

#include "vector.h"

t_vector *
escalado_vector_1_svc(entrada1 *argp, struct svc_req *rqstp) {
    static t_vector result;

    static float vector_aux[MAX_VECTOR]; /* Espacio estático para almacenar el vector resultado*/

    /* Recuperar argumentos: vector + constante */
    t_vector vector = argp->v;
    float constante = argp->c;

    /* Inicializar vector de salida (indicar tamano + pedir espacio) */
    result.t_vector_len = vector.t_vector_len;
    result.t_vector_val = vector_aux;

    /* Multiplicar cada componente por la constante */
    int i;
    for (i = 0; i < result.t_vector_len; i++) {
        result.t_vector_val[i] = constante * vector.t_vector_val[i];
    }

    return &result;
}

t_vector *
suma_vectorial_1_svc(entrada2 *argp, struct svc_req *rqstp) {
    static t_vector result;

    static float vector_aux[MAX_VECTOR]; /* Espacio estático para almacenar el vector resultado*/

    /* Recuperar argumentos: 2 vectores  */
    t_vector vector1 = argp->v1;
    t_vector vector2 = argp->v2;

    /* Inicializar vector de salida (indicar tamano + pedir espacio) */
    result.t_vector_len = vector1.t_vector_len;
    result.t_vector_val = vector_aux;

    /* Sumar componente a componente */
    int i;
    for (i = 0; i < result.t_vector_len; i++) {
        result.t_vector_val[i] = vector1.t_vector_val[i] + vector2.t_vector_val[i];
    }

    return &result;
}
