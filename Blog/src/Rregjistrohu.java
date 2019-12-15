import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;

import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.text.ParseException;

/**
 * Rregjistrohu
 */
public class Rregjistrohu extends JPanel {
    private JLabel uno, duo, tre, quattro;

    JTextField[] tekst_em;
    JTextField[] tekst_mb;
    JTextField[] tekst_msh;
    JButton[] btnreg;
    JButton[] btnlog;
    JButton[] kthehu;
    JButton[] konfirmo;
    boolean _gati[]={false,false,false,false};
    String [] _emlojtar;
    
    //SQL part
    String url="jdbc:mysql://localhost:3306/dice";
	String user="root";
	String password="testing1234";

    public Menu a;
    Connection myCon;
    Statement myStmt;
    

    Rregjistrohu(Menu a) {
    	
    	
		
    	
        event k = new event(this.a, this);
        int y = 60;
        
        int nr_lojtarve=a.get_nrlojtarve();
        _emlojtar=new String[nr_lojtarve];
        
        tekst_em = new JTextField[nr_lojtarve];
        tekst_mb = new JTextField[nr_lojtarve];
        tekst_msh = new JTextField[nr_lojtarve];
         btnreg = new JButton[nr_lojtarve];
         btnlog = new JButton[nr_lojtarve];
         kthehu=new JButton[nr_lojtarve];
        konfirmo=new JButton[nr_lojtarve];
        
        this.a = a;
        setLayout(null);
        setBounds(0, 0, this.a.getWidth(), this.a.getHeight());
        setBackground(Color.red);

        uno = new JLabel("Lojtari i parë");
        uno.setBounds(50, 50, 200, 50);
        duo = new JLabel("Lojtari i dyte");
        duo.setBounds(50, 100, 200, 50);
        tre = new JLabel("Lojtari i tret");
        tre.setBounds(50, 150, 200, 50);
        quattro = new JLabel("Lojtari i katert");
        quattro.setBounds(50, 200, 200, 50);

        add(uno);
        add(duo);
        add(tre);
        add(quattro);

        // teksti per emrin
        for (int i = 0; i < tekst_em.length; i++) {
            tekst_em[i] = new JTextField();
            tekst_em[i].setBounds(150, y, 120, 30);
            // add(tekst[i]);
            
            tekst_mb[i] = new JTextField();
            tekst_mb[i].setBounds(280, y, 120, 30);
            
            tekst_msh[i] = new JTextField();
            tekst_msh[i].setBounds(410, y, 120, 30);
            
            konfirmo[i] = new JButton("Ruaj");
            konfirmo[i].setBounds(540, y, 120, 30);
            
            y += 50;
        }

       
      

        y=60;
        for (int i = 0; i < btnlog.length; i++) {

            btnlog[i] = new JButton("Hyr");
            btnlog[i].setBounds(180, y, 120, 30);
            
            add(btnlog[i]);
            
            /////////////////////////////////
            
            btnreg[i] = new JButton("Regjistrohu");
            btnreg[i].setBounds(300, y, 120, 30);
            
            btnreg[i].addActionListener(k);
            add(btnreg[i]);
            
            y += 50;
        }
       
    }

    public class event implements ActionListener {
        private Menu a;
        private Rregjistrohu c;
        

