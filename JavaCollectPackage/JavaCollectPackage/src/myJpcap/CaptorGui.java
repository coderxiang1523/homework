package myJpcap;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class CaptorGui extends JFrame {
	
	private JTabbedPane tab = new JTabbedPane();
	private JButton start=new JButton("Start");
	private JButton stop=new JButton("Stop");
	private JTextField inputfilter= new JTextField("");
	private JLabel filter=new JLabel("filter : ");
	private JScrollPane scroll;
	private JPanel main = new JPanel();
	private JPanel chart = new JPanel();
	private JPanel function= new JPanel();
	private JPanel display= new JPanel();
	private JTable table ;
	private DefaultTableModel tableModel;  
	private PacketCaptor captor = new PacketCaptor();
	private Vector rows, columns;
	private String[] col={"Abbbbbbbbbbbbbbbbbbbbbbbbbbbbbb","A","A","A","A","A","A","A","A","A","A","A","A","A","A"};
	private String[][] row={};
	public CaptorGui(){
		super();
		initGui();
	}
	
	public void initGui(){
		
		getContentPane().add(tab,BorderLayout.CENTER);
		tab.addTab("包数据", main);
	    tab.addTab("统计数据", chart);
	//    function.setSize(100, 200);
	   
	   // main.add(scroll,BorderLayout.CENTER);
	    function.add(filter,new FlowLayout());
	    inputfilter.setPreferredSize(new Dimension(300, 30));
	    function.add(inputfilter,new FlowLayout());
	    function.add(start,new FlowLayout());
	    function.add(stop,new FlowLayout());
	    main.add(function,BorderLayout.NORTH);
	    
	    
	    
	    
	    tableModel = new DefaultTableModel(row,col);
	    table = new JTable(tableModel);
	    table.setAutoResizeMode(NORMAL);
	    scroll = new JScrollPane(table);
	 
	    main.add(scroll, BorderLayout.CENTER);
	 //   scroll.setViewportView(table);  
	    scroll.setPreferredSize(new Dimension(750,470));
	    
	  
		
	}
	public void actionPerformed(ActionEvent event) throws IOException {
		String cmd = event.getActionCommand();

		if (cmd.equals("start")) {
			captor.opendevice();
			captor.startCaptor();
			
		} else if (cmd.equals("stop")) {
			captor.stop();
		} else if (cmd.equals("exit")) {
			
			System.exit(0);
		}
	}
      public static void main(String[] args){
    	  SwingUtilities.invokeLater(new Runnable() {
  			public void run() {
  				CaptorGui inst = new CaptorGui();
  				inst.setLocationRelativeTo(null);
  				inst.setVisible(true);
  				//inst.setResizable(false);
  				inst.setSize(800, 600);
  				//inst.pack();
  			}
  		});
      }
}
