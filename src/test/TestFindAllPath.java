package test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class TestFindAllPath {

	static ArrayList<ArrayList<Integer>> all_paths = new ArrayList<>();
	static ArrayList<Integer> path = new ArrayList<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// String filepath =
		// "/Users/jiyuanshi/Downloads/SimpleDAG/v6_e8_i1.csv";
		// SimpleDirectedWeightedGraph<Integer, DefaultWeightedEdge> graph =
		// Graph_Generator
		// .read_graph_from_file(filepath);
		SimpleDirectedWeightedGraph<Integer, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<>(
				DefaultWeightedEdge.class);
		Graphs.addEdgeWithVertices(graph, 0, 1);
		Graphs.addEdgeWithVertices(graph, 1, 2);
		Graphs.addEdgeWithVertices(graph, 1, 3);
		Graphs.addEdgeWithVertices(graph, 1, 4);
		Graphs.addEdgeWithVertices(graph, 1, 6);
		Graphs.addEdgeWithVertices(graph, 2, 4);
		Graphs.addEdgeWithVertices(graph, 4, 6);
		Graphs.addEdgeWithVertices(graph, 3, 6);
		Graphs.addEdgeWithVertices(graph, 2, 5);
		Graphs.addEdgeWithVertices(graph, 5, 6);

		boolean[] visitedflag = new boolean[graph.vertexSet().size()];
		System.out.println(graph);
		System.out.println("find_all_path1------------");
		find_all_path1(graph, 0, graph.vertexSet().size() - 1, visitedflag);
		for (ArrayList<Integer> p : all_paths) {
			System.out.println(p);
		}
		System.out.println("find_all_path------------");
		ArrayList<ArrayList<Integer>> paths = find_all_path(graph, 0, graph
				.vertexSet().size() - 1);
		for (ArrayList<Integer> p : paths) {
			System.out.println(p);
		}

	}

	public static ArrayList<ArrayList<Integer>> find_all_path(
			SimpleDirectedWeightedGraph<Integer, DefaultWeightedEdge> graph,
			Integer start_vertex, Integer end_vertex) {
		ArrayList<ArrayList<Integer>> all_paths = new ArrayList<>();
		ArrayList<List<Integer>> successor_list = new ArrayList<>();
		for (int i = 0; i < graph.vertexSet().size(); i++) {
			List<Integer> successors = Graphs.successorListOf(graph, i);
			successor_list.add(successors);
		}

		Stack<Integer> stack = new Stack<>();
		stack.push(start_vertex);
		while (!stack.empty()) {
			Integer current_vertex = stack.peek();
			if (current_vertex == end_vertex) {
				// System.out.println("stack: " + stack);
				ArrayList<Integer> path = new ArrayList<>();
				for (Integer vertex : stack) {
					path.add(vertex);
				}
				all_paths.add(path);
				stack.pop();
			} else {
				if (successor_list.get(current_vertex).isEmpty()) {
					stack.pop();
					successor_list.set(current_vertex,
							Graphs.successorListOf(graph, current_vertex));
				} else {
					Integer successor = successor_list.get(current_vertex).get(
							0);
					successor_list.get(current_vertex).remove(0);
					stack.push(successor);
				}
			}
		}

		return all_paths;
	}

	public static void find_all_path1(
			SimpleDirectedWeightedGraph<Integer, DefaultWeightedEdge> graph,
			Integer start_vertex, Integer end_vertex, boolean[] visitedflag) {
		// System.out.println("start: " + start_vertex + "--end " + end_vertex);
		path.add(start_vertex);
		visitedflag[start_vertex] = true;
		List<Integer> successorList = Graphs.successorListOf(graph,
				start_vertex);
		for (Integer successor : successorList) {
			if (successor == end_vertex) {
				ArrayList<Integer> new_path = new ArrayList<>();
				for (Integer integer : path) {
					new_path.add(integer);
				}
				new_path.add(successor);
				// System.out.println("new_path: " + new_path);
				all_paths.add(new_path);
			} else {
				find_all_path1(graph, successor, end_vertex, visitedflag);
			}
		}

		path.remove(start_vertex);
	}

	public static ArrayList<ArrayList<Integer>> BreadthFirst(
			SimpleDirectedWeightedGraph<Integer, DefaultWeightedEdge> graph) {
		ArrayList<ArrayList<Integer>> all_paths = new ArrayList<>();
		boolean[] visitedflag = new boolean[graph.vertexSet().size()];
		Queue<Integer> queue = new LinkedList<>();
		System.out.println(0);
		visitedflag[0] = true;
		queue.add(0);
		while (!queue.isEmpty()) {
			Integer current_vertex = queue.poll();
			List<Integer> successorList = Graphs.successorListOf(graph,
					current_vertex);
			for (Integer successor : successorList) {
				if (!visitedflag[successor]) {
					System.out.println(successor);
					visitedflag[successor] = true;
					queue.add(successor);
				}
			}
		}

		return all_paths;
	}

	public static ArrayList<ArrayList<Integer>> DepthFirst(
			SimpleDirectedWeightedGraph<Integer, DefaultWeightedEdge> graph) {
		ArrayList<ArrayList<Integer>> all_paths = new ArrayList<>();
		boolean[] visitedflag = new boolean[graph.vertexSet().size()];
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		while (!stack.isEmpty()) {
			Integer current_vertex = stack.pop();
			if (!visitedflag[current_vertex]) {
				System.out.println(current_vertex);
				visitedflag[current_vertex] = true;
			}
			List<Integer> successorList = Graphs.successorListOf(graph,
					current_vertex);
			for (Integer successor : successorList) {
				if (!visitedflag[successor]) {
					stack.push(successor);
				}
			}
		}

		return all_paths;
	}
}
