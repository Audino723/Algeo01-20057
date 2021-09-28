public class testfile {
    
    public static void tesAdjoin() {
        InOut io = new InOut();
        Matriks matriks;

        matriks = io.bacaTxtMatriks("adjoin.txt");
        io.tulisTxtMatriks("kofaktor.txt", Matriks.matrixKofaktor(matriks));
        io.tulisTxtMatriks("adjoin2.txt", Matriks.matrixKofaktor(matriks).Transpose());

    }

    public static void tesInvers() {
        InOut io = new InOut();
        Matriks matriks;

        matriks = io.bacaTxtMatriks("testinvers.txt");
        io.tulisTxtMatriks("testinvers1.txt", Matriks.balikanAdjoin(matriks));

    }

    public static void TesBacaTerminal() {
        InOut io = new InOut();
        Matriks matriks;
        matriks = io.bacaTerminalMatrix();
        io.tulisTerminalMatrix(matriks);
        double hasil = Matriks.detKofaktor(matriks);
        System.out.println(hasil);

    }

    public static void TesBacaText() {
        InOut io = new InOut();

        System.out.println("Membaca mattriks dari .txt");
        Matriks matrix1 = io.bacaTxtMatriks("matrix.txt");
        InOut.tulisTerminalMatrix(matrix1);
        System.out.println();

        System.out.println("Menampilkan matriks tereduksi");
        Matriks matrix2 = matrix1.reduksiMatriks();
        InOut.tulisTerminalMatrix(matrix2);
        System.out.println();
        
        System.out.println("Menampilkan matriks dengan eselon Tereduksi matriks");
        Matriks matriks3 = FungsiSPL.splGaussJordan(matrix1);
        System.out.println();
        
        /*
        System.out.println("Menampilkan matriks dengan spl Gauss");
        Matriks matriks3 = FungsiSPL.splGauss(matrix1);
        io.tulisTerminalMatrix(matriks3);
        */


        System.out.println(Matriks.detReduksiBaris(matrix2) + "hasil det: ");

        io.tulisTxtMatriks("matrix3.txt", matrix2);
    }
}
