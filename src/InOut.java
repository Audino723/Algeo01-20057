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

    public void buatTxtBaru (String fileName) {
        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-16");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();// TODO: handle exception
        }

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
            matrix.undefMatriks();
            System.out.println("Tidak terdapat file dengan nama tersebut.");
        }
        return matrix;        
    }

    public void tulisTxtMatriks(String fileName, Matriks m1) {
        int i, j;
        try {
            FileWriter writer = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

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

    public Matriks bacaTerminalMatrixSquare() {
        //Kamus Lokal
            int i, j;
            int n;
            Matriks matriks;
        //Algoritma
            Scanner scanner = new Scanner(System.in);
            System.out.println();
            System.out.print("Masukkan jumlah baris dan kolom: "); n = scanner.nextInt();
            System.out.println("Masukkan elemen dalam matriks: ");
            matriks = new Matriks(n,n);
            for (i = 0; i < n; i++) {
                    for (j = 0; j < n; j++) {
                        matriks.Mat[i][j] = scanner.nextDouble();
                }
            }
            return matriks;
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
                    System.out.print(String.format("%.2f",matriks.Mat[i][j]));
                } else {
                    System.out.print((String.format("%.2f", matriks.Mat[i][j])) + " ");
                }
            }
            System.out.println("");
        }
    }

    public void tulisPenyelesaianSPL(String fileName, Matriks coefHasil){
        //KAMUS
        int i, j;

        //ALGORITMA
        print(fileName, "Hasil penyelesaian SPL : \n");
        for (i=0; i<coefHasil.col; i++){
            //Menampilkan X ke-
            print(fileName, ("X" + (i+1) + " = " + (String.format("%.2f",coefHasil.Mat[i][0]))));

            //Menampilkan penyelesaian bukan solusi unik
            for (j = 1 ; j < coefHasil.col; j++){
                if ((i!=j) && coefHasil.isRowSPLZero(j, 0, coefHasil.col-1)){
                    if (coefHasil.Mat[i][j] != 0){
                        print(fileName, (" + " + (String.format("%.2f", (coefHasil.Mat[i][j]))) + "(a" + (j+1) + ")"));
                    }
                }
            }

            print(fileName, "\n");


        }
    }

   

    public static void tulisPenyelesaianSPLCramer (double[] B) {
    //Kamus Lokal
        int i;
    //Algoritma
        for(i = 0; i < B.length; i++) {
            System.out.println("X" + i + " : " + (String.format("%.2f", B[i])));
        }
    }

    public static void tulisPenyelesaianSPLBalikan (double[] B) {
    //Kamus Lokal
        int i;
    //Algoritma
        for(i = 0; i < B.length; i++) {
            System.out.println("x" + i + " : " + (String.format("%.2f",B[i])));
        }
    }

    public static void print(String fileName, String str) {
        System.out.print(str);

        try {
            FileWriter writer = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(str);
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();// TODO: handle exception
        }
    }

}


    /*
    

    public void tulisTxtBaris(String fileName, String line) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(line);
            bufferedWriter.newLine();

            
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();// TODO: handle exception
        }

    }

    public void tulisTxtNewLine(String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.newLine();

            
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();// TODO: handle exception
        }

    }
    */