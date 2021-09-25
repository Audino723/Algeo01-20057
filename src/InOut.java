import java.io.*;
import java.util.Scanner;

public class InOut {
    
    static double[] method(String str, int col) {
        int i;

        String[] splitArray = str.split(" ");
        double[] array = new double[col];

        // parsing the String argument as a signed decimal integer object and
        // storing that integer into the array
        for (i = 0; i < col; i++) {
            array[i] = Double.parseDouble(splitArray[i]);
        }
        return array;
    }

    public int colTxt(String fileName) {
        int col = 0 ;
        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;

            line = bufferedReader.readLine();
            String[] splitArray = line.split(" ");
            col = splitArray.length;
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();//TODO: handle exception
        }
        return col;
    }
    
    public int rowTxt(String fileName) {
        int row=0;
        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(reader);

            while ((bufferedReader.readLine()) != null) {
                row++;
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();//TODO: handle exception
        }
        return row;
    }

    public Matriks bacaTxtMatriks(String fileName) {
        Matriks matrix;
        try {
            FileReader reader = new FileReader(fileName);
            matrix = new Matriks(rowTxt(fileName),colTxt(fileName));
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            int i = 0;
            while ((line = bufferedReader.readLine()) != null) {
                matrix.Mat[i] = method(line, matrix.col);
                i++;
            }
            reader.close();
            

        } catch (IOException e) {
            matrix = new Matriks(-1, -1);
            System.out.println("Tidak terdapat file dengan nama tersebut.");
        }
        return matrix;        
    }

    public void tulisTxtMatriks(String fileName, Matriks m1) {
        int i, j;
        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-16");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            for (i = 0; i < m1.row; i++) { 
                for (j = 0; j < m1.col; j++) {
                    bufferedWriter.write(Double.toString(m1.Mat[i][j]));

                    if (j != m1.col-1) {
                        bufferedWriter.write(" ");
                    }
                } 
                if (i != m1.row-1) {
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();// TODO: handle exception
        }

    }
    
    public Matriks bacaTerminalMatrix() {
    //Kamus Lokal
        int i, j;
        int row,col;
        Matriks matriks;
    //Algoritma
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Masukkan jumlah baris: "); row = scanner.nextInt();
        System.out.print("Masukkan jumlah kolom: "); col = scanner.nextInt();
        System.out.println("Masukkan elemen dalam matriks: ");
        matriks = new Matriks(row,col);
        for (i = 0; i < row; i++) {
                for (j = 0; j < col; j++) {
                    matriks.Mat[i][j] = scanner.nextDouble();
            }
        }
        return matriks;
    }
    
    public void tulisTerminalMatrix(Matriks matriks) {
    //Kamus Lokal
        int i, j;
        int row, col;
    //Algoritma
        row = matriks.row;
        col = matriks.col;
        System.out.println("Elemen matriks m adalah: ");
        for(i = 0; i < row; i++)
        {
            for(j = 0; j < col; j++)
            {
                if(j == col-1) {
                    System.out.print(matriks.Mat[i][j]);
                } else {
                    System.out.print(matriks.Mat[i][j] + " ");
                }
            }
            System.out.println("");
        }
    }

    public void tulisPenyelesaianSPL(Matriks coefHasil, int jumlahX){
        //KAMUS
        int i, j;
        double temp;

        //ALGORITMA
        for (i=0; i<jumlahX; i++){
            temp = coefHasil.Mat[i][0];
            //Menampilkan X ke-
            System.out.print("X" + (i+1) + " = ");

            //Menampilkan penyelesaian dengan solusiunik
            for (j = 1 ; j < coefHasil.col; j++){
                temp += coefHasil.Mat[i][j] * coefHasil.Mat[j][0];
            }
            System.out.print(temp);

            //Menampilkan penyelesaian bukan solusi unik
            for (j = 1 ; j < coefHasil.col; j++){
                if ((i!=j) && coefHasil.isRowSPLZero(j, 0, coefHasil.col-1)){
                    if (coefHasil.Mat[i][j] != 0){
                        System.out.print(" + (" + (coefHasil.Mat[i][j]) + "S" + (j+1) + ")");
                    }
                }
            }

            System.out.println();


        }
    }

    public static double[] bacaTerminalMatrixBalikan(Matriks m) {
    //Kamus Lokal
        int i;
    //Algoritma
        Scanner scanner = new Scanner(System.in);
        double[] B = new double[m.col];
        System.out.println("Masukkan elemen dalam kolom B: ");
        for (i = 0; i < m.col; i++) {
            B[i] = scanner.nextDouble();
        }
        return B;
    }

    public static void tulisPenyelesaianSPLCramer (double[] B) {
//        Kamus Lokal
        int i;
//        Algoritma
        for(i = 0; i < B.length; i++) {
            System.out.println("x" + i + " : " + B[i]);
        }
    }

    public static void tulisPenyelesaianSPLBalikan (double[] B) {
//        Kamus Lokal
        int i;
//        Algoritma
        for(i = 0; i < B.length; i++) {
            System.out.println("x" + i + " : " + B[i]);
        }
    }
}
