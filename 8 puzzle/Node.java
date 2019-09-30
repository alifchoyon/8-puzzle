import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Node implements Comparable<Node> {
	static int FIXED_COST = 1;

	State state;
	Node parent;
	int depth;
	int cost;

	public Node(State s, Node p, int d, int c) {
		// super();
		this.state = s;
		this.parent = p;
		this.depth = d;
		cost = c;
	}



	public static Node BFS(State init) {

		Node root = new Node(init, null, 0, 0);

		Queue<Node> fringe = new LinkedList<Node>();

		fringe.add(root);

		while (fringe.isEmpty() == false) {
			Node front = fringe.poll();

			// System.out.println(front.state);

			State s = front.state;

			if (s.goal_test())
				return front;

			ArrayList<State> successors = s.apply_actions();

			for (State state : successors)
				if (state != null) {
					Node child = new Node(state, front, front.depth + 1, front.cost + FIXED_COST);
					fringe.add(child);
				}
		}

		return null;
	}

	public static Node UCS(State init) {

		Node root = new Node(init, null, 0, 0);

		PriorityQueue<Node> fringe = new PriorityQueue<Node>();
		// Queue<Node> fringe = new LinkedList<Node>();

		fringe.add(root);

		while (fringe.isEmpty() == false) {
			Node front = fringe.poll();

			// System.out.println(front.state);

			State s = front.state;

			if (s.goal_test())
				return front;

			ArrayList<State> successors = s.apply_actions();

			for (State state : successors)
				if (state != null) {
					Node child = new Node(state, front, front.depth + 1, front.cost + FIXED_COST);
					fringe.add(child);
				}
		}

		return null;
	}

	public static Node A_star(State init) {

		Node root = new Node(init, null, 0, 0);

		PriorityQueue<Node> fringe = new PriorityQueue<Node>();
		// Queue<Node> fringe = new LinkedList<Node>();

		fringe.add(root);

		while (fringe.isEmpty() == false) {
			Node front = fringe.poll();

			// System.out.println(front.state);

			State s = front.state;

			if (s.goal_test())
				return front;

			ArrayList<State> successors = s.apply_actions();

			for (State state : successors)
				if (state != null) {
					Node child = new Node(state, front, front.depth + 1, front.cost + FIXED_COST);
					fringe.add(child);
				}
		}

		return null;
	}


	public int Line (int piece , State s){

		int bR=0;
		int bC=0;

		int bR1=0;
		int bC1=0;

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j)
				if (s.board[i][j] == piece) {
					bR = i;
					bC = j;


					break;
				}
		}




		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j)
				if (s.goa[i][j] == piece) {
					bR1 = i;
					bC1 = j;


					break;
				}
		}

		int h=(int)Math.sqrt ((double) ( Math.pow(Math.abs(bR-bR1),2)     )     +  Math.pow( Math.abs(bC-bC1) ,2) ) ;

		return h;




	}

	public int Hermann (int piece , State s){

	    int bR=0;
        int bC=0;

        int bR1=0;
        int bC1=0;

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j)
                if (s.board[i][j] == piece) {
                    bR = i;
                    bC = j;


                    break;
                }
        }




        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j)
                if (s.goa[i][j] == piece) {
                    bR1 = i;
                    bC1 = j;


                    break;
                }
        }

        return (Math.abs(bR-bR1)+Math.abs(bC-bC1) );




    }

	public int heuristic() {

	    int h = 0;
        int piece=0;
      	    State s = this.state;

        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j <3 ; j++) {

                h+=Hermann(piece++ ,s );
            }
        }




	    return h;
	}

	public static void print_soln(Node n) {
		while (n != null) {
			System.out.println(n.state);
			n = n.parent;
		}
	}

	@Override
	public int compareTo(Node another) {
		return (cost + heuristic()) - (another.cost + another.heuristic());
	}
}
