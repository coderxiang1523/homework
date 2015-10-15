package myJpcap;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.SwingUtilities;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class NewJFrame extends javax.swing.JFrame {
	private JTabbedPane jTabbedPane1;
	private JButton jButton1;
	private JTextField jTextField1;
	private JLabel jLabel1;
	private JScrollPane jScrollPane1;
	private JPanel jPanel3;
	private JPanel jPanel1;
	private JPanel jPanel2;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				NewJFrame inst = new NewJFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
				inst.setSize(400, 309);
			}
		});
	}
	
	public NewJFrame() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setSize(400, 300);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jTabbedPane1 = new JTabbedPane();
				getContentPane().add(jTabbedPane1, BorderLayout.CENTER);
				{
					jPanel1 = new JPanel();
					jTabbedPane1.addTab("包数据", null, jPanel1, null);
					{
						jPanel3 = new JPanel();
						FlowLayout jPanel3Layout = new FlowLayout();
						jPanel3.setLayout(jPanel3Layout);
						jPanel1.add(jPanel3);
						jPanel3.setPreferredSize(new java.awt.Dimension(346, 27));
						{
							jLabel1 = new JLabel();
							FlowLayout jLabel1Layout = new FlowLayout();
							jLabel1.setLayout(jLabel1Layout);
							jPanel3.add(jLabel1);
							jLabel1.setText("filter\uff1a");
							jLabel1.setPreferredSize(new java.awt.Dimension(346, 25));
						}
						{
							jTextField1 = new JTextField();
							jPanel3.add(jTextField1);
							jTextField1.setPreferredSize(new java.awt.Dimension(284, 27));
						}
						{
							jButton1 = new JButton();
							BorderLayout jButton1Layout = new BorderLayout();
							jButton1.setLayout(jButton1Layout);
							jPanel3.add(jButton1);
							jButton1.setText("start");
							jButton1.setPreferredSize(new java.awt.Dimension(40, 24));
						}
					}
					{
						jScrollPane1 = new JScrollPane();
						jPanel1.add(jScrollPane1);
						jScrollPane1.setPreferredSize(new java.awt.Dimension(362, 189));
					}
				}
				{
					jPanel2 = new JPanel();
					jTabbedPane1.addTab("统计数据", null, jPanel2, null);
				}
			}
			
			
			pack();
		
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
