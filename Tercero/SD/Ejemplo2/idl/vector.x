const MAX_VECTOR=100;
typedef float t_vector<>;

struct entrada1 {
	t_vector v;
	float c;
};

struct entrada2 {
	t_vector v1;
	t_vector v2;
};

program OPER_VECTOR {
	version OPER_VECTORVER {
		t_vector escalado_vector(entrada1) = 1;
		t_vector suma_vectorial(entrada2) = 2;
	} = 1;
} = 0x30000002;