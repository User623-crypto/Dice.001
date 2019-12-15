import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.FlowLayout;

public class TableExample extends JPanel {    
    
      
    DefaultTableModel model;
    TableExample(String s,int x){ 
     model = new DefaultTableModel(); 
     setLayout(new BorderLayout());   
     
       int kost=2;
         

    JTable jt=new JTable(model); 
   
    jt.setBounds(0,0,200,200);  
    jt.setRowHeight(35);
    
        model.addColumn(s);
        
        
    

    for (int i = 0; i < 13; i++) {
        model.addRow(new Object[]{0});
    }
    
        jt.getColumnModel().getColumn(0).setMaxWidth(65);
        jt.getColumnModel().getColumn(0).setPreferredWidth(27);
        
        
    
    
    JScrollPane sp=new JScrollPane(jt); 
    
    
    setBounds(x, 0, 65, 700); 
    add(sp,BorderLayout.CENTER);   
           
      
    setVisible(true); 
    jt.setEnabled(false);

    
    
    } 
    public DefaultTableModel getTable()
    {
        return model;
    } 
    
    public void addpoints(int piketshtes,int ax,int ay)
    {
        model.setValueAt((int)model.getValueAt(ax, ay)+piketshtes, ax, ay);
    }
}     