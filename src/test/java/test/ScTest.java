package test;

import java.util.Scanner;

public class ScTest {

	public static void main(String[] args) {
		
		int userInput = 1;
		Scanner sc = new Scanner(System.in);
		while(userInput == 1) {
			System.out.println("Enter 1 to continue: ");
			userInput = sc.nextInt();
			System.out.println("Enter your name: ");
			String next = sc.next();
		    System.out.println(next);
		}
		
		sc.close();
		

	}

}
