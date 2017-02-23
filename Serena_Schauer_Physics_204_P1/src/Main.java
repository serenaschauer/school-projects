import javax.swing.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;


public class Main {

	
	public static String readTextFile(String L){
		JOptionPane.showMessageDialog(null,"If You Want To Analyze the Frequency in a Text File Click 'OK'");
		JFileChooser FileChooser = new JFileChooser();
		FileChooser.showOpenDialog(null);
		File FileYouWant = FileChooser.getSelectedFile();
		String FileName = FileYouWant.getAbsolutePath();
		System.out.println("You want to Anayze File " + FileName );
		System.out.println();
		try{
			BufferedReader BR = new BufferedReader(new FileReader(FileName));
			L = BR.readLine();
		} catch (FileNotFoundException fnfe){
			System.out.println("Error. Please Try Again. File Not Found.");
		} catch (Exception e) {
			System.out.println("Error. Please Try Again.");
		}
		return L;
		
	}
	
	public static int[] frequency(int[] popArray, int[] binArray, int bsize, int freq[]){
		int count;
		for(int bin=0 ; bin <binArray.length ; bin++){
			count = 0;
			for (int pop=0 ; pop <popArray.length ; pop++){
				if( (popArray[pop] <= binArray[bin]) && (popArray[pop] > (binArray[bin] - bsize))  ) {
					freq[bin] = ++count;
				}
			}			
		}
		return freq;
	}
	
	
	
	
	public static void main(String[] args) {

		
		//Choose a text file you want to read
		String line = null;
		line = readTextFile(line);
		
		try{
			
		
		// Now Parse the Commas and Fill the Array
		String[] w = line.split(",");
		// Find Size of Array
		StringTokenizer Tokenizer = new StringTokenizer(line,",");
		int sizeOfArray = Tokenizer.countTokens();
		int a[] = new int[sizeOfArray]; 
		// Print whats in Array and remove whitespace
		int highestNumber  = 0;
		for(int i=0 ; i < sizeOfArray ; i++){	
			w[i] = w[i].replace(" ","");
			a[i] = Integer.parseInt(w[i]);
			//System.out.println("[" + a[i] + "]");
			if(a[i] > highestNumber) highestNumber = a[i];
		}
		System.out.println("The highest population size is " + highestNumber + ".");
		System.out.println();
		
		// Choose your Bin Size  (surround in try catch block)
		// Make a BinsArray
		int binsize;
		String WhatBinSize = JOptionPane.showInputDialog(null,"What Bin Size Do You Want?");
		binsize = Integer.parseInt(WhatBinSize);
		int findOutHowManyBins = 1;
		int bin = binsize;
		int bbb= binsize;
		while (bin < highestNumber){
			findOutHowManyBins = findOutHowManyBins +1;
			bin = bin + bbb;
		}
		System.out.println("There are " +findOutHowManyBins + " Bins.");
		System.out.println("Your bin size is: " + binsize);
		System.out.print("These Bins are: ");
		int b[] = new int[findOutHowManyBins]; 
		int bb = binsize;
		for (int i=0 ; i<findOutHowManyBins  ; i++){
			b[i] = bb;
			bb = bb + binsize;
			System.out.print("[" + b[i] + "]");
		}
		System.out.println();
		
		// Finding the Frequencies according to the bin sizes
		int highest_freq;
		int[] frequency = new int[b.length]; 
		frequency(a, b, binsize, frequency);
		System.out.println();
		System.out.println("This is what is in the Bin and Frequency Arrays: ");
		highest_freq = 0;
		for(int i=0; i<b.length ; i++){
			System.out.println("Bin ["+ i + "] = " + b[i] + " (and has a frequency of) Freq[" + i +"] = " + frequency[i]);
			if (frequency[i] > highest_freq) { highest_freq = frequency[i]; }
		}

		// x-axis is the bin 
		// y-axis is the frequency of the population size
		System.out.println();
		System.out.println();
		System.out.println("								Bar Graph of Population Size vs Frequency");
		System.out.println();	
		System.out.print("            ");
		for(int i=0 ; i<highest_freq ; i++) { System.out.print("_"); }		
		System.out.print("_");
		System.out.println();
		for (int i=0 ; i<b.length ; i++){
			int count = 0;
			if(i < 10) { 
				System.out.print("Bin # " + i + "     | "); 
			} else if ((i<100) && (i>9)) {
				System.out.print("Bin # " + i + "    | ");
			} else if ((i<1000)&&(i>99)){
				System.out.print("Bin # " + i + "   | ");
			} else if ((i<10000)&&(i>999)){
				System.out.print("Bin # " + i + "  | ");
			} else if ((i<100000)&&(i>9999)){
				System.out.print("Bin # " + i + " | ");
			}		
			int num_repeat = frequency[i];
			for (int j=0 ; j<num_repeat ; j++){
				System.out.print("*");
			}
			System.out.println();
		}
		System.out.print("            |"); 
		for(int i=0 ; i<highest_freq ; i++) { System.out.print("_"); }		
		System.out.println();
		System.out.println("								Frequency of Population");
		
		

		
		// Finding the log of each
		System.out.println();
		System.out.println();
		System.out.println("Finding the Logs...........");
		System.out.println();

		int count_for_log = 0;
		double highest_log_base = 0;
		double highest_log_freq = 0;
		for(int i=0; i<b.length ; i++){
			if(frequency[i]!= 0){
				if(frequency[i]!= 1){
					System.out.println("Bin ["+ i + "] = " + b[i] + "   Freq[" + i +"] = " + frequency[i] + " ...... logs are " + Math.log10(b[i]) + " " + Math.log10(frequency[i]));
					count_for_log = count_for_log + 1;
					if(Math.log10(b[i])>=highest_log_base) { highest_log_base = Math.log10(b[i]); }
					if(Math.log10(frequency[i])>=highest_log_freq) { highest_log_freq = Math.log10(frequency[i]); }
				}
			}
		}
		System.out.println();
		System.out.println("# of logs = " + count_for_log);
		System.out.println("Highest # for log Base = " + highest_log_base);
		System.out.println("Highest # for log Frequency = " + highest_log_freq);

		double[] log_array_base = new double[b.length];
		double[] log_array_freq = new double[b.length];
		for(int i=0; i<b.length ; i++){
			if(frequency[i]!= 0){
				if(frequency[i]!= 1){
					 log_array_base[i] = Math.log10(b[i]);
					 log_array_freq[i] = Math.log10(frequency[i]);
				}
			}
		}	
		
		int highest_log_base_int = (int) (highest_log_base  * 10);
		int highest_log_freq_int = (int) (highest_log_freq  * 10);
		
		System.out.println("Highest # for log Base_int = " + highest_log_base_int);
		System.out.println("Highest # for log Frequency_int = " + highest_log_freq_int);		
		
		String int_log_array[][] = new String [highest_log_freq_int ][highest_log_base_int];
		
		
		// checking if log arrays are right
//		System.out.println("printing log base array" ); 
		for(int i=0 ; i<b.length ; i++){
			if(frequency[i]!= 0){
				if(frequency[i]!= 1){
//					System.out.println( (int) (log_array_base[i]*10) + " ........ " + (int) (log_array_freq[i]*10));
				}
			}
		}
		
		// Filling the array with the STARS and BLANKS
		for(int i=0 ; i<b.length ; i++){
		//	System.out.println("first for : BASE:  b[" + i + "] = " + b[i]  );
		//	System.out.println("            BASE:  SO  b[" + i + "] LOG =  " + Math.log10(b[i]) );
		//	System.out.println("            BASE:  SO  b[" + i + "] LOG Shud go int array =  " + (int) (log_array_base[i]*10) );

		//	System.out.println("first for : FREQ:  freq[" + i + "] = " + frequency[i]  );
		//	System.out.println("            FREQ:  SO  freq[" + i + "] LOG =  " + Math.log10(frequency[i]) );
		//	System.out.println("            FREQ:  SO  freq[" + i + "] LOG Shud go int array =  " + (int) (log_array_freq[i]*10) );

			if(frequency[i]!=0){
				if(frequency[i]!=1){
					for(int row=0 ; row<highest_log_freq_int  ; row++ ){
		//				System.out.println("List: " + i + " ... Freq[ " + i + "] = " + (int) (log_array_freq[i]*10) +" ... 2nd for Frequency:    row(freq) = " + row );
						if( (int) (log_array_freq[i]*10) == row - 1){
							for(int col=0; col<highest_log_base_int  ; col++){
			//					System.out.println(" List: " + i + " ... 3rd for Base :    col(base) = " + col);
								if((int) (log_array_base[i]*10) == col - 1){
									int_log_array[row][col] = "* ";
			//						System.out.println(" List: " + i + " ... last if : row = " + row + " ... col = " + col + " ....... STAR INSERTED" );
								}
								else {
		//							System.out.println(" List: " + i + " ... Col = " + col + " while base it is is = " + (int) (log_array_base[i]*10));

								}
							}
						} 
						else {
		//					System.out.println(" List: " + i + "... Row = " + row + " while freq it is is = " + (int) (log_array_freq[i]*10));
						}
					}	
				}
			}
		}

		// Replacing null's with blanks
		for(int i=0 ; i<highest_log_freq_int ; i++ ){
			for(int j=0 ; j<highest_log_base_int ; j++){
				if(int_log_array[i][j] != "* ") { int_log_array[i][j] = "  "; }					
			}
		}
		
		
		// Printing out the LOG GRAPH
		System.out.println();
		System.out.println("        				 POPULATION SIZE vs FREQUENCY (LOG/LOG GRAPH)           ");
		for (int i=0 ; i<highest_log_base_int +1 ; i++) { System.out.print("__"); }
		System.out.println();
		for(int i=highest_log_freq_int-1 ; i>=0 ; i-- ){
			System.out.print("|");
			for(int j=0 ; j<highest_log_base_int ; j++){
				System.out.print(int_log_array[i][j]);
			}
			if(i == (highest_log_freq_int/2) ){
				System.out.println("|   Frequency   "); 
			} else { 		
				System.out.println("|"); 
			}
		}
		System.out.print("|");
		for (int i=0 ; i<highest_log_base_int ; i++) { System.out.print("__"); }
		System.out.print("|"); 
		System.out.println();
		System.out.println("                                       Population Size   ");
		System.out.println();

		} catch (Exception e){
			System.out.println("Not a text file of numbers seperated by commas.");
			JOptionPane.showMessageDialog(null,"Not a text file of numbers seperated by commas. Please run program again with the correct text file.");

		}

	}

}