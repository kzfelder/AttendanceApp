import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class Main {

    public static void main(String[] args)
    {
        System.out.println("\n\nHello, AttendanceApp!\n");

        // Welcome the user
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name: kswagthagoat");
        String name = "kswagthagoat";
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
        System.out.println("\nEnter a number to find the indexes of students with that amount of absences: 2");
        int num = 3;
        ArrayList ind = findIndexes(absences, num);
        System.out.println("The indexes of students with " + num + " absences are: " + ind);


        // Find the index(es) of the student(s) that FE'd the course
        System.out.println("\nHow many times does this course meet a week? 3");
        // I know the variable has a bad name... need help
        int meetFrequency = 3;
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


        // Change absences in a ArrayList
        // Which variables to use for this function? (x & y)
        changeListValues(absences, 7,5);
        System.out.println("\nThe new absences: " + absences);

        // Sort the absences using a library function
        Collections.sort(absences);
        System.out.println("Sorted absences: " + absences);

        // Shuffle absences with a library function
        Collections.shuffle(absences);
        System.out.println("Shuffled absences: " + absences + "\n");

        //  Find how many of each absences
        // 1. Create Sorted
        ArrayList<Integer> duplicateAbs = absences;
        Collections.sort(duplicateAbs);

        // 2. Count amount & add to map
        Map<Integer, Integer> absenceFrequency = new HashMap<>();
        for (int i = 0; i < duplicateAbs.size(); i++)
        {
            int frq = countFrequency(duplicateAbs.get(i), duplicateAbs);
            absenceFrequency.putIfAbsent(duplicateAbs.get(i), frq);
        }
        // 3. Obtain val & print amount of *'s w/ key
        for (Map.Entry<Integer,Integer> entry: absenceFrequency.entrySet())
        {
            int key = entry.getKey();
            int val = entry.getValue();
            String stars = createStars(val);
            System.out.println(key + " " + stars);
        }


        // Find the amount of unique absences
        int numUniques = findUniques(absences);
        System.out.println("\nNumber of unique absences: " + numUniques);


        // Sort absences with a user-defined sort
        sort(absences);
        System.out.println("\nUser sorted absences: " + absences);

        // Shuffle absences with a user-defined shuffle
        shuffle(absences);
        System.out.println("User shuffled absences: " + absences);


        // Create and output an ArrayList of 5 distinct names.
        ArrayList<String> names = new ArrayList<>();
        names.add("Joe");
        names.add("Peggie");
        names.add("Kyazia");
        names.add("Kyrah");
        names.add("Khalil");
        System.out.println("\nNames: " + names);

        // Shuffle the names using a user-defined shuffle
        strShuffle(names);
        System.out.println("Shuffled names: " + names);

        // Using the 5 names, create another list that has the same size as the absences list
        ArrayList<String> newNames = lengthenList(absences.size(), names);
        System.out.println("New name list: " + newNames);

        // Check if all  5 names were used at least once
        if(checkUsage(names, newNames) == true)
        {
            System.out.println("All names were used at least once.");
        }
        else
        {
            System.out.println("All names weren't used at least once.");
        }


        // What are the names of the students with perfect attendance?
        // 1. Get indexes of those with perfect attendance
        ArrayList<Integer> perfAttenIndexes = findIndexes(absences, 0);
        // 2. Add the names with perfect attendance to a new list using the previous indexes and newName list
        ArrayList<String> perfAttenNames = new ArrayList<>();
        for (int index : perfAttenIndexes)
        {
            String perfName = newNames.get(index);
            perfAttenNames.add(perfName);
        }
        // 3. Output results
        System.out.println("\nStudents with perfect attendance: " + perfAttenNames);

        // What are the names of the students who have FE'd some course?
        ArrayList<Integer> feACourseIndexes = findIndexesOfFE(absences, fe);
        // 2. Add the names with perfect attendance to a new list using the previous indexes and newName list
        ArrayList<String> feACourseNames = new ArrayList<>();
        for (int index : feACourseIndexes)
        {
            String feName = newNames.get(index);
            feACourseNames.add(feName);
        }
        // 3. Output results
        System.out.println("Students who FE'd some course: " + feACourseNames);

        // How many courses does [name] have?
        System.out.println("\nEnter name of student: kyrah");
        String student = "kyrah";

        System.out.println("Number of courses " + student + " has: " + findNameIndexes(newNames, student).size());

        // Which courses did [name] FE?
        ArrayList<Integer> coursesStudentFE = new ArrayList<>();
        for ( int index : feACourseIndexes)
        {
            if (newNames.get(index).toLowerCase().equals(student.toLowerCase()))
            {
                coursesStudentFE.add(index);
            }
        }
        System.out.println("Courses " + student + " FE'd: " + coursesStudentFE);


        // Generate today's date and output it
        LocalDate today = LocalDate.now();
        System.out.println("\nToday's date: " + today);

        // How many days have you been alive?  Use date1.equals(date2) to check your result
        LocalDate birthDate = LocalDate.of(1998,12,23);
        long totalDays = daysAlive(today, birthDate);
        System.out.println("Days alive: " + totalDays);
        Boolean check = checkDays(totalDays, today, birthDate);
        System.out.println("Correct amount of days: " + check);
        
        // Create a list of LocalDate objects
        ArrayList<LocalDate> dates = generateDates(newNames, today);
        System.out.println("\nDates: " + dates + "\nNames: " + newNames);

        // What are the names of the students with the fewest absences?
        ArrayList<String> fewestAbsences = findNamesWithMin(absences, newNames);
        System.out.println("\nStudents with fewest absences: " + fewestAbsences);

        // What are the names of students who have the longest number of days since an absence?
        // Solution #1: Finds furthest date in the list
        LocalDate furthestDate = findFurthestDateFrom(dates, today);
        ArrayList<Integer> indexesWithFurthestDate = findIndexesOfDate(dates, furthestDate);
        System.out.println("Longest days since absence: " + findNamesAtIndexes(newNames, indexesWithFurthestDate));
        
        // Solution #2: Finds by longest number of days since last absence
        Map<String, ArrayList<LocalDate>> mapOfNamesAndDates = getNamesAndDates(newNames, dates);
        Map<String, LocalDate>recentStudentDates = getAllRecentStudentDates(mapOfNamesAndDates, dates, today);
        LocalDate furthestDateFromRecents = findFurthestDateFromWithMap(recentStudentDates, today);
        System.out.println("Longest days since last absence: " + findStudentsWithDateInMap(recentStudentDates, furthestDateFromRecents));


        // What is the range of absence dates?
        System.out.println("\nRange(in days): " + findRangeOfDates(dates, today));

        // What are the indexes of the students who have [X] absence date?
        LocalDate absenceDate = today; // or can enter own date here using LocalDate.of()
        ArrayList<Integer> indexesAtDate =  findIndexesOfDate(dates, absenceDate);
        System.out.println("Indexes of students with " + absenceDate + " absence date: " + indexesAtDate);
        

        // What are the indexes of the students who have the same absence date?
        System.out.println("Indexes with same date: " + getDatesAndIndexes(dates));
    }

    private static ArrayList<String> findStudentsWithDateInMap(Map<String, LocalDate> recentStudentDates, LocalDate furthestDateFromRecents)
    {
        ArrayList<String>studentsWithDate = new ArrayList<>();
        for(Map.Entry<String, LocalDate> entry : recentStudentDates.entrySet())
        {
            String key = entry.getKey();
            LocalDate val = entry.getValue();
            if(val.equals(furthestDateFromRecents))
            {
                studentsWithDate.add(key);
            }
        }
        return studentsWithDate;
    }

    private static LocalDate findFurthestDateFromWithMap(Map<String, LocalDate>datesAndNames, LocalDate today)
    {
        LocalDate furthestDate = today;
        for (Map.Entry<String, LocalDate> entry : datesAndNames.entrySet())
        {
            LocalDate val = entry.getValue();
            if(furthestDate.isAfter(val))
            {
                furthestDate = val;
            }
        }
        return furthestDate;
    }

    private static ArrayList<String> findStudentsWithDate(ArrayList<LocalDate> dates, ArrayList<String> names, LocalDate date)
    {
        ArrayList<Integer> dateIndexes = findIndexesOfDate(dates, date);
        ArrayList<String> namesAtIndexes = findNamesAtIndexes(names, dateIndexes);
        return namesAtIndexes;
    }

    private static Map<String, LocalDate>getAllRecentStudentDates(Map<String, ArrayList<LocalDate>> mapOfNamesAndDates, ArrayList<LocalDate>dates, LocalDate today)
    {
        Map<String, LocalDate> recentDates = new HashMap<>();
        for(Map.Entry<String,ArrayList<LocalDate>> entry: mapOfNamesAndDates.entrySet())
        {
            String key = entry.getKey();
            ArrayList<LocalDate> val = entry.getValue();
            recentDates.putIfAbsent(key, getMostRecentDate(val, today));
        }
        return recentDates;
    }

    private static LocalDate getMostRecentDate(ArrayList<LocalDate>dates, LocalDate today)
    {
        LocalDate recentDate = dates.get(0);
        for(LocalDate date : dates)
        {
            if(today.compareTo(date) < today.compareTo(recentDate))
            {
                recentDate = date;
            }
        }
        return recentDate;
    }

    private static ArrayList<LocalDate> findDatesAtIndexes(ArrayList<LocalDate> dates, ArrayList<Integer> indexes)
    {
        ArrayList<LocalDate> datesAtIndexes = new ArrayList<>();
        for(int index : indexes)
        {
            datesAtIndexes.add(dates.get(index));
        }
        return datesAtIndexes;
    }

    private static Map<String, ArrayList<LocalDate>> getNamesAndDates(ArrayList<String> names, ArrayList<LocalDate> dates)
    {
        Map<String, ArrayList<LocalDate>> studentDates = new HashMap<>();
        for (int i = 0; i < names.size(); i++)
        {
            ArrayList<Integer> nameIndexes = findNameIndexes(names, names.get(i));
            ArrayList<LocalDate>datesFromIndexes = findDatesAtIndexes(dates, nameIndexes);
            studentDates.putIfAbsent(names.get(i), datesFromIndexes);
        }
        return studentDates;
    }

    private static Map<LocalDate, ArrayList<Integer>> getDatesAndIndexes(ArrayList<LocalDate> dates)
    {
        Map<LocalDate, ArrayList<Integer>> studentsWithDate = new HashMap<>();
        for (int i = 0; i < dates.size(); i++)
        {
            ArrayList<Integer> dateIndexes = findIndexesOfDate(dates, dates.get(i));
            studentsWithDate.putIfAbsent(dates.get(i), dateIndexes);
        }
        return studentsWithDate;
    }

    private static ArrayList<String> findNamesAtIndexes(ArrayList<String> newNames, ArrayList<Integer> indexes)
    {
        ArrayList<String> namesAtIndexes = new ArrayList<>();
        for (int index : indexes)
        {
            namesAtIndexes.add(newNames.get(index));
        }
        return  namesAtIndexes;
    }

    private static ArrayList<Integer> findIndexesOfDate(ArrayList<LocalDate> dates, LocalDate furthestDate)
    {
        ArrayList<Integer> dateIndexes = new ArrayList<>();
        for (int i = 0; i < dates.size(); i++)
        {
            if(dates.get(i).equals(furthestDate))
            {
                dateIndexes.add(i);
            }
        }
        return dateIndexes;
    }

    private static LocalDate findFurthestDateFrom(ArrayList<LocalDate> dates, LocalDate today)
    {
        LocalDate furthestDate = today;
        for (LocalDate date : dates)
        {
            if(furthestDate.isAfter(date))
            {
                furthestDate = date;
            }
        }
        return furthestDate;
    }

    private static int findRangeOfDates(ArrayList<LocalDate> dates, LocalDate today)
    {
        ArrayList<Integer> ranges = new ArrayList<>();
        for (int i = 0; i < dates.size(); i++)
        {
            ranges.add(today.compareTo(dates.get(i)));
        }
        return (Collections.max(ranges) - Collections.min(ranges));
    }

    private static ArrayList<LocalDate> generateDates(ArrayList<String> newNames, LocalDate today)
    {
        ArrayList<LocalDate> dates = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < newNames.size(); i++)
        {
            dates.add(today.minusDays(rand.nextInt(21)));
        }
        return dates;
    }

    private static ArrayList<String> findNamesWithMin(ArrayList<Integer> absences, ArrayList<String> newNames)
    {
        ArrayList<String> namesWithMin = new ArrayList<>();
        int min = Collections.min(absences);
        for (int i = 0; i < absences.size(); i++)
        {
            if (absences.get(i).equals(min))
            {
                namesWithMin.add(newNames.get(i));
            }
        }
        return namesWithMin;
    }


    private static Boolean checkDays(long totalDays, LocalDate today, LocalDate birthDate)
    {
        return today.equals(birthDate.plusDays(totalDays));
    }

    private static long daysAlive(LocalDate today, LocalDate birthDate)
    {
        long daysFromToday = today.toEpochDay();
        long daysFromBirth = birthDate.toEpochDay();
        long days = daysFromToday - daysFromBirth;
        return days;
    }

    private static ArrayList<Integer> findNameIndexes(ArrayList<String> newNames, String student)
    {
        ArrayList<Integer>indexes = new ArrayList<>();
        for (int i = 0; i < newNames.size(); i++)
        {
            if(newNames.get(i).toLowerCase().equals(student.toLowerCase()))
            {
                indexes.add(i);
            }
        }
        return indexes;
    }


    private static int countFrequency(int num, ArrayList<Integer> absences)
    {
        int count = 0;
        for (int i = 0; i < absences.size(); i++)
        {
            if (absences.get(i) == num) 
            { 
                count++; 
            }

        }
        return count;
    }
    
    private static String createStars(int num)
    {
        String stars = "";
        for (int j = 0; j < num; j++)
        {
            stars += "*";
        }
        return stars;
    }


    private static int findUniques(ArrayList<Integer> absences)
    {
        int numUniques = 0;
        for (int i = 0; i < absences.size(); i++)
        {
            int count = 0;
            for (int j = 0; j < absences.size(); j++)
            {
                if(absences.get(i) == absences.get(j))
                {
                    count++;
                }
            }
            if(count == 1)
            {
                numUniques++;
            }
        }
        return numUniques;
    }

    private static void sort(ArrayList<Integer> absences)
    {
        for (int i = 0; i < absences.size(); i++)
        {
            for (int j = 0; j < absences.size(); j++)
            {
                if(absences.get(i) < absences.get(j))
                {
                    int temp = absences.get(i);
                    absences.set(i,absences.get(j));
                    absences.set(j,temp);
                }
            }
        }
    }

    private static Boolean checkUsage(ArrayList<String>names, ArrayList<String>newNames)
    {
        Set<String> seen = new HashSet<>();
        for (int i = 0; i < names.size(); i++)
        {
            for (int j = 0; j < newNames.size(); j++)
            {
                if(names.get(i).equals(newNames.get(j)))
                {
                    seen.add(names.get(i));
                }
            }
        }
        return seen.size() == names.size();
    }

    private static ArrayList<String> lengthenList(int size, ArrayList<String> names)
    {
        ArrayList<String> newNames = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < size; i++)
        {
            newNames.add(names.get(rand.nextInt(names.size())));
        }
        return newNames;
    }

    private static void strShuffle(ArrayList<String> names)
    {
        Random rand = new Random();
        for (int i = 0; i < names.size(); i++)
        {
            int index = rand.nextInt(names.size());
            String temp = names.get(i);
            names.set(i,names.get(index));
            names.set(index,temp);
        }
    }

    private static void shuffle(ArrayList<Integer> absences)
    {
        Random rand = new Random();
        for (int i = 0; i < absences.size(); i++)
        {
            int index = rand.nextInt(absences.size());
            int temp = absences.get(i);
            absences.set(i,absences.get(index));
            absences.set(index,temp);
        }
    }

    // Function to change the values (add or subtract in a list up to 15 or down to 0)
    private static void changeListValues(ArrayList<Integer> absences, int x, int y)
    {
        for (int i = 0; i < absences.size(); i++)
        {
            int element;
            if(absences.get(i) > y)
            {
                element = absences.get(i) + x;
                if(element > 15)
                {
                    element = 15;
                }
                else if(element < 0)
                {
                    element = 0;
                }
                absences.set(i, element);
            }
        }
    }

    // Function to create a list of absences that haven't FE'd
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
