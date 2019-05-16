package dns;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.io.IOException;
import java.lang.String;

/** Class to send a message
 * @author texierl
 *
 */
public class SendUDP {
	public static void main(String[] args){
		DatagramPacket p;
		DatagramSocket s = null;
		InetAddress dst = null;
		try {
			dst = InetAddress.getByName(args[0]);
		} catch (UnknownHostException e) {
			System.out.println("Impossible de se connecter à l'adresse");
			System.exit(0);
		}
		int port = Integer.parseInt(args[1]);
		String message = args[2];
		byte array[] = message.getBytes();
		p = new DatagramPacket(array,  array.length, dst, port);
		try {
			s = new DatagramSocket();
		} catch (SocketException e) {
			System.out.println("Impossible de créer le DatagramSocket");
			System.exit(0);
		}
		try {
			s.send(p);
		} catch (IOException e) {
			System.out.println("Impossible d'envoyer le paquet");
			System.exit(0);
		}
		s.close();
	}
}
