import java.io.*;

public class Matriks {
    // Atribut
    double[][] Mat = new double[10][10]; // Masih hard coded harus diganti dengan efektif dari matriks

    // Method:
    Matriks() {
        int i, j;

        for (i = 0; i < 10; i++) {
            for (j = 0; j < 10; j++) {
                this.Mat[i][j] = 0;
            }
        }
    }

    static double[] method(String str) {
        int i;

        String[] splitArray = str.split(" ");
        double[] array = new double[splitArray.length];

        // parsing the String argument as a signed decimal integer object and
        // storing that integer into the array
        for (i = 0; i < splitArray.length; i++) {
            array[i] = Double.parseDouble(splitArray[i]);
        }
        return array;
    }

    public void bacaTxtMatriks(String fileName) {
        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            int i = 0;
            while ((line = bufferedReader.readLine()) != null) {
                this.Mat[i] = method(line);
                i++;
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tulisTxtMatriks(String fileName) {
        int i, j;
        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-16");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            System.out.print("\n[");
            for (i = 0; i < 3; i++) { // Masih hard coded harus diganti dengan efektif dari matriks
                for (j = 0; j < 5; j++) {
                    bufferedWriter.write(Double.toString(this.Mat[i][j]));

                    if (j != 4) {
                        bufferedWriter.write(" ");
                    }
                } // Masih hard coded harus diganti dengan efektif dari matriks
                if (i != 2) {
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();// TODO: handle exception
        }

    }
}