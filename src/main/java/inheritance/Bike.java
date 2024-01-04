package inheritance;

class Bike extends Vehicle {

    Bike(double speed, int numberOfWheels) {
        super(speed, numberOfWheels);
    }

    @Override
    void go() {
        System.out.println("Bike is moving");
    }

}
