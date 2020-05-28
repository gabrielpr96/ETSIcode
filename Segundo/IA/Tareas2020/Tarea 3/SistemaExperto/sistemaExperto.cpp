#include <dlib/bayes_utils.h>
#include <dlib/graph_utils.h>
#include <dlib/graph.h>
#include <dlib/directed_graph.h>
#include <iostream>

using namespace dlib;
using namespace std;


int main(){
    try {
        using namespace bayes_node_utils;
        directed_graph<bayes_node>::kernel_1a_c bn;

        enum nodes{
            T = 0,
			G = 1,
			B = 2,
			F = 3,
			E = 4,
			D = 5
        };

		//Varoables
        bn.set_number_of_nodes(6);

		//Dependencias
        bn.add_edge(T, B);
        bn.add_edge(G, F);
        bn.add_edge(G, E);
        bn.add_edge(B, E);
        bn.add_edge(B, D);


        //Estados de cada variable, dos porque son booleanas
        set_node_num_values(bn, T, 2);
        set_node_num_values(bn, G, 2);
        set_node_num_values(bn, B, 2);
        set_node_num_values(bn, F, 2);
        set_node_num_values(bn, E, 2);
        set_node_num_values(bn, D, 2);

		//Objeto usado para informar de las dependencias
        assignment parent_state;

		//Asignando función de probabilidad a las variables

        set_node_probability(bn, T, 1, parent_state, 0.2);
        set_node_probability(bn, T, 0, parent_state, 1-0.2);

        set_node_probability(bn, G, 1, parent_state, 0.05);
        set_node_probability(bn, G, 0, parent_state, 1-0.05);


        parent_state.add(T, 1);

        set_node_probability(bn, B, 1, parent_state, 0.08);
        set_node_probability(bn, B, 0, parent_state, 1-0.08);

        parent_state[T] = 0;

        set_node_probability(bn, B, 1, parent_state, 0.01);
        set_node_probability(bn, B, 0, parent_state, 1-0.01);

		parent_state.clear();
		parent_state.add(G, 1);

        set_node_probability(bn, F, 1, parent_state, 0.9);
        set_node_probability(bn, F, 0, parent_state, 1-0.9);

        parent_state[G] = 0;

        set_node_probability(bn, F, 1, parent_state, 0.03);
        set_node_probability(bn, F, 0, parent_state, 1-0.03);


		parent_state.add(B, 1);
        parent_state[G] = 1;

        set_node_probability(bn, E, 1, parent_state, 0.7);
        set_node_probability(bn, E, 0, parent_state, 1-0.7);

        parent_state[B] = 0;
        parent_state[G] = 1;

        set_node_probability(bn, E, 1, parent_state, 0.6);
        set_node_probability(bn, E, 0, parent_state, 1-0.6);

        parent_state[B] = 1;
        parent_state[G] = 0;

        set_node_probability(bn, E, 1, parent_state, 0.4);
        set_node_probability(bn, E, 0, parent_state, 1-0.4);

        parent_state[B] = 0;
        parent_state[G] = 0;

        set_node_probability(bn, E, 1, parent_state, 0.05);
        set_node_probability(bn, E, 0, parent_state, 1-0.05);

		parent_state.clear();
		parent_state.add(B, 1);

        set_node_probability(bn, D, 1, parent_state, 0.8);
        set_node_probability(bn, D, 0, parent_state, 1-0.8);

        parent_state[B] = 0;

        set_node_probability(bn, D, 1, parent_state, 0.02);
        set_node_probability(bn, D, 0, parent_state, 1-0.02);


		//Inferencia

        typedef dlib::set<unsigned long>::compare_1b_c set_type;
        typedef graph<set_type, set_type>::kernel_1a_c join_tree_type;
        join_tree_type join_tree;
        create_moral_graph(bn, join_tree);
        create_join_tree(join_tree, join_tree);

        set_node_as_evidence(bn, D);
        set_node_as_evidence(bn, E);
        set_node_as_evidence(bn, F);
        set_node_as_evidence(bn, T);

		char c;
		do{
			cout<< "Sistema experto de diagnostico medico probabilistico" << endl
				<< "1. Realizar diagnostico" << endl
				<< "0. Salir" << endl;
			cin >> c;
			cout << endl << endl << endl << endl;
			switch(c){
				case '1':
					cout << ((char)168) << "Tiene fiebre? (s/n): ";
					cin >> c;
					set_node_value(bn, F, c=='s');
					cout << ((char)168) << "Tiene escalofrios? (s/n): ";
					cin >> c;
					set_node_value(bn, E, c=='s');
					cout << ((char)168) << "Tiene dificultad para respirar? (s/n): ";
					cin >> c;
					set_node_value(bn, D, c=='s');
					cout << ((char)168) << "Es fumador? (s/n): ";
					cin >> c;
					set_node_value(bn, T, c=='s');

					bayesian_network_join_tree solution(bn, join_tree);
					double pG = solution.probability(G)(1), pB = solution.probability(B)(1);
					cout<< endl
					 	<< "Probabilidad gripe: " << pG << endl
						<< "Probabilidad bronquitis: " << pB << endl << endl
						<< "El diagnostico es: " << ((pG<0.5&&pB<0.5)?"Ninguna enfermedad":((pG>0.5&&pB>0.5)?"Enfermo de gripe y bronquitis":(pG>0.5?"Enfermo de gripe":"Enfermo de bronquitis"))) << endl << endl
						<< "Pulse una tecla para continuar." << endl;
					cin.ignore();
					cin.ignore();
				break;
			}
			cout << endl << endl << endl << endl;
		}while(c != '0');









    }catch (std::exception& e){
        cout << "exception thrown: " << endl;
        cout << e.what() << endl;
        cout << "hit enter to terminate" << endl;
        cin.get();
    }
}
