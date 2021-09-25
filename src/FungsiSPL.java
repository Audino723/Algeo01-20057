public class FungsiSPL {
    // public static String[] splGauss(Matriks matrix) {

    // }
   
    public static Matriks konversiCoefHasil(Matriks mTemp) {
        //KAMUS
        int i, j, k;
        double ratio;
        Matriks coefHasil = new Matriks(mTemp.col-1, mTemp.col-1);

        //ALGORITMA
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

        //Mengubah ke coefHasil
        coefHasil = konversiCoefHasil(mTemp);
        

        return coefHasil; 
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
    
            //Mengubah ke coefHasil
            coefHasil = konversiCoefHasil(mTemp);
            
    
            return coefHasil; 
        }
    
    public static double[] splMatriksBalikan(Matriks m) {
        // Kamus Lokal
        int i, j;
        double[] B = new double[m.col];
        double[] X = new double[m.col];
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
}
