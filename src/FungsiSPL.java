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
            Matriks mTemp = matrix.copyMatriks();
            Matriks coefHasil;
    
            //Inisialisasi coefHasil
    
            //ALGORITMA
            //Mengubah matriks tereduksi menjadi matriks tereduksi eselon
            mTemp = mTemp.reduksiMatriks();
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
    
    public static void splMatriksBalikan(String fileName, Matriks m) {
        // Kamus Lokal
        int i, j;
        double[] B = new double[m.row];
        double[] X = new double[m.row];
        Matriks mvalue;
        Matriks mtemp;
        InOut io = new InOut();
        mtemp = new Matriks(m.row,m.col);

        // Algoritma
        mvalue = new Matriks(m.row,m.col-1);

        for(i = 0; i < m.row;i++) {
            for(j=0; j < m.col-1;j++) {
                mvalue.Mat[i][j] = m.Mat[i][j];
            }
        }
        for(i = 0; i < m.row; i++) {
            B[i] = m.Mat[i][m.col-1];
        }
        if(m.col - 1 != m.row) {
            System.out.println("Tidak ada solusi SPL untuk matriks ini sebab tidak memiliki ukuran n x n");
        } else {
            mtemp = Matriks.MatriksIdentitas(mvalue);
            if(mtemp.isMatriksUndef()) {
                System.out.println("Tidak ada solusi SPL untuk matriks ini");
            } else {
                for(i = 0; i < m.row; i++) {
                    X[i] = 0;
                    for(j = 0; j < m.col - 1; j++) {
                        X[i] += B[j] * mtemp.Mat[i][j];
                    }
                    // X[i] = Math.round(X[i] * 100) / 100.0;
                    X[i] = (double) Math.round(X[i]);
                    System.out.println(X[i]);
                }
                io.tulisPenyelesaianSPLNotAugmented(fileName, X);
            }
        }
    }

    public static void splCramer(String fileName, Matriks m) {
        // Kamus Lokal
        int i, j, k;
        int itemp, jtemp;
        double []B = new double[m.row];
        boolean isDeterminanZero;
        Matriks mtemp, mawal;
        InOut io = new InOut();

        // Algoritma
        isDeterminanZero = false;
        if(m.row == m.col-1) {
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
                if(Matriks.detKofaktor(mawal) == 0) {
                    isDeterminanZero = true;
                    System.out.println("Tidak ada penyelesaian SPL Cramer pada matriks ini, sebab determinan matriks adalah 0");
                    break;
                } else {
                    B[i] = (float)Matriks.detKofaktor(mtemp) / (float)Matriks.detKofaktor(mawal);
                    B[i] = Math.round((B[i] * 100)) / 100.0;
                }
            }
            if(!isDeterminanZero) {
                io.tulisPenyelesaianSPLNotAugmented(fileName, B);
            }
        } else {
            System.out.println("Tidak ada penyelesaian SPL Cramer pada matriks ini, sebab tidak berbentuk n x n");
        }

    }

    public static double zigmaNormalEquation(Matriks matriks, int firstCol, int secCol){
        //KAMUS
        int i, j;
        double temp = 0, a, b;
        //ALGORITMA
        for (i=0; i<matriks.row; i++){
            if (firstCol == 0){
                a = 1;
            }else{
                a = matriks.Mat[i][firstCol-1];
            }
            if (secCol == 0){
                b = 1;
            }else{
                b = matriks.Mat[i][secCol-1];
            }

            temp += a * b;
        }

        return temp;
    }
}
