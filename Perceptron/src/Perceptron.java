import java.io.BufferedInputStream;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Created by tshi206 on 2/11/2017.
 */
public class Perceptron {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        System.out.print("number of epoch: "+"\n");
        int epoch = 0;
        try{
            epoch = Integer.valueOf(scanner.nextLine());
        }catch (NumberFormatException e){
            System.out.print("FUCK U");
        }
        ArrayList<ArrayList<Integer>> dataSet = new ArrayList<ArrayList<Integer>>();
        try{
            while (scanner.hasNextLine()){
                String input = scanner.nextLine();
                String[] parsedInput = input.split("\\s+");
                ArrayList<Integer> temp = new ArrayList<>();;
                for (String word : parsedInput){
                    temp.add(Integer.valueOf(word));
                }
                dataSet.add(temp);
            }
        }catch (NumberFormatException e){
            System.out.print("OK\n");
        }finally {
            scanner.close();
        }
        dataSet.forEach(aRow -> {
            aRow.forEach(integer -> {
                System.out.print(integer + " ");
            });
            System.out.print("\n");
        });
        Solver solver = new Solver(epoch, dataSet);

    }

}
