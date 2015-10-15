package test;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import jpcap.packet.EthernetPacket;
import jpcap.packet.IPPacket;
import jpcap.packet.TCPPacket;
import jpcap.packet.UDPPacket;

public class IPView {
	public static void main(String[] args) throws IOException {
		
		final ArrayList<IPPacket> ipPackets= new ArrayList<IPPacket>();
		
		// �ϲ����
		/* IP���ݱ���ʾ��� */
		final Object[] columnNames = { "Number", "Protocol", "Identifier","Source IP address", 
				"Destination IP address", "Priority" ,"Delay", "Through", 
				"Reliability", "Type of Service", "Fragmentation Reservation flag",
				"Don't fragment flag", "More fragment flag", "Offset"};
		Object[][] rowData = null;
		DefaultTableModel model = new DefaultTableModel(rowData, columnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		final JTable ipViewer = new JTable(model);
		for (int i = 0; i < columnNames.length; i++) {
			int width = ((String) columnNames[i]).length() * 10;
			ipViewer.getColumnModel().getColumn(i).setPreferredWidth(width);
		}
		ipViewer.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		ipViewer.setDefaultRenderer(Object.class, r);

		ipViewer.setSelectionMode(0);

		JScrollPane scroll = new JScrollPane(ipViewer);
		scroll.setPreferredSize(new Dimension(600, 150));

		// �²����
		JPanel downPanel = new JPanel(new GridLayout(1, 2));

		/* ������� */
		Object[] details_columnNames = {"Type","Value"};
		Object[][] details_rowData = null;
		DefaultTableModel details_model = new DefaultTableModel(details_rowData, details_columnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		final JTable details = new JTable(details_model);
		for (int i = 0; i < details_columnNames.length; i++) {
			int width = ((String) columnNames[i]).length() * 10;
			details.getColumnModel().getColumn(i).setPreferredWidth(width);
		}
		DefaultTableCellRenderer details_r = new DefaultTableCellRenderer();
		details_r.setHorizontalAlignment(JLabel.CENTER);
		details.setDefaultRenderer(Object.class, details_r);

		details.setSelectionMode(0);

		JScrollPane details_scroll = new JScrollPane(details);
		downPanel.add(details_scroll);

		/* ������� */
		JPanel operatePanel = new JPanel(new GridLayout(5, 1));
		JPanel usePanel1 = new JPanel(new FlowLayout());
		JPanel usePanel2 = new JPanel(new FlowLayout());
		JPanel usePanel3 = new JPanel(new FlowLayout());
		JPanel usePanel4 = new JPanel(new FlowLayout());
		JPanel usePanel5 = new JPanel(new FlowLayout());
		JLabel text_captedNum = new JLabel("��ץ������");
		final JLabel num_captedNum = new JLabel(Integer.toString(ipViewer.getRowCount()));
		JLabel end_captedNum = new JLabel("��");
		JLabel text_state = new JLabel("��ǰ״̬��");
		final JLabel state = new JLabel("�ȴ�����");
		JButton startButton = new JButton("START");
		JButton resetButton = new JButton("RESET");
		JLabel packetNum = new JLabel("ץ������:");
		final JTextField getNum = new JTextField();
		getNum.setPreferredSize(new Dimension(50, 28));
		JLabel filterType = new JLabel("���������ã�");
		final JComboBox filter = new JComboBox();
		filter.addItem("IP");
		filter.addItem("TCP");
		filter.addItem("UDP");
		filter.addItem("ICMP");
		usePanel1.add(startButton);
		usePanel1.add(resetButton);
		usePanel2.add(filterType);
		usePanel2.add(filter);
		usePanel3.add(packetNum);
		usePanel3.add(getNum);
		usePanel4.add(text_captedNum);
		usePanel4.add(num_captedNum);
		usePanel4.add(end_captedNum);
		usePanel5.add(text_state);
		usePanel5.add(state);
		operatePanel.add(usePanel2);
		operatePanel.add(usePanel3);
		operatePanel.add(usePanel1);
		operatePanel.add(usePanel4);
		operatePanel.add(usePanel5);

		downPanel.add(operatePanel);
		
		
		// ������齨����ʾ
		final JFrame frame = new JFrame();
		frame.setTitle("IPCaptor");
		frame.setBounds(125, 50, 600, 380);
		frame.setLayout(new GridLayout(2, 1));
		frame.add(scroll);
		frame.add(downPanel);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		//����������ץ��
		IPCaptor.open();
		
		// START�����ʼץ��
		startButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				state.setText("ץ�����!");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				state.setText("ץ����...");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(getNum.getText().equals("")) {
					state.setText("ץȡ����Ϊ��!");
					return;
				}
				int max = Integer.parseInt(getNum.getText());
				if (max < 0) {
					state.setText("������д����!");
					return;
				}
				int filterType = filter.getSelectedIndex();
				
				try {
					IPCaptor.setFilterType(filterType);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				while (max-- != 0) {
					DefaultTableModel tableModel = (DefaultTableModel) ipViewer.getModel();
					
					IPPacket ip= IPCaptor.cap();
					ipPackets.add(ip);
						
					String ipPacket[] = new String[14];
					ipPacket[0]=Integer.toString(ipViewer.getRowCount()+1);
					ipPacket[1]=IPCaptor.change(ip.protocol);
					ipPacket[2]=Integer.toString(ip.ident);
					ipPacket[3]=ip.src_ip.toString().substring(1, ip.src_ip.toString().length());
					ipPacket[4]=ip.dst_ip.toString().substring(1, ip.dst_ip.toString().length());
					ipPacket[5]=Byte.toString(ip.priority);
					ipPacket[6]=Boolean.toString(ip.d_flag);
					ipPacket[7]=Boolean.toString(ip.t_flag);
					ipPacket[8]=Boolean.toString(ip.r_flag);
					ipPacket[9]=Byte.toString(ip.rsv_tos);
					ipPacket[10]=Boolean.toString(ip.rsv_frag);
					ipPacket[11]=Boolean.toString(ip.dont_frag);
					ipPacket[12]=Boolean.toString(ip.more_frag);
					ipPacket[13]=Short.toString(ip.offset);
						
					tableModel.addRow((Object[])ipPacket);
				}
				getNum.setText("");
				num_captedNum.setText(Integer.toString(ipViewer.getRowCount()));
			}
		});

		// RESET������ץ��
		resetButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				state.setText("�������!");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				state.setText("������...");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				filter.setSelectedIndex(0);
				getNum.setText("");
				DefaultTableModel model = (DefaultTableModel) ipViewer.getModel();
				for (int i = model.getRowCount() - 1; i >= 0; i--) {
					model.removeRow(i);
				}
				
				DefaultTableModel tableModel = (DefaultTableModel) details.getModel();
				
				for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
					tableModel.removeRow(i);
				}
				
