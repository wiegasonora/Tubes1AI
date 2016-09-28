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
        List<Ruangan> ruang = new ArrayList<>();
        List<Jadwal> jadwal = new ArrayList<>();
        JFileChooser fad = new JFileChooser();
//        File input = new File("../../Testcase.txt");
        File input = new File("/home/rezaramadhan/t1.txt");
//        int rent = fad.showOpenDialog(fad);  
//        input = fad.getSelectedFile();
        
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
       }
       in.close();
    
    
        long startTime = System.nanoTime(); 
        GeneticAlgorithm g = new GeneticAlgorithm(jadwal, ruang);
        String str1 = g.generateRandom();
//        String str1 = "a3hkb3kmb5klc2jla4hid4kla5hib1jk";
        System.out.println(str1);
        String format = String.format("%f", g.hitungPersentasiIsi(str1));
        System.out.println("persentasi "+format);
        System.out.println("fitnessfunction "+ (float)1/g.hitungFitnessFunction(str1));
//        long estimatedTime = System.nanoTime() - startTime;
//        System.out.println(estimatedTime/1000000 + "ms");
    
   
        //Debugging Simmulated Annealing
//        SimulatedAnnealing sa = new SimulatedAnnealing(jadwal, ruang);
//        sa.execute();
//        System.out.println(sa.showSolution());

    }
}
