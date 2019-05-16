package dns;

import java.net.DatagramPacket;
import java.net.SocketException;
import java.net.DatagramSocket;
import java.io.IOException;
import java.lang.String;

/** Class to receive the messages
 * @author texierl
 *
 */
public class ReceiveUDP {
	public static void main(String[] args){
		DatagramPacket p;
		DatagramSocket s = null;
		int port = Integer.parseInt(args[0]);
		try {
			s = new DatagramSocket(port);
		} catch (SocketException e) {
			System.out.println("Impossible de créer le DatagramSocket");
			System.exit(0);
		}
		p = new DatagramPacket(new byte[2048], 2048);
		try {
			s.receive(p);
		} catch (IOException e) {
			System.out.println("Impossible de recevoir le paquet");
			System.exit(0);
		}
		System.out.println("paquet reçu de :"+p.getAddress()+", port "+p.getPort()+", taille "+p.getLength());
		byte array[] = p.getData();
		System.out.println(new String(array));
		s.close();
	}
}