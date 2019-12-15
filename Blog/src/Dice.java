
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Dice  extends JPanel{
	private int dice_x;
	private int dice_y;
	public int k;
	private int dice_width;
	private int dice_height;
	private String _path;
	public JLabel label,label1;
	//public ArrayList<JLabel> dice_label = new ArrayList() {label,label1};
	public JLabel[] dice_label = new JLabel[5];
	ImageIcon imageic1;
	Image image1;
	Image myimage1;
	private int dice_points = 0;
	static int[] _point_vector = new int[5];
	public int sum_points = 0;
	int zari1;
	int zari2;
	public int rand_dice;
	JFrame _frame;
	public Dice(int _dice_x,int _dice_y)
	{
		dice_x = _dice_x;
		dice_y = _dice_y;
		//int[] point_vektor = new int[dice_label.length];
		//point_vector = new int[dice_label.length];
		this.setBounds(0, 0, 200, 500);
		this.setBackground(Color.YELLOW);
		this.setLayout(null);
		for(int i = 0;i<5;i++)
		{
			
			dice_label[i] = new JLabel();
			dice_label[i].setBounds(dice_x, dice_y, 70, 70);
			dice_y += 100;
			dice_label[i].setName("lab"+i);
			rand_dice = _generateRandom();
			
			Dice._point_vector[i] = rand_dice;
			add_dice(dice_label[i],rand_dice);
			//sum_points = sum_points + rand_dice;
			this.add(dice_label[i]);
			
		}
		/*
		 label = new JLabel();
		 label.setBounds(dice_x, dice_y, 70, 70);
		 dice_width = 70;
		 dice_height = 70;
		 add_dice(path);
		 this.add(label);
		 */
		 //_point_vektor = point_vektor;
	}
	
	public int return_points()
	{
		sum_points = 0;
		for(int i = 0;i<dice_label.length;i++)
		{
			sum_points = sum_points + Dice._point_vector[i];
		}
		return sum_points;
	}
	//shton zarin ne label
	public void add_dice(JLabel label,int random_nr)
	{	
		imageic1 = new ImageIcon("dice"+random_nr+".png");
		
		image1 = imageic1.getImage();
		myimage1 = image1.getScaledInstance(label.getWidth(),label.getHeight(), Image.SCALE_SMOOTH);
		imageic1 = new ImageIcon(myimage1);
		label.setIcon(imageic1);
		
	}
	public int _generateRandom()
	{
		Random num = new Random();
		int rand_num = 1 + num.nextInt(6);
		//sum = sum + rand_num;
		//dice_points = dice_points + rand_num;
		return rand_num;
		
	}
	public int change_dice(JLabel label,int random_nr)
	{
		add_dice(label,random_nr);
		return random_nr;
	//frame.getContentPane().add(label);
		
	}
	public String return_dice()
	{
		return _path;
	}
	
	public int return_width()
	{
		return dice_width;
	}
	
	public int return_height()
	{
		return dice_height;
	}

	

}
