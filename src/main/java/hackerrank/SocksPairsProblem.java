package hackerrank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SocksPairsProblem {

    //Given an array of sock colors, return the number of pairs
    public static void main(String[] args) throws IOException {
        List<Integer> array = new ArrayList<>(List.of(1, 2, 1, 2, 1, 2, 3));
        int result = Result.sockMerchant(array);
        System.out.println(result);
    }

    static class Result {
        static int sockMerchant(List<Integer> array) {
            int pairCount = 0;
            array.sort(Comparator.comparing(Integer::intValue));
            for (int i = 0; i < array.size() - 1; ) {
                if (array.get(i).equals(array.get(i + 1))) {
                    pairCount++;
                    i += 2;
                    continue;
                }
                i++;
            }
            return pairCount;
        }

    }
}