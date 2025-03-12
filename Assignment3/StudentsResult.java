package Assignment3;
import java.util.*;

class Marksheet{

    double averageGrades(ArrayList<Integer> grades){
        int n = grades.size();
        double sumGrades = 0;

        for(int grade: grades){
            sumGrades += grade;
        }

        return (sumGrades / n);
    }

    int maxGrades(ArrayList<Integer> grades){
        int maxGrade = 0;

        for(int grade: grades){
            if(grade >= maxGrade) maxGrade = grade;
        }

        return maxGrade;
    }

    int minGrades(ArrayList<Integer> grades){
        int minGrade = Integer.MAX_VALUE;

        for(int grade: grades){
            if(grade <= minGrade) minGrade = grade;
        }

        return minGrade;
    }

    double passedPer(ArrayList<Integer> grades){
        int n = grades.size();
        int pass = 0;

        for(int grade: grades){
            if(grade >= 40) pass++;
        }

        double per = (pass*100) / n;
        return per;
    }
}

class StudentsResult{
    public static void main(String[] args){

        ArrayList<Integer> grades = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of student: ");
        int n = sc.nextInt();

        System.out.println("Enter the Marks: ");
        try{
            for(int i = 0; i < n; i++){
                int marks = sc.nextInt();
                if(marks < 0 || marks > 100) {
                    throw new ArithmeticException("marks will be range between 0 to 100.");
                }else grades.add(marks);
            }
            Marksheet result = new Marksheet();
            System.out.println("Average grades: "+String.format("%.2f",result.averageGrades(grades)));
            System.out.println("Max Grades: "+result.maxGrades(grades));
            System.out.println("Min Grades: "+result.minGrades(grades));
            System.out.println("Passed Percentage: "+String.format("%.2f",result.passedPer(grades)));
        }catch(ArithmeticException e){
            System.out.println(e.getMessage());
        }
    }
}