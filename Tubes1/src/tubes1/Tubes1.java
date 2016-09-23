/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFileChooser;
//import com.csvreader.CsvWriter;
//import com.csvreader.CsvReader;

/**
 *
 * @author Ratna Dira
 */
public class Tubes1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        List<Ruangan> ruang = new ArrayList<Ruangan>();
        List<Jadwal> jadwal = new ArrayList<Jadwal>();
        JFileChooser fad = new JFileChooser();
        File input;
        int rent = fad.showOpenDialog(fad);  
        input = fad.getSelectedFile();
        //String of2="Jadwal.csv";
        //String of1="Ruangan.csv";
        //CsvWriter csvOutput1 = new CsvWriter(new FileWriter(of1, true),';');
        //CsvWriter csvOutput2 = new CsvWriter(new FileWriter(of2, true),' ');
        /*try (BufferedReader br = new BufferedReader(new FileReader(input))) {
        String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
               // process the line.
            }
        }*/
        
        Scanner in = new Scanner(input);
        String nextLine = in.nextLine();
        String[] tes;
        Ruangan B = new Ruangan();
        nextLine = in.nextLine();
        while(!nextLine.equals("")) {
            tes = nextLine.split(";");
            B.Nama=tes[0];
            B.JamMulai=tes[1];
            B.JamSelesai=tes[2];
            B.Hari=tes[3];
            ruang.add(B);
            //csvOutput1.write(nextLine);
            //csvOutput1.endRecord();
            nextLine = in.nextLine();
            B = new Ruangan();
        }
        nextLine = in.nextLine();
        Jadwal A = new Jadwal();
        while(in.hasNext()) {
            nextLine = in.nextLine();
            tes = nextLine.split(";");
            A.NamaKegiatan=tes[0];
            A.Ruangan=tes[1];
            A.JamMulai=tes[2];
            A.JamSelesai=tes[3];
            A.Durasi=tes[4];
            A.Hari=tes[5];
            jadwal.add(A);
            A = new Jadwal();
            //csvOutput2.write(nextLine);
            //csvOutput2.endRecord();   
        }
        in.close();
       /* int i=0;
        while (i<jadwal.size()) {
            System.out.println(jadwal.get(i).NamaKegiatan);
            i++;
        }
        i=0;
        System.out.println("Ruang:");
        while (i<ruang.size()) {
            System.out.println(ruang.get(i).Hari);
            i++;
        }*/ //TESTING ISI LIST
        
        //HILL CLIMBING
        
    }
}