				num_captedNum.setText(Integer.toString(ipViewer.getRowCount()));
				ipPackets.clear();
			}
		});
		
		//��ȡѡ������ϸ��Ϣ
		ipViewer.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				int selectRows=ipViewer.getSelectedRows().length;// ȡ���û���ѡ�е�����
				if(selectRows==1) {
					int selectedRowIndex = ipViewer.getSelectedRow(); // ȡ���û���ѡ����
					
					DefaultTableModel tableModel = (DefaultTableModel) details.getModel();
					
					for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
						tableModel.removeRow(i);
					}
					
					if(ipPackets.get(selectedRowIndex).protocol==IPPacket.IPPROTO_UDP) {
						UDPPacket udpPacket = (UDPPacket) ipPackets.get(selectedRowIndex);
						Object[] row1 = { "src_port", Integer.toString(udpPacket.src_port)};
						Object[] row2 = { "dst_port", Integer.toString(udpPacket.dst_port)};
						Object[] row3 = { "length", Integer.toString(udpPacket.length)};
						EthernetPacket datalink = (EthernetPacket) udpPacket.datalink;
						Object[] row4 = { "src_mac", datalink.src_mac.toString()};
						Object[] row5 = { "dst_mac", datalink.dst_mac.toString()};
						
						tableModel.addRow(row1);
						tableModel.addRow(row2);
						tableModel.addRow(row3);
						tableModel.addRow(row4);
						tableModel.addRow(row5);
					}
					else if(ipPackets.get(selectedRowIndex).protocol==IPPacket.IPPROTO_TCP) {
						TCPPacket tcpPacket = (TCPPacket) ipPackets.get(selectedRowIndex);
						Object[] row1 = { "src_port", Integer.toString(tcpPacket.src_port)};
						Object[] row2 = { "dst_port", Integer.toString(tcpPacket.dst_port)};
						Object[] row3 = { "length", Integer.toString(tcpPacket.length)};
						Object[] row4 = { "ack", Boolean.toString(tcpPacket.ack)};
						Object[] row5 = { "ack_num", Long.toString(tcpPacket.ack_num)};
						Object[] row6 = { "fin", Boolean.toString(tcpPacket.fin)};
						Object[] row7 = { "psh", Boolean.toString(tcpPacket.psh)};
						Object[] row8 = { "rst", Boolean.toString(tcpPacket.rst)};
						Object[] row9 = { "rsv1", Boolean.toString(tcpPacket.rsv1)};
						Object[] row10 = { "rsv2", Boolean.toString(tcpPacket.rsv2)};
						Object[] row11 = { "sequence", Long.toString(tcpPacket.sequence)};
						Object[] row12 = { "syn", Boolean.toString(tcpPacket.syn)};
						Object[] row13 = { "urg", Boolean.toString(tcpPacket.urg)};
						Object[] row14 = { "urgent_pointer", Short.toString(tcpPacket.urgent_pointer)};
						Object[] row15 = { "window", Integer.toString(tcpPacket.window)};
						EthernetPacket datalink = (EthernetPacket) tcpPacket.datalink;
						Object[] row16 = { "src_mac", datalink.src_mac.toString()};
						Object[] row17 = { "dst_mac", datalink.dst_mac.toString()};
						
						tableModel.addRow(row1);
						tableModel.addRow(row2);
						tableModel.addRow(row3);
						tableModel.addRow(row4);
						tableModel.addRow(row5);
						tableModel.addRow(row6);
						tableModel.addRow(row7);
						tableModel.addRow(row8);
						tableModel.addRow(row9);
						tableModel.addRow(row10);
						tableModel.addRow(row11);
						tableModel.addRow(row12);
						tableModel.addRow(row13);
						tableModel.addRow(row14);
						tableModel.addRow(row15);
						tableModel.addRow(row16);
						tableModel.addRow(row17);
					}
					else {
						EthernetPacket datalink = (EthernetPacket) ipPackets.get(selectedRowIndex).datalink;
						Object[] row1 = { "src_mac", datalink.src_mac.toString()};
						Object[] row2 = { "dst_mac", datalink.dst_mac.toString()};
						
						tableModel.addRow(row1);
						tableModel.addRow(row2);
					}
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}