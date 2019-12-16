import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * KonfigurimP
 */
public class KonfigurimP extends JPanel {
    JRadioButton [] _jradio=new JRadioButton[13];
    ButtonGroup G1=new ButtonGroup(); 
    String s;
    
    KonfigurimP(){
        int y=10;
        setLayout(null);
        setBounds(220, 0, 120, 700);
    for(int i=0;i<_jradio.length;i++)
    {
    	
        if(i==0)
        {
            _jradio[i]=new JRadioButton("Kategoria "+(i+1)+" :",true);
            _jradio[i].setBounds(10, y, 120, 20);
            y+=40;
        }else{
        _jradio[i]=new JRadioButton("Kategoria "+(i+1)+" :");
        _jradio[i].setBounds(10, y, 120, 20);
        y+=35;
        }
        
        G1.add(_jradio[i]);
        add(_jradio[i]);
        _jradio[0].setSelected(false);

        
        
    }
  
    //_jradio[0].setEnabled(false);
   // System.out.println(_jradio[0].isSelected());  
    //_jradio[1].setSelected(true);
}

    public void update_kategori(int index,boolean b)
    {
    	_jradio[index].setEnabled(b);
    }
public JRadioButton[] getbutonatradio(){
    return _jradio;
}

public JRadioButton getJRadio(int x)
{
    return _jradio[x];
}
}