import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Backend {

    private ArrayList<Double> freq = new ArrayList<Double>();
    private ArrayList<Integer> xVals = new ArrayList<Integer>();
    private ArrayList<Integer> yVals = new ArrayList<Integer>();
    private ArrayList<Double> entropy = new ArrayList<Double>();

    public ArrayList<Double> getEntropy() {
        return entropy;
    }

    public ArrayList<Double> getFreq() {
        return freq;
    }

    public void setFreq(double num) {
        freq.add(num);
        this.freq = freq;
    }

    public void setEntropy(double val) {
        entropy.add(val);
        this.entropy = entropy;
    }

    private int K;
    private int pNum;

    public ArrayList<Integer> getxVals() {

        Scanner input =  new Scanner(System.in);
        for (int i = 1; i <= K; i++)
        {
            for (int j = 1; j <= pNum; j++)
            {
                System.out.println("Please enter points: \n");
                System.out.print("please enter x");
                xVals.add(input.nextInt());
            }
        }
        return xVals;
    }

    public ArrayList<Integer> getyVals() {
        return yVals;
    }

    public void calcKMeans(){
        double dis2 = 0, dis = 0;
        double cx = 0, cy = 0;
        double x = 0, y = 0;
        Scanner input = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.0000");
        ArrayList<Double> xValues = new ArrayList<>();
        ArrayList<Double> yValues = new ArrayList<>();


        System.out.println("please enter the number of centroids you want");
        int K = input.nextInt();
        System.out.println("Please enter the number of points you have");
        int pNum = input.nextInt();
        System.out.println("\n");

        for(int i =0; i< pNum; i++)
        {
            System.out.println("Please enter your points x and y: \n");
            System.out.print("please enter x");
            x = input.nextDouble();
            xValues.add((double) x);
            System.out.print("please enter y");
            y = input.nextDouble();
            yValues.add((double) y);
        }

        ArrayList<Double> Dis1 = new ArrayList<>();
        ArrayList<Double> Dis2 = new ArrayList<>();

        for (int i = 1; i <= K; i++) {
            System.out.print("\nplease enter centroid x");
            cx = input.nextDouble();
            System.out.print("please enter centroid y");
            cy = input.nextDouble();

            for (int j = 0; j < pNum; j++) {

                dis2 = Math.pow(xValues.get(j) - cx, 2) + Math.pow(yValues.get(j) - cy, 2);
                dis = Math.sqrt(dis2);

                if (i > 1) {
                    Dis2.add(dis);
                } else {
                    Dis1.add(dis);
                }
            }
        }
        int k = 0;
        System.out.println("\n Point after Distance Matrix:");
        for (double i : Dis1) {
            System.out.println("X values: " + df.format(i) + "\tY values: " + df.format(Dis2.get(k)));
            k++;
        }

        ArrayList<Integer> cent1 = new ArrayList<>();
        ArrayList<Integer> cent2 = new ArrayList<>();

        for (int d = 0; d < pNum; d++) {
            if (Dis1.get(d) < Dis2.get(d)) {
                cent1.add(1);
                cent2.add(0);
            } else {
                cent1.add(0);
                cent2.add(1);
            }
        }

        System.out.println("\n");
        int b = 0, d = 0;
        System.out.println("\n New Groups");
        for (int item : cent1) {
            System.out.println("X: " + item + "\tY: " + cent2.get(b));
            b++;
        }
        System.out.println("\n");
    }

    public void calcEntropy()
    {
        DecimalFormat df = new DecimalFormat("0.0000");
        Scanner Sue = new Scanner(System.in);

        System.out.print("Please enter the number of Yes's: ");
        double Yes = Sue.nextInt();
        System.out.print("Please enter the number of No's: ");
        double No = Sue.nextInt();
        double answer = 0;
        double total = Yes + No;

        if((Yes == 0) || (No == 0)){
            answer = 0;
        }
        else
        {
            answer = -Yes/total*getLog2(Yes/total) - No/total*getLog2(No/total) ;
        }
        setEntropy(answer);
        System.out.println("\nYour entropy is: "+df.format(answer)+"\n\n");
    }

    public double getLog2(double n)
    {
        return Math.log(n)/Math.log(2);
    }

    public void calcAvgEntropy(int num, ArrayList<Integer> numX, ArrayList<Double> entropy2, Double tot)
    {
        DecimalFormat df = new DecimalFormat("0.0000");
        double avg = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("please enter total Enrtopy: ");
        double totEnt = input.nextDouble();
        for(int i = 0; i < num; i++)
        {
            avg += (-numX.get(i)/tot)*entropy2.get(i);
        }
        System.out.println("Your average entropy is: "+avg+"\nYour Gain is: "+ (df.format(totEnt+avg)));
    }

    public void naiveBayes(){
        Scanner in = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.0000");
        String yes, no;
        double sumYes = 1, sumNo = 1, sumPyes = 1, sumPno = 1, sumP = 1, pYes = 0, pNo = 0, sumHolder = 0, divYes = 0, divNo = 0, div = 0, Yes = 0, No = 0;

        System.out.print("please enter the probability of yes's (format e.g \'7/6\'): ");
        yes = in.next();
        pYes = convertToCalc(yes);
        divYes = Double.parseDouble(yes.substring(0, yes.indexOf("/")));
        System.out.print("Please enter the probability of No's (format e.g \'7/6\'): ");
        no = in.next();
        pNo = convertToCalc(no);
        divNo = Double.parseDouble(no.substring(0, no.indexOf("/")));
        div = Double.parseDouble(no.substring(no.indexOf("/")+1));

        System.out.print("Please enter the total number values to calculate: ");
        int n = in.nextInt();
        System.out.println("\n");

        for(int i = 0; i < n; i++){
            System.out.print("\n\nPlease enter the positive probability: ");
            Yes = in.nextDouble();
            sumHolder = Yes/divYes;
            System.out.println("Your positive probability is : "+ sumHolder);
            sumYes *=sumHolder;
            System.out.print("\nPlease enter the negative probability: ");
            No = in.nextDouble();
            sumHolder = No/divNo;
            System.out.println("Your negative probability is : "+ sumHolder);
            sumNo *= sumHolder;

            sumP *= (Yes+No)/div;
        }
        System.out.println("\nP(X|Cyes) = "+df.format(sumYes)+"\nP(X|Cno) = "+df.format(sumNo)+"\nP(X) = "+df.format(sumP));
        System.out.println("p(Cyes) = "+df.format(pYes)+"\np(Cno) = "+df.format(pNo));

        System.out.println("The naive bayes of our \'Yes\' tuple is: "+df.format((sumYes*pYes)/sumP)+"\nThe naive bayes of our \'No\'tuple is: "+df.format((sumNo*pNo)/sumP));
    }

    public double convertToCalc(String ex)
    {
        double firstNum = 0, secondNum = 0;
        int index = 0;
        index = ex.indexOf("/");
        firstNum = Integer.parseInt(ex.substring(0,1));
        secondNum = Integer.parseInt(ex.substring(index+1));
        return firstNum/secondNum;

    }

    public double calcMean(){
        float mean = 0;
        for (double val:
                freq) {
            mean += val;
        }
        //setMean(mean/freq.size());
        return mean/freq.size();
    }

    public void calcVaraince()
    {
        double temp =0;
        for (double val:
                freq) {
            temp += Math.pow((val - calcMean()),2);
        }

        //setVariance(temp/(freq.size()-1));
        System.out.println("The variance of your data is: "+temp/(freq.size()-1));
    }



}
