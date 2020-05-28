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
            R = 0,
			T = 1,
			A = 2,
			M = 3
        };

		//Varoables
        bn.set_number_of_nodes(4);

		//Dependencias
        bn.add_edge(R, A);
        bn.add_edge(T, A);
        bn.add_edge(A, M);


        //Estados de cada variable, dos porque son booleanas
        set_node_num_values(bn, R, 2);
        set_node_num_values(bn, T, 2);
        set_node_num_values(bn, A, 2);
        set_node_num_values(bn, M, 2);

		//Objeto usado para informar de las dependencias
        assignment parent_state;

		//Asignando función de probabilidad a las variables

        set_node_probability(bn, R, 1, parent_state, 0.001);
        set_node_probability(bn, R, 0, parent_state, 1-0.001);

        set_node_probability(bn, T, 1, parent_state, 0.002);
        set_node_probability(bn, T, 0, parent_state, 1-0.002);


        parent_state.add(R, 1);
		parent_state.add(T, 1);

        set_node_probability(bn, A, 1, parent_state, 0.95);
        set_node_probability(bn, A, 0, parent_state, 1-0.95);

        parent_state[R] = 0;
		parent_state[T] = 1;

		set_node_probability(bn, A, 1, parent_state, 0.29);
        set_node_probability(bn, A, 0, parent_state, 1-0.29);

		parent_state[R] = 1;
		parent_state[T] = 0;

		set_node_probability(bn, A, 1, parent_state, 0.94);
        set_node_probability(bn, A, 0, parent_state, 1-0.94);

		parent_state[R] = 0;
		parent_state[T] = 0;

		set_node_probability(bn, A, 1, parent_state, 0.001);
        set_node_probability(bn, A, 0, parent_state, 1-0.001);


		parent_state.clear();
		parent_state.add(A, 1);

        set_node_probability(bn, M, 1, parent_state, 0.7);
        set_node_probability(bn, M, 0, parent_state, 1-0.7);

        parent_state[A] = 0;

        set_node_probability(bn, M, 1, parent_state, 0.01);
        set_node_probability(bn, M, 0, parent_state, 1-0.01);


		//Inferencia

        typedef dlib::set<unsigned long>::compare_1b_c set_type;
        typedef graph<set_type, set_type>::kernel_1a_c join_tree_type;
        join_tree_type join_tree;
        create_moral_graph(bn, join_tree);
        create_join_tree(join_tree, join_tree);

        set_node_as_evidence(bn, M);
        set_node_as_evidence(bn, T);
        set_node_as_evidence(bn, R);

		set_node_value(bn, M, 1);
		set_node_value(bn, T, 1);
		set_node_value(bn, R, 0);

		bayesian_network_join_tree solution(bn, join_tree);
		cout << solution.probability(A)(1) << endl;

    }catch (std::exception& e){
        cout << "exception thrown: " << endl;
        cout << e.what() << endl;
        cout << "hit enter to terminate" << endl;
        cin.get();
    }
}
