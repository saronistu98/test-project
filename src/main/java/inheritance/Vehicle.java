package inheritance;

public abstract class Vehicle {

    protected double speed;
    protected int numberOfWheels;

    Vehicle(double speed, int numberOfWheels) {
        this.speed = speed;
        this.numberOfWheels = numberOfWheels;
    }

    Vehicle() {

    }

    abstract void go();

    void stop() {
        System.out.println("Stopped");
    }

    @Override
    public String toString() {
        return "  - speed: " + speed +
                "\n   - numberOfWheels: " + numberOfWheels;
    }
}
