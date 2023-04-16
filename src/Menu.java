import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public Menu() {
    }

    public void menu(){
        boolean stop = false;
        int choice;

        do {
            System.out.println("Choose an option");
            System.out.println("0. Exit");
            System.out.println("1. Calculate K-means");
            System.out.println("2. Calculate entropy");
            System.out.println("3. Calculate average entropy");
            System.out.println("4. Naive Bayes");
            System.out.println("5. Support Vector Machines");
            System.out.println("6. Mapping function");

            Scanner reed = new Scanner(System.in);
            choice = reed.nextInt();
            Backend bn = new Backend();

            switch(choice){
                case 1:
                    bn.calcKMeans();
                    break;

                case 2:
                    bn.calcEntropy();
                    break;

                case 3:
                    Scanner num = new Scanner(System.in);
                    ArrayList<Integer> values = new ArrayList<>();
                    ArrayList<Double> ent = new ArrayList<>();
                    int total= 0;
                    int input = 0;

                    System.out.println("Please enter the number of the same class values you have: ");
                    int k = num.nextInt();
                    for (int j = 1; j<= k; j++)
                    {
                        System.out.println("Please enter the total number of times the class value no."+j+" appears\n");
                        input = num.nextInt();
                        values.add(input);
                        System.out.println("Please enter the entropy \n");
                        ent.add(num.nextDouble());
                        total += input;
                    }
                    bn.calcAvgEntropy(k, values, ent, (double)total);
                    break;

                case 4:
                    bn.naiveBayes();
                    break;

                case 5:
                    Scanner svm = new Scanner(System.in);
                    String s1, s2;
                    System.out.print("Please enter the value of s1: ");
                    s1 = svm.next();
                    System.out.print("Please enter the value of s2: ");
                    s2 = svm.next();

                    bn.SVM(s1, s2);
                    break;

                case 6:
                    Scanner map = new Scanner(System.in);
                    double x = 0, y = 0;
                    System.out.print("Please enter the x value: ");
                    x = map.nextDouble();
                    System.out.print("Please enter the y value: ");
                    y = map.nextDouble();

                    bn.MappingFunc(x, y);
                    break;

                case 0:
                    System.out.println("Thank you. bye :D");
                    stop = true;
                    break;

                default:
                    System.out.println("Invalid value entered");
                    break;
            }

        }while(stop == false);
    }
}
