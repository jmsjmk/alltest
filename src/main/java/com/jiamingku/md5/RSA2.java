package com.jiamingku.md5;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSA2 {

    public static String pu = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkLrgtsMbgLfH+glclQ8h91PdqjUL9dHeb2NLQW2d0REOn9fxYW1vd53yOhtWB2DWUgcoNn/MWuDPjT+yFiW+Sff8XSfY2nruj7eEkahyV5xs80nDNhiurvENeaw6uN9skurKavPOYffR/BMlWh8e+MVSwp+7hQJWNFSxU5NRBrxTGs7+sA5FNYTax9/jntnIMGnBo92fEYGSqZ4eQlwNfrfEArMg6hDS2QXOWlHI/iH3UT/GVIk1kSp5bQFGb9pZABHQJ/xJvXcMyADPC38nHyqcWl4lghhugnBEz08OAGP4D81Bf9ZA0vJtOObrS4jwvqER0XBtxGIsGYRz2JyOkwIDAQAB";
    public static String pr = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCQuuC2wxuAt8f6CVyVDyH3U92qNQv10d5vY0tBbZ3REQ6f1/FhbW93nfI6G1YHYNZSByg2f8xa4M+NP7IWJb5J9/xdJ9jaeu6Pt4SRqHJXnGzzScM2GK6u8Q15rDq432yS6spq885h99H8EyVaHx74xVLCn7uFAlY0VLFTk1EGvFMazv6wDkU1hNrH3+Oe2cgwacGj3Z8RgZKpnh5CXA1+t8QCsyDqENLZBc5aUcj+IfdRP8ZUiTWRKnltAUZv2lkAEdAn/Em9dwzIAM8LfycfKpxaXiWCGG6CcETPTw4AY/gPzUF/1kDS8m045utLiPC+oRHRcG3EYiwZhHPYnI6TAgMBAAECggEAPgfqskpzajrauh7zEGwY+cF3OzFbO/PEa2daZOOPhYDTDM1oBoL6CIGoxSDcQXNOyH+Ky7kApF7AdmC7emSY52kjP+YJOrL/tpdIeYmB4G0KAUg3I+N2U0K7rM+Vp5ETHvEE6swuQ60M0gZ+tt6pzqP8MbB2la6HtgwqqJB2ZPT/5xIxz/kG1lnPE5FBYVu8LetBavhDz6eRB8+p9y37XLQ5HSlI2xQCQpnsaZUP1YIErL1M670zlGMylxfziCqme0sTlCO9EVLjE8ov3Sl8SpBN1V34tH+xY354pcS4/+nzFfYwQ8aSFO9upbw97U5FycDMfpuwJG+70z+PuvqPiQKBgQDtPL4cnE0bMNAY7AO90/t1RUjkjA1xJfHXM5wxfVB0hrox+7vHR7b1hBCpL9iyGeLsZcovGMMrxn/w+YQTp4TvwcFjoNkgIwTL+fGdPQVfZ4r0dP8KDc5LNaulONubks32thQt188fNt569ShYEFRLu49DAbQ6/j1W0k/o7ua1TwKBgQCcLSxxz46JyJ8W86c98qGchCoxdU5tTIL1HKq3U8mZU7dp0oVx0bLsIulcXTb94L0fXUrCMizwtHLR7LU75TbBVGRbb/eKo2fZvSjochgaO+Wu71WzeDkh5QakonWul5ei/HCXizO+vNQbfDcQVSasEN8rOPsMy5hZaVVOp/XJfQKBgQCG9QEpgXkTuMcJBmpyeL8ZzVpUOaAD+BQF2mRGewVj5KjTemMuUIuyS3i25nMdjkBbPuSvuiR9KKTrzuXl0Gx9f/ml1YDvGTzHVN6wFmCSD3Mq8cuMSqJKgrOsgBXeALuOV7JPCe/Xz/zscb6Lqm5pzr8G3VdMIAmA9I9k8VLpXQKBgQCTIWemjMAfA/cEm2SqUqN4HXbWg0nwZE1Xd7tI3NGm9hxrwF3hPYoPNlCcqGB/SrNBEhW036pA1e7538NZevZbqhKlpdUSMiFLGw6n9ZzJUjZu2KDnY9KHIQliHn85jSzY6nN0ATstbR1yC1Kgu2ekYHyubo7snf2X9kjcwhUmzQKBgDFZ7Na6A+1Kwwz0SfnZW4YQuBAQTCBb7eTdih8HuIqlt8YSKwbx9z1b9ddLL34rKRL36Ic80YglUAsPY9hjDHqP5Wd/tqUpPbzujNxIvKWExzZOTfIhuPthVGdYpD+UtSXnRM7xXkVfLd/G9Jz6LmtXNqqarbF2kp682+Wqee/F";


    public static void main(String[] args) {
        String src = "马保国";
        try {
            //1.初始化密钥
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

            //String strPublicKey = Base64.encodeBase64String(rsaPublicKey.getEncoded());
            //String strPrivateKey = Base64.encodeBase64String(rsaPrivateKey.getEncoded());

            String strPublicKey = pu;

            String strPrivateKey = pr;

            System.out.println("Public Key : " + strPublicKey);
            System.out.println("Private Key : " + strPrivateKey);


            //1.私钥加密加密
            byte[] keyPrivateBytes = Base64.decodeBase64(strPrivateKey);
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyPrivateBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("私钥加密、公钥解密——加密 : " + Base64.encodeBase64String(result));


            //2.公钥解密
            byte[] keyPublicBytes = Base64.decodeBase64(strPublicKey);
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyPublicBytes);
            keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            result = cipher.doFinal(result);
            System.out.println("私钥加密、公钥解密——解密：" + new String(result));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
