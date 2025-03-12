class HcfLcm{
    
        public int findHcfMethod(int num1, int num2){
            return num2 == 0 ? num1 : findHcfMethod(num2, num2 % num1);
        }
        
        int count = 0;
        int val1 = 0;
        int val2 = 0;
        public int findLcmMethod(int num1, int num2){
            if(count == 0){
                val1 = num1;
                val2 = num2;
            }
            count++;

            if(num1 == num2) return num1;
            return num1 > num2 ? findLcmMethod(num1, num2+val2) : findLcmMethod(num1+val1, num2);
        }
}

public class LcmAndHcf {
    public static void main(String[] args) {
        HcfLcm val = new HcfLcm();
        int hcf = val.findHcfMethod(12,6);
        int lcm = val.findLcmMethod(12,18);
        System.out.println(hcf+" "+lcm);
    }
}
