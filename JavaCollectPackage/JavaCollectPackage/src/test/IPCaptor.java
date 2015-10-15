package test;

import java.io.IOException;



import jpcap.*;
import jpcap.packet.*;

public class IPCaptor {
	
	private static JpcapCaptor captor = null;
	
	public static void open() throws IOException {
		NetworkInterface[] devices = JpcapCaptor.getDeviceList();
		captor = JpcapCaptor.openDevice(devices[0], 2000, true, 3000);	
	}
	
	public static void setFilterType(int type) throws IOException {
		switch (type) {
			case 0:
				captor.setFilter("ip", true);
				break;
			case 1:
				captor.setFilter("tcp", true);
				break;
			case 2:
				captor.setFilter("udp", true);
				break;
			case 3:
				captor.setFilter("icmp", true);
				break;
			}
	}
	
	public static IPPacket cap(){
		IPPacket ip = null;
		while (true) {
			ip = (IPPacket) captor.getPacket();
			if (ip != null) {
				return ip;
			}
		}
	}
	
	static String change(short m) {
		String str;
		switch (m) {
		
		case IPPacket.IPPROTO_TCP:
			str="TCP";
			break;
		case IPPacket.IPPROTO_UDP:
			str="UDP";
			break;
		case IPPacket.IPPROTO_HOPOPT:
			str="HOPOPT";
			break;
		case IPPacket.IPPROTO_ICMP:
			str="ICMP";
			break;
		case IPPacket.IPPROTO_IGMP:
			str="IGMP";
			break;
		case IPPacket.IPPROTO_IP:
			str="IP";
			break;
		case IPPacket.IPPROTO_IPv6:
			str="IPv6";
			break;
		case IPPacket.IPPROTO_IPv6_Frag:
			str="IPv6_Frag";
			break;
		case IPPacket.IPPROTO_IPv6_ICMP:
			str="IPv6_ICMP";
			break;
		case IPPacket.IPPROTO_IPv6_NoNxt:
			str="IPv6_NoNxt";
			break;
		case IPPacket.IPPROTO_IPv6_Opts:
			str="IPv6_Opts";
			break;
		case IPPacket.IPPROTO_IPv6_Route:
			str="IPv6_Route";
			break;
		default:
			str="UNKOWN!";
			break;
		}
		return str;
	}
}
