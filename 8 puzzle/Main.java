import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {



		int b[][] = {

				{0,8,2},
				{5,4,3},
				{7,6,1}
		};




		State p = new State(b);


		int a[] = new int[9];


		int x=0;
		for (int i = 0; i <3 ; i++) {

			for (int j = 0; j <3 ; j++) {

				a[x++]=b[i][j];
			}
		}

		int inversions =0;

		for (int i = 0; i <a.length ; i++) {

			for (int j = i; j <a.length ; j++) {

				if(a[i]>a[j] && (a[j]!=0)){
					inversions++;
                   // System.out.println(a[i]+">"+a[j]);
                }

			}
		}

        System.out.println(inversions );


		if(inversions%2==0) {

            long startTime = System.currentTimeMillis();


            Node goal = Node.A_star(p);

            long endTime   = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println(totalTime);


            Node.print_soln(goal);

        }
        else{
            System.out.println("not possible");
        }






    }


}
