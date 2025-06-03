package util;

import org.mindrot.jbcrypt.BCrypt;

public class HashUtil {

    // Genera un hash de la contraseña usando BCrypt
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
    }

    // Verifica si la contraseña ingresada coincide con el hash almacenado
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        if (hashedPassword == null || !hashedPassword.startsWith("$2a$")) {
            throw new IllegalArgumentException("Hash inválido para verificar");
        }
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    // Método de prueba
    public static void main(String[] args) {
        String password = "1234";

        // Generar hash
        String hashed = hashPassword(password);
        System.out.println("Hash generado: " + hashed);

        // Verificar contraseña
        boolean match = checkPassword("1234", hashed);
        System.out.println("¿La contraseña coincide? " + match);
    }
}
