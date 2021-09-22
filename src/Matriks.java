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

    public double detReduksiBaris() {
        int i,j,k;
        double ratio, det;
        Matriks mTemp;
        int n = this.row;

        mTemp = this;

        if (mTemp.col != mTemp.row || mTemp.row != mTemp.col + 1){
            System.out.println("Ukuran matriks tidak didukung untuk dicari determinannya");
            det = 0.0;
        }else{
            for (i = 0; i < n-1; i++) {
                if (mTemp.Mat[i][i]==0.0) {
                    det = 0.0;
                    break;
                } else {
                    for (j = i + 1; j < n; j++) {
                        ratio = mTemp.Mat[j][i] / mTemp.Mat[i][i];

                        for (k = 0; k <n ; k++) {
                            mTemp.Mat[j][k] = mTemp.Mat[j][k] - ratio * mTemp.Mat[i][k];
                        }
                    }
                }
            
            }
            det
        }
        
        
        return det;
    }
}