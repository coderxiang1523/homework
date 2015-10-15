package myJpcap;
import java.io.IOException;
import jpcap.*;
import jpcap.packet.*;

public class PacketCaptor {
	private static JpcapCaptor captor = null;
	private  Thread s = null;
	private String[] packetinfo;
	public  void opendevice()throws IOException{
		NetworkInterface[] devices = JpcapCaptor.getDeviceList();
		captor = JpcapCaptor.openDevice(devices[0], 2000, true, 3000);	
	}
	
	public static void filter(String type) throws IOException{
		type = type.trim().toLowerCase();
		switch (type) {
		case "ip":
			captor.setFilter("ip", true);
			break;
		case "tcp":
			captor.setFilter("tcp", true);
			break;
		case "udp":
			captor.setFilter("udp", true);
			break;
		case "icmp":
			captor.setFilter("icmp", true);
		case "arp":
			captor.setFilter("arp", true);
			break;
		}
	}
	
	 private PacketReceiver packetreceiver=new PacketReceiver(){
         public void receivePacket(Packet packet) {
                if(packet instanceof ARPPacket){
                	ARPPacket arp = (ARPPacket) packet;
                	
                }
                if(packet instanceof IPPacket){
                	IPPacket ip = (IPPacket) packet;
                }
         }
        
  };
  
	public  void startCaptor(){
		if(captor!=null)
			return;
		s=new Thread(new Runnable(){
			public void run() {
				while(true)
					captor.processPacket(1, packetreceiver);
			}	
		});
		
		s.start();

	}
	
	public void stop(){
		captor=null;
	}
}
