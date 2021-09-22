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

    


}