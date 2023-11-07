import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static final int MONTHS_IN_YEAR =12;
    static final int PERCENT=100;

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        CsvWriter csvWriter;

        try{
            FileWriter writer = new FileWriter(FileProvider.getFile());
            csvWriter = new CsvWriter(writer);
            csvWriter.writeHeader();
        }catch(IOException e){
            System.out.println("Some error occured when initializing the Csv writer"+e.getMessage());
            return;
        }


        int amount;
        int period;
        double interestRate;


        System.out.println("Please enter the amount: ");


        try{
            amount=Integer.parseInt(scanner.nextLine());
        }catch(NumberFormatException e){
            System.out.println("The amount is mandatory to be numeric!");
            return;
        }

        System.out.println("Please enter the loan period in year: ");

        try{
            period =Integer.parseInt(scanner.nextLine());
        }catch (NumberFormatException e) {
            System.out.println("Period is mandatory to be numeric!");
            return;
        }
        System.out.println("Please enter the annual interest rate: ");

        try{
            interestRate=Double.parseDouble(scanner.nextLine());
        }catch(NumberFormatException e){
            System.out.println("The interest rate is mandatory to be numeric!");
            return;
        }


        double balance = amount;
        for(int month=1; month<=period * MONTHS_IN_YEAR;month++){
            double lastMonthBalance = balance;
            double monthlyMortage =calculateMortage(amount,period,interestRate);
            double monthlyInterest = calculateInterest(lastMonthBalance,interestRate);
            double paidAmount = monthlyMortage - monthlyInterest;

            balance = (lastMonthBalance-paidAmount) < 0? 0:(lastMonthBalance-paidAmount);

            try {
                csvWriter.writeRecord(month,monthlyMortage,balance,monthlyInterest,paidAmount);
            } catch (IOException e) {
                System.out.println("Error while writting the csv file"+e.getMessage());
            }

        }
        try {
            csvWriter.closeFile();
        } catch (IOException e) {
            System.out.println("Error while trying to close the csv file"+e.getMessage());
        }




        //suma +perioada + rata anuala de la dobanda user input
    }
    //calcul rata lunara
    private static double calculateMortage(int amount, int period, double interestRate) {
        double monthlyRate=interestRate/PERCENT/MONTHS_IN_YEAR;
        return(monthlyRate*amount)/(1-Math.pow(1+monthlyRate,(-period*MONTHS_IN_YEAR)));

    }
    //calcul dobanda pe o luna
    private static double calculateInterest(double balance,double interestRate){
        double interestPerYear= balance * interestRate/PERCENT;
        return interestPerYear/MONTHS_IN_YEAR;
    }
}
