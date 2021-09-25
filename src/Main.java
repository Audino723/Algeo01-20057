import java.util.Scanner;

public class Main {
    public static int command;
    public static Scanner scanner = new Scanner(System.in);
    public static InOut io = new InOut();

    public static void main(String[] args) {

        // tesInvers();

        /* Kamus */

        /* Algoritma */
        do {
            System.out.println("MENU");
            System.out.println("1. Sistem Persamaan Linier");
            System.out.println("2. Determinan");
            System.out.println("3. Matriks balikan");
            System.out.println("4. Interpolasi Polinom");
            System.out.println("5. Regresi linier berganda");
            System.out.println("6. Keluar");

            System.out.print("> ");
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    SPL();
                    break;

                case 2:
                    Determinan();
                    break;

                case 3:
                    Balikan();
                    break;

                // case 4:

                // break;

                // case 5:

                // break;
            }
        } while (command != 6);
        scanner.close();

    }

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

        Matriks matrix1 = io.bacaTxtMatriks("matrix.txt");
        io.tulisTerminalMatrix(matrix1);

        Matriks matrix2 = matrix1.reduksiMatriks();
        io.tulisTerminalMatrix(matrix2);

        Matriks matriks3 = FungsiSPL.splGauss(matrix1);
        io.tulisTerminalMatrix(matriks3);

        io.tulisPenyelesaianSPL(matriks3, matrix1.col - 1);

        System.out.println(Matriks.detReduksiBaris(matrix2) + "hasil det: ");

        io.tulisTxtMatriks("matrix3.txt", matrix2);
    }

    public static void SPL() {
        Matriks matrix;
        int opMethod;

        matrix = flowBacaMatriks();

        System.out.println();
        System.out.println("| Matriks Berhasil dibaca. Pilih metode operasi SPL! |");
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Metode Cramer");

        do {

            System.out.print("> ");
            opMethod = scanner.nextInt();

            switch (opMethod) {
                case 1:

                    break;

                case 2:

                    break;
                case 3:

                    break;

                case 4:
                    break;
                default:
                    opMethod = 0;
                    System.out.println();
                    System.out.println("Masukan untuk metode operasi SPL matriks tidak valid!!");
                    break;
            }
        } while (opMethod == 0);

    }

    public static void Determinan() {
        Matriks matrix;
        double det;
        int opMethod;
        String fileName, detStr;

        matrix = flowBacaMatriks();

        System.out.println();
        System.out.println("| Matriks Berhasil dibaca. Pilih metode operasi mencari determinan! |");
        System.out.println("1. Metode reduksi baris");
        System.out.println("2. Metode ekspansi kofaktor");

        do {

            System.out.print("> ");
            opMethod = scanner.nextInt();

            switch (opMethod) {
                case 1:
                    det = Matriks.detReduksiBaris(matrix);
                    break;

                case 2:
                    det = Matriks.detKofaktor(matrix);
                    break;

                default:
                    det = 0;
                    opMethod = 0;
                    System.out.println("Masukan untuk metode operasi mencari determinan matriks tidak valid!!");
                    break;
            }
        } while (opMethod == 0);

        detStr = Double.toString(det);

        System.out.println();
        System.out.println("| Operasi selesai dilakukan |");
        System.out.println("Matriks yang ingin dicari determinannya adalah:");
        io.tulisTerminalMatrix(matrix);

        System.out.println("Determinan dari matrix tersebut adalah " + detStr);

        System.out.println();
        System.out.println("| Masukkan nama file untuk menuliskan hasil operasi!! (beserta extensi .txt) |");
        System.out.print("> ");
        fileName = scanner.next();

        io.buatTxtBaru(fileName);
        io.tulisTxtBaris(fileName, "Matriks yang ingin dicari determinannya adalah:");
        io.tulisTxtNewLine(fileName);
        io.tulisTxtMatriks(fileName, matrix);
        io.tulisTxtNewLine(fileName);
        io.tulisTxtBaris(fileName, "Determinan dari matrix tersebut adalah ");
        io.tulisTxtBaris(fileName, detStr);

    }

    public static void Balikan() {
        Matriks matrix;
        int opMethod;

        matrix = flowBacaMatriks();

        System.out.println();
        System.out.println("| Matriks Berhasil dibaca. Pilih metode operasi mencari determinan! |");
        System.out.println("1. Metode adjoin");
        System.out.println("2. Metode matriks identitas");

        do {

            System.out.print("> ");
            opMethod = scanner.nextInt();

            switch (opMethod) {
                case 1:

                    break;

                case 2:
                    break;

                default:
                    opMethod = 0;
                    System.out.println("Masukkan untuk metode operasi mencari balikan matriks tidak valid!!");
                    break;
            }
        } while (opMethod == 0);

    }

    public static Matriks flowBacaMatriks() {
        int inputMethod;
        Matriks matrix = new Matriks(1, 1);
        String namaFile;
        do {
            System.out.println();
            System.out.println("| Pilih cara untuk membaca matriks! |");
            System.out.println("1. Baca dari terminal");
            System.out.println("2. Baca dari file txt");

            System.out.print("> ");
            inputMethod = scanner.nextInt();

            switch (inputMethod) {
                case 1:
                    if (command == 2 || command == 3) {
                        System.out.print("> ");
                        matrix = io.bacaTerminalMatrixSquare();
                    } else {
                        matrix = io.bacaTerminalMatrix();
                    }
                    break;

                case 2:

                    System.out.println();
                    System.out.println("| Masukkan nama file beserta extensi txt |");
                    System.out.print("> ");
                    namaFile = scanner.next();
                    matrix = io.bacaTxtMatriks(namaFile);
                    break;
                default:
                    inputMethod = 0;
                    System.out.println("Masukkan untuk metode membaca matriks tidak valid");
                    break;
            }
        } while (inputMethod == 0);
        return matrix;

    }
}