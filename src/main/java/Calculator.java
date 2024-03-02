import java.util.HashSet;
import java.util.Set;

public class Calculator {
    public int add(int a,int b){
        return a+b;
    }

    public int multiply(int a, int b){
        return a*b;
    }

    public Set<Integer> digitsSet(int number) {
        Set<Integer> wert = new HashSet<Integer>();
        String numberString = String.valueOf(number);

        for (int i = 0; i < numberString.length(); i++) {
            if (numberString.charAt(i) != '-') {
                wert.add(Integer.parseInt(numberString, i, i + 1, 10));
            }
        }
        return wert;
    }
}
