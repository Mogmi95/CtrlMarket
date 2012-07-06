/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dev
 */
public class MD5Utility {
    
    public static String computeHash(String login, String pass) {
        try {
            return MD5(login + pass);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MD5Utility.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MD5Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    private static String convertToHex(byte[] data) {        
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < data.length; i++) {            
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {                
                if ((0 <= halfbyte) && (halfbyte <= 9)) {
                    buf.append((char) ('0' + halfbyte));
                } else {
                    buf.append((char) ('a' + (halfbyte - 10)));
                }
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }        
        return buf.toString();
    }    
    
    public static String MD5(String text)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {        
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        byte[] md5hash = new byte[32];
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        md5hash = md.digest();
        return convertToHex(md5hash);
    }
}
