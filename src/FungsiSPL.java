public class FungsiSPL {
    
    

    public static Matriks splGauss(Matriks matrix) {
        //KAMUS
        Matriks mTemp = matrix.reduksiMatriks();
        int i, j;
        double temp;

        //ALGORITMA
        for (i = 0; i < matrix.row;++i){
            if (mTemp.Mat[i][i] != 1 && (mTemp.Mat[i][i] != 0)){

                temp = mTemp.Mat[i][i];
                for (j = 0; j < matrix.col; j ++){
                    mTemp.Mat[i][j] /= temp;
                }
            }
        }
        
        
        return mTemp;
    }
}
