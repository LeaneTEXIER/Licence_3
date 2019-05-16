package dns;

import java.net.*;

public class DNS {
	public static void main(String[] args) {
		String addr = args[0];
		String dest = args[1];
		byte[] messageEmis = createRequestDNS(addr);
		InetAddress destination = getInetAdress(dest);
		DatagramSocket ds = sendRequestDNS(messageEmis,destination);
		byte[] messageReceive = receiveRequestDNS(ds);
		analyse(messageReceive, 18 + addr.length());
	}
	
	private static byte[] createRequestDNS(String addr) {
		//Taille du tableau de byte à envoyer
		int l = 18 + addr.length();
		//Initialisation tableau de byte suivant la taille de la requete
		byte[] rep = new byte[l];
		//Remplissage du tableau
		//Non relatif à l'adresse
		rep[0] = (byte) 0x08;
		rep[1] = (byte) 0xbb;
		rep[2] = (byte) 0x01;
		rep[3] = (byte) 0x00;
		rep[4] = (byte) 0x00;
		rep[5] = (byte) 0x01;
		for (int i=6; i<12; i++){
			rep[i] = (byte) 0x00;
		}
		rep[l-5] = (byte) 0x00;
		rep[l-4] = (byte) 0x00;
		rep[l-3] = (byte) 0x01;
		rep[l-2] = (byte) 0x00;
		rep[l-1] = (byte) 0x01;
		//Relatif à l'adresse
		String[] addrSplit = addr.split("\\.");
		int i=12;
		for (String s: addrSplit){
			rep[i++] = (byte) s.length();
			char[] strToChar = s.toCharArray();
			for (char c: strToChar){
				rep[i++] = (byte) c;
			}
		}
		return rep;
	}
	
	private static InetAddress getInetAdress(String dest) {
		System.err.print(" get inetaddress by name ... ");
		InetAddress destination;
		try {
		    destination = InetAddress.getByName(dest);
		} catch (Exception e) {
		    System.err.println("[error] :" +  e.getMessage());
		    return null;
		}
		System.err.println("[ok]");
		return destination;
	}
	
	private static DatagramSocket sendRequestDNS(byte[] messageEmis, InetAddress dest) {
		//Creation d'un DatagramPacket pour l'envoi de la question DNS
		System.err.println(" preparing  datagrampacket, message size : "+messageEmis.length);
		DatagramPacket dp = new DatagramPacket(messageEmis,messageEmis.length,dest,53);
		//Creation d'un DatragramSocket (port au choix)
		System.err.print(" create datagram socket  ... ");
		DatagramSocket ds;
		try {
			ds = new DatagramSocket() ;
		} catch (Exception e) {
			System.err.println("[error] :" +  e.getMessage());
			return null;
		}
		System.err.println("[ok]");
		// Envoi du packet
		System.err.print(" send datagram ... ");
		try {
		    ds.send(dp);
		} catch (Exception e) {
		    System.err.println("[error] :" +  e.getMessage());
		    return null;
		}
		System.err.println("[ok]");		
		return ds;
	}
	
	private static byte[] receiveRequestDNS(DatagramSocket ds){
		//Reception du paquet
		DatagramPacket dp = new DatagramPacket(new byte[512],512);
		System.err.print(" receive datagram ... ");
		try {
		    ds.receive(dp);
		} catch (Exception e) {
		    System.err.println("[error] :" +  e.getMessage());
		    return null;
		}
		System.err.println("[ok]");
		byte[] recu = dp.getData();
		//Affichage
		System.out.println("- message length : " + dp.getLength());
	    System.out.println("- message : \n" + new String(recu, 0, dp.getLength()));
	    //Affichage
		for(int i = 0; i < dp.getLength(); i++) {
		    System.out.print(","+Integer.toHexString((recu[i])&0xff));
		    if ((i+1)%16 == 0)
			System.out.println("");
		}
		System.out.println("");	
		return recu;
	}
	
	private static void analyse(byte[] messageReceive, int size) {
		//Pour récuperer le nombre d'adresses IPv4
		int nb = messageReceive[6];
		nb += messageReceive[7];
		//Pour passer l'entete (de 12) - les 2 derniers nécessaires pour avoir la taille de la réponse
		int i = size+10;
		//Taille de l'adresse IP
		int l = messageReceive[i++] & 0xff;
		l += messageReceive[i++] & 0xff;
		System.out.println("");	
		System.out.println("Adresses IPv4 disponibles: ");
		for (int k=0; k<nb; k++){
			for (int j=0; j<l-1; j++){
				System.out.print(Integer.valueOf(Integer.toHexString(messageReceive[i++]&0xff),16)+".");
			}
	        System.out.println(Integer.valueOf(Integer.toHexString(messageReceive[i++]&0xff),16));
		i+=10;
		l = messageReceive[i++] & 0xff;
		l += messageReceive[i++] & 0xff;
		}
		
	}

}
