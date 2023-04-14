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
            System.out.println("5. Naive Bayes (enter values)");
            System.out.println("6. Naive Bayes (laplace)");

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
                    Scanner richards = new Scanner(System.in);
                    double inout = richards.nextDouble();
                    bn.setFreq(inout);
                    break;

                case 6:
                    System.out.println("your mean is: "+bn.getFreq());
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
