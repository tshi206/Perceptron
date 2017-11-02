import java.io.BufferedInputStream;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

/**
 * Created by tshi206 on 2/11/2017.
 *
 * for binary target features only.
 */
public class Perceptron {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        System.out.print("number of epoch: "+"\n");
        int epoch = 0;
        try{
            epoch = Integer.valueOf(scanner.nextLine().trim());
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        System.out.print("number of input features plus target feature: "+"\n");
        int inpFeature = 0;
        try{
            inpFeature = Integer.valueOf(scanner.nextLine().trim());
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        System.out.print("initial weight vector: "+"\n");
        ArrayList<Integer> initialVec = new ArrayList<>();
        try{
            String temp = scanner.nextLine();
            String[] parsedTemp = temp.split("\\s+");
            int tempCount = 0;
            for (String s : parsedTemp) {
                if (s.equals("") || s.matches("\\s+")){
                    continue;
                }
                initialVec.add(Integer.valueOf(s.trim()));
                tempCount++;
            }
            if (tempCount != inpFeature){
                throw new RuntimeException("number of input features and feature count doesn't match.");
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        System.out.print("start taking input rows. input an empty line to quit."+"\n");
        ArrayList<ArrayList<Integer>> dataSet = new ArrayList<>();
        try{
            while (scanner.hasNextLine()){
                String input = scanner.nextLine();
                if (input.equals("") || input.matches("\\s+")){
                    break;
                }
                int featuresCount = 0;
                String[] parsedInput = input.split("\\s+");
                ArrayList<Integer> temp = new ArrayList<>();
                for (String word : parsedInput){
                    if (word.equals("") || word.matches("\\s+")){
                        continue;
                    }
                    temp.add(Integer.valueOf(word.trim()));
                    featuresCount++;
                }
                if (featuresCount != inpFeature){
                    throw new RuntimeException("number of input features and feature count doesn't match.");
                }else{
                    dataSet.add(temp);
                }
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }finally {
            scanner.close();
        }
        System.out.print("#####################################################\n");
        System.out.print("Initial Weight Vector: \n");
        System.out.print("[ ");
        Queue<Integer> tempQueue = new ConcurrentLinkedQueue<>();
        initialVec.forEach(integer -> tempQueue.add(integer));
        String preparedStatement = "";
        while (!tempQueue.isEmpty()){
            preparedStatement = preparedStatement + tempQueue.poll() + ", ";
        }
        preparedStatement = preparedStatement.substring(0, preparedStatement.length()-2);
        System.out.print(preparedStatement);
        System.out.print(" ]\n");
        for (int i=0; i< inpFeature; i++){
            if (i == inpFeature-1){
                System.out.print("Target Feature"+"\t\n");
                break;
            }
            System.out.print("Input Feature "+i+"\t");
        }
        dataSet.forEach(aRow -> {
            aRow.forEach(integer -> {
                System.out.print(integer + "\t\t\t\t");
            });
            System.out.print("\n");
        });
        System.out.print("#####################################################\n\n\n");
        Solver solver = new Solver(epoch, dataSet, inpFeature, initialVec);
        solver.solveIt();
    }

}