        /*
         * event(){
         * 
         * }
         */
        event(Menu a, Rregjistrohu c) {
            this.a = a;
            this.c = c;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
           
            if (e.getActionCommand().equals("Regjistrohu")) {

                for (int i = 0; i < btnreg.length; i++) {
                    if (e.getSource() == btnreg[i]) {
                        // kot=i;
                        System.out.println(i);
                        c.add(tekst_em[i]);
                        c.add(tekst_mb[i]);
                        c.add(tekst_msh[i]);
                        c.add(konfirmo[i]);
                        btnreg[i].setVisible(false);
                        btnlog[i].setVisible(false);
                        
                        konfirmo[i].addActionListener(this);
                        
                        c.validate();
                        c.repaint();
                        break;
                    }
                }

            } 
              if(e.getActionCommand().equals("Ruaj"))
              {
                for (int i = 0; i < konfirmo.length; i++) {
                    if(e.getSource()==konfirmo[i])
                    {
                        String emer,mbiemer,moshe;
                        emer=tekst_em[i].getText();
                        mbiemer=tekst_mb[i].getText();
                        
                        moshe=tekst_msh[i].getText();
                       
                       
                        
                        if(!existemri(emer)&& isParsable(moshe)) {
                    	try {
                    		
                    			myCon=DriverManager.getConnection(url,user,password);
                    			 myStmt=myCon.createStatement();
                    		
                    		
                    		int moshaint=Integer.parseInt(moshe);
                			
                			//System.out.println("U krujua me sukses");
                			//3.Execute SQL query
                		//myStmt.executeUpdate("INSERT INTO loginaccount (username,password,ts) VALUES('DOK','doktest','1980-01-01 00:00:00')");
                			String mysql="INSERT INTO lojtaret"+ "(Emri,Mbiemri,Mosha)"+" VALUES('"+emer+"','"+mbiemer+"','"+moshaint+"')";
                			myStmt.executeUpdate(mysql);
                			
                			JLabel gati=new JLabel("Gati");
                            gati.setBounds(tekst_em[i].getX(), tekst_em[i].getY(), tekst_em[i].getWidth(), tekst_em[i].getHeight());
                            c.add(gati);
                            konfirmo[i].removeActionListener(this);
                            c.remove(konfirmo[i]);
                            c.remove(tekst_em[i]);
                            c.remove(tekst_mb[i]);
                            c.remove(tekst_msh[i]);
                            c.validate();
                            c.repaint();
                            
                            _gati[i]=true;
                            _emlojtar[i]=emer;
                            
                            if(checkgati(konfirmo.length))
                            {
                            	try { myStmt.close(); } catch (Exception exception) { /* ignored */ }
                        	    try { myCon.close(); } catch (Exception exception) { /* ignored */ }
                            	
                            	
                            	/*SHUME E RENDESISHME NUK ESHTE E SAKTE DUHET SHTUAR HEQJA E TE GJITHE KOMPONENTEVE*/
                            	c.menubar().setVisible(false);
                            	new Game(c.menubar(),c.kthe_emrat());
                                c.menubar().dispose();
                            }
                		}
                		catch(Exception exception){exception.printStackTrace();}
                    	finally {
                    	    try { myStmt.close(); } catch (Exception exception) { /* ignored */ }
                    	    try { myCon.close(); } catch (Exception exception) { /* ignored */ }
                    		}
                    	}
                
                       else {JOptionPane.showMessageDialog(null,"Të dhënat janë gabim ose të zëna","Message",JOptionPane.ERROR_MESSAGE);
                       tekst_em[i].setText("");
                       tekst_mb[i].setText("");
                       tekst_msh[i].setText("");
                       
                       }

                    }
                }
              }

        }
    }



    public void rregjstro()
    {
        
    }

    public Menu menubar()
    {
        return this.a;
    }
    
    //Kontrollon nese ekziston emri
    public  boolean existemri(String a)
    {
    	
    	Connection connection=null;
    	Statement myStmtA=null;
    	ResultSet myRs=null;
    	String c;
    	boolean flag=false;
    	
    	try {
    		
			connection=DriverManager.getConnection(url,user,password);
			 myStmtA=connection.createStatement();
			
			 myRs=myStmtA.executeQuery("select Emri from lojtaret");
			 
			
			while(myRs.next())
			{
				 
				c=myRs.getString("Emri");
				System.out.println(c);
				
				if(c.equals(a))
				{
					flag=true;
					
					break;
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	finally {
    	    try { myRs.close(); } catch (Exception e) { /* ignored */ }
    	    try { myStmt.close(); } catch (Exception e) { /* ignored */ }
    	    try { connection.close(); } catch (Exception e) { /* ignored */ }
    	}
    	
    	return flag;
    	
    }
    
    public static boolean isParsable(String input){
        try{
            Integer.parseInt(input);
            return true;
        }catch(NumberFormatException on){
            return false;
        }
    }
    
    public boolean checkgati(int u)
    {
        boolean flag= true;
        for (int i = 0; i < u; i++) {
           flag &=_gati[i];
            
        }
        return flag;
    }
    
    public String[] kthe_emrat()
    {
    	return _emlojtar;
    }
    
}