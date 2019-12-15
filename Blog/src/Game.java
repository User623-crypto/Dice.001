import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Game extends JFrame implements Runnable,MouseListener{
	
	/*SQL PART*/
	 String url="jdbc:mysql://localhost:3306/dice";
	 String user="root";
	 String password="testing1234";
	 
	 
	 

	String [] _emrat;
	
	private int sum = 0;

	 public Dice my_dice; //Zarat
	 public JButton button,btnktg;
	 private int click_count = 0;
     JPanel show_turn = new JPanel();
     public static  int game_turn=0;
	 private JLabel turn_text = new JLabel("TURN: "+(game_turn+1));
	 private int points = 0;
	 //ArrayList per te mbajtur zaret qe do te hiddhen 
	 //public ArrayList<JLabel> dice_arr = new ArrayList();
	 public ArrayList<Integer> dice_arr = new ArrayList();
	 private int same_3_vlera;
     private int cat_count = 0;
     public int countercount=0;
     public boolean roll=false;
     
     
     /*SHTIM I RENDSISHEM .........*/ 
     KonfigurimP panel_konf;
     TableExample testing;
     private mainevent _mainevent;

     /*me i Rendsishem */
     private TableExample[] _lojtaret;
     private int[] _piket;
 	 
	public Game(Menu c,String [] d)
	{
		
		int nr_lojtarve =c.get_nrlojtarve();
		_lojtaret=new TableExample[nr_lojtarve];
		
		this._emrat=d;
		System.out.println(_emrat.length);
		
		_piket=new int[nr_lojtarve];
		for(int i=0;i<_piket.length;i++)
		{
			_piket[i]=0;
		}
		
		
		
        int pozx=500;
		_mainevent=new mainevent();
        panel_konf=new KonfigurimP();
        for(int i=0;i<_lojtaret.length;i++)
        {
        _lojtaret[i]=new TableExample("Luka",pozx);
        pozx+=65;
        }
		
		//Krijimi i Frames
		this.setSize(1200, 700);
	
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.CYAN);
		getContentPane().setLayout(null);

		//Create a button to click;
		button = new JButton("Roll the dice");
		button.setBounds(20, 500, 150, 50);
		getContentPane().add(button);
        
        //Create a button to choose category
        btnktg=new JButton("Zgjidh Kategorin");
        btnktg.setBounds(170, 500, 150, 50);
        getContentPane().add(btnktg);
		
		
		my_dice = new Dice(20,10);
		getContentPane().add(my_dice);
		//Add all dice label to arraylist
		for(int i =0;i<my_dice.dice_label.length;i++)
			
		{
			dice_arr.add(i);
		}
		//shton Mouse Listener te cdo imazh i zarave
		
		for(int i = 0;i<my_dice.dice_label.length;i++)
		{
			my_dice.dice_label[i].addMouseListener(this);
		}
		
	
		
		showplayer_turn();

        getContentPane().add(panel_konf);
        for(int i=0;i<_lojtaret.length;i++)
        {
            getContentPane().add(_lojtaret[i]);
        }
        btnktg.setEnabled(false);
        setVisible(true);
        
			play_game();
		
	}
	
	public void play_game()
	{
        button.addActionListener(_mainevent);
        btnktg.addActionListener(_mainevent);
		
	
}

public int generateRandom()
{
	Random num = new Random();
	int rand_num = 1 + num.nextInt(6);
	sum = sum + rand_num;
	return rand_num;
	
}
public int return_sum()
{
	return sum;
}
	
