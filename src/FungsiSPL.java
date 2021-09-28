public class FungsiSPL {
    // public static String[] splGauss(Matriks matrix) {

    // }
    


    public static Matriks konversiCoefHasil(Matriks mTemp) {
        //KAMUS
        int i, j, k;
        double ratio;
        Matriks coefHasil = new Matriks(mTemp.col-1, mTemp.col-1);

        //ALGORITMA

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

        for (i=0; i<coefHasil.col; i++){
            //Menjumlahkan penyelesaian dengan solusiunik
            for (j = 1 ; j < coefHasil.col; j++){
                coefHasil.Mat[i][0] += coefHasil.Mat[i][j] * coefHasil.Mat[j][0];
            }
        }

        return coefHasil;
    }


    public static Matriks splGauss(Matriks matrix) {
    /*
        Akan menampilkan solusi dari splGauss
        Sebelum perhitungan, akan diinisialisasi sebuah matriks 1 x (column-1), yang akan menampung koefisien hasil perhitungan.
    */
        //KAMUS
        Matriks mTemp = matrix.reduksiMatriks();
        Matriks coefHasil;

        //Inisialisasi coefHasil

        //ALGORITMA
        //Mengubah matriks tereduksi menjadi matriks tereduksi eselon
        mTemp = mTemp.konversiEselonMatriks();

        if (!mTemp.isMatriksUndef()){
            //Mengubah ke coefHasil
            coefHasil = konversiCoefHasil(mTemp);
            return coefHasil; 
        } else{
            return mTemp;
        }

    }
    
    public static Matriks splGaussJordan(Matriks matrix) {
        /*
            Akan menampilkan solusi dari splGauss
            Sebelum perhitungan, akan diinisialisasi sebuah matriks 1 x (column-1), yang akan menampung koefisien hasil perhitungan.
        */
            //KAMUS
            Matriks mTemp = matrix.reduksiMatriks();
            Matriks coefHasil;
    
            //Inisialisasi coefHasil
    
            //ALGORITMA
            //Mengubah matriks tereduksi menjadi matriks tereduksi eselon
            mTemp = mTemp.eselonTereduksiMatriks();

            //Mengecek apakah undef
            if (!mTemp.isMatriksUndef()){
                //Mengubah ke coefHasil
                coefHasil = konversiCoefHasil(mTemp);
                return coefHasil; 
            } else{
                return mTemp;
            }
            
    
    }
    
    public static double[] splMatriksBalikan(Matriks m) {
        // Kamus Lokal
        int i, j;
        double[] B = new double[m.row];
        double[] X = new double[m.row];
        // Algoritma
        B = InOut.bacaTerminalMatrixBalikan(m);
        for(i = 0; i < m.row; i++) {
            X[i] = 0;
            for(j = 0; j < m.col; j++) {
                X[i] += B[j] * m.Mat[i][j];
            }
            X[i] = Math.round((X[i]));
        }
        return X;
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
