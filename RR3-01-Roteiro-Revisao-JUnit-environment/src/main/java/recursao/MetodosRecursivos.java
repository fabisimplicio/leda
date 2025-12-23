package recursao;

public class MetodosRecursivos {

	public int calcularSomaArray(int[] array) {
    	return calcularSomaArray(array, 0);
	}

	private int calcularSomaArray(int[] array, int index) {
    	if (index >= array.length) return 0;
    	return array[index] + calcularSomaArray(array, index + 1);
	}

	public long calcularFatorial(int n) {
    	if (n <= 1) {
       		return 1;
    	}
    	long resultado = n * calcularFatorial(n - 1);
    	return resultado;
	}

	public int calcularFibonacci(int n) {
    	if (n <= 2) {
        	return 1;
    	}
    	int resultado = calcularFibonacci(n - 1) + calcularFibonacci(n - 2);
    	return resultado;
	}

	public int countNotNull(Object[] array) {
    	return countNotNull(array, 0);
	}

	private int countNotNull(Object[] array, int index) {
    	if (index >= array.length) {
        	return 0;
    	}

    	if (array[index] != null) {
        	return 1 + countNotNull(array, index + 1);
    	} else {
        	return countNotNull(array, index + 1);
    	}
	}

	public long potenciaDe2(int expoente) {
    	if (expoente == 0) return 1;
    	return 2 * potenciaDe2(expoente - 1);
	}

	public double progressaoAritmetica(double termoInicial, double razao, int n) {
    	if (n == 1) return termoInicial;
    	return razao + progressaoAritmetica(termoInicial, razao, n - 1);
	}


	public double progressaoGeometrica(double termoInicial, double razao, int n) {
    	if (n == 1) return termoInicial;
    	return razao * progressaoGeometrica(termoInicial, razao, n - 1);
	}
	
	
}
