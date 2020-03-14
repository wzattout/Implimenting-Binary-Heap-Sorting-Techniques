package eg.edu.alexu.csd.filestructure.sort;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

public class Test {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Integer> arr = new ArrayList();
        Long time;
        PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
        System.setOut(out);
        ISort test = new Sort();
        int randomNumber;
        Random rand = new Random();
        for (int i = 500; i <= 10000; i += 500){
            arr.clear();
            for (int j = 0; j < i; j++){
                randomNumber = rand.nextInt(100000);
                arr.add(randomNumber);
            }
            long startTime = System.currentTimeMillis();
            test.sortSlow(arr);
            long endTime = System.currentTimeMillis();
            time = endTime - startTime;
            System.out.println(i + "\t" + time);
        }
    }

}
