package Assignment6;

final class Poly {
    private int[] polynomial;

    Poly(int[] array){
        int n = array.length;
        polynomial = new int[n];

        for(int i = 0; i < n; i++){
            polynomial[i] = array[i];
        }
    }

    public int[] getPolynomial(){
        return polynomial;
    }

    float evaluate(float x) {
        int n = polynomial.length;
        float value = 0;

        for (int i = 0; i < n; i++) {
            value += polynomial[i] * Math.pow(x, (n - i - 1));
        }
        return value;
    }

    int degree() {
        int n = polynomial.length;
        int degreeOfPolynomial = n - 1;
        return degreeOfPolynomial;
    }

    Poly addPoly(Poly p1, Poly p2){
        int n = p1.polynomial.length;
        int m = p2.polynomial.length;

        int degree1 = n-1;
        int degree2 = m-1;

        int j = 0;
        int k = 0;

        int len = Math.max(n, m);
        int[] addPolynomial= new int[len];

        for(int i = 0; i < len; i++){
            if(degree1 > degree2){
                addPolynomial[i] = p1.polynomial[j++];
                degree1--;
            }else if(degree1 < degree2){
                addPolynomial[i] = p2.polynomial[k++];
                degree2--;
            }else{
                int add = p1.polynomial[j++] + p2.polynomial[k++];
                addPolynomial[i] = add;
                degree1--;
                degree2--;
            }
        }

        Poly addPoly = new Poly(addPolynomial);
        return addPoly;
    }

    Poly multiplyPoly(Poly p1, Poly p2){
        int n = p1.polynomial.length;
        int m = p2.polynomial.length;

        int degree1 = n-1;
        int degree2 = m-1;
        int[] multiplyPolynomial = new int[degree1+degree2+1];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                multiplyPolynomial[i+j] += p1.polynomial[i] * p2.polynomial[j];
            }
        }

        Poly multiplyPoly = new Poly(multiplyPolynomial);
        return multiplyPoly;
    }
}

public class Polynomial {
    public static void main(String[] args) {
        int[] array1 = {6,7,8};
        int[] array2 = {2,1};

        Poly p1 = new Poly(array1);
        System.out.println(p1.evaluate(2));
        System.out.println(p1.degree());

        Poly p2 = new Poly(array2);
        Poly addition = p1.addPoly(p1, p2);
        int[] additionSet = addition.getPolynomial();

        Poly multiplication = p1.multiplyPoly(p1, p2);
        int[] multiplicationSet = multiplication.getPolynomial();

        for(int i = 0; i < additionSet.length; i++){
            System.out.print(additionSet[i]+" ");
        }

        System.out.println();
        for(int i = 0; i < multiplicationSet.length; i++){
            System.out.print(multiplicationSet[i]+" ");
        }
    }
}
