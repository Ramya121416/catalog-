import org.json.JSONObject;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Codechef {

    public static void main(String[] args) throws Exception {
        
        
        JSONObject testCase1 = readJSON("input1.json");
        BigInteger secret1 = findSecret(testCase1);
        System.out.println("Secret for the first test case: " + secret1);  // 3

      
        JSONObject testCase2 = readJSON("input2.json");
        BigInteger secret2 = findSecret(testCase2);
        System.out.println("Secret for the second test case: " + secret2);
    }

    
    private static JSONObject readJSON(String filePath) throws IOException {
        ClassLoader classLoader = Codechef.class.getClassLoader();
        File file = new File(classLoader.getResource(filePath).getFile());

        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder jsonString = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonString.append(line);
        }
        reader.close();

        return new JSONObject(jsonString.toString());
    }

    
    private static BigInteger findSecret(JSONObject jsonData) {
        JSONObject keys = jsonData.getJSONObject("keys");
        int n = keys.getInt("n");
        int k = keys.getInt("k");

        
        List<int[]> points = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            JSONObject root = jsonData.getJSONObject(String.valueOf(i));
            int base = Integer.parseInt(root.getString("base"));
            String value = root.getString("value");

            int x = i;
            int y = decodeBase(value, base);
            points.add(new int[]{x, y});
        }

      
        return calculateSecret(points, n, k);
    }

    
    private static int decodeBase(String value, int base) {
        return new BigInteger(value, base).intValue();
    }

    
    private static BigInteger calculateSecret(List<int[]> points, int n, int k) {
        BigInteger secret = BigInteger.ZERO;

        for (int i = 0; i < k; i++) {
            int xi = points.get(i)[0];
            int yi = BigInteger.valueOf(points.get(i)[1]).mod(BigInteger.valueOf(256)).intValue();

            
            BigInteger numerator = BigInteger.ONE;
            BigInteger denominator = BigInteger.ONE;

            for (int j = 0; j < k; j++) {
                if (i != j) {
                    int xj = points.get(j)[0];
                    numerator = numerator.multiply(BigInteger.valueOf(0 - xj));
                    denominator = denominator.multiply(BigInteger.valueOf(xi - xj));
                }
            }

          
            BigInteger li = numerator.divide(denominator);
            BigInteger term = li.multiply(BigInteger.valueOf(yi));

            
            secret = secret.add(term);
        }

        return secret.mod(BigInteger.valueOf(256)); 
    }
}
// output input1 - 3
