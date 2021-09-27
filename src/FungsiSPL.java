public class FungsiSPL {
    // public static String[] splGauss(Matriks matrix) {

    // }
   
    public static Matriks splGauss(Matriks matrix) {
    /*
        Akan menampilkan solusi dari splGauss
        Sebelum perhitungan, akan diinisialisasi sebuah matriks 1 x (column-1), yang akan menampung koefisien hasil perhitungan.
    */
        //KAMUS
        Matriks mTemp = matrix.reduksiMatriks();
        int i, j, k;
        double temp, ratio;
        Matriks coefHasil = new Matriks(matrix.col-1, matrix.col-1);

        //Inisialisasi coefHasil

        //ALGORITMA
        //Mengubah matriks tereduksi menjadi matriks tereduksi eselon
        for (i = 0; i < matrix.row;++i){
            if (mTemp.Mat[i][i] != 1 && (mTemp.Mat[i][i] != 0)){

                temp = mTemp.Mat[i][i];
                for (j = 0; j < matrix.col; j ++){
                    mTemp.Mat[i][j] /= temp;
                }
            }
        }

        //Mengecek apakah terdapat solusi penyelesaian
        for (i = 0; i < mTemp.row ; i++){
            if (mTemp.isRowSPLZero(i, 0, mTemp.col-1) && (mTemp.Mat[i][mTemp.col-1] != 0)){
                System.out.println("SPL Tidak memiliki penyelesaian");
                return mTemp;
            }
        }

        //Mengubah ke dalam matrix coefHasil
        for (i = 0; i < mTemp.row ; i ++ ){
            coefHasil.Mat[i][0] = mTemp.Mat[i][mTemp.col-1]; 
            for (j = i+1 ; j < mTemp.col-1 ; j++){
                coefHasil.Mat[i][j] = (-1) * (mTemp.Mat[i][j]);
            }
        }

        //Mencari hasil penyelesaian
        for (i = mTemp.row-1; i>=0; i--){
            for (j = coefHasil.col-1 ; j>0; j--){
                ratio = coefHasil.Mat[i][j];
                for (k = coefHasil.col-1 ; k >= 0; k--){ 
                    coefHasil.Mat[i][k] +=  ratio * coefHasil.Mat[j][k];
                }
                if ((coefHasil.Mat[j][0] != 0) && (coefHasil.isRowSPLZero(j, 1, coefHasil.col-1))){
                    coefHasil.Mat[i][j] = 0;
                }
            }
        }

        return coefHasil; 
    }

    public static void splMatriksBalikan(Matriks m) {
        // Kamus Lokal
        int i, j;
        double[] B = new double[m.row];
        double[] X = new double[m.row];
        // Algoritma
        B = InOut.bacaTerminalMatrixBalikan(m);
        if(m.col != m.row) {
            System.out.println("Tidak ada solusi SPL untuk matriks ini sebab tidak memiliki ukuran n x n");
        } else {
            m = Matriks.MatriksIdentitas(m);

            for(i = 0; i < m.row; i++) {
                X[i] = 0;
                for(j = 0; j < m.col; j++) {
                    X[i] += B[j] * m.Mat[i][j];
                }
                X[i] = Math.round((X[i]));
            }
            InOut.tulisPenyelesaianSPLBalikan(B);
        }
    }

    public static double[] splCramer(Matriks m) {
        // Kamus Lokal
        int i, j, k;
        int itemp, jtemp;
        double []B = new double[m.row];
        Matriks mtemp, mawal;
        // Algoritma
        mawal = new Matriks(m.row,m.row);
        for(i = 0; i < m.row; i++) {
            for(j = 0; j < m.col-1;j++) {
                mawal.Mat[i][j] = m.Mat[i][j];
            }
        }

        for(i = 0; i < m.row; i++) {
            itemp = 0;
            jtemp = 0;
            mtemp = new Matriks(m.row,m.row);
            for(j = 0; j < m.row;j++) {
                jtemp = 0;
                for(k = 0;k < m.col-1;k++) {
                    if(i != k) {
                        mtemp.Mat[itemp][jtemp] = m.Mat[j][k]; 
                        jtemp++;
                    } else {
                        mtemp.Mat[itemp][jtemp] = m.Mat[j][m.col-1];
                        jtemp++;
                    }
                }
                itemp++;
            }

            B[i] = (float)Matriks.detKofaktor(mtemp) / (float)Matriks.detKofaktor(mawal);
            B[i] = Math.round((B[i] * 100)) / 100.0;
        }
        return B;
    }
}
