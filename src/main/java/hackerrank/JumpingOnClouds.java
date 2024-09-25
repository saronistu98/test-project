package hackerrank;

import java.util.List;

public class JumpingOnClouds {

    public static void main(String[] args) {

        System.out.println(jumpingOnClouds(List.of(0, 0, 1, 0, 0, 1, 0)));

    }

    private static int jumpingOnClouds(List<Integer> c) {
        int jumpCount = 0;
        int currentIndex = 0;
        while (currentIndex <= c.size() - 1) {
            if (c.size() - currentIndex > 2) {
                if (c.get(currentIndex + 2) == 0) {
                    jumpCount++;
                    currentIndex += 2;
                    continue;
                }
            }
            if (c.size() - currentIndex > 1) {
                if (c.get(currentIndex + 1) == 0) {
                    jumpCount++;
                    currentIndex++;
                    continue;
                }
            }
            break;
        }

        return jumpCount;
    }

}
