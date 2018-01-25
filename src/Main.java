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
        System.out.println("\nThe elements are: " + absences);

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

        // Find the index(es) of the students who had a specified number of absences
        System.out.print("\nEnter a number to find the indexes of students with that amount of absences: ");
        int num = sc.nextInt();
        ArrayList ind = findIndexes(absences, num);
        System.out.println("The indexes of students with " + num + " absences are: " + ind);

        // Find the index(es) of the student(s) that FE'd the course
        System.out.print("\nHow many times does this course meet a week? ");
        // I know the variable has a bad name... need help
        int meetFrequency = sc.nextInt();
        int fe = meetFrequency * 2;
        System.out.println("The indexes of students that FE'd are: " + findIndexesOfFE(absences, fe));

    }
    // Function that calculates average
    private static double average(ArrayList<Integer> absences)
    {
        return (double)sum(absences)/absences.size();
    }

    // Function that calculates sum
    private static int sum(ArrayList<Integer> absences)
    {
        int sum = 0;
        for (int i = 0; i < absences.size(); i++)
        {
            sum+=absences.get(i);
        }
        return sum;
    }

    // Function that calculates percentage
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

    // Function that finds the index(es) of the students who had a specified number of absences
    private static ArrayList findIndexes(ArrayList<Integer> absences, int num)
    {
        ArrayList<Integer>indexes = new ArrayList<>();
        for (int i = 0; i < absences.size(); i++)
        {
            if(absences.get(i) == num)
            {
                indexes.add(i);
            }
        }
        return indexes;
    }

    // Function that finds the index(es) of students who FE'd
    // ASK ABOUT THIS, BASICALLY THE SAME FUNCTION EXCEPT ONE THING, A WAY TO KEEP ONLY ONE FUNCTION???
    private static ArrayList findIndexesOfFE(ArrayList<Integer> absences, int num)
    {
        ArrayList<Integer>indexes = new ArrayList<>();
        for (int i = 0; i < absences.size(); i++)
        {
            if(absences.get(i) > num)
            {
                indexes.add(i);
            }
        }
        return indexes;
    }




}
