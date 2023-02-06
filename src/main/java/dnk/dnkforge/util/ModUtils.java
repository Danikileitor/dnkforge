package dnk.dnkforge.util;

public class ModUtils {
    public static int generarAleatorio(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    public static int comprobarAciertos(int[] numeros1, int[] numeros2) {
        int aciertos = 0;
        for (int i = 0; i < numeros1.length; i++) {
            for (int j = 0; j < numeros2.length; j++) {
                if (numeros1[i] == numeros2[j]) {
                    aciertos++;
                }
            }
        }
        return aciertos;
    }

    public static boolean estaRepetido(int numero, int[] numeros, int pos) {
        boolean repetido = false;
        for (int i = 0; i < numeros.length; i++) {
            if (i == pos) {
                continue;
            }
            if (numero == numeros[i]) {
                repetido = true;
            }
        }
        return repetido;
    }
}
