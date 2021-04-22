//Generate a Node class in charge of data storing
public class Node {
    private int id;

    //Aggregate 2 nodes, left(lower) and right(higher(
    private Node left;
    private Node right;

    //Constructor
    public Node(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Node getLeft() {
        return left;
    }
    public void setLeft(Node left) {
        this.left = left;
    }
    public Node getRight() {
        return right;
    }
    public void setRight(Node right) {
        this.right = right;
    }
}
