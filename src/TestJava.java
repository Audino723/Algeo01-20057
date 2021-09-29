import java.util.*;

public class TestJava {
    public static int command;
    public static Scanner scanner = new Scanner(System.in);
    public static InOut io = new InOut();

    public static void main(String[] args) {
        nomor1();
    }

    public static void nomor1() {
        ArrayList<String> input = new ArrayList<String>();
        input.add("1"); // operasi
        input.add("2"); // metode baca dari txt
        input.add("test/Nomor1/1a.txt"); // nama txt asal
        input.add("test/Nomor1/Hasil1a.txt"); // nama txt tujuan
        input.add("1"); // metode operasi
        mainTest(input);
        input.clear();
        input.clear();

        input.add("1"); // operasi
        input.add("2"); // metode baca dari txt
        input.add("test/Nomor1/1b.txt"); // nama txt asal
        input.add("test/Nomor1/Hasil1b.txt"); // nama txt tujuan
        input.add("1"); // metode operasi
        mainTest(input);
        input.clear();

        input.add("1"); // operasi
        input.add("2"); // metode baca dari txt
        input.add("test/Nomor1/1c.txt"); // nama txt asal
        input.add("test/Nomor1/Hasil1c.txt"); // nama txt tujuan
        input.add("1"); // metode operasi
        mainTest(input);
        input.clear();

        input.add("1"); // operasi
        input.add("2"); // metode baca dari txt
        input.add("test/Nomor1/1da.txt"); // nama txt asal
        input.add("test/Nomor1/Hasil1da.txt"); // nama txt tujuan
        input.add("1"); // metode operasi
        mainTest(input);
        input.clear();

        input.add("1"); // operasi
        input.add("2"); // metode baca dari txt
        input.add("test/Nomor1/1db.txt"); // nama txt asal
        input.add("test/Nomor1/Hasil1db.txt"); // nama txt tujuan
        input.add("1"); // metode operasi
        mainTest(input);
        input.clear();

    }

    public static void mainTest(ArrayList<String> input) {
        /* Kamus */

        /* Algoritma */
        do {
            System.out.println("\nMENU");
            System.out.println("1. Sistem Persamaan Linier");
            System.out.println("2. Determinan");
            System.out.println("3. Matriks balikan");
            System.out.println("4. Interpolasi Polinom");
            System.out.println("5. Regresi linier berganda");
            System.out.println("6. Keluar");
            System.out.print(">23 ");
            command = Integer.parseInt(input.get(0));
            switch (command) {
                case 1:
                    SPL(input);
                    break;
                case 2:
                    Determinan(input);
                    break;

                case 3:
                    Balikan(input);
                    break;
                case 4:
                    InterpolasiPolinom(input);
                    break;
                case 5:
                    RegresiLinier(input);
                    break;

                default:
                    System.out.println("Masukkan command tidak valid");
            }
            command = 6;
            System.out.println();
        } while (command != 6);
        scanner.close();

    }

    public static void SPL(ArrayList<String> input) {
        // KAMUS
        Matriks matrix;
        int opMethod;
        InOut io = new InOut();
        String fileName;

        // ALGORITMA
        matrix = flowBacaMatriks(input);

        if (matrix.isMatriksUndef()) {
            System.out.println("Matriks tidak valid");
        } else {
            System.out.println();
            System.out.println("| Masukkan nama file untuk menuliskan hasil operasi!! (beserta extensi .txt) |");
            System.out.print(">124 ");
            fileName = input.get(3);
            io.buatTxtBaru(fileName);

            System.out.println();
            System.out.println("| Matriks Berhasil dibaca. Pilih metode operasi SPL! |");
            System.out.println("1. Metode eliminasi Gauss");
            System.out.println("2. Metode eliminasi Gauss-Jordan");
            System.out.println("3. Metode matriks balikan");
            System.out.println("4. Metode Cramer");

            do {

                System.out.print(">129 ");
                opMethod = Integer.parseInt(input.get(4));

                switch (opMethod) {
                    case 1:
                        matrix = FungsiSPL.splGauss(matrix);
                        break;
                    case 2:
                        matrix = FungsiSPL.splGaussJordan(matrix);
                        break;
                    case 3:
                        FungsiSPL.splMatriksBalikan(fileName, matrix);
                        break;
                    case 4:
                        FungsiSPL.splCramer(fileName, matrix);
                        break;
                    default:
                        opMethod = 0;
                        System.out.println();
                        System.out.println("Masukan untuk metode operasi SPL matriks tidak valid!!");
                        break;
                }
            } while (opMethod == 0);

            if (matrix.isMatriksUndef()) {
                InOut.print(fileName, "Hasil penyelesaian tidak valid, tidak dapat menggunakan metode tersebut");

            } else if (opMethod == 1 || opMethod == 2) {
                io.tulisPenyelesaianSPL(fileName, matrix);
            }
        }

    }

