import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("\n\nHello, AttendanceApp!\n");

        // Welcome the user
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.println("Welcome, " + name + "!");

        // Create and output list of absences
        ArrayList<Integer> absences = new ArrayList<>();
        Random rand = new Random();
        for(int i = 0; i < name.length(); i++)
        {
            absences.add(rand.nextInt(11));
        }
        System.out.println("The elements are: " + absences);

        // Number of students with perfect attendance
        int countPerf = 0;
        for (int i = 0; i < absences.size(); i++)
        {
            if(absences.get(i) == 0)
            {
                countPerf++;
            }
        }
        System.out.println("Students with perfect attendance: " + countPerf);

        // Calculate the average of the absences
        double avg = average(absences);
        System.out.println("The average of the absences is: " + avg);

        // Calculate the percentage of students with 3 or less absences
        double percentage = percentage(absences);
        System.out.println("Percentage of students with 3 or less absences: " + percentage + "%");

    }

    private static double average(ArrayList<Integer> absences)
    {
        return (double)sum(absences)/absences.size();
    }

    private static int sum(ArrayList<Integer> absences)
    {
        int sum = 0;
        for (int i = 0; i < absences.size(); i++)
        {
            sum+=absences.get(i);
        }
        return sum;
    }

    private static double percentage(ArrayList<Integer> absences)
    {
        int count = 0;
        for (int i = 0; i < absences.size(); i++)
        {
            if(absences.get(i) <= 3)
            {
                count++;
            }
        }
        return (double)count/absences.size();
    }



}
