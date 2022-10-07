
import java.io.*;

class App {
    public static void main(String[] args) throws Exception {

    String str = "";
    str = "Il était une fois   ";


    File ancien = new File("test.txt");
    ancien.delete();

    RandomAccessFile fichier = new RandomAccessFile("test.txt", "rw");

    fichier.writeUTF(str);
    fichier.seek(0);
    System.out.println("readUTF: " + fichier.readUTF() + "!");

    fichier.writeBytes(str);
    fichier.seek(2);
    String reconstruction1 = "";
    for (int i = 0; i < 20; i++) {
        reconstruction1 += (char)fichier.readByte();
    }
    System.out.println("reconstruction1: " + reconstruction1 + "!");

    // str = "Il était une fois  a";
    // fichier.seek(0);
    // fichier.writeUTF(str);
    // fichier.seek(0);
    // System.out.print("fichier.readUTF(): " + fichier.readUTF() + "\n");

    str = "Il était une fois  ÉÉÉÉÉÉÉÉÉÉÉÉ";
    fichier.seek(0);
    fichier.writeBytes(String.format("%1$-20.20s", str));
    fichier.seek(0);
    String reconstruction2 = "";
    for (int i = 0; i < 20; i++) {
        reconstruction2 += (char)fichier.readByte();
    }
    System.out.println("reconstruction2: " + reconstruction2 + "!");
    // System.out.print("fichier.readUTF(): " + fichier.readUTF() + "\n");

    fichier.close();

    }
}