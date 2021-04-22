import java.util.Objects;

public class Car {
    private int plate;
    private String color;

    //Constructor
    public Car(int plate, String color) {
        this.plate = plate;
        this.color = color;
    }

    //Getters
    public int getPlate() {
        return plate;
    }
    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object carInstance) {
        if (this == carInstance) return true;
        if (carInstance == null || getClass() != carInstance.getClass()) return false;
        Car car = (Car) carInstance;
        return getPlate() == car.getPlate() && Objects.equals(getColor(), car.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlate(), getColor());
    }

    @Override
    public String toString() {
        return "Car{" +
                "plate=" + plate +
                ", color='" + color + '\'' +
                '}';
    }
}
