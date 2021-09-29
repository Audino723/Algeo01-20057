
public class Matriks {
    // Atribut
    public int row;
    public int col;
    public double[][] Mat; // Masih hard coded harus diganti dengan efektif dari matriks
    final int UNVAL_INDEX = -1;
    final int UNVAL_VALUE = -999;

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

    public void undefMatriks(){
        int i, j;

        if (row > UNVAL_INDEX && col > UNVAL_INDEX) {
            this.Mat = new double[this.row][this.col];
            for (i = 0; i < row; i++) {
                for (j = 0; j < col; j++) {
                    this.Mat[i][j] = UNVAL_VALUE;
                }
            }
        }

        this.col = UNVAL_INDEX;
        this.row = UNVAL_INDEX;
    }

    public boolean isMatriksUndef(){

        return (this.col == -1 && this.row == -1);
    }

    public Matriks copyMatriks(){
        //KAMUS
        Matriks mCopy = new Matriks(this.row, this.col);
        int i, j;

        //ALGORITMA
        for (i = 0; i< mCopy.row; i++){
            for (j = 0; j < mCopy.col; j++){
                mCopy.Mat[i][j] = this.Mat[i][j];
            }
        } 

        return mCopy;
    }

    //Perlu dimerge sama punya mikel
    public boolean isRowSPLZero (int i, int jStart, int jMax){
        /*
        Mengembalikan true jika kolom spl pada matrix augmented bernilai nol semua, dengan kata lain tidak memiliki penyelesaian.
        */
            //KAMUS
            boolean flag = true;
            int j;
    
            //ALGORITMA
            for (j = jStart; j < jMax; j ++){
                if (this.Mat[i][j] != 0){
                    flag = false;
                    break;
                }
            }
    
            return flag;
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
        for (i = iStart; i < this.row; i++) {
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
        int i, j, k;
        double ratio;
        Matriks mTemp;
        mTemp = this;

        //ALGORITMA
        for (i = 0; i < mTemp.row; ++i) {
            k = 0;
            if (this.isZeroColExist(i, i)) {
                continue;
            }
            while ((k + i < mTemp.row) && (mTemp.Mat[i + k][i] == 0)) {
                k++;
                if (mTemp.Mat[i + k][i] != 0) {
                    for (j = 0; j < mTemp.col; ++j) {
                        mTemp.Mat[i][j] += mTemp.Mat[i + k][j];
                    }
                    break;
                }

            }

            for (j = i + 1; j < mTemp.row; ++j) {
                ratio = mTemp.Mat[j][i] / mTemp.Mat[i][i];
                for (k = i; k < mTemp.col; ++k) {
                    mTemp.Mat[j][k] -= ratio * mTemp.Mat[i][k];
                }
            }
        }

        return mTemp;
    }

    public Matriks konversiEselonMatriks(){
        //KAMUS
        Matriks mTemp = this.reduksiMatriks();
        int i, j;
        double temp;

        //Mengubah matriks tereduksi menjadi matriks tereduksi eselon
        for (i = 0; i < this.row;++i){
            if (mTemp.Mat[i][i] != 1 && (mTemp.Mat[i][i] != 0)){

                temp = mTemp.Mat[i][i];
                for (j = 0; j < this.col; j ++){
                    mTemp.Mat[i][j] /= temp;
                }
            }
        }

        //Mengecek apakah terdapat solusi penyelesaian
        for (i = 0; i < mTemp.row ; i++){
            if (mTemp.isRowSPLZero(i, 0, mTemp.col-1) && (mTemp.Mat[i][mTemp.col-1] != 0)){
                mTemp.undefMatriks();
                return mTemp;
            }
        }

        return mTemp;
    }

    public Matriks eselonTereduksiMatriks(){
        //KAMUS
        Matriks mTemp = this.konversiEselonMatriks();
        int i, j, k;
        double ratio;

        //ALGORITMA

        for (i = 1; i < mTemp.row ; i ++){
            if (isZeroColExist(0, i) || isRowSPLZero(i, 0, this.col)){
                continue;
            }
            for (j = 0; j < mTemp.row; ++j) {
                if (i!= j){
                    ratio = mTemp.Mat[j][i] / mTemp.Mat[i][i];
                    for (k = i; k < mTemp.col ; k ++){
                        mTemp.Mat[j][k] -= ratio * mTemp.Mat[i][k];
                    }
                }
            }
        }

        return mTemp;
    }

    public static double detReduksiBaris(Matriks m) {
        double det;
        Matriks mTemp;
        mTemp = m.reduksiMatriks();
        det = mTemp.Trace();
        return det;
    }

    public static Matriks ubahKaliBaris(Matriks m, int index, float x) {
        int i;
        for(i = 0; i < m.col; i++) {
            m.Mat[index][i] *= x;
        }
        return m;
    }

    public static Matriks ubahKurangBaris(Matriks m, int index1, int index2, float konstanta) {
        int i;
        for(i = 0; i < m.col; i++) {
            m.Mat[index1][i] *= konstanta;
            m.Mat[index1][i] -= m.Mat[index2][i];
        }
        return m;
    }

     public static Matriks ubahTambahBaris(Matriks m, int index1, int index2) {
        int i;
        for(i = 0; i < m.col; i++) {
            m.Mat[index1][i] += m.Mat[index2][i];
        }
        return m;
    }

    public static Matriks MatriksIdentitas(Matriks m) {
        // I.S. Matriks m tidak memiliki satu baris atau satu kolom yang semuanya terdiri atas 0
        // Kamus Lokal
        int i, j;
        int a;
        float konstanta;
        boolean identity;
        // Algoritma
        identity = true;
        Matriks identitas = new Matriks(m.row,m.col);
        for(i = 0; i < m.row; i++) {
            for(j = 0; j < m.col; j++) {
                if(i == j) {
                    identitas.Mat[i][j] = 1;
                } else {
                    identitas.Mat[i][j] = 0;
                }
            }
        }
        
        for(j = 0; j < m.col; j++) {
            if(m.isZeroRowExist()) {
                identity = false;
                break;
            }
            if(m.Mat[j][j] == 0) {
                a = j;
                while(m.Mat[a][a] == 0) {
                    if(m.Mat[a+1][a] != 0) {
                        ubahTambahBaris(m,a,a+1);
                        ubahTambahBaris(identitas,a,a+1);
                        break;
                    } 
                    a++;
                    if(a == m.col) {
                        a = 0;
                    }
                }
            }
            if(m.Mat[j][j] != 1) {
                konstanta = 1/(float)m.Mat[j][j];
                ubahKaliBaris(m, j, konstanta);
                ubahKaliBaris(identitas,j,konstanta);
            }
            for(i = j+1; i < m.row; i++) {
                if(m.Mat[i][j] != 0) {
                    konstanta = (float)m.Mat[j][j]/(float)m.Mat[i][j];
                    ubahKurangBaris(m, i, j, konstanta);
                    ubahKurangBaris(identitas,i,j,konstanta);
                    if(m.isZeroColExist(0,j) || m.isZeroRowExist()) {
                        identity = false;
                        break;
                    }
                }
                if(!identity) {
                    break;
                }
            }
        }
        if(identity) {
            for(j = m.col-1; j >= 0; j--) {
            if(m.isZeroRowExist()) {
                identity = false;
                break;
            }
            if(m.Mat[j][j] == 0) {
                a = j;
                while(m.Mat[a][a] == 0) {
                    if(m.Mat[a+1][a] != 0) {
                        ubahTambahBaris(m,a,a+1);
                        ubahTambahBaris(identitas,a,a+1);
                        break;
                    } 
                    a++;
                    if(a == m.col) {
                        a = 0;
                    }
                }
            }
            if(m.Mat[j][j] != 1) {
                konstanta = 1/(float)m.Mat[j][j];
                ubahKaliBaris(m, j, konstanta);
                ubahKaliBaris(identitas,j,konstanta);
            }
            for(i = j-1; i >= 0; i--) {
                if(m.Mat[i][j] != 0) {
                    konstanta = (float)m.Mat[j][j]/(float)m.Mat[i][j];
                    ubahKurangBaris(m, i, j, konstanta);
                    ubahKurangBaris(identitas,i,j,konstanta);
                    if(m.isZeroColExist(0,j) || m.isZeroRowExist()) {
                        identity = false;
                        break;
                    }
                }
                if(!identity) {
                    break;
                }
            }
        }
            for(i = 0; i < m.col;i++) {
                for(j=0;j<m.row;j++) {
                    identitas.Mat[i][j] = Math.round((identitas.Mat[i][j] * 100)) / 100.0;
                }
            }
            return identitas;
        } else {
            identitas.undefMatriks();
            return identitas;
        }
        
        
    }
    
    public static double detKofaktor(Matriks m) {
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
                    det += -1 * m.Mat[i][0] * detKofaktor(mtemp);
                } else {
                    det += m.Mat[i][0] * detKofaktor(mtemp);
                }
            }
            return det;
        }
    }

    public static Matriks matrixKofaktor(Matriks matrix) {
        Matriks mRes, mTemp;
        int p, q, m, n, i, j;
        int dimensi = matrix.row;

        mRes = new Matriks(dimensi, dimensi);
        mTemp = new Matriks(dimensi - 1, dimensi - 1);

        for (q = 0; q < dimensi; q++) {
            for (p = 0; p < dimensi; p++) {
                m = 0;
                n = 0;
                for (i = 0; i < dimensi; i++) {
                    for (j = 0; j < dimensi; j++) {
                        if (i != q && j != p) {
                            mTemp.Mat[m][n] = matrix.Mat[i][j];
                            if (n < (dimensi - 2))
                                n++;
                            else {
                                n = 0;
                                m++;
                            }
                        }
                    }
                }
                mRes.Mat[q][p] = Math.pow(-1, q + p) * detKofaktor(mTemp);
            }
        }
        return mRes;
    }

    public static Matriks balikanAdjoin(Matriks matrix) {
        // Kamus Lokal
        Matriks mRes, mAdjoin;
        int dimensi = matrix.row, i, j;
        double det;
        
        mRes = new Matriks(dimensi, dimensi);
        mAdjoin = new Matriks(dimensi,dimensi);
        det = detKofaktor(matrix);

        if (det == 0) {

        } else {
            if (dimensi == 2) {
                mRes.Mat[0][0] = (1 / det) * matrix.Mat[1][1];
                mRes.Mat[0][1] = (-1 / det) * matrix.Mat[0][1];
                mRes.Mat[1][0] = (-1 / det) * matrix.Mat[1][0];
                mRes.Mat[1][1] = (1 / det) * matrix.Mat[0][0];
            } else{
                mAdjoin = matrixKofaktor(matrix).Transpose();
                for (i = 0; i < dimensi; i++) {
                    for (j=0; j< dimensi;j++) {
                        mRes.Mat[i][j] = (1 / det) * mAdjoin.Mat[i][j];
                    }
                }
            }
        }
        return mRes;

    }

}