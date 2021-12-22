package com.b0ve.daw.proyecto.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class EncriptacionServicio {

    private final static String SAL = "daW*Sal";
    private final static String SECRETO = "daW*Secreto";

    private final static EncriptacionServicio instance = new EncriptacionServicio();

    public static EncriptacionServicio instance() {
        return instance;
    }

    public String encriptar(String texto) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(texto.getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder();
            for (byte b : hash) {
                hex.append(String.format("%02X ", b));
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EncriptacionServicio.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    public boolean comparar(String hash1, String texto2) {
        String hash2 = encriptar(texto2);
        return hash1.equals(hash2);
    }

    public String codigoAleatorio() {
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        r.setSeed(System.nanoTime());
        for (int i = 0; i < 6; i++) {
            sb.append((char) (r.nextInt('Z' - 'A' + 1) + 'A'));
        }
        return sb.toString();
    }

    public String coficarSecreto(String secreto) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRETO.toCharArray(), SAL.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(secreto.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String decodificarSecreto(String texto) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRETO.toCharArray(), SAL.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(texto)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
