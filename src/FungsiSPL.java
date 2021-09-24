import java.util.Scanner;

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
