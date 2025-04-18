import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

class Student {
    String name;
    int rank;
    String[] preference;
    String alloctionProgram;

    Student(String name, int rank, String[] givenPreference) {
        this.name = name;
        this.rank = rank;
        preference = new String[5];
        alloctionProgram = null;

        for (int i = 0; i < givenPreference.length; i++) {
            preference[i] = givenPreference[i];
        }
    }
}

class Program {
    String programName;
    int capacity;

    Program(String programName, int capacity) {
        this.programName = programName;
        this.capacity = capacity;
    }
}

class College {
    private static College instance;
    List<Student> studentEnrollList;
    HashMap<String, Integer> programs;

    private College() {
        studentEnrollList = new ArrayList<>();
        programs = new HashMap<>();

        programs.put("CSE", 2);
        programs.put("IT", 3);
        programs.put("ME", 5);
        programs.put("EE", 5);
        programs.put("ECE", 1);
        programs.put("CE", 10);
    }

    public static College getInstance() {
        if (instance == null) {
            instance = new College();
        }
        return instance;
    }

    void displayStudent() {
        System.out.println("\nCollege Enrolled Students With Program");
        for (int i = 0; i < studentEnrollList.size(); i++) {
            System.out.println(studentEnrollList.get(i).name + "  -->  " + studentEnrollList.get(i).alloctionProgram);
        }
    }

    void displayProgram() {
        System.out.println("\nSeat available");
        for (HashMap.Entry<String, Integer> e : programs.entrySet()) {
            String program = e.getKey();
            int vacantSeat = e.getValue();
            System.out.println(program + "  -->  " + vacantSeat + " ");
        }
    }
}

interface Queue {
    void enqueue(Student student);

    Student dequeue();
}

class Counseling {
    College college = College.getInstance();
    QueueImplement queue;

    void addStudentInQueue(ArrayList<Student> students) {
        queue = new QueueImplement(students.size());
        for (Student student : students) {
            queue.enqueue(student);
        }
    }

    void allocationProgram() {
        int idx = 0;

        while (!queue.isEmpty()) {
            Student student = queue.dequeue();
            // this is preference array loop which prefrence give student
            while (idx < student.preference.length) {
                String preference = student.preference[idx];

                if (college.programs.getOrDefault(preference, 0) > 0) {
                    college.programs.put(preference, college.programs.get(preference) - 1);
                    student.alloctionProgram = preference;
                    addStudentInCollege(student);
                    break;
                }
                idx++;
            }
        }
    }

    void addStudentInCollege(Student enrollStudent) {
        college.studentEnrollList.add(enrollStudent);
    }
}

class QueueImplement implements Queue {
    private Student[] queue;
    private int capacity, rear, front, size;
    Counseling counseling;

    QueueImplement(int capacity) {
        this.capacity = capacity;
        queue = new Student[capacity];
        counseling = new Counseling();
        rear = -1;
        front = 0;
        size = 0;
    }

    @Override
    public void enqueue(Student student) {
        rear = (rear + 1) % capacity;
        size++;
        queue[rear] = student;
    }

    @Override
    public Student dequeue() {
        if (size == 0) {
            System.out.println("Queue is UnderFlow.");
            return null;
        }
        Student student = queue[front];
        front = (front + 1) % capacity;
        size--;
        return student;
    }

    public boolean isEmpty() {
        if (size == 0)
            return true;
        return false;
    }

    public void display() {
        if (size == 0) {
            System.out.println("Queue is Empty.");
            return;
        }
        System.out.println("Rank-wise Sorted students list:");
        int temp = front;
        while (temp != rear) {
            System.out.print(queue[temp].name + "->");
            temp = (temp + 1) % capacity;
        }
        System.out.println(queue[temp].name);
    }
}

public class CounselingProcess {
    public static void main(String[] args) {
        ArrayList<Student> studentsDetails = new ArrayList<>();
        Counseling counseler = new Counseling();

        String[] pref = { "CSE", "IT", "ME", "ECE", "EE" };
        studentsDetails.add(new Student("Aarohan", 5, pref));
        studentsDetails.add(new Student("Ram", 1, pref));
        studentsDetails.add(new Student("Mohan", 2, pref));
        studentsDetails.add(new Student("Rohan", 4, pref));
        studentsDetails.add(new Student("Sohan", 3, pref));
        studentsDetails.add(new Student("John", 6, pref));

        // QueueImplement queue = new QueueImplement(studentsDetails.size());
        Collections.sort(studentsDetails, Comparator.comparingInt(c -> c.rank));
        counseler.addStudentInQueue(studentsDetails);
        counseler.allocationProgram();

        College college = College.getInstance();
        college.displayProgram();
        college.displayStudent();
    }
}
