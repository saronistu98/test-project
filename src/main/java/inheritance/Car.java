package inheritance;

public class Car extends FuelPoweredVehicle {

    private int numberOfDoors;

    Car(int numberOfDoors, double speed, double tankCapacity, int numberOfWheels) {
        super(speed, numberOfWheels, tankCapacity);
        this.numberOfDoors = numberOfDoors;
    }

    Car(Car anotherCar) {
        this.copy(anotherCar);
    }

    void go() {
        super.go();
        System.out.println("Car is moving");
    }

    void explode() {
        System.out.println("Car goes BOOOM!");
    }

    private void copy(Car car) {
        this.numberOfDoors = car.numberOfDoors;
        this.tankCapacity = car.tankCapacity;
        this.speed = car.speed;
        this.numberOfWheels = car.numberOfWheels;
    }

    @Override
    public String toString() {
        return "Car:\n " + super.toString() + "\n   - numberOfDoors: " + numberOfDoors;
    }

}
