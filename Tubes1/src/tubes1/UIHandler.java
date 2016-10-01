/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes1;

import java.awt.*;
import java.util.List;
import javax.swing.*;
/**
 *
 * @author rezaramadhan
 */
public class UIHandler  extends JFrame {
    private List<MatrixJadwal> l;
    private JLabel[][] tabel;
    private final int nHari = 5;
    private final int nJam = 11;
    
    public UIHandler() throws InterruptedException {
        super("tes");
        
        JPanel headContainer = new JPanel();
        JPanel tableContainer = new JPanel();
        JLabel title = new JLabel("Appa Yipyip Scheduler");
        
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 32));
        this.setLayout(new BorderLayout());
        getContentPane().add(headContainer, BorderLayout.PAGE_START);
        getContentPane().add(tableContainer, BorderLayout.PAGE_END);
        
        JPanel head = new JPanel();
        
        //add title
        headContainer.add(title);
        headContainer.add(head);
        
        //add opsi2 gitu
        head.setLayout(new FlowLayout());
        JPanel leftHead = new JPanel();
        JPanel rightHead = new JPanel();
        head.add(leftHead);
        head.add(rightHead);
        
        
        //add pilihan algoritma
        JLabel algo = new JLabel("Pilihan Algoritma");
        JRadioButton HCRButton = new JRadioButton("Hill Climbing");
        JRadioButton SARButton = new JRadioButton("Simmulated Annealing");
        JRadioButton GARButton = new JRadioButton("Genetic Algorithm");
        Button runButton = new Button("Run!");
        
        ButtonGroup group = new ButtonGroup();
        group.add(HCRButton);
        group.add(SARButton);
        group.add(GARButton);
        
        rightHead.setLayout(new BoxLayout(rightHead, BoxLayout.PAGE_AXIS));
        rightHead.add(algo);
        rightHead.add(HCRButton);
        rightHead.add(SARButton);
        rightHead.add(GARButton);
        rightHead.add(runButton);
        
        //add container ruangan & file chooser
        JPanel chooseRuangContainer = new JPanel();
        JPanel chooseFileContainer = new JPanel();
        leftHead.add(chooseRuangContainer);
        leftHead.add(chooseFileContainer);
        
        chooseRuangContainer.setLayout(new BoxLayout(chooseRuangContainer, BoxLayout.PAGE_AXIS));
        chooseFileContainer.setLayout(new BoxLayout(chooseFileContainer, BoxLayout.PAGE_AXIS));
        
        //add pilihan ruangan
        JLabel ruangLabel = new JLabel("Ruangan:");
        
        chooseRuangContainer.add(ruangLabel);
        
        
        //add pilihan file
        Button fileButton = new Button("FileChooser");
        chooseFileContainer.add(fileButton);
                
        //add table
        tabel = new JLabel[nJam+1][nHari+1];
        tableContainer.setLayout(new GridLayout(nJam+1, nHari+1));
        for (int i = 0; i<nJam+1; i++) {
            for (int k = 0; k < nHari+1; k++) {
                tabel[i][k] = new JLabel("testabel");
                tabel[i][k].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tableContainer.add(tabel[i][k]);
            }
        }
        for (int i = 1; i < nJam+1; i++) {
            tabel[i][0].setText(Integer.toString(i+6));
        }
        tabel[0][0].setText("Jam\\Hari");
        tabel[0][1].setText("Senin");
        tabel[0][2].setText("Selasa");
        tabel[0][3].setText("Rabu");
        tabel[0][4].setText("Kamis");
        tabel[0][5].setText("Jumat");
        
        
        
//        JLabel j = new JLabel("HelloWorld");
//        JLabel j1 = new JLabel("tesbaru");
//        JLabel j2 = new JLabel("cobacobacuy");
//        JPanel tabelContainer = new JPanel();
//        tabelContainer.setLayout(new GridLayout(nHari, nJam));
//        tabelContainer.setBorder(BorderFactory.createLineBorder(Color.black));
        //this.setLayout(new BorderLayout());
//        getContentPane().add(j, BorderLayout.PAGE_START);
//        getContentPane().add(j1);
//        getContentPane().add(j2,BorderLayout.PAGE_END);
//        getContentPane().add(tabelContainer);
//        
//        tabel = new JLabel[nHari][nJam];
//        for (int i = 0; i<nHari; i++) {
//            for (int k = 0; k < nJam; k++) {
//                tabel[i][k] = new JLabel("testabel");
//                tabel[i][k].setBorder(BorderFactory.createLineBorder(Color.BLUE));
//                tabelContainer.add(tabel[i][k]);
////                tabel[i][j].setText("aaa");
////                System.out.println("sds");
////                createLayout(x + i*100, y + j*100, tabel[i][j]);
////                tabel[i][j].setAlignmentX(100);
//            }
//        }
//        for (int i = 0; i<nHari; i++) {
//            for (int k = 0; k < nJam; k++) {
//                tabel[i][k].setText("heyy");
//                
////                tabel[i][j].setText("aaa");
////                System.out.println("sds");
////                createLayout(x + i*100, y + j*100, tabel[i][j]);
////                tabel[i][j].setAlignmentX(100);
//            }
//        }
//        
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        pack();
//        setVisible(true);
        
//        tabel = new JLabel[nHari][nJam];
//        initComponents();
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          pack();
          setVisible(true);
    }
    
    private void initComponents() {
        int x = 0;
        int y = 0;
         
       
        for (int i = 0; i<nHari; i++) {
            for (int j = 0; j < nJam; j++) {
                tabel[i][j] = new JLabel();
//                tabel[i][j].setText("aaa");
//                System.out.println("sds");
//                createLayout(x + i*100, y + j*100, tabel[i][j]);
//                tabel[i][j].setAlignmentX(100);
            }
        }
        
        
        
        
        
        JLabel j = new JLabel();
        j.setText("cobacoba");
        createLayout(50, 10, j);
        
        
        
        JLabel j1 = new JLabel();
        j1.setText("cobaaa");
        createLayout(55, 10, j1);
        
        setVisible(true);
        
//        setTitle("Simple example");
//        setSize(1000, 600);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
//        j.setBounds(100, 200, WIDTH, HEIGHT);
        
        
//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//
//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
//        getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addContainerGap()
//                .addContainerGap(1000, Short.MAX_VALUE))
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addContainerGap()
//                .addContainerGap(600, Short.MAX_VALUE))
//        );
//
//        pack();
    }
    
     private void createLayout(int x, int y, JComponent... arg) {

        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(x)
                .addComponent(arg[0])
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(y)
                .addComponent(arg[0])
        );
    }
    
//    public void display() {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new UIHandler().setVisible(true);
//            }
//        });
//    }
    
}
