/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RandomGenerator;

import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author nipun
 */
public class RandomGenerator {
    
    
    
      
    public static int generateRandomId(){
       return new Random(System.currentTimeMillis()).nextInt(99999999);
    }
    
      public static String generateRandomPassword() {
        int len = 10;
        int randNumOrigin = 48, randNumBound = 122;

       return passwordGenerator(len, randNumOrigin, randNumBound);
    }

    private static String passwordGenerator(int len, int randNumOrigin, int randNumBound) {
        SecureRandom random = new SecureRandom();
        return random.ints(randNumOrigin, randNumBound + 1)
                .filter(i -> Character.isAlphabetic(i) || Character.isDigit(i))
                .limit(len)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }

    public static void main(String[] args) {
        System.out.println(generateRandomId());
        System.out.println(generateRandomPassword());
    }
    
//  char[] chars = "abcdefghijklmnopqrstuvwxyzABSDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
//Random r = new Random(System.currentTimeMillis());
//char[] id = new char[8];
//for (int i = 0;  i < 8;  i++) {
//    id[i] = chars[r.nextInt(chars.length)];
//}
//return new String(id);
  
}
