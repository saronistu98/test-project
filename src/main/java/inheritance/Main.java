package inheritance;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Vehicle> vehicles = new ArrayList<>();
        Car car = new Car(3, 140.3, 57.5, 4);
        Car car2 = new Car(car);
        Vehicle bike = new Bike(32, 2);
        vehicles.add(car);
        vehicles.add(bike);
        vehicles.add(car2);
        vehicles.forEach(Vehicle::go);

    }
}