    public static void Determinan(ArrayList<String> input) {
        Matriks matrix;
        double det;
        int opMethod;
        String fileName;

        matrix = flowBacaMatriks(input);
        if (matrix.isMatriksUndef()) {
            System.out.println("Matriks tidak valid");
        } else {
            System.out.println();
            System.out.println("| Masukkan nama file untuk menuliskan hasil operasi!! (beserta extensi .txt) |");
            System.out.print(">185 ");
            fileName = input.get(3);
            io.buatTxtBaru(fileName);

            System.out.println();
            System.out.println("| Matriks Berhasil dibaca. Pilih metode operasi mencari determinan! |");
            System.out.println("1. Metode reduksi baris");
            System.out.println("2. Metode ekspansi kofaktor");

            do {

                System.out.print(">180 ");
                opMethod = Integer.parseInt(input.get(4));

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

            io.tulisPenyelesaianDeterminan(fileName, matrix, det);
        }

    }

    public static void Balikan(ArrayList<String> input) {
        // KAMUS
        Matriks matrix;
        int opMethod;
        String fileName;
        // ALGORITMA
        matrix = flowBacaMatriks(input);
        if (matrix.isMatriksUndef()) {
            System.out.println("Matriks tidak valid");
        } else {
            System.out.println();
            System.out.println("| Masukkan nama file untuk menuliskan hasil operasi!! (beserta extensi .txt) |");
            System.out.print(">256 ");
            fileName = input.get(3);
            io.buatTxtBaru(fileName);

            System.out.println();
            System.out.println("| Matriks Berhasil dibaca. Pilih metode operasi mencari determinan! |");
            System.out.println("1. Metode adjoin");
            System.out.println("2. Metode matriks identitas");

            do {

                System.out.print("> ");
                opMethod = Integer.parseInt(input.get(4));

                switch (opMethod) {
                    case 1:
                        matrix = Matriks.balikanAdjoin(matrix);
                        break;
                    case 2:
                        matrix = Matriks.MatriksIdentitas(matrix);
                        break;

                    default:
                        opMethod = 0;
                        System.out.println("Masukkan untuk metode operasi mencari balikan matriks tidak valid!!");
                        break;
                }
            } while (opMethod == 0);

            if (matrix.isMatriksUndef()) {
                InOut.print(fileName, "Hasil penyelesaian tidak valid, tidak dapat menggunakan metode tersebut");
            } else if (opMethod == 1 || opMethod == 2) {
                io.tulisTerminalMatrix(fileName, matrix);
            }
        }

    }

    public static void InterpolasiPolinom(ArrayList<String> input) {
        // KAMUS
        Matriks matriksSPL;
        Matriks matriks = new Matriks(1, 1);
        int inputMethod;
        int n, i, j;
        String fileName;
        double temp, predX;
        Scanner scanner = new Scanner(System.in);

        // ALGORITMA

        do {
            System.out.println();
            System.out.println("| Pilih cara untuk membaca titik! |");
            System.out.println("1. Baca dari terminal");
            System.out.println("2. Baca dari file txt");

            System.out.print(">233 ");
            inputMethod = scanner.nextInt();

            switch (inputMethod) {
                case 1:
                    do {
                        System.out.println("Masukkan jumlah titik: ");
                        n = scanner.nextInt();
                    } while (n <= 0);

                    matriks = new Matriks(n, 2);

                    // Menerima semua titik
                    for (i = 0; i < n; i++) {
                        for (j = 0; j < 2; j++) {
                            matriks.Mat[i][j] = scanner.nextDouble();
                        }
                    }

                    break;

                case 2:
                    System.out.println();
                    System.out.println("| Masukkan nama file beserta extensi txt |");
                    System.out.print(">257 ");
                    fileName = input.get(3);
                    matriks = io.bacaTxtMatriks(fileName);
                    break;

                default:
                    inputMethod = 0;
                    System.out.println("Masukkan untuk metode membaca titik tidak valid");
                    break;
            }
        } while (inputMethod == 0);

        // Menconvert titik menjadi splAugmented
        n = matriks.col + 1;
        matriksSPL = new Matriks(n, n + 1);
        for (i = 0; i < n; i++) {
            temp = matriks.Mat[i][0];
            matriksSPL.Mat[i][n] = matriks.Mat[i][1];
            for (j = 0; j < n; j++) {
                matriksSPL.Mat[i][j] = Math.pow(temp, j);
            }
        }

        // Menampilkan hasil Interpolasi Polinom
        if (matriks.isMatriksUndef()) {
            System.out.println("Titik-titik tidak valid");
        } else {
            System.out.println();
            System.out.println("| Masukkan nama file untuk menuliskan hasil operasi!! (beserta extensi .txt) |");
            System.out.print(">285 ");
            fileName = input.get(3);
            io.buatTxtBaru(fileName);

            // Mencari penyelesaian menggunakan Gauss-Jordan
            matriksSPL = FungsiSPL.splGaussJordan(matriksSPL);

            // Menampilkan penyelesaian
            System.out.println("\n|Nilai x yang ingin diprediksi : |");
            System.out.print(">295 ");
            predX = scanner.nextDouble();

            InOut.tulisPenyelesaianInterpolasi(fileName, matriksSPL, predX);
        }

    }

    public static void RegresiLinier(ArrayList<String> input) {
        // KAMUS
        Matriks matriksRegresi, matriksHasil;
        Matriks matriks = new Matriks(1, 1), predX;
        int inputMethod;
        int x, m, i, j, rowMat;
        String fileName, inputX;
        double temp;
        Scanner scanner = new Scanner(System.in);

        // ALGORITMA

        do {
            System.out.println();
            System.out.println("| Pilih cara untuk membaca x dan y! |");
            System.out.println("1. Baca dari terminal");
            System.out.println("2. Baca dari file txt");

            System.out.print(">321 ");
            inputMethod = scanner.nextInt();

            switch (inputMethod) {
                case 1:
                    do {
                        System.out.println("Masukkan jumlah peubah x: ");
                        x = scanner.nextInt();
                        System.out.println("Masukkan jumlah sampel m: ");
                        m = scanner.nextInt();

                    } while (m <= 0 && x <= 0);

                    matriks = new Matriks(m, x + 1);

                    // Menerima semua titik
                    for (i = 0; i < m; i++) {
                        for (j = 0; j < x + 1; j++) {
                            matriks.Mat[i][j] = scanner.nextDouble();
                        }
                    }

                    break;

                case 2:
                    System.out.println();
                    System.out.println("| Masukkan nama file beserta extensi txt |");
                    System.out.print(">284 ");
                    fileName = input.get(3);
                    matriks = io.bacaTxtMatriks(fileName);
                    break;

                default:
                    inputMethod = 0;
                    System.out.println("Masukkan untuk metode membaca x dan y tidak valid");
                    break;
            }
        } while (inputMethod == 0);

        // Menconvert titik menjadi matriks Regresi
        rowMat = matriks.col;
        matriksRegresi = new Matriks(rowMat, rowMat + 1);
        for (i = 0; i < rowMat; i++) {
            for (j = 0; j < rowMat + 1; j++) {
                matriksRegresi.Mat[i][j] = FungsiSPL.zigmaNormalEquation(matriks, i, j);
            }
        }

        // Menampilkan hasil Interpolasi Polinom
        if (matriks.isMatriksUndef()) {
            System.out.println("Titik-titik tidak valid");
        } else {
            System.out.println();
            System.out.println("| Masukkan nama file untuk menuliskan hasil operasi!! (beserta extensi .txt) |");
            System.out.print(">285 ");
            fileName = input.get(3);
            io.buatTxtBaru(fileName);

            // Mencari penyelesaian menggunakan Gauss-Jordan
            matriksHasil = FungsiSPL.splGaussJordan(matriksRegresi);
            InOut.tulisTerminalMatrix(matriksHasil);
            // Menampilkan penyelesaian
            predX = new Matriks(1, rowMat + 1);
            System.out.println("\n|Nilai x yang ingin diprediksi : |");
            inputX = scanner.next();
            inputX += scanner.nextLine();
            predX.Mat[0] = InOut.method(inputX, rowMat - 1);

            InOut.tulisPenyelesaianRegresi(fileName, matriksRegresi, matriksHasil, predX);
        }
    }

    public static Matriks flowBacaMatriks(ArrayList<String> input) {
        int inputMethod;
        Matriks matrix = new Matriks(1, 1);
        String namaFile;

        do {
            System.out.println();
            System.out.println("| Pilih cara untuk membaca matriks! |");
            System.out.println("1. Baca dari terminal");
            System.out.println("2. Baca dari file txt");

            System.out.print(">268 ");
            inputMethod = Integer.parseInt(input.get(1));

            switch (inputMethod) {
                case 1:
                    if (command == 2 || command == 3) {
                        matrix = io.bacaTerminalMatrixSquare();
                    } else {
                        matrix = io.bacaTerminalMatrix();
                    }
                    break;

                case 2:
                    System.out.println();
                    System.out.println("| Masukkan nama file beserta extensi txt |");
                    System.out.print(">284 ");
                    namaFile = input.get(2);
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
