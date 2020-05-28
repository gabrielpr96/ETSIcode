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
            A = 0,
			B = 1,
			C = 2,
			D = 3
        };

		//Varoables
        bn.set_number_of_nodes(4);

		//Dependencias
        bn.add_edge(B, C);
        bn.add_edge(A, C);
        bn.add_edge(C, D);


        //Estados de cada variable, dos porque son booleanas
        set_node_num_values(bn, A, 2);
        set_node_num_values(bn, B, 2);
        set_node_num_values(bn, C, 2);
        set_node_num_values(bn, D, 2);

		//Objeto usado para informar de las dependencias
        assignment parent_state;

		//Asignando función de probabilidad a las variables

        set_node_probability(bn, A, 1, parent_state, 0.01);
        set_node_probability(bn, A, 0, parent_state, 1-0.01);

        set_node_probability(bn, B, 1, parent_state, 0.006);
        set_node_probability(bn, B, 0, parent_state, 1-0.006);


        parent_state.add(B, 1);
		parent_state.add(A, 1);

        set_node_probability(bn, C, 1, parent_state, 0.99);
        set_node_probability(bn, C, 0, parent_state, 1-0.99);

        parent_state[B] = 0;
		parent_state[A] = 1;

		set_node_probability(bn, C, 1, parent_state, 0.8);
        set_node_probability(bn, C, 0, parent_state, 1-0.8);

		parent_state[B] = 1;
		parent_state[A] = 0;

		set_node_probability(bn, C, 1, parent_state, 0.9);
        set_node_probability(bn, C, 0, parent_state, 1-0.9);

		parent_state[B] = 0;
		parent_state[A] = 0;

		set_node_probability(bn, C, 1, parent_state, 0.001);
        set_node_probability(bn, C, 0, parent_state, 1-0.001);


		parent_state.clear();
		parent_state.add(C, 1);

        set_node_probability(bn, D, 1, parent_state, 0.99);
        set_node_probability(bn, D, 0, parent_state, 1-0.99);

        parent_state[C] = 0;

        set_node_probability(bn, D, 1, parent_state, 0.9);
        set_node_probability(bn, D, 0, parent_state, 1-0.9);


		//Inferencia

        typedef dlib::set<unsigned long>::compare_1b_c set_type;
        typedef graph<set_type, set_type>::kernel_1a_c join_tree_type;
        join_tree_type join_tree;
        create_moral_graph(bn, join_tree);
        create_join_tree(join_tree, join_tree);

        set_node_as_evidence(bn, D);
        set_node_as_evidence(bn, A);
        set_node_as_evidence(bn, B);

		set_node_value(bn, D, 1);
		set_node_value(bn, A, 1);
		set_node_value(bn, B, 0);

		bayesian_network_join_tree solution(bn, join_tree);
		cout << solution.probability(C)(1) << endl;

    }catch (std::exception& e){
        cout << "exception thrown: " << endl;
        cout << e.what() << endl;
        cout << "hit enter to terminate" << endl;
        cin.get();
    }
}