public void showplayer_turn()
{

	show_turn.add(turn_text);
	show_turn.setBounds(120, 600, 200, 60);
	show_turn.setBackground(Color.RED);
	getContentPane().add(show_turn);
}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

				//System.out.println("Array List size: "+ dice_arr.size());

				for(int x = 0;x<my_dice.dice_label.length;x++ )
				{
					
					if(e.getSource() instanceof JLabel)
						
					{
						
						JLabel src = (JLabel) e.getSource();
						
							if(src == my_dice.dice_label[x])
							{
								System.out.println("Clicked"+x);
								//my_dice.change_dice(my_dice.dice_label[x], 7);
								my_dice.dice_label[x].setEnabled(false);
								//dice_arr.add(my_dice.dice_label[x]);
								dice_arr.add(x);
								
								if(click_count == 0)
									break;
		
							}

					}
				}

				System.out.println("SUM: "+ my_dice.return_points());	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
	public int pointper_category(int category_num)
	{
		if(category_num == 1)
		{
			points = 0;
			//points = my_dice.return_points() + 100;
			for(int i = 0;i<Dice._point_vector.length;i++)
			{
				if(Dice._point_vector[i] == 1)
				{
					points = points + 1;
				}
			}
		}
		if(category_num == 2)
		{
			points = 0;
			//points = my_dice.return_points() + 100;
			for(int i = 0;i<Dice._point_vector.length;i++)
			{
				if(Dice._point_vector[i] == 2)
				{
					points = points + 2;
				}
			}
		}
		if(category_num == 3)
		{
			points = 0;
			//points = my_dice.return_points() + 100;
			for(int i = 0;i<Dice._point_vector.length;i++)
			{
				if(Dice._point_vector[i] == 3)
				{
					points = points + 3;
				}
			}
		}
		
		
		if(category_num == 4)
		{
			points = 0;
			//points = my_dice.return_points() + 100;
			for(int i = 0;i<Dice._point_vector.length;i++)
			{
				if(Dice._point_vector[i] == 4)
				{
					points = points + 4;
				}
			}
		}
		if(category_num == 5)
		{
			points = 0;
			//points = my_dice.return_points() + 100;
			for(int i = 0;i<Dice._point_vector.length;i++)
			{
				if(Dice._point_vector[i] == 5)
				{
					points = points + 5;
				}
			}
		}
		if(category_num == 6)
		{
			points = 0;
			//points = my_dice.return_points() + 100;
			for(int i = 0;i<Dice._point_vector.length;i++)
			{
				if(Dice._point_vector[i] == 6)
				{
					points = points + 6;
				}
			}
		}
		
		//Te pakten 3 me nje vlere dhe piket jan sa shuma e gjithe zarave
		if(category_num == 7)
		{
			points = 0;
			
			if(longest_freq(Dice._point_vector)>=3)
			{
				for(int i = 0;i<Dice._point_vector.length;i++)
				{
					
						points = points  + Dice._point_vector[i];
					
				}
			}
			//points = my_dice.return_points() + 100;
			
		}
		
		if(category_num == 8)
		{
			points = 0;
			
			if(longest_freq(Dice._point_vector)>=4)
			{
				for(int i = 0;i<Dice._point_vector.length;i++)
				{
					
						points = points  + Dice._point_vector[i];
					
				}
			}
			//points = my_dice.return_points() + 100;
			
		}
		
		if(category_num == 9)
		{
			points = 0;
			
			if(longest_freq(Dice._point_vector)==3 && same_2(Dice._point_vector ) == true)
			{
				points = 25;
			}
			//points = my_dice.return_points() + 100;
			
		}
		
		if(category_num == 10)
		{
			points = 0;
			
			if(continous(Dice._point_vector) == 4)
			{
				points = 30;
			}
			//points = my_dice.return_points() + 100;
			
		}
		if(category_num == 11)
		{
			points = 0;
			
			if(continous(Dice._point_vector) == 5)
			{
				points = 40;
			}
			//points = my_dice.return_points() + 100;
			
		}
		if(category_num == 12)
		{
			points = 0;
			
			if(all_same(Dice._point_vector) == true)
			{
				points = 50;
			}
			//points = my_dice.return_points() + 100;
			
		}
		
		if(category_num == 13)
		{
			points = 0;
			
			for(int i = 0;i<Dice._point_vector.length;i++)
			{
				points = points + Dice._point_vector[i];
			}
			//points = my_dice.return_points() + 100;
			
		}
		return points;
	}
	
	public int longest_freq(int Arr[])
	{
		int freq = 1;
		int count = 1;
		for(int i = 0;i<Arr.length-1;i++)
		{
			count = 1;
			for(int j = i+1;j<Arr.length;j++)
			{
				if(Arr[i] == Arr[j])
				{
					count++;
					
				}
			}
			if(count > freq)
			{
				freq = count;
				same_3_vlera = Arr[i];
			}
		}
		return freq;
	}
	

	public boolean same_2(int Arr[])
	{
		int count = 1;
		if(longest_freq(Arr) == 3)
		{
			
			
			for(int i = 0;i<Arr.length-1;i++)
			{
				if(Arr[i] != same_3_vlera)
				{
					for(int j = i+1;j<Arr.length;j++)
					{
						if(Arr[i] == Arr[j])
						{
							count++;
						}
						
					}
				}
				
			}
			//return freq;
		}
		if(count == 2)
			
		{
			return true;
		}
		return false;
	}
	
	public int continous(int Arr[])
	{
		int count = 1;
		
		for(int i = 0;i<Arr.length-1;i++)
		{
			if(i == 2 && count == 1)
				break;
			if(Arr[i] - Arr[i+1] == -1)
			{
				count++;
			}
			
		}
		return count;
	}
	
	public boolean all_same(int Arr[])
	{

		for(int i = 0;i<Arr.length-1;i++)
		{
			if(Arr[i] != Arr[i+1])
				return false;
			
		}
		return true;
	}
    
    



    ///////////////SHTESE/////////////////////////////
    /* METODS E  EVENTLISTENER KUR SHTYPET BUTONAT  */

    public class mainevent implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==button)
            {
                roll=true;
                btnktg.setEnabled(true);
                if(countercount < 20 && cat_count < 2)
				
			  
                {
                    
                    countercount++;
                    //gjenerimi randon i imazheve per zarat
                    click_count = 0;
                    if(dice_arr.size() == 0)
                    {
                        for(int i =0;i<my_dice.dice_label.length;i++)
                          {
                            //dice_arr.get(i).setEnabled(true);
                              dice_arr.add(i);
                              //dice_arr.get(i).setEnabled(true);
                          }
                    }
                       for(int i = 0;i<dice_arr.size();i++)
                        {
                            int num_ = generateRandom();
                           my_dice.dice_label[dice_arr.get(i)].setEnabled(true);
                            my_dice.change_dice(my_dice.dice_label[dice_arr.get(i)], num_);
                            
                            Dice._point_vector[dice_arr.get(i)] = num_;				  
                        }
  
                    System.out.println("SUM: "+ my_dice.return_points());
              
                    dice_arr.removeAll(dice_arr);
                }	  
            }

            if(e.getSource()==btnktg)
            {
                if(roll==true){
                    countercount=0;
                    int piket=getcategorypoitns();
                    _piket[game_turn]+=piket;
                _lojtaret[game_turn].addpoints(piket, getSelectedJr(), 0);
                game_turn++;
                
                roll=false;
                btnktg.setEnabled(false);
                panel_konf.getJRadio(0).setSelected(true);
                if(checkwinner())
                {
                	for(int i=0;i<_emrat.length;i++)
                		rregjistro_piket(_piket[i],_emrat[i]);
                }

                if(game_turn==_lojtaret.length)
                    game_turn=0;
                }
                turn_text.setText("Turn: "+(game_turn+1));
            }

        }

    }

    public int getcategorypoitns()
    {
        
        return pointper_category(getSelectedJr()+1);
    }

    public int getSelectedJr()
    {
        int kthehet=0;
        for(int i=0;i<panel_konf.getbutonatradio().length;i++)
        {
            if(panel_konf.getJRadio(i).isSelected())
            {
                kthehet=i;
            }
        }
        return kthehet;
    }

    public boolean checkwinner()
    {
        for (int i = 0; i < _piket.length; i++) {
            if(_piket[i]>=20) {
            System.out.println("Lojtari"+(i+1)+"fitoi");
            return true;
            }

        }
        return false;
    }
    
    
    
    
    
    /*SQL PART*/
    
    public  void rregjistro_piket(int  pik,String emrat)
    {
    	
    	
    	Connection rr_connection=null;
    	Statement rr_statement=null;
    	ResultSet rr_rs=null;
    	
    	 
    	
    	try {
    		rr_connection=DriverManager.getConnection(url,user,password);
    		rr_statement=rr_connection.createStatement();
			
			// myRs=myStmt.executeQuery("select Emri from lojtaret"
			 	 
    		rr_rs=rr_statement.executeQuery("select * from lojtaret");
				while(rr_rs.next())
				{
					
					if(emrat.equals(rr_rs.getString("Emri")))
					{
						if(pik>rr_rs.getInt("Piket")) {
							rr_statement.executeUpdate("UPDATE lojtaret set Piket="+pik+" where idLojtaret="+rr_rs.getInt("idLojtaret"));}
						
						
						
					}
					
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	finally {
    		try { rr_rs.close(); } catch (Exception e) { /* ignored */ }
    	    try { rr_statement.close(); } catch (Exception e) { /* ignored */ }
    	    try { rr_connection.close(); } catch (Exception e) { /* ignored */ }
    	}
    	if(rr_rs!=null)
    	{
    		try {
				rr_rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	if(rr_statement!=null)
    	{
    		try {
				rr_statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	if(rr_connection!=null)
    	{
    		try {
				rr_connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	
    	
    	
    	
    }
}