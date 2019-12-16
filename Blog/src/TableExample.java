import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.FlowLayout;

public class TableExample extends JPanel {    
    private boolean[] table_kategori = new boolean[13];
      
    DefaultTableModel model;
    TableExample(String s,int x){ 
    	
    	for(int i = 0;i<table_kategori.length;i++)
    	{
    		table_kategori[i] = true;
    	}
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
    public boolean[] return_kategori()
    {
    	return table_kategori;
    }
    public boolean return_kategori(int index)
    {
    	return table_kategori[index];
    }
    
    public void  set_kategori(int cat_index)
    {
    	table_kategori[cat_index] = false;
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