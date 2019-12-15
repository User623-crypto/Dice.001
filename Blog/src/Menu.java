
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Menu
 */
public class Menu extends JFrame implements Runnable{

    
	
        

         public JButton button;
         public JButton button2;
         public JButton button3;
         public int nr_lojtarve=2;
         public JPanel firstpanel;
         JPanel show_turn = new JPanel();
         Rregjistrohu regjister;
         
    
        
         
         
          
        public Menu()
        {
           
            
            firstpanel=new JPanel();
            event e=new event(this);
            
            //Krijimi i Frames
            this.setSize(870, 700);
        
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            //getContentPane().setBackground(Color.CYAN);
            getContentPane().setLayout(null);
            //firstpanel.setSize(getWidth(), getHeight());
            firstpanel.setLayout(null);
            firstpanel.setBounds(0,0,getWidth(),getHeight());
            firstpanel.setBackground(Color.GRAY);
            JLabel numri=new JLabel("Sa vete dëshironi të luani");
            numri.setBounds(200,200,200,50);
            //Create a button to click;
            button = new JButton("Dy");
            button.setBounds(20, 450, 150, 50);

            button2 = new JButton("Tre");
            button2.setBounds(180, 450, 150, 50);

            button3 = new JButton("Kater");
            button3.setBounds(350, 450, 150, 50);


            
            /*button2 = new JButton("Rregjistrohu");
            button3 = new JButton("Hyr");

            button2.setBounds(20, 150, 150, 50);
            button3.setBounds(20, 300, 150, 50);
            button.setBounds(20, 450, 150, 50);
            getContentPane().add(button);
            getContentPane().add(button2);
            getContentPane().add(button3);

            button2.addActionListener(e);
            button3.addActionListener(e);*/
            firstpanel.add(button);
            firstpanel.add(button2);
            firstpanel.add(button3);
            firstpanel.add(numri);
            
            getContentPane().add(firstpanel);
            button.addActionListener(e);
            button2.addActionListener(e);
            button3.addActionListener(e);
            
            //regjister=new Rregjistrohu(this);
            //getContentPane().add(regjister);

            setVisible(true);
            

            
        
    }

    @Override
    public void run() {
    
    }

    public class event implements ActionListener
    {
        private Menu a;
        event(){

        }
        event(Menu a){
            this.a=a;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getSource()==button2)
            {
                a.set_nrlojtarve(3);
                a.getContentPane().remove(firstpanel);
               a.getContentPane().add(new Rregjistrohu(a));
               a.revalidate();
               a.repaint();
               
                
            } else if(e.getSource()==button3)
            {
                a.set_nrlojtarve(4);
                a.getContentPane().remove(firstpanel);
               a.getContentPane().add(new Rregjistrohu(a));
               a.revalidate();
               a.repaint();
                
            }
            else if(e.getSource()==button)
            {
               /* Container parent=button.getParent();
                parent.remove(button);
                parent.revalidate();
                parent.repaint();*/
                
               // a.firstpanel.removeAll();
                //a.firstpanel.revalidate();
                //a.firstpanel.repaint();

                a.set_nrlojtarve(2);
               a.getContentPane().remove(firstpanel);
               a.getContentPane().add(new Rregjistrohu(a));
               a.revalidate();
               a.repaint();

              /* a.getContentPane().add(regjister);
               a.revalidate();
               a.repaint();*/
               
                
            }
            
            
        }
    }
    public JPanel getpanel()
    {
        return firstpanel;
    }

    public void set_nrlojtarve(int a)
    {
            nr_lojtarve=a;
    }

    public int get_nrlojtarve()
    {
        return nr_lojtarve;
    }
}