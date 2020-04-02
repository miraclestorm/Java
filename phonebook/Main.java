package phonebook;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class Main {

    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static String[] names() throws IOException {
        String pathToFile = "C:\\Users\\Majid\\IdeaProjects\\Phone Book1\\Phone Book\\task\\src\\phonebook\\directory.txt";
        File dir = new File(pathToFile);
        Scanner dirScanner = new Scanner(dir);
        int x = 0;
        while (dirScanner.hasNext()){
            dirScanner.nextLine();
            x++;
        }
        String[] nameArray = new String[x];
        x = 0;
        dirScanner.close();
        dirScanner = new Scanner(dir);
        while (dirScanner.hasNext()){
            nameArray[x] = dirScanner.nextLine().replaceAll("\\d","").substring(1);
            x++;
        }
        return nameArray;
    }

    public static String[] quickSort(String[] array, int left, int right) {
        String tmp;
        int i = left;
        int j = right;
        String pivot = array[left + (right - left) / 2];
        while (i <= j) {
            while (array[i].compareToIgnoreCase(pivot) < 0) {
                i++;
            }
            while (array[j].compareToIgnoreCase(pivot) > 0) {
                j--;
            }
            if (i <= j) {
                tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                i++;
                j--;
            }
        }
        if (left < j) {
            quickSort(array, left, j);
        }
        if (i < right) {
            quickSort(array, i, right);
        }
        return array;
    }

    public static boolean binarySearch(String[] array, String name, int left, int right, FileWriter quickWriter) throws IOException {
        int middle = (left + right) / 2;
        boolean isFound = false;
        boolean isEnd = false;
        quickWriter.write(name+"\n");
        while (!isFound && !isEnd) {
            if (middle == right || middle == left) {
                isEnd = true;
            } else {
                if (name.compareToIgnoreCase(array[middle]) > 0) {
                    left = middle;
                    isFound = binarySearch(array, name, left, right, quickWriter);
                } else if (name.compareToIgnoreCase(array[middle]) < 0) {
                    right = middle;
                    isFound = binarySearch(array, name, left, right, quickWriter);
                } else if (name.compareTo(array[middle]) == 0) {
                    isFound = true;
                }
            }
        }
        return isFound;
    }

    public static void main(String[] args) throws IOException {
        String pathToFile = "C:\\Users\\Majid\\IdeaProjects\\Phone Book1\\Phone Book\\task\\src\\phonebook\\find.txt";
        File find = new File(pathToFile);
        String pathToFile2 = "C:\\Users\\Majid\\IdeaProjects\\Phone Book1\\Phone Book\\task\\src\\phonebook\\directory.txt";
        File dir = new File(pathToFile2);
        String linearPath = "C:\\Users\\Majid\\IdeaProjects\\Phone Book1\\Phone Book\\task\\src\\phonebook\\linear.txt";
        File linearFile = new File(linearPath);
        String quickPath = "C:\\Users\\Majid\\IdeaProjects\\Phone Book1\\Phone Book\\task\\src\\phonebook\\quick.txt";
        File quickFile = new File(quickPath);

        //Linear Search
        int allNames = 0;
        int foundNames = 0;
        System.out.println("Start searching (linear search)...");
        Instant start = Instant.now();
        String directory = null;
        try {
            directory = readFileAsString(pathToFile2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner findScanner = new Scanner(find);
        while (findScanner.hasNext()) {
            allNames++;
            if (directory.contains(findScanner.nextLine())) {
                foundNames++;
            }
        }

        Instant stopLinear = Instant.now();
        Duration d = Duration.between(start, stopLinear);
        System.out.println("Found " + foundNames + " / " + allNames + " entries. Time taken: " + d.toMinutesPart() + " min. " + d.toSecondsPart() + " sec. " + d.toMillisPart() + " ms.\n" );

        //Bubble Sort
        System.out.println("Start searching (bubble sort + jump search)...");
        Instant startSort = Instant.now();
        Scanner dirScanner = new Scanner(dir);
        String tmp;
        int x = 0;
        while (dirScanner.hasNext()){
            dirScanner.nextLine();
            x++;
        }
        String[] sorted = new String[x];
        x = 0;
        dirScanner.close();
        dirScanner = new Scanner(dir);

        while (dirScanner.hasNext()){
            sorted[x] = dirScanner.nextLine().replaceAll("\\d","").substring(1);
            x++;
        }

        Instant endLevel;
        Duration levelDuration = null;
        boolean swapped;
        boolean faster = false;
        for (int i = 0; i < x - 1; i++) {
            swapped = false;
            for (int j = 0; j < x - 1 - i; j++) {
                if (sorted[j].compareTo(sorted[j + 1]) > 0) {
                    tmp = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = tmp;
                    swapped = true;
                }
            }
            endLevel = Instant.now();
            levelDuration = Duration.between(startSort, endLevel);
            if (levelDuration.toNanos() > d.toNanos() * 20 )
                faster = true;
            if (!swapped | faster)
                break;
        }

        if (faster) {
            //Stopped to linear search
            Instant startLinear = Instant.now();
            allNames = 0;
            foundNames = 0;
            directory = null;
            try {
                directory = readFileAsString(pathToFile2);
            } catch (IOException e) {
                e.printStackTrace();
            }
            findScanner = new Scanner(find);
            while (findScanner.hasNext()) {
                allNames++;
                if (directory.contains(findScanner.nextLine())) {
                    foundNames++;
                }
            }
            stopLinear = Instant.now();
            d = Duration.between(startLinear, stopLinear);
            Duration allDuration = levelDuration.plus(d);
            System.out.println("Found " + foundNames + " / " + allNames + " entries. Time taken: " + allDuration.toMinutesPart() + " min. " + allDuration.toSecondsPart() + " sec. " + allDuration.toMillisPart() + " ms." );
            System.out.println("Sorting time: " + levelDuration.toMinutesPart() + " min. " + levelDuration.toSecondsPart() + " sec. " + levelDuration.toMillisPart() + " ms.");
//            System.out.println("Sorting time: " + levelDuration.toMinutesPart() + " min. " + levelDuration.toSecondsPart() + " sec. " + levelDuration.toMillisPart() + " ms. - STOPPED, moved to linear search");
            System.out.println("Searching time: " + d.toMinutesPart() + " min. " + d.toSecondsPart() + " sec. " + d.toMillisPart() + " ms.\n");
        } else
        {
            Instant stopSort = Instant.now();
            Duration sortDuration = Duration.between(startSort, stopSort);

            // Jump Search
            Instant startJs = Instant.now();
            int part = (int) Math.sqrt(x);
            boolean found;
            allNames = x;
            foundNames = 0;
            String ff;

            Scanner fScanner = new Scanner(find);
            while (fScanner.hasNext()) {
                found = false;
                ff = fScanner.nextLine();
                int i = 0;
                int c = part;

                if (ff.compareTo(sorted[0]) != 0) {
                        while (ff.compareTo(sorted[c]) > 0) {
                            i = c;
                            c = !(c + part < x) ? x - 1 : c + part;
                        }
                        for (int j = i; j < c; j++) {
                            if (sorted[j].compareTo(ff) == 0) {
                                foundNames++;
                                found = true;
                            }
                            if (found)
                                break;
                        }
                } else {
                    foundNames++;
                }
            }
            // End of jump search
            Instant stopAll = Instant.now();
            Duration allDuration = Duration.between(startSort, stopAll);
            Duration searchDuration = Duration.between(startJs,stopAll);
            System.out.println("Found " + foundNames + " / " + allNames + " entries. Time taken: " + allDuration.toMinutesPart() + " min. " + allDuration.toSecondsPart() + " sec. " + allDuration.toMillisPart() + " ms." );
            System.out.println("Sorting time: "+ sortDuration.toMinutesPart() + " min. " + sortDuration.toSecondsPart() + " sec. " + sortDuration.toMillisPart() + " ms.");
            System.out.println("Searching time: "+ searchDuration.toMinutesPart() + " min. " + searchDuration.toSecondsPart() + " sec. " + searchDuration.toMillisPart() + " ms.\n");

        }

        //quick sort + binary search
        System.out.println("Start searching (quick sort + binary search)...");
        Instant startQS = Instant.now();
        String[] array = quickSort(names(), 0, names().length -1);
        Instant stopQS = Instant.now();

        Instant startBS = Instant.now();
        allNames = 0;
        foundNames = 0;
        Scanner nameScanner = new Scanner(find);
        boolean isFound = false;
        FileWriter quickWriter = new FileWriter(quickFile);
        String aa;
        while (nameScanner.hasNext()) {
            aa = nameScanner.nextLine();
            allNames++;
            if (binarySearch(array, aa, 0, array.length - 1, quickWriter)) {
                foundNames++;
            }
        }
        quickWriter.close();
        Instant stopBS = Instant.now();

        Duration qsDuration = Duration.between(startQS, stopQS);
        Duration bsDuration = Duration.between(startBS,stopBS);
        Duration allDuration = Duration.between(startQS,stopBS);

        System.out.println("Found " + foundNames + " / " + allNames + " entries. Time taken: " + allDuration.toMinutesPart() + " min. " + allDuration.toSecondsPart() + " sec. " + allDuration.toMillisPart() + " ms." );
        System.out.println("Sorting time: "+ qsDuration.toMinutesPart() + " min. " + qsDuration.toSecondsPart() + " sec. " + qsDuration.toMillisPart() + " ms.");
        System.out.println("Searching time: "+ bsDuration.toMinutesPart() + " min. " + bsDuration.toSecondsPart() + " sec. " + bsDuration.toMillisPart() + " ms.");

   }
}