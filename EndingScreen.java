import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;

class EndingScreen extends JPanel{
	Image play_o, play_x;
	public static int lineId;
	public EndingScreen(int playerid, int winningLine){		// for winning screen
		lineId = winningLine;
 		try{
			play_o = ImageIO.read(new File("img/player_o.png"));
			play_x = ImageIO.read(new File("img/player_x.png"));
        } catch(IOException e){
        	System.out.println(e);
        }

		JLabel wins = new JLabel("Player        wins", JLabel.CENTER);
		JLabel player;
		JLabel instruction = new JLabel("Click to play again", JLabel.CENTER);
		if(playerid==0) player = new JLabel(new ImageIcon(play_o), JLabel.CENTER);
		else player = new JLabel(new ImageIcon(play_x), JLabel.CENTER);
		setLayout(null);

        wins.setFont(new Font("Segoe UI", 0, 30));
        wins.setForeground(Color.WHITE);

        instruction.setFont(new Font("Segoe UI", 0, 16));
        add(player);
		add(wins);
		add(instruction);
		instruction.setBounds(10,320,300,20);
		instruction.setForeground(Color.WHITE);
		player.setBounds(15,215,300,80);
		wins.setBounds(0,238,300,60);
		setBackground(new Color(1.0f,0.2f,1.0f,0.5f));
	}
	public EndingScreen(){									// for draw screen
		lineId = -1;
 		try{
			play_o = ImageIO.read(new File("img/player_o.png"));
			play_x = ImageIO.read(new File("img/player_x.png"));
        } catch(IOException e){
        	System.out.println(e);
        }

		JLabel draw = new JLabel("It's a Draw!", JLabel.CENTER);
		JLabel instruction = new JLabel("Click to play again", JLabel.CENTER);
		setLayout(null);

        draw.setFont(new Font("Segoe UI", 0, 30));
        draw.setForeground(Color.WHITE);

        instruction.setFont(new Font("Segoe UI", 0, 16));
		add(draw);
		add(instruction);
		instruction.setBounds(10,320,300,20);
		instruction.setForeground(Color.WHITE);
		draw.setBounds(10,238,300,60);
		setBackground(new Color(1.0f,0.2f,1.0f,0.5f));
	}
	@Override
    protected void paintComponent(Graphics g) {			// draw the winning move if won
        super.paintComponent(g);
        if(lineId<0) return;
        Graphics2D f = (Graphics2D) g;
        f.setColor(Color.WHITE);
        f.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        f.setStroke(new BasicStroke(16, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        //g.drawLine(50,50,250,50);
        //System.out.println(lineId);
        g.setColor(new Color(55,84,0));
        if(lineId==0) g.drawLine(50,130+50,250,130+50);
        else if(lineId==1) g.drawLine(50,		130+150,	250,	130+150); // + offset given to
        else if(lineId==2) g.drawLine(50,		130+250,	250,	130+250);
        else if(lineId==3) g.drawLine(50,		130+50,		50,		130+250);
        else if(lineId==4) g.drawLine(150,		130+50,		150,	130+250);
        else if(lineId==5) g.drawLine(250,		130+50,		250,	130+250);
        else if(lineId==6) g.drawLine(50,		130+50,		250,	130+250);
        else if(lineId==7) g.drawLine(250,		130+50,		50,		130+250);

    }
}