//import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        InOut io = new InOut();
        Matriks m1 = io.bacaTxtMatriks("matrix.txt");
        String[] solusi = FungsiSpl.splGauss(m1);
        System.out.println(m1.detReduksiBaris());
        

       
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

    public static void TesBacaTerminal(){
        InOut io = new InOut();
        Matriks matriks;

        matriks = io.bacaTerminalMatrix();
        io.tulisTerminalMatrix(matriks);
        double hasil = Matriks.Kofaktor(matriks);
        System.out.println(hasil);
        
    }

    public static void TesBacaText(){
        InOut io = new InOut();
        Matriks matrix1 = io.bacaTxtMatriks("matrix.txt");
        
        io.tulisTxtMatriks("matrix3.txt", matrix1); 
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