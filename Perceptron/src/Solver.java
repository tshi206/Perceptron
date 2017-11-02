import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by tshi206 on 2/11/2017.
 */
public class Solver {

    int numOfEpoch;
    ArrayList<ArrayList<Integer>> dataSet = new ArrayList<ArrayList<Integer>>();
    int featuresCount;
    ArrayList<Integer> initialVector = new ArrayList<>();

    public Solver(int numOfEpoch, ArrayList<ArrayList<Integer>> dataSet, int featuresCount, ArrayList<Integer>
            initialVector){
        this.numOfEpoch = numOfEpoch;
        this.dataSet = dataSet;
        this.featuresCount = featuresCount;
        this.initialVector = initialVector;
    }

    public void solveIt(){
        for (int i=0; i<numOfEpoch; i++){
            dataSet.forEach(rows -> {
                int classification = initialVector.get(0);
                for (int j=1; j<initialVector.size(); j++){
                    classification += initialVector.get(j) * rows.get(j - 1);
                }
                int pclass;
                if (classification > 0){
                    pclass = 1;
                }else{
                    pclass = 0;
                }
                ArrayList<Integer> temp = new ArrayList<>();
                switch (rows.get(rows.size()-1) - pclass){
                    case 0:
                        break;
                    case 1:
                        temp.add(initialVector.get(0) + 1);
                        for (int k=1; k<initialVector.size(); k++){
                            temp.add(initialVector.get(k) + rows.get(k - 1));
                        }
                        initialVector.clear();
                        initialVector.addAll(temp);
                        break;
                    case -1:
                        temp.add(initialVector.get(0) - 1);
                        for (int k=1; k<initialVector.size(); k++){
                            temp.add(initialVector.get(k) - rows.get(k - 1));
                        }
                        initialVector.clear();
                        initialVector.addAll(temp);
                        break;
                }
                System.out.print("Weight Vector: ");
                System.out.print("[ ");
                Queue<Integer> tempQueue = new ConcurrentLinkedQueue<>();
                initialVector.forEach(integer -> tempQueue.add(integer));
                String preparedStatement = "";
                while (!tempQueue.isEmpty()){
                    preparedStatement = preparedStatement + tempQueue.poll() + ", ";
                }
                preparedStatement = preparedStatement.substring(0, preparedStatement.length()-2);
                System.out.print(preparedStatement);
                System.out.print(" ]\n");
            });
            System.out.print("\nepoch " + (i + 1) + " finished.\n\n");
        }
    }
}
