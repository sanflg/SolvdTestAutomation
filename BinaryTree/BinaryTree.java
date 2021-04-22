import java.util.ArrayList;

//This class is in charge of storing and ordering nodes with a binary tree algorithm
public class BinaryTree {
    Node root;

    //Method for node insertion in the tree, calling the node constructor
    public void addNode(int id){
        Node newNode = new Node(id);

        if (root == null) root = newNode; //if there is no root, the firs element will be used as root
        else{
            Node actualNode = root;
            Node parent;

            //For each iteration, we will make "actualNode" the "parent" node and check if the new node is
            //lower or higher that the actualNode, then the new node will be aggregated at the left or right
            //If the left or right path is null, the new node is aggregated at the parent node and break the cycle.
            while(true){
                parent = actualNode;
                if(id < actualNode.getId()){
                    actualNode = actualNode.getLeft();
                    if(actualNode == null){
                        parent.setLeft(newNode);
                        return;
                    }
                }else{
                    actualNode = actualNode.getRight();
                    if(actualNode == null) {
                        parent.setRight(newNode);
                        return;
                    }
                }
            }
        }
    }

    //Method for getting in increasing order the nodes, iterating through the leftmost paths and reaching the
    //lowest element, at that point, given a null element, the iteration stops and start going up
    private static void increasingOrder(Node actualNode, ArrayList<Node> nodeList){
        if (actualNode != null){
            increasingOrder(actualNode.getLeft(), nodeList);
            nodeList.add(actualNode);
            increasingOrder(actualNode.getRight(), nodeList);
        }
    }

    private static void descendingOrder(Node actualNode, ArrayList<Node> nodeList){
        if (actualNode != null){
            descendingOrder(actualNode.getRight(), nodeList);
            nodeList.add(actualNode);
            descendingOrder(actualNode.getLeft(), nodeList);
        }
    }

    //Stores the elements of the tree in an array using the increasingOrder iteration
    public ArrayList<Node> getNodeArray(int value){
        ArrayList<Node> nodeList = new ArrayList<>();
        if (value == 0) {
            BinaryTree.increasingOrder(this.root, nodeList);
        } else {
            BinaryTree.descendingOrder(this.root, nodeList);
        }
        return nodeList;
    }
}
