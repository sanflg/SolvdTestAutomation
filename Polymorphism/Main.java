public class Main {
    public static void main(String[] args) {
        Car car1 = new Car(123,"Red");
        Car car2 = new Car(123,"Red");
        Car car3 = new Car(789,"Blue");

        int[] hashCodes = {car1.hashCode(), car2.hashCode(), car3.hashCode()};
        for(int i: hashCodes){
            System.out.println(i);
        }

        boolean[] equalsArray = {car1.equals(car2), car2.equals(car3)};
        for(boolean i: equalsArray){
            System.out.println(i);
        }

        System.out.println(car1.toString());
    }
}
