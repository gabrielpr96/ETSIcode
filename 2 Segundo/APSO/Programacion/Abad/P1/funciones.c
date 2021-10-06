#include <stdio.h>
#include <math.h>
#include "funciones.h"

int suma(const int arr[]){
	int sum = 0, i;
	for(i = 0; i < 5; i++)
		sum += arr[i];
	return sum;
}
float cubica(const int arr[]){
	int max = arr[0], i;
	for(i = 1; i < 5; i++)
		if(arr[i] > max)
			max = arr[i];
	return cbrt(max);
}
