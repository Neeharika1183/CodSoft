import java.util.Scanner;
class GradeCalculator {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of subjects: ");
        int num = sc.nextInt();
        int[] marks = new int[num];
        for (int i=0; i<num; i++) {
            System.out.print("Enter marks obtained in subject " + (i+1) + " (out of 100): ");
            marks[i]=sc.nextInt();
        }
        int total = 0;
        for (int mark : marks) {
            total+=mark;
        }
        double avgPercentage = (double) total/num;
        char grade;
        if (avgPercentage>=90) {
            grade='A';
        } else if (avgPercentage>=80) {
            grade='B';
        } else if (avgPercentage>=70) {
            grade='C';
        } else if (avgPercentage>=60) {
            grade='D';
        } else {
            grade='F';
        }
        System.out.println("\nResults:");
        System.out.println("Total Marks: " + total + " out of " + (num*100));
        System.out.printf("Average Percentage: %.2f%%\n", avgPercentage);
        System.out.println("Grade: " + grade);
        sc.close();
    }
}