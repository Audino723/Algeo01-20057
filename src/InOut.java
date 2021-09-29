import java.io.*;
import java.util.Scanner;

public class InOut {
    
    public static double[] method(String str, int col) {
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

    //PRIMITIF
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

    //InOut TEXT
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
    
    //InOut Terminal
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

    public static void tulisTerminalMatrix(Matriks matriks) {
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
                        System.out.print(String.format("%.2f", matriks.Mat[i][j])+ " ");
                    }
                }
                System.out.println();
            }
        }

    public void tulisTerminalMatrix(String fileName, Matriks matriks) {
    //Kamus Lokal
        int i, j;
        int row, col;
    //Algoritma
        row = matriks.row;
        col = matriks.col;
        print(fileName, ("Elemen matriks m adalah: \n"));
        for(i = 0; i < row; i++)
        {
            for(j = 0; j < col; j++)
            {
                if(j == col-1) {
                    print(fileName, (String.format("%.2f",matriks.Mat[i][j])));
                } else {
                    print(fileName, ((String.format("%.2f", matriks.Mat[i][j])) + " "));
                }
            }
            print(fileName, "\n");
        }
    }

    //Inout SPL
    public void tulisPenyelesaianSPL(String fileName, Matriks coefHasil){
        //KAMUS
        int i, j;
        String[] ans = new String[coefHasil.row];

        //ALGORITMA
        //TESTING
        //Mengecek apakah solusi unik
        for (i = 0 ; i < coefHasil.row; i++){
            if (coefHasil.isRowSPLZero(i, 0, coefHasil.col)){
                ans[i] = ("a"+(i+1));
            } else{
                ans[i] = (String.format("%.2f",coefHasil.Mat[i][0]));
            }
        }

        print(fileName, "\nHasil penyelesaian SPL : \n");
        for (i=0; i<coefHasil.col; i++){
            //Menampilkan X ke-
            print(fileName, ("X" + (i+1) + " = " + ans[i]));


            //Menampilkan penyelesaian bukan solusi unik
            for (j = i+1 ; j < coefHasil.col; j++){
                if ((i!=j) && coefHasil.isRowSPLZero(j, 0, coefHasil.col-1)){
                    if (coefHasil.Mat[i][j] != 0){
                        print(fileName, (" + " + (String.format("%.2f", (coefHasil.Mat[i][j]))) + "(a" + (j+1) + ")"));
                    }
                }
            }

            print(fileName, "\n");


        }
    }

    public static void tulisPenyelesaianSPLCramer (Matriks m) {
        //Kamus Lokal
        int i;
        //Algoritma
        Scanner scanner = new Scanner(System.in);
        double[] B = new double[m.row];
        System.out.println("Masukkan elemen dalam kolom B: ");
        for (i = 0; i < m.row; i++) {
            B[i] = scanner.nextDouble();
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

    public void tulisPenyelesaianSPLNotAugmented (String fileName, double[] B) {
        //Kamus Lokal
        int i;
        //Algoritma
        for(i = 0; i < B.length; i++) {
            print(fileName, ("x" + (i+1) + " : " + B[i] + "\n"));
        }
    }

    public void tulisPenyelesaianDeterminan (String fileName, Matriks matrix, double det){  
        System.out.println();
        print(fileName, ("Matriks yang ingin dicari determinannya adalah:\n"));
        tulisTerminalMatrix(fileName, matrix);
        print(fileName, ("\nDeterminan dari matrix tersebut adalah " + det));
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

    //Inout Interpolasi
    public static void tulisPenyelesaianInterpolasi(String fileName, Matriks matriks, double predX){
        //KAMUS
        int i, n = matriks.col;
        double temp = 0;

        //ALGORITMA
        //Menampilkan polinom
        print(fileName, ("\nPolinom Interpolasi yang melewati " + n +  " titik tersebut adalah :\n"));

        print(fileName, ("P" + (n-1) + "(x) ="));
        for (i = 0; i<n; i++){
            print(fileName, (String.format(" %.4f",matriks.Mat[i][0])));
            
            if (i > 1){
                print(fileName, ("x^" + i));
            } else if (i > 0){
                print(fileName, ("x"));
            }      
            
            if (i < n - 1){
                print(fileName, " +");
            }
            
            temp += matriks.Mat[i][0] * Math.pow(predX, i);
        }

        //Menampilkan hasil
        print(fileName, ("\nP" + (n-1) + "(" + predX + ") = " + (String.format(" %.4f",temp))));
        


    }

    //InOut Regresi
    public static void tulisPenyelesaianRegresi(String fileName, Matriks matriks, Matriks matriksHasil, Matriks predX){
        //KAMUS
        int i, j, n = matriks.row;
        double temp = matriksHasil.Mat[0][0];

        //ALGORITMA
        //Menampilkan polinom
        print(fileName, ("\nDari data-data yang ada, diperoleh SPL sebagai berikut:\n"));
        for (i = 0; i<n;i++ ){
            for (j=0; j<n+1;j++){
                print(fileName, ((String.format(" %.4f",matriks.Mat[i][j])) ));
                if (j<n-1){
                    print(fileName, ("b" + j + " + "));
                } else if (j == n-1){
                    print(fileName, ("b" + j + " = "));
                }
            }
            print(fileName, "\n");
        }

        //Mencari hasil prediksi
        for (i = 1; i<n;i++){
            temp += predX.Mat[0][i-1] * matriksHasil.Mat[i][0];
        }
        print(fileName, ("Hasil prediksi : " + (String.format(" %.4f",temp))));

    }
}