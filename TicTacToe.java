   /*____________________________________________________________________________________________________________/
  /                                                                                                             /
 /    E/12/206 | Lab 8 (Tic-Tac-Toe) submission                                                                   /
/____________________________________________________________________________________________________________*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;

class TicTacToe extends JPanel{
	public static int width = 300;//+15;
	public static int height = 400 + 29;

	public static int currentPlayer; // 0:O;  1:X;
	public static int[][] boardState = new int[3][3]; // -1:empty; 0:O; 1:X;
	public static int totalMoves;
	public static int gameState = 0; // haven't started

	public JButton b1,b2,b3,b4,b5,b6,b7,b8,b9;
	Integer[] blockNames = {1,2,3,4,5,6,7,8,9};
    public static HashMap<Integer, JButton> blockMap;

	public static JLabel l_results;
	public static JLabel l_turn;
	public static Image imgTurn_o, imgTurn_x, play_o, play_x;

	public static int player_wins_X = 0, player_wins_O = 0;

	public static JFrame frame;

	public static int winningLine;

	public TicTacToe(){
		gameState = 1; // playing
		totalMoves = 0;
		currentPlayer = 0;
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				boardState[i][j] = -1;	
			}
		}
		//debug_printBoardState();
		blockMap = new HashMap<Integer, JButton>();
		setBackground(Color.BLACK);
		setLayout(null);
		b1 = new GameBlock(0);
		b2 = new GameBlock(1);
		b3 = new GameBlock(2);
		b4 = new GameBlock(3);
		b5 = new GameBlock(4);
		b6 = new GameBlock(5);
		b7 = new GameBlock(6);
		b8 = new GameBlock(7);
		b9 = new GameBlock(8);
	    blockMap.put(0, b1);
	    blockMap.put(1, b2);
	    blockMap.put(2, b3);
	    blockMap.put(3, b4);
	    blockMap.put(4, b5);
	    blockMap.put(5, b6);
	    blockMap.put(6, b7);
	    blockMap.put(7, b8);
	    blockMap.put(8, b9);
		JLabel title = new JLabel("Tic-Tac-Toe", JLabel.CENTER);
        title.setBackground(new Color(30,31,32));
        title.setOpaque(true);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Consolas", 0, 20));

		l_results = new JLabel(" Wins  X:" + player_wins_X +  "  O:" + player_wins_O);
        l_results.setBackground(new Color(25,25,25));
        l_results.setOpaque(true);
        l_results.setForeground(Color.WHITE);
        l_results.setFont(new Font("Consolas", 0, 20));

        try{
			imgTurn_o = ImageIO.read(new File("img/img_oturn.png"));
			imgTurn_x = ImageIO.read(new File("img/img_xturn.png"));
			play_o = ImageIO.read(new File("img/player_o.png"));
			play_x = ImageIO.read(new File("img/player_x.png"));
        } catch(IOException e){
        	System.out.println(e);
        }


        l_turn = new JLabel(new ImageIcon(imgTurn_o), JLabel.CENTER);
        l_turn.setBackground(new Color(34,37,39));
        l_turn.setOpaque(true);
        l_turn.setForeground(Color.WHITE);
        l_turn.setFont(new Font("Consolas", 0, 30));


        add(title);
        add(l_results);
        add(l_turn);
        title.setBounds(0,0,200,50);
        l_results.setBounds(0,50,200,50);
        l_turn.setBounds(200,0,100,100);
		b1.setBorder(null);
		b2.setBorder(null);
		b3.setBorder(null);
		b4.setBorder(null);
		b5.setBorder(null);
		b6.setBorder(null);
		b7.setBorder(null);
		b8.setBorder(null);
		b9.setBorder(null);
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(b5);
		add(b6);
		add(b7);
		add(b8);
		add(b9);
		b1.setBounds(0,100,100,100);
		b2.setBounds(100,100,100,100);
		b3.setBounds(200,100,100,100);
		b4.setBounds(0,200,100,100);
		b5.setBounds(100,200,100,100);
		b6.setBounds(200,200,100,100);
		b7.setBounds(0,300,100,100);
		b8.setBounds(100,300,100,100);
		b9.setBounds(200,300,100,100);
	}

	public static void main(String args[]){
		frame = new JFrame("TicTacToe");
        frame.setPreferredSize(new Dimension(width, height));
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setContentPane(new TicTacToe());
		frame.pack();
		frame.setLocationRelativeTo(null); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void GameBlockClicked(int id){
		if(gameState<0) {
			frame.setContentPane(new TicTacToe());	//	 Game restarting
		}
		totalMoves++;								// Keep count on the moves
		int col = id%3;
		int row = id/3;
		boardState[row][col] = currentPlayer;
		JButton btn = blockMap.get(id);
		if(currentPlayer==0){
			btn.setIcon(new ImageIcon(play_o));

		} else {
			btn.setIcon(new ImageIcon(play_x));
		}
		btn.setEnabled(false);
		if(checkPlayerWon(currentPlayer)==1){
			playerWon();
		}
		switchPlayer();
	}

	public static void switchPlayer(){
		if(currentPlayer==0){
			l_turn.setIcon(new ImageIcon(imgTurn_x));
			currentPlayer = 1;
		} else {
			l_turn.setIcon(new ImageIcon(imgTurn_o));
			currentPlayer = 0;
		} 
	}
	public static int checkPlayerWon(int player){	// 0:keep playing; 1:currentPlayerWins; -1:draw;
		// debug_printBoardState();
		boolean won = true;
		for (int i=0; i<3; i++) {
			won = true;
			for (int j=0; j<3; j++) {
				if(boardState[i][j] != currentPlayer){
					won=false;
					break;
				}
			}
			if(won){ 
				winningLine = i;
				return 1;
			};
		}
		winningLine = 3;
		won = true;
		for (int j=0; j<3; j++) {
			for (int i=0; i<3; i++) {
				if(boardState[i][j] != currentPlayer){
					won=false;
					break;
				}
			}
			if(won) {
				winningLine += j;
				return 1;
			};
			won = true;
		}
		if(boardState[0][0]==boardState[1][1]&&boardState[1][1]==boardState[2][2]&&boardState[2][2]==currentPlayer){
			winningLine = 6;
			return 1;
		}
		if(boardState[0][2]==boardState[1][1]&&boardState[1][1]==boardState[2][0]&&boardState[2][0]==currentPlayer){
			winningLine = 7;
			return 1;
		}
		if(totalMoves==9){
			gameState = -1; // game ended
			playerDraw();
			return -1;
		} // draw
		return 0;				// keep playing
	}

	public static void debug_printBoardState(){		
		for(int i[]:boardState){
			for(int j:i)
				System.out.print(j + " ");
			System.out.println();
		}
	}
	public static void playerWon(){
		System.out.println("player " + currentPlayer + " won!");
			if(currentPlayer==0) player_wins_O++;
			else player_wins_X++;
			l_results.setText (" Wins  X:" + player_wins_X +  "  O:" + player_wins_O);
			gameState = -1; // ended
			
			final JFrame winframe = new JFrame("Game Finished!");
			winframe.setSize(width, height);
			winframe.setUndecorated(true);
			winframe.setLocationRelativeTo(frame);
			winframe.setAlwaysOnTop(true);
	        winframe.setContentPane(new EndingScreen(currentPlayer, winningLine));
			winframe.setBackground(new Color(0.0f,0.0f,0.0f,0.5f));
			winframe.addMouseListener(new MouseAdapter() {
			@Override
				public void mouseClicked(MouseEvent e) {
				    frame.setContentPane(new TicTacToe());
				    frame.revalidate();
				    winframe.dispose();
			    }
			});
			winframe.setVisible(true);
	}
	public static void playerDraw(){
		System.out.println("a Draw!");
			final JFrame winframe = new JFrame("It's a Draw!");
			winframe.setSize(width, height);
			winframe.setUndecorated(true);
			winframe.setLocationRelativeTo(null); 
	        winframe.setContentPane(new EndingScreen());
			winframe.setBackground(new Color(0.0f,0.0f,0.0f,0.5f));
			winframe.addMouseListener(new MouseAdapter() {
			@Override
				public void mouseClicked(MouseEvent e) {
				    frame.setContentPane(new TicTacToe());
				    frame.revalidate();
				    winframe.dispose();
			    }
			});
			winframe.setVisible(true);
	}
}