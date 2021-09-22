
public class Matriks {
    // Atribut
    public int row;
    public int col;
    public double[][] Mat; // Masih hard coded harus diganti dengan efektif dari matriks
    final int UNVAL_INDEX = -1;

    // Method:
    Matriks(int row,int col) {
        this.col = col;
        this.row = row;
        
        int i, j;
        
        if (row > UNVAL_INDEX && col > UNVAL_INDEX) {
            this.Mat = new double[this.row][this.col];
            for (i = 0; i < row; i++) {
                for (j = 0; j < col; j++) {
                    this.Mat[i][j] = 0;
                }
            }
        }
    }

    public Matriks Transpose(){
        Matriks m2 = new Matriks(this.col, this.row);
        
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                m2.Mat[j][i] = this.Mat[i][j];
            }            
        }

        return m2;
    }

    public float Trace(){
        /*
        Asumsi : Matriks adalah matriks persegi
        */
    
        float hasilTrace = 1;

        for (int i = 0; i < Mat.length; i++) {
            hasilTrace *= this.Mat[i][i];
        }

        return hasilTrace;
    }
  
    public static double Kofaktor(Matriks m){
        // Kamus Lokal
        int i,j,k;
        int kolom, baris;
        int itemp,jtemp;
        double det = 0;
        // Algoritma
        baris = m.row;
        kolom = m.col;
        if(baris == 1) {
            return m.Mat[0][0];
        } else {
            if(baris == 2) {
                return m.Mat[0][0] * m.Mat[1][1] - m.Mat[0][1] * m.Mat[1][0];
            }
            for(i = 0;i < baris;i++) {
                Matriks mtemp = new Matriks(baris-1,kolom-1);
                itemp = 0;
                jtemp = 0;
                for(j = 1;j < baris;j++) {
                    for(k = 0;k < kolom;k++) {
                        if(k != i) {
                            mtemp.Mat[itemp][jtemp] = m.Mat[k][j];
                            itemp += 1;
                        }
                    }
                    jtemp += 1;
                    itemp = 0;
                }
                if(i % 2 == 1) {
                    det += -1 * m.Mat[i][0] * Kofaktor(mtemp);
                } else {
                    det += m.Mat[i][0] * Kofaktor(mtemp);
                }
            }
            return det;
        }
    }



}