/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes1;

/**
 *
 * @author rezaramadhan
 */
public class MatrixJadwal {
    public String[][] elmt;
    public int nHari;
    public int nJam;
    public MatrixJadwal() {
        nHari = 5;
        nJam = 11;
        elmt = new String[nJam][nHari];
        for (int i = 0; i < nJam; i++) {
            for (int j = 0; j < nHari; j++) {
                elmt[i][j] = "";
            }
        }
    }

    @Override
    public String toString() {
        String out = "";
        for (int i = 0; i < nJam; i++) {
            for (int j = 0; j < nHari; j++) {
                if (elmt[i][j] == "") {
                    out += "      " + "   |   ";
                } else if (elmt[i][j] == "X") {
                    out += "  " + elmt[i][j] + "   " + "   |   ";
                } else {
                    out += elmt[i][j] + "   |   ";
                }
            }
            out += "\n";
        }
        out += "\n\n\n";
        return out;
    }
}
