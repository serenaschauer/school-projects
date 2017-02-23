import javax.swing.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

import javax.swing.JFileChooser;
import java.util.Random;

public class Main {

	public static int ask_Num_Pages(){
		String inpt = JOptionPane.showInputDialog("What Web Pages would you like to find the Page Rank of? "
				+ "\n" + "Options are: Any Number 1 through 20 or Random");
		int input = Integer.parseInt(inpt); 
		return input;
	}
	
	public static int[][] create_Matrix(int nP){
		int[][] m = new int[nP][nP];
		for(int i=0 ; i<m.length ; i++){
			for(int j=0 ; j<m.length ; j++){
				if( i==j ) { m[i][j] = 0; }
				else {
					Random rn = new Random();
					int rand = rn.nextInt() % 3;
					if (rand==0 || rand==1) { m[i][j] = 0; }
					else { m[i][j] = 1; }
				}
			}
		}
		
		
		return m;
	}
	
	
	public static void main(String[] args) {

		String inpt = JOptionPane.showInputDialog("What Web Pages would you like to find the Page Rank of? " + "\n" + "Options are: Any Number 1 through 20 or Random");
		try {
		int input = Integer.parseInt(inpt); 
		int[][] Matrix;
		if(input == 0){
			JOptionPane.showMessageDialog(null, "0 size matrix not permitted \n A Default Matrix was Chosen");
			Matrix = new int[][] {
					{0,1,0,1,1},
					{0,0,0,0,1},
					{0,0,0,0,1},
					{1,0,1,0,0},
					{1,1,1,0,0}			
			};
		} else if (input == 1){
			JOptionPane.showMessageDialog(null, "1 size matrix not permitted \n A Default Matrix was Chosen");
			Matrix = new int[][]{
					{0,1,0,1,1,0,0,0,0,1,0,0,1,0,1,0,1,0,1,1},	
					{1,0,1,0,0,1,1,0,0,0,0,1,1,0,0,0,0,1,0,0},
					{0,1,0,1,1,1,0,0,0,1,1,0,0,1,0,0,1,0,1,1},
					{0,0,0,0,1,0,1,0,1,0,0,0,0,0,1,1,1,1,1,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
					{1,1,0,1,1,0,0,1,0,0,0,1,0,0,1,0,0,1,0,1},
					{0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
					{1,0,1,0,0,1,1,0,0,1,1,1,1,0,1,1,0,1,0,0},
					{0,0,0,0,1,1,1,0,0,0,1,1,1,0,0,0,1,1,1,0},
					{1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0},
					{0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
					{1,1,1,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,1},
					{0,0,0,1,1,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0},
					{0,0,0,0,1,0,1,0,1,0,0,0,0,0,0,1,0,1,0,1},
					{1,0,1,0,0,1,0,0,0,0,0,1,1,0,0,0,0,1,0,0},
					{0,0,1,1,0,1,0,1,0,0,1,0,1,1,0,0,0,0,0,0},			
					{0,0,1,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,1,0},
					{0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
					{0,0,0,0,1,0,1,1,1,0,1,0,0,0,0,1,0,1,0,0}
			};			
		} else {
			Matrix = create_Matrix(input);
		}
		


		System.out.println();

		
	
		System.out.println("Understanding Page Rank.......");
		System.out.println();
		System.out.println("Number of Pages Being Analyzed: " + Matrix.length);

		System.out.println();
		System.out.println();
		System.out.println("                           Web Page Number   ");
		System.out.println();
		System.out.print("     ");
		for(int i=0; i<Matrix.length ; i++){
			if(i<9) { System.out.print((i+1) + "  "); }
			else { System.out.print((i+1) + " "); }
		}
		System.out.println();
		System.out.print("   ");
		for(int i=0; i<Matrix.length ; i++){
			System.out.print("___");    
		}
		System.out.print("__");
		System.out.println();
		for(int i=0; i<Matrix.length ;i++){
			if(i<9) { System.out.print((i+1) + "  | "); }
			else { System.out.print((i+1) + " | "); }
			for(int j=0; j<Matrix.length ; j++){
				System.out.print(Matrix[i][j] + "  "); 
				if(j == Matrix.length -1) { System.out.print("|"); }
			}
			System.out.println();
		}
		System.out.print("   ");
		for(int i=0; i<Matrix.length ; i++){
			System.out.print("___");    
		}
		System.out.print("___");
		System.out.println();
		double first_step = 1/(double)Matrix.length;
		System.out.println();
		System.out.println();
		System.out.println();

		System.out.println("__________________________________________________________________");
		System.out.print("First Step: Find the initial values of each Page Rank" + "\n");
		System.out.println("__________________________________________________________________");
		System.out.println();
		
		for(int i=0; i<Matrix.length ;i++){
			System.out.println("  Page " + (i+1) + ": " + first_step);
		}
	
		System.out.println();
		System.out.println("__________________________________________________________________");
		System.out.print("Second Step: Add the Page Ranks of the pages that link to that one " + "\n");
		System.out.println("__________________________________________________________________");
		System.out.println();
		
		double page_rank;
		double step_two;
		double[] Step_2_Array = new double[Matrix.length];

		for(int i=0; i<Matrix.length ;i++){
			page_rank = 0;
			for(int j=0 ; j<Matrix.length ;j++){
				if(Matrix[i][j]==1) { page_rank = page_rank + 1; }
			}
			step_two = page_rank * first_step;
			System.out.println("Page " + (i+1) + ": " + step_two);
			Step_2_Array[i] = step_two;
		}

		System.out.println();
		System.out.println("__________________________________________________________________");
		System.out.print("Third Step: Find how many Pages link to other Pages" + "\n");
		System.out.println("__________________________________________________________________");
		System.out.println();
		
		
		// Finding how many links each page has
		int[] How_Many_Links_This_Page_Has = new int[Matrix.length];
		for(int i=0; i<Matrix.length ;i++){   
			How_Many_Links_This_Page_Has[i] = 0;
			for(int j=0 ; j<Matrix.length ;j++){  
				if(Matrix[j][i] == 1) { How_Many_Links_This_Page_Has[i] = How_Many_Links_This_Page_Has[i] + 1; }				
			}
			System.out.println("Page " + (i+1) + " links to " + How_Many_Links_This_Page_Has[i] + " other pages.");
		}
		
		// Finding how many items to add
		System.out.println();
		int[] How_Many_Items_To_Add = new int[Matrix.length];
		for(int i=0; i<Matrix.length ;i++){   
			How_Many_Items_To_Add[i] = 0;
			for(int j=0 ; j<Matrix.length ;j++){  
				if(Matrix[i][j] == 1) { How_Many_Items_To_Add[i] = How_Many_Items_To_Add[i] + 1; }				
			}
			//System.out.println("Page " + i + " adds " + How_Many_Items_To_Add[i] + " items.");
		}		
		
		System.out.println();
		double[] page_rank_copy = new double [Matrix.length];
		double[] Step_3_Array = new double[Matrix.length];	
		// initilize array to zero
		for(int y=0 ; y<Matrix.length ; y++){
			Step_3_Array[y] = 0;
		}		
		
		/************************************************************************************
		*
		*  Finding All the Probability For Step 3
		*
		**************************************************************************************/
		System.out.println();
		System.out.println("__________________________________________________________________");
		System.out.println("Finding All the Probability For Step 3");
		System.out.println("__________________________________________________________________");
		System.out.println();
		
		int[] count_zeros = new int[Matrix.length];
		for (int xx=0 ; xx<Matrix.length ; xx++){
			count_zeros[xx] = 0;
		}

		for(int i=0; i<Matrix.length ;i++){   // For finding page 0's step 3																	//		page_rank = Step_2_Array[i] ;   //.6
			// Reset Copy Each Time
			for(int y=0 ; y<Matrix.length ; y++){
				page_rank_copy[y] = Step_2_Array[y];
			}
			// Write the Probability Formula
			int count = 0;
			int count_pluses;
			
			System.out.print("P(Page " + (i+1) + ") = ");
			for (int p=0 ; p<Matrix.length ; p++){
				if (Matrix[i][p]==0) {
					count_zeros[i] = count_zeros[i] + 1;
				}
			}
			if(count_zeros[i] == Matrix.length) { System.out.print("0 (No Links to This Page)"); }			
		
			count_pluses = 0;			
			for(int x=0 ; x<Matrix.length ; x++){
				if(Matrix[i][x]==1) {
					System.out.print("P(Page " + (x+1) + ")/L(" + (x+1) + ")");
					count_pluses = count_pluses + 1;					
					if(count_pluses < How_Many_Items_To_Add[i]  ) { System.out.print(" + "); }
				}
				if(count_pluses == How_Many_Items_To_Add[i] ) {count_pluses = 0; }
			}
			
			// Filling in the Numbers
			System.out.println();
			count_pluses = 0;
			System.out.print("          =");
			if(count_zeros[i] == Matrix.length) { System.out.print(" 0 (No Links to This Page)"); }

			for(int j=0 ; j<Matrix.length ;j++){  // go threw each row 
				//if(count_zeros[j] == Matrix.length) { System.out.print(" 0 (No Links to This Page)"); }
				count = count+ 1;
				if(i != j){
					if(Matrix[i][j]==0) { page_rank_copy[j] = 0; }
					if(Matrix[i][j]==1) {
						page_rank_copy[j] = page_rank_copy[j] / How_Many_Links_This_Page_Has[j]; 
						System.out.print("(" + Step_2_Array[j] + "/" + How_Many_Links_This_Page_Has[j] + ")");
						count_pluses = count_pluses + 1;					
						if(count_pluses < How_Many_Items_To_Add[i]  ) { System.out.print(" + "); }
					}
					if(count_pluses == How_Many_Items_To_Add[i] ) {count_pluses = 0; }
				}

				// Doing the Division
				if(count == Matrix.length){
					System.out.println();
					System.out.print("          = ");
					if(count_zeros[i] == Matrix.length) { System.out.print("0 (No Links to This Page)"); }
					count_pluses = 0;
					for(int z=0 ; z<Matrix.length ; z++){
						if(Matrix[i][z]==1) {
							count_pluses = count_pluses + 1;	
							System.out.print(page_rank_copy[z]);
							Step_3_Array[i] = Step_3_Array[i] + page_rank_copy[z];
							if(count_pluses < How_Many_Items_To_Add[i]  ) { System.out.print(" + "); }
						}
						if(count_pluses == How_Many_Items_To_Add[i] ) {count_pluses = 0; }
					}
					//if(count_zeros[j] == Matrix.length) { System.out.print("0 (No Links to This Page)"); }
				}
				//if(count_zeros[j] == Matrix.length) { System.out.print("0 (No Links to This Page)"); }
			}
			
			// Doing the Final Addition
			System.out.println();
			if(count_zeros[i] == Matrix.length) { System.out.println("          = 0 (No Links to this Page)"); }

			
			if(How_Many_Items_To_Add[i] > 1){
				System.out.println("          = " + Step_3_Array[i]);
			}
			System.out.println();
			
		}		

		
		// Ranking the Page
		System.out.println();
		System.out.println("__________________________________________________________________");
		System.out.println("Final Ranking of Each Page in Decending Order:");
		System.out.println("__________________________________________________________________");

		int Highest_Num_Array; 
		double Highest_Num;
		for(int i=0; i<Matrix.length ; i++){
			Highest_Num = -1;
			Highest_Num_Array = -1;
			System.out.println();
			for(int j=0; j<Matrix.length ; j++){
				if(Step_3_Array[j] >= Highest_Num) { 
					Highest_Num = Step_3_Array[j]; 
					Highest_Num_Array = j;
				}
			}
			System.out.print("Page " + (Highest_Num_Array+1) + " with the Page Ranking of " + Step_3_Array[Highest_Num_Array]);
			Step_3_Array[Highest_Num_Array] = -1;
		}		
		
	
	}
	 catch (Exception e){
		JOptionPane.showMessageDialog(null,"This is not a number... \n Please Enter a Number");
	} 
	}	
}
