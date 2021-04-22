import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        //Instantiate a new BinaryTree
        BinaryTree binaryTree1 = new BinaryTree();

        //Generate a random int list
        int[] randomArray;
        try{
            randomArray = createRandomArray(Integer.parseInt(args[0]));
        }catch(ArrayIndexOutOfBoundsException e){
            randomArray = createRandomArray(30);
        }

        for(int i: randomArray){
            System.out.print(i + " ");
        }

        //for each element given, add them to the tree
        for(int i: randomArray){
            binaryTree1.addNode(i);
        }

        //Trigger the sort algorithm, choosing between increasing or descending order using the input of the console
        //or as default the increasing order
        ArrayList<Node> orderedNodeList;
        try{
            orderedNodeList = binaryTree1.getNodeArray(Integer.parseInt(args[1]));
        }catch (ArrayIndexOutOfBoundsException e){
            orderedNodeList = binaryTree1.getNodeArray(0);
        }

        System.out.println(" ");
        printNodeList(orderedNodeList);
    }

    //Random list with n(input)*5 range and n elements
    public static int[] createRandomArray(int n){
        int[] array = new int[n];
        Random random = new Random();
        for (int i=0; i<n; i++){
            array[i] = random.nextInt(n*5);
        }
        return array;
    }

    public static void printNodeList(ArrayList<Node> nodeForPrint){
        for(Node i: nodeForPrint){
            System.out.print(i.getId() + " ");
        }
    }
}
