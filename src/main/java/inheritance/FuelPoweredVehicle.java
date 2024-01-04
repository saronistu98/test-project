package inheritance;

public class FuelPoweredVehicle extends Vehicle {

    double tankCapacity;

    FuelPoweredVehicle(double speed, int numberOfWheels, double tankCapacity) {
        super(speed, numberOfWheels);
        this.tankCapacity = tankCapacity;
    }

    FuelPoweredVehicle() {
    }

    @Override
    void go() {
        System.out.println("The fuel is burning");
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n   - tankCapacity: " + tankCapacity;
    }
}
