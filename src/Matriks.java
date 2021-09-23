public class Matriks {
    // Atribut
    public int row;
    public int col;
    public double[][] Mat; // Masih hard coded harus diganti dengan efektif dari matriks
    final int UNVAL_INDEX = -1;

    // Method:
    Matriks(int row, int col) {
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

    public boolean isZeroRowExist() {
        int i, j;
        boolean isZero;
        isZero = false;
        for (i = 0; i < this.row; i++) {
            isZero = true;
            for (j = 0; j < this.col; j++) {
                if (this.Mat[i][j] != 0) {
                    isZero = false;
                }
            }
            if (isZero) {
                break;
            }
        }
        return isZero;
    }

    public boolean isZeroColExist(int iStart, int jStart) {
        int i;
        boolean isZero;
        isZero = true;
        for (i = iStart; i < this.col; i++) {
            if (this.Mat[i][jStart] != 0) {
                isZero = false;
                break;
            }
        }
        return isZero;

    }

    public Matriks Transpose() {
        Matriks m2 = new Matriks(this.col, this.row);

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                m2.Mat[j][i] = this.Mat[i][j];
            }
        }

        return m2;
    }

    public double Trace() {
        /*
         * Asumsi : Matriks adalah matriks persegi
         */

        double hasilTrace = 1;

        for (int i = 0; i < Mat.length; i++) {
            hasilTrace *= this.Mat[i][i];
        }

        return hasilTrace;
    }

    public Matriks reduksiMatriks(){
        //KAMUS

        //ALGORITMA
        int i, j, k;
        double ratio;
        Matriks mTemp;
        mTemp = this;

        InOut io = new InOut();
        io.tulisTerminalMatrix(mTemp);

        for (i = 0; i < mTemp.row; ++i) {
            k = 0;
            if (isZeroColExist(i, i)){
                continue;
            }

            while ((k+i < mTemp.row) && (mTemp.Mat[i+k][i] == 0)){
                k++;
                if (mTemp.Mat[i+k][i] != 0 ){
                    for (j = 0; j< mTemp.col; ++j){
                        mTemp.Mat[i][j] += mTemp.Mat[i+k][j];
                    }
                    break;
                }

            }

            for (j = i + 1; j < mTemp.row; ++j) {
                System.out.println(i + " " + j);
                ratio = mTemp.Mat[j][i] / mTemp.Mat[i][i];
                for (k = i; k < mTemp.col; ++k) {
                    mTemp.Mat[j][k] -= ratio * mTemp.Mat[i][k];
                }
            }
        }

        return mTemp;
    }
    
    public double detReduksiBaris() { // MASIH SALAH MASIH BUINGUNG KALO DIAGONALNYA NOL
        double det;
        Matriks mTemp;
        mTemp = this.reduksiMatriks();
        det = mTemp.Trace();
        return det;
    }
    /* Dimas
    public double detReduksiBaris() { // MASIH SALAH MASIH BUINGUNG KALO DIAGONALNYA NOL
        int i, j, k;
        double ratio, det;
        Matriks mTemp;
        mTemp = this;

        if (mTemp.isZeroColExist(0,0) || mTemp.isZeroRowExist()) {
            det = 0.0;
        } else {
            for (i = 0; i < this.row - 1; i++) {
                if (mTemp.Mat[i][i] == 0.0) {
                    for (k = i + 1; k < mTemp.row; k++) {
                        if (mTemp.Mat[k][i] != 0){
                            for (j=i;j<mTemp.col;j++){
                                mTemp.Mat[i][j] += mTemp.Mat[k][j];
                            }
                        }
                        if (k == mTemp.row-1 && mTemp.Mat[i][i] == 0){
                            k = 0;
                        }
                    }
                } else {
                    for (j = i + 1; j < this.row; j++) {
                        ratio = mTemp.Mat[j][i] / mTemp.Mat[i][i];

                        for (k = 0; k < this.col; k++) {
                            mTemp.Mat[j][k] = mTemp.Mat[j][k] - ratio * mTemp.Mat[i][k];
                        }
                    }
                }

            }
            det = mTemp.Trace();
        }

        return det;
    }
    */
    public static double Kofaktor(Matriks m) {
        // Kamus Lokal
        int i, j, k;
        int kolom, baris;
        int itemp, jtemp;
        double det = 0;
        // Algoritma
        baris = m.row;
        kolom = m.col;
        if (baris == 1) {
            return m.Mat[0][0];
        } else {
            if (baris == 2) {
                return m.Mat[0][0] * m.Mat[1][1] - m.Mat[0][1] * m.Mat[1][0];
            }
            for (i = 0; i < baris; i++) {
                Matriks mtemp = new Matriks(baris - 1, kolom - 1);
                itemp = 0;
                jtemp = 0;
                for (j = 1; j < baris; j++) {
                    for (k = 0; k < kolom; k++) {
                        if (k != i) {
                            mtemp.Mat[itemp][jtemp] = m.Mat[k][j];
                            itemp += 1;
                        }
                    }
                    jtemp += 1;
                    itemp = 0;
                }
                if (i % 2 == 1) {
                    det += -1 * m.Mat[i][0] * Kofaktor(mtemp);
                } else {
                    det += m.Mat[i][0] * Kofaktor(mtemp);
                }
            }
            return det;
        }
    }

}