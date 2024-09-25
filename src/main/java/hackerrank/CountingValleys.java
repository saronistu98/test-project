package hackerrank;

public class CountingValleys {

    public static void main(String[] args) {

        System.out.println(countingValleys("UDDDUDUU"));

    }

    private static int countingValleys(String path) {
        int previousLevel;
        int currentLevel = 0;
        int valleyCount = 0;
        for (int i = 0; i < path.length(); i++) {
            char currentMovement = path.charAt(i);
            previousLevel = currentLevel;
            if (currentMovement == 'U')
                currentLevel++;
            else
                currentLevel--;
            if (previousLevel < 0 && currentLevel == 0)
                valleyCount++;
        }
        return valleyCount;
    }

}