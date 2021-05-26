package udpreaderbinary;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPreaderBinary {

    public static void main(String[] args) throws SocketException, IOException {
        send(127, 16, 32, 144, (127+16+32+144)%256);
        //receive();
    }
    
    private static void send(int p, int v1, int v2, int f, int crc) throws SocketException, IOException{
        byte[] buf = new byte[]{(byte)240, (byte)15, (byte)p, (byte)v1, (byte)v2, (byte)f, (byte)2565, (byte)crc};
        DatagramSocket socket = new DatagramSocket();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, InetAddress.getByName("192.168.1.255"), 42421);
        socket.send(packet);
    }
    
    private static void receive() throws SocketException, IOException{
        byte[] buf = new byte[256];
        DatagramSocket socket = new DatagramSocket(42421);
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        
        while(true){
            socket.receive(packet);
            System.out.print("Recibido: ");
            for (int i = 0; i < packet.getLength(); i++) {
                System.out.print(Byte.toUnsignedInt(packet.getData()[i])+" ");
            }
            System.out.println();
        }
    }

}
