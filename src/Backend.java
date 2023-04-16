import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;


public class Backend {

    public void calcKMeans(){
        String c1, c2; //val
        boolean stop = false;
        Scanner input = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.0000");
        ArrayList<Double> xValues = new ArrayList<>();
        ArrayList<Double> yValues = new ArrayList<>();
        //ArrayList<String> values = new ArrayList<>();
        ArrayList<String> roids = new ArrayList<>();


        System.out.println("Please enter the number of points you have");
        int pNum = input.nextInt();
        System.out.println("\n");

        System.out.print("please enter 1st centroid x:\t");
        c1 = input.next();
        roids.add(c1);
        System.out.print("please enter 2nd centroid y:\t");
        c2 = input.next();
        roids.add(c2);

        for(int i =0; i< pNum; i++)
        {
            System.out.println("Please enter your points x and y: \n");
            System.out.print("please enter x");
            double x = input.nextDouble();
            xValues.add(x);
            System.out.print("please enter y");
            double y = input.nextDouble();
            yValues.add(y);
            //val = x+";"+y;
            //values.add(val);
        }

        do{
            System.out.println("Please choose an option: ");
            System.out.println("1. run K-means");
            System.out.println("0. end");
            int m = 1;

            Scanner read = new Scanner(System.in);
            int choice = read.nextInt();

            switch (choice){

                case 0:
                    System.out.println("Ciao! :)");
                    stop = true;
                    break;

                case 1:
                    ArrayList<Double> Dis1 = new ArrayList<>();
                    ArrayList<Double> Dis2 = new ArrayList<>();

                    for(String centroid: roids){
                        double cx = convertToCalc(centroid.substring(0, centroid.indexOf(";")));
                        double cy = convertToCalc(centroid.substring(centroid.indexOf(";")+1));

                        for (int j = 0; j < pNum; j++) {

                            double dis2 = Math.pow(xValues.get(j) - cx, 2) + Math.pow(yValues.get(j) - cy, 2);
                            double dis = Math.sqrt(dis2);

                            if (m > 1) {
                                Dis2.add(dis);
                            } else {
                                Dis1.add(dis);
                            }
                        }
                        m++;
                    }

                    int k = 0;
                    System.out.println("\n Points after Distance Matrix:");
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
                    int b = 0;
                    System.out.println("\n New Groups");
                    for (int item : cent1) {
                        System.out.println("X: " + item + "\tY: " + cent2.get(b));
                        b++;
                    }

                    double xCent = 0, yCent = 0, xCent2 = 0, yCent2 = 0, count1 = 0, count2 = 0;
                    for(int i = 0; i < pNum; i++){
                        if(cent1.get(i) == 1){
                            xCent += xValues.get(i);
                            yCent += yValues.get(i);
                            count1 ++;
                        }
                        else{
                            xCent2 += xValues.get(i);
                            yCent2 += yValues.get(i);
                            count2++;
                        }
                    }
                    System.out.println("\nNew centroid 1 = "+df.format(xCent/count1)+";"+df.format(yCent/count1));
                    System.out.println("New centroid 2 = "+df.format(xCent2/count2)+";"+df.format(yCent2/count2));
                    roids.clear();
                    roids.add(convertDecimalToFraction(xCent/count1)+";"+convertDecimalToFraction(yCent/count1));
                    roids.add(convertDecimalToFraction(xCent2/count2)+";"+convertDecimalToFraction(yCent2/count2));


                    System.out.println("\n");

                    Dis1.clear();
                    Dis2.clear();
                    cent2.clear();
                    cent1.clear();

                    break;
            }
        }while(!stop);
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

        }
        else
        {
            answer = -Yes/total*getLog2(Yes/total) - No/total*getLog2(No/total) ;
        }
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
        double sumYes = 1, sumNo = 1, sumP = 1;

        System.out.print("please enter the probability of yes's (format e.g \"7/6\"): ");
        yes = in.next();
        double pYes = convertToCalc(yes);
        double divYes = Double.parseDouble(yes.substring(0, yes.indexOf("/")));
        System.out.print("Please enter the probability of No's (format e.g \"7/6\"): ");
        no = in.next();
        double pNo = convertToCalc(no);
        double divNo = Double.parseDouble(no.substring(0, no.indexOf("/")));
        double div = Double.parseDouble(no.substring(no.indexOf("/")+1));

        System.out.print("Please enter the total number values to calculate: ");
        int n = in.nextInt();
        System.out.println("\n");

        for(int i = 0; i < n; i++){
            System.out.print("\n\nPlease enter the positive probability: ");
            double Yes = in.nextDouble();
            double sumHolder = Yes/divYes;
            System.out.println("Your positive probability is : "+ sumHolder);
            sumYes *=sumHolder;
            System.out.print("\nPlease enter the negative probability: ");
            double No = in.nextDouble();
            sumHolder = No/divNo;
            System.out.println("Your negative probability is : "+ sumHolder);
            sumNo *= sumHolder;

            sumP *= (Yes+No)/div;
        }
        System.out.println("\nP(X|Cyes) = "+df.format(sumYes)+"\nP(X|Cno) = "+df.format(sumNo)+"\nP(X) = "+df.format(sumP));
        System.out.println("p(Cyes) = "+df.format(pYes)+"\np(Cno) = "+df.format(pNo));

