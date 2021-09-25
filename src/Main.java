//import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
    
        TesBacaText();

        // /* Kamus */
        // int command;
        // Scanner scanner = new Scanner(System.in);
        
        // /* Algoritma */
        // do {
        //     System.out.println("MENU");
        //     System.out.println("1. Sistem Persamaan Linier");
        //     System.out.println("2. Determinan");
        //     System.out.println("3. Matriks balikan");
        //     System.out.println("4. Interpolasi Polinom");
        //     System.out.println("5. Regresi linier berganda");
        //     System.out.println("6. Keluar");

        //     command = scanner.nextInt();
        //     switch (command) {
        //         case 1:
        //             SPL();
        //             break;
            
        //         case 2:
        //             Determinan();
        //             break;
                
        //         case 3:
        //             Balikan();
        //             break;
            
        //         case 4:
                    
        //             break;
                
        //         case 5:
                    
        //             break;
        //     }
        // } while (command != 6);
        // scanner.close();
    }

    public static void tesAdjoin(){
        InOut io = new InOut();
        Matriks matriks;

        matriks = io.bacaTxtMatriks("adjoin.txt");
        io.tulisTxtMatriks("kofaktor.txt", Matriks.matrixKofaktor(matriks));
        io.tulisTxtMatriks("adjoin2.txt", Matriks.matrixKofaktor(matriks).Transpose());
        
    }

    public static void tesInvers(){
        InOut io = new InOut();
        Matriks matriks;

        matriks = io.bacaTxtMatriks("testinvers.txt");
        io.tulisTxtMatriks("tesinvers1.txt", Matriks.balikanAdjoin(matriks));
        
        
    }

    public static void TesBacaTerminal(){
        InOut io = new InOut();
        Matriks matriks;

        matriks = io.bacaTerminalMatrix();
        io.tulisTerminalMatrix(matriks);
        double hasil = Matriks.detKofaktor(matriks);
        System.out.println(hasil);
        
    }

    public static void TesBacaText(){
        InOut io = new InOut();

        System.out.println("Membaca mattriks dari .txt");
        Matriks matrix1 = io.bacaTxtMatriks("matrix.txt");
        io.tulisTerminalMatrix(matrix1);
        System.out.println();

        System.out.println("Menampilkan matriks tereduksi");
        Matriks matrix2 = matrix1.reduksiMatriks();
        io.tulisTerminalMatrix(matrix2);
        System.out.println();
        
        System.out.println("Menampilkan matriks dengan eselon Tereduksi matriks");
        Matriks matriks3 = FungsiSPL.splGaussJordan(matrix1);
        System.out.println();
        
        /*
        System.out.println("Menampilkan matriks dengan spl Gauss");
        Matriks matriks3 = FungsiSPL.splGauss(matrix1);
        io.tulisTerminalMatrix(matriks3);
        */

        io.tulisPenyelesaianSPL(matriks3, matrix1.col-1);        
        io.tulisTxtMatriks("matrix3.txt", matrix2); 
    }

    public static void SPL() {
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Metode Cramer");
        
    }

    public static void Determinan() {
        System.out.println("1. Metode reduksi baris");
        System.out.println("2. Metode ekspansi kofaktor");
        
        
    }

    public static void Balikan() {
        System.out.println("1. Metode adjoin");
        System.out.println("2. Metode matriks identitas");
           
    }
}