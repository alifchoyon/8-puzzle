import java.util.ArrayList;

public class State {

    public int[][] goa;
	int board[][], bR, bC;

	public State(int[][] board) {
		// super();
		this.board = board;

        goa=new int[][]{
                {1,2,3},
                {4,5,6},
                {7,8,0}
        };
	}

	public void search_blank() {
		for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j)
                if (board[i][j] == 0) {
                    bR = i;
                    bC = j;


                    return;
                }
        }
	}

	public State up() {

		search_blank();

		if (bR == 0)
			return null;


		int newBoard[][] = new int[3][3];

		for (int i = 0; i < 3; ++i)
			for (int j = 0; j < 3; ++j)
				newBoard[i][j] = board[i][j];

		int temp = newBoard[bR][bC];
		newBoard[bR][bC] = newBoard[bR - 1][bC];
		newBoard[bR - 1][bC] = temp;

		State s = new State(newBoard);


		return new State(newBoard);

	}

	public State down() {
        search_blank();

        if (bR == 2)
            return null;


        int newBoard[][] = new int[3][3];

        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j)
                newBoard[i][j] = board[i][j];

        int temp = newBoard[bR][bC];
        newBoard[bR][bC] = newBoard[bR + 1][bC];
        newBoard[bR + 1][bC] = temp;

        State s = new State(newBoard);


            return new State(newBoard);


	}

	public State left() {

        search_blank();

        if (bC == 0)
            return null;


        int newBoard[][] = new int[3][3];

        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j)
                newBoard[i][j] = board[i][j];

        int temp = newBoard[bR][bC];
        newBoard[bR][bC] = newBoard[bR ][bC-1];
        newBoard[bR][bC-1] = temp;

        State s = new State(newBoard);


            return new State(newBoard);


	}

	public State right() {

        search_blank();

        if (bC == 2)
            return null;


        int newBoard[][] = new int[3][3];

        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j)
                newBoard[i][j] = board[i][j];

        int temp = newBoard[bR][bC];
        newBoard[bR][bC] = newBoard[bR ][bC+1];
        newBoard[bR][bC+1] = temp;

        State s = new State(newBoard);


            return new State(newBoard);

	}



    public boolean goal_test() {

	    State goal = new State(goa);

	    return this.toString().equals(goal.toString());

    }

    public ArrayList<State> apply_actions() {

	    ArrayList<State> successors = new ArrayList<>();

        if(up()!=null)
	    successors.add(up());

        if(down()!=null)
        successors.add(down());

        if(left()!=null)
        successors.add(left());

        if(right()!=null)
        successors.add(right());

	    return  successors;
    }

    @Override
    public String toString() {

	    String state ="";

        for (int i = 0; i <3 ; i++) {

            for (int j = 0; j <3 ; j++) {

                state+=board[i][j]+" ";
            }
            state+="\n";
        }
        return "\n"+state;

    }


}