        System.out.println("The naive bayes of our \"Yes\" tuple is: "+df.format((sumYes*pYes)/sumP)+"\nThe naive bayes of our \"No\"tuple is: "+df.format((sumNo*pNo)/sumP));
    }

    public double convertToCalc(String ex)
    {
        double result = Double.parseDouble(ex);
        if(ex.contains("/")){
            int index = ex.indexOf("/");
            double firstNum = Integer.parseInt(ex.substring(0,index));
            double secondNum = Integer.parseInt(ex.substring(index+1));
            result = firstNum/secondNum;
        }
        return result;

    }

    public void SVM(String s1, String s2)
    {
        DecimalFormat df = new DecimalFormat("0.0000");
        double x1 = Double.parseDouble(s1.substring(0, s1.indexOf(";"))), y1 = Double.parseDouble(s1.substring(s1.indexOf(";")+1)), x2 = Double.parseDouble(s2.substring(0, s2.indexOf(";"))), y2 = Double.parseDouble(s2.substring(s2.indexOf(";")+1));
        double xy1 = makePositive(x2-x1), xy2 = makePositive(y2-y1);

        System.out.println("W = a[("+Math.round(x1)+";"+Math.round(y1)+") - ("+Math.round(x2)+";"+Math.round(y2)+")]");

        System.out.println("W = a("+Math.round(xy1)+";"+Math.round(xy2)+")\n");
        double sum1 = x2*xy1 + y2*xy2;
        double sum2 = x1*xy1 + y1*xy2;
        System.out.println(Math.round(sum2)+"a + Wo = +1");
        System.out.println(Math.round(sum1)+"a + Wo = -1");
        double a = 2/(sum2-sum1);
        System.out.println("a = "+convertDecimalToFraction(a));

        double Wo = 1 - sum2*a;
        System.out.println("Wo = "+convertDecimalToFraction(Wo));

        double w1 = xy1*a; double w2 = xy2*a;
        System.out.println("W  = ( "+convertDecimalToFraction(w1)+" ; "+convertDecimalToFraction(w2)+" )\n");
        System.out.println("g(x) = ("+convertDecimalToFraction(w1)+")x1 + ("+convertDecimalToFraction(w2)+")x2 + ("+convertDecimalToFraction(Wo)+")");
        System.out.println("g(x) = ("+(w1*reverseFraction(a))+")x1 + ("+(w2*reverseFraction(a))+")x2 + ("+(Wo*reverseFraction(a))+")");

        double m2 = Math.pow(w1, 2) + Math.pow((w2), 2);
        double m = 2/Math.sqrt(m2);
        System.out.println("m = "+df.format(m));
    }

    public void MappingFunc(double x, double y)
    {
        if(checkMapping(x, y)){
            double line1 = 6 - x + Math.pow((x-y), 2);
            double line2 = 6 - y + Math.pow((x-y), 2);

            System.out.println("your new coordinates are ("+convertDecimalToFraction(line1)+";"+convertDecimalToFraction(line2)+")");
        }
        else{
            System.out.println("Your coordinates cannot be transformed :(");
        }

    }

    public String convertDecimalToFraction(double x){
        if (x < 0){
            return "-" + convertDecimalToFraction(-x);
        }
        double tolerance = 1.0E-6;
        double h1=1; double h2=0;
        double k1=0; double k2=1;
        double b = x; String result;
        do {
            double a = Math.floor(b);
            double aux = h1; h1 = a*h1+h2; h2 = aux;
            aux = k1; k1 = a*k1+k2; k2 = aux;
            b = 1/(b-a);
        } while (Math.abs(x-h1/k1) > x*tolerance);

        if(k1 == 1){
            result = Math.round(h1)+"";
        }
        else{
            result = Math.round(h1)+"/"+Math.round(k1);
        }

        return result;
    }

    public double makePositive(double val)
    {
        double finalValue = -1*val;
        if(val > 0){
            finalValue = val;
        }

        return finalValue;
    }

    public double reverseFraction(double deci)
    {
        String decimal = convertDecimalToFraction(deci);
        double pt1 = Double.parseDouble(decimal.substring(0,decimal.indexOf("/"))), pt2 = Double.parseDouble(decimal.substring(decimal.indexOf("/")+1));
        return pt2/pt1;
    }

    public boolean checkMapping(double x, double y)
    {
        double calc = Math.pow(x, 2) + Math.pow(y, 2), calc2 = Math.sqrt(calc);
        boolean greater = calc2 >= 2;

        return greater;
    }



}
