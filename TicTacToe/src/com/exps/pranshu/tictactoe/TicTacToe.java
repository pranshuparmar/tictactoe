package com.exps.pranshu.tictactoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TicTacToe {
	
	private Scanner scan = new Scanner(System.in);
	private String[][] gameBoard = new String[3][3];
	private String user, computer;
	private boolean gameCompleted;
	private String winner;
	
	private void playGame() {
		System.out.print("You want to be X or O: ");
		user = scan.nextLine();
		if(user.equals("X")) {
			computer = "O";
		} else if(user.equals("O")) {
			computer = "X";
		} else {
			System.err.println("\nInvalid user input!");
			System.exit(1);
		}
		System.out.println("I am " + computer + " and you are " + user + ".\nLet's begin, I'll start first.");
		
		while(true) {
			makeMove();
			evaluateGameBoard();
			System.out.println("\nMy move:");
			printGameBoard();
			if(gameCompleted)
				break;
			
			acceptMove();
			evaluateGameBoard();
			System.out.println("\nYour move:");
			printGameBoard();
			if(gameCompleted)
				break;
		}
		
		if(winner.equals(computer))
			System.out.println("\nI win, you suck!");
		else if(winner.contentEquals(user))
			System.out.println("\nYou win, I suck!");
		else
			System.out.println("\nIt's a draw!\nEither we both suck or we're both good!");
	}
	
	private void printGameBoard() {
		int ctr = 0;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(gameBoard[i][j] != null) {
					System.out.print("." + gameBoard[i][j] + ".");
					++ctr;
				} else
					System.out.print(". .");
			}
			System.out.println();
		}
		
		if(ctr == 9 && !gameCompleted) {
			winner = "draw";
			gameCompleted = true;
		}
	}
	
	private void makeMove() {
		ArrayList<ArrayList<Integer>> blanks = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(gameBoard[i][j] == null) {
					ArrayList<Integer> blank = new ArrayList<Integer>();
					blank.add(i);
					blank.add(j);
					blanks.add(blank);
				}
			}
		}
		Collections.shuffle(blanks);
		gameBoard[blanks.get(0).get(0)][blanks.get(0).get(1)] = computer;
	}
	
	private void acceptMove() {
		System.out.print("\nEnter your co-ordinates (0-3,0-3): ");
		String[] userInput = scan.nextLine().split(",");
		int row = Integer.parseInt(userInput[0]);
		int col = Integer.parseInt(userInput[1]);
		if(gameBoard[row][col] == null)
			gameBoard[row][col] = user;
		else {
			System.err.println("\nYou've to select a blank co-ordinate.");
			System.exit(1);
		}
	}
	
	private void evaluateGameBoard() {
		for(int i = 0; i < 3; i++) {
			ArrayList<String> row = new ArrayList<String>();
			ArrayList<String> col = new ArrayList<String>();
			for(int j = 0; j < 3; j++) {
				row.add(gameBoard[i][j]);
				col.add(gameBoard[j][i]);
			}
			if(!row.contains(null) && row.get(0).equals(row.get(1)) && row.get(1).equals(row.get(2))) {
				if(row.get(0).equals(computer))
					winner = computer;
				else
					winner = user;
				gameCompleted = true;
				return;
			}
			if(!col.contains(null) && col.get(0).equals(col.get(1)) && col.get(1).equals(col.get(2))) {
				if(col.get(0).equals(computer))
					winner = computer;
				else
					winner = user;
				gameCompleted = true;
				return;
			}
		}
		
		ArrayList<String> diag1 = new ArrayList<String>();
		diag1.add(gameBoard[0][0]);
		diag1.add(gameBoard[1][1]);
		diag1.add(gameBoard[2][2]);
		if(!diag1.contains(null) && diag1.get(0).equals(diag1.get(1)) && diag1.get(1).equals(diag1.get(2))) {
			if(diag1.get(0).equals(computer))
				winner = computer;
			else
				winner = user;
			gameCompleted = true;
			return;
		}
		
		ArrayList<String> diag2 = new ArrayList<String>();
		diag2.add(gameBoard[0][2]);
		diag2.add(gameBoard[1][1]);
		diag2.add(gameBoard[2][0]);
		if(!diag2.contains(null) && diag2.get(0).equals(diag2.get(1)) && diag2.get(1).equals(diag2.get(2))) {
			if(diag2.get(0).equals(computer))
				winner = computer;
			else
				winner = user;
			gameCompleted = true;
			return;
		}
	}
	
	public static void main(String[] args) {
		TicTacToe tic = new TicTacToe();
		tic.playGame();
	}
}
