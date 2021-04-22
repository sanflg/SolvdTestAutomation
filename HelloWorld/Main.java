public class Main {
    public static void main(String[] args){
        try{
            System.out.println("Hello " + args[0]);
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Hello World!");
        }
    }
}
