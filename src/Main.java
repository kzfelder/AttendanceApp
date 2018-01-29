import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
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
        /*System.out.println("How many 3's: " + absences.stream().filter(p->p==3).count());
        System.out.println("How many > 3: " + absences.stream().filter(p->p>3).count());
        System.out.println("How many < 3: " + absences.stream().filter(p->p<3).count());*/

        // Number of students with perfect attendance
        int countPerf = 0;
        for (int i = 0; i < absences.size(); i++)
        {
            if(absences.get(i) == 0)
            {
                countPerf++;
            }
        }
        System.out.println("Amount of students with perfect attendance: " + countPerf);

        // Calculate the average of the absences
        double avg = average(absences);
        System.out.println("The average of the absences: " + avg);

        // Calculate the percentage of students with 3 or less absences
        int threeOrLess = countLessThan(absences,3);
        double percentage = percentage(countPerf, threeOrLess);
        System.out.println("Percentage of students with less than 3 absences that have perfect attendance: " + percentage + "%");

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
        ArrayList listOfFE = findIndexesOfFE(absences, fe);
        // Find the percentage of students that FE'd
        double FEPercentage = percentage(listOfFE.size(), absences.size());
        System.out.println("The indexes of students that FE'd: " + listOfFE);
        System.out.printf("The percentage of students that FE'd: %.2f%%", FEPercentage);

        // Find the average of the non-FE'd absences
        // HELP: function is too specific :-(, how can fix this?
        ArrayList<Integer> nonFE = createListOfNonFE(absences, fe);
        System.out.println("\nThe average of non-FE'd absences: " + average(nonFE));

    }

    private static ArrayList createListOfNonFE(ArrayList<Integer> absences, int fe)
    {
        ArrayList<Integer> nonFEAbsences = new ArrayList<>();
        for (int i = 0; i <absences.size(); i++)
        {
            if (absences.get(i) < fe)
            {
                nonFEAbsences.add(absences.get(i));
            }
        }
        return nonFEAbsences;
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
    private static double percentage(int num, int total)
    {
        double fraction = (double)num/total;

        return fraction*100;
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

    // Function that counts the amount of students that had less than a certain number of absences
    private static int countLessThan(ArrayList<Integer> absences, int num)
    {
        int count = 0;
        for (int i = 0; i < absences.size(); i++)
        {
            if(absences.get(i) < num)
            {
                count++;
            }
        }

        return count;
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
