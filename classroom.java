import java.util.Scanner;
import java.io.*;
import java.lang.*;

// TODO

// ADD NEW OPTION IN NAME OPTIONS (SETUPNAMES FUNCTION)
// PICK RANDOM FIRST NAME AND RANDOM LAST NAME THEN COMBINE


// GET CHECKED

class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.println("\nNEW CLASS");
    System.out.println("\nName: NEW CLASS\nTeacher: TEACHER\n(both can be changed)\n");
    String teacherName = "TEACHER";
    String className = "NEW CLASS";

    String[] firstNames = {"Andrew", "Daniel", "David", "Devin", "Timothy", "Chloe", "Christina", "Charlotte", "Natalee", "Megan"};
    String[] lastNames = {"Oh", "Sohn", "Chung", "Yi", "Hwang", "Sim", "Park", "Hong", "Yun", "Lee"};
    
    String[] names = setUpNames(firstNames, lastNames);
    int size = names.length;
    
    double[] test1 = new double[size];
    double[] test2 = new double[size];
    double[] test3 = new double[size];
    double[] test4 = new double[size];
    double[] test5 = new double[size];

    int[] testValue = {20,20,20,20,20};

    System.out.println("\nWould you like to\n1. create random scores/grades\n2. give all students 100/A\n3. manually assign all scores/grades");

    int in = strictAnswer(3);
    test1 = setUpGrades(in, size, names);
    test2 = setUpGrades(in, size, names);
    test3 = setUpGrades(in, size, names);
    test4 = setUpGrades(in, size, names);
    test5 = setUpGrades(in, size, names);

    double[] avgGrades = new double[size];
  
    System.out.println("\n\nCongratulations! You have created your classroom. You can now view or change the following:");
    System.out.println("Individual student grades/scores\nClass average grades/scores\nAnd more");

    boolean calculating = true;

    while (calculating) {
      System.out.println("\n\nWhat would you like to access?");
      System.out.println("1. Student Info\n2. Class Info\n3. Test/Teacher Info\n4. Exit");

      switch(strictAnswer(4)) {
        case 1:
          System.out.println("\nStudents' Names:");
          for (int i = 0; i < names.length; i++) {
            System.out.println((i+1) + ": " + names[i]);
          }
          System.out.println("\nWould you like to\n1. Return info of individual student or\n2. Return info of all students at once");
          switch(strictAnswer(2)) {
            case 1:
              int index = StudentInfo(names, test1, test2, test3, test4, test5, testValue);
              boolean repeat = true;
              while (repeat) {
                System.out.println("\nWould you like to change anything?");
                System.out.println("1. Change Name\n2. Change Test Score\n3. Exit");
                switch(strictAnswer(3)) {
                  case 1:
                    System.out.println("What would you like to change it to?");
                    names[index] = scan.nextLine();
                    getStudentInfo(index, names, test1, test2, test3, test4, test5, testValue);
                    break;
                  case 2:
                    System.out.println("Which test score would you like to change?");
                    int testChange = strictAnswer(5);
                    System.out.println("What would you like to change it to?");
                    switch(testChange) {
                      case 1:
                        test1[index] = getScore();
                        break;
                      case 2:
                        test2[index] = getScore();
                        break;
                      case 3:
                        test3[index] = getScore();
                        break;
                      case 4:
                        test4[index] = getScore();
                        break;
                      case 5:
                        test5[index] = getScore();
                        break;
                    }
                    getStudentInfo(index, names, test1, test2, test3, test4, test5, testValue);
                    break;
                  case 3:
                    repeat = false;
                }
              }
              break;
            case 2:
              for (int i = 0; i < names.length; i++) {
                getStudentInfo(i, names, test1, test2, test3, test4, test5, testValue);
              }
              break;
          }
          break;
        case 2:
          getClassInfo(names, testValue, test1, test2, test3, test4, test5);
          break;
        case 3:
          boolean repeat = true;
          while (repeat) {
            getEtcInfo(className, teacherName, testValue);
            System.out.println("\nWould you like to change anything?");
            System.out.println("1. Change Class Name\n2. Change Teacher Name\n3. Change Test Values\n4. Nothing");
            switch(strictAnswer(4)) {
              case 1:
                System.out.println("What would you like to change the Class name to?");
                className = scan.nextLine();
                break;
              case 2:
                System.out.println("What would you like to change the Teacher's name to?");
                teacherName = scan.nextLine();
                break;
              case 3:
                int sum = 0;
                while (sum != 100) {
                  sum = 0;
                  for (int i = 0; i < testValue.length; i++) {
                    System.out.println("What would you like to change the value of Test " + (i+1) + " to? (In percentage)");
                    testValue[i] = scan.nextInt();
                    sum += testValue[i];
                  }
                  if (sum != 100) {
                    System.out.println("Values do not add up to 100, please try again");
                  }
                }
                break;
              case 4:
                repeat = false;
                break;
            }
          }
          break;
        case 4:
          System.out.println("Thank you for visiting the Classroom!");
          calculating = false;
          break;
      }
    }
  }

  //--------------------------------------------------


  //SETUP
  static String[] setUpNames(String[] firstNames, String[] lastNames) {
    Scanner scan = new Scanner(System.in);
    System.out.println("How many students are in this class?");
    int size = scan.nextInt();
    System.out.println("\nWould you like to:\n1. set each students' name\n2. set the students' names according to their corresponding numbers\n3. create randomized names");
    String[] names = new String[size];
    switch(strictAnswer(3)) {
      case 1:
        System.out.println("What are the names of the students?");
        for (int i = 0; i < size; i++) {
          System.out.println("Student " + (i+1));
          names[i] = scan.nextLine();
        }
        break;
      case 2:
        for (int i = 0; i < size; i++) {
          names[i] = String.valueOf(i+1);
        }
       break;
      case 3:
        for (int i = 0; i < size; i++) {
          names[i] = firstNames[(int)(Math.random() * 10)] + " " + lastNames[(int)(Math.random() * 10)];
        }
    }
    return names;
  }

  static double[] setUpGrades(int option, int size, String[] names) {
    double[] output = new double[size];
    for (int i = 0; i < size; i++) {
      switch(option) {
        case 1:
          output[i] = Double.parseDouble(String.format("%.2f", Math.random()*100));
          break;
        case 2:
          output[i] = 100;
          break;
        case 3:
          System.out.println("What is the score for " + names[i] + "for test 1?");
          output[i] = getScore();
          break;
      }
    }
    return output;
  }

  static double getScore() {
    Scanner scan = new Scanner(System.in);
    double input = -1;
    while (input < 0 || input > 100) {
      input = scan.nextDouble();
      if (input < 0 || input > 100) {
        System.out.println("Invalid");
      }
    }
    return input;
  }

  //--------------------------------------------------------------

  // COMMON FUNCTIONS
  static int strictAnswer(int max) {
    Scanner scan = new Scanner(System.in);
    int input = 0;
    while (input < 1 || input > max) {
      input = scan.nextInt();
      if (input < 1 || input > max) {
        System.out.println("Invalid");
      }
    }
    return input;
  }

  static double summation(double[] arr) {
    double sum = 0;
    for (int i = 0; i < arr.length; i++) {
      sum += arr[i];
    }
    return sum;
  }

  static double mean(double[] arr) {
    return summation(arr)/arr.length;    
  }

  static double[] sort(double[] arr) {
    boolean swapped = true;
    while (swapped) {
      swapped = false;
      for (int i = 0; i < arr.length-1; i++) {
        if (arr[i] > arr[i+1]) {
          double copy = arr[i+1];
          arr[i+1] = arr[i];
          arr[i] = copy;
          swapped = true;
        }
      }
    }
    return arr;
  }
  
  static double median(double[] arr) {
    arr = sort(arr);
    if (arr.length % 2 == 0) {
      return (arr[(int)(Math.floor(arr.length/2))]+arr[(int)(Math.ceil(arr.length/2))])/2;
    } else {
      return arr[(int)(Math.ceil(arr.length/2))];
    }
  }
  

  static double standDev(double[] arr) {
    double mean = mean(arr);
    double[] newArr = new double[arr.length];
    for (int i = 0; i < arr.length; i++) {
      newArr[i] = Math.pow(arr[i] - mean, 2);
    }
    return Math.sqrt(summation(newArr) / newArr.length);
  }
  
  //--------------------------------------------------------------
  
  // STUDENT INFO
  static int StudentInfo(String[] names, double[] test1, double[] test2, double[] test3, double[] test4, double[] test5, int[] testValue) {
    System.out.println("\nWhat is the number of the student?");
    int index = strictAnswer(names.length)-1;
    getStudentInfo(index, names, test1, test2, test3, test4, test5, testValue);
    return index;
  }


  // static int findStudent(String[] names) {
  //   Scanner scan = new Scanner(System.in);
  //   String name = "";
  //   while (true) {
  //     System.out.println("\nWhat is the name of the student?");
  //     name = scan.nextLine();
  //     for (int i = 0; i < names.length; i++) {
  //       if (names[i].equals(name)) {
  //         return i;
  //       }
  //     }
  //     System.out.println("Student is not in the class (Inputs are case sensitive)");
  //   }
  // }
  
  static void getStudentInfo(int index, String[] names, double[] test1, double[] test2, double[] test3, double[] test4, double[] test5, int[] testValue) {
    double score = Double.valueOf(String.format("%.2f", findScore(index, test1, test2, test3, test4, test5, testValue)));
    System.out.println("\nStudent " + (index+1));
    System.out.println("Name: " + names[index]);
    System.out.println("Grade: " + findGrade(score));
    System.out.println("Score: " + score + "%");
    double[] studentScores = {test1[index], test2[index], test3[index], test4[index], test5[index]};
    for (int i = 0; i < studentScores.length; i++) {
      System.out.println("Test " + (i+1) + " Score: " + studentScores[i] + "% (" + findGrade(studentScores[i]) + ")");
    }
    System.out.println("Average test score: " + String.format("%.2f", mean(studentScores)) + "%");
  }
  static String findGrade(double score) {
    String grade = "";
    if (score/20 < 3) {
      grade = "F";
    }
    if (score/20 >= 3) {
      grade = "D";
    }
    if (score/20 >= 3.5) {
      grade = "C";
    }
    if (score/20 >= 4) {
      grade = "B";
    }
    if (score/20 >= 4.5) {
      grade = "A";
    }
    return grade;
  }
  static double findScore(int index, double[] test1, double[] test2, double[] test3, double[] test4, double[] test5, int[] testValue) {
    double points = test1[index]*testValue[0] + test2[index]*testValue[1] + test3[index]*testValue[2] + test4[index]*testValue[3] + test5[index]*testValue[4];
    double full = 0;
    for (int i = 0; i < 5; i++) {
      full += 100*testValue[i];
    }
    return (points/full)*100;
  }
  
  //--------------------------------------------------
  
  // CLASS INFO

  static void getClassInfo(String[] names, int[] testValue, double[] test1, double[] test2, double[] test3, double[] test4, double[] test5) {
    double[] classScores = findClassScores(names, testValue, test1, test2, test3, test4, test5);
    System.out.println("Average Class Grade: " + findGrade(mean(classScores)));
    System.out.println("Average Class Score: " + String.format("%.2f", mean(classScores)));
    System.out.println("Median Class Grade: " + findGrade(median(classScores)));
    System.out.println("Median Class Score: " + String.format("%.3f", median(classScores)));
    System.out.println("Test 1 Average Grade: " + findGrade(mean(test1)));
    System.out.println("Test 2 Average Grade: " + findGrade(mean(test2)));
    System.out.println("Test 3 Average Grade: " + findGrade(mean(test3)));
    System.out.println("Test 4 Average Grade: " + findGrade(mean(test4)));
    System.out.println("Test 5 Average Grade: " + findGrade(mean(test5)));
    System.out.println("Class Scores Standard Deviation: " + String.format("%.3f", standDev(classScores)));
    System.out.println("Test 1 Scores Standard Deviation: " + String.format("%.3f", standDev(test1)));
    System.out.println("Test 2 Scores Standard Deviation: " + String.format("%.3f", standDev(test2)));
    System.out.println("Test 3 Scores Standard Deviation: " + String.format("%.3f", standDev(test3)));
    System.out.println("Test 4 Scores Standard Deviation: " + String.format("%.3f", standDev(test4)));
    System.out.println("Test 5 Scores Standard Deviation: " + String.format("%.3f", standDev(test5)));
  }

  static double[] findClassScores(String[] names, int[] testValue, double[] test1, double[] test2, double[] test3, double[] test4, double[] test5) {
    double[] scores = new double[names.length];
    for (int i = 0; i < names.length; i++) {
      scores[i] = findScore(i, test1, test2, test3, test4, test5, testValue);
    }
    return scores;
  }


  //--------------------------------------------------------

  // TEACHER / TEST INFO

  static void getEtcInfo(String className, String teacherName, int[] testValue) {
    System.out.println("\nClass Name: " + className);
    System.out.println("Teacher Name: " + teacherName);
    for (int i = 0; i < testValue.length; i++) {
      System.out.println("Test " + (i+1) + " Percentage of Grade: " + testValue[i] + "%");
    }
  }
}

