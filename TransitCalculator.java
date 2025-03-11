
/*
 * This project aim to calculate the best fare for public transport avaible, having three options, for only one ride, 
 * seven days unlimited and a month unlimited, giving the best alternative with the value of eatch ride
 * 
 * Author: Lucas Gabriel Nordio
 * Date: 11/03/2025
 * 
 */

import java.util.Scanner;

public class TransitCalculator {

    //Basic Variables

    int numDays;
    int numRides;

    //Fare Options

    double perRide = 2.75;
    double sevenDayUnlimited = 33.00;
    double thirtyDayUnlimited = 127.00;

    //Calculating price for 7 day unlimited package

    static double unlimited7Price(double numDays, int numRides){
        TransitCalculator calculator = new TransitCalculator();
        double weeksNedeed = Math.ceil(numDays/7);
        double totalCost = weeksNedeed * calculator.sevenDayUnlimited;
        double returnCost = totalCost / numRides;
        return returnCost;
    }
    
    //Calculating price for 30 day unlimited package

    static double unlimited30Price(double numDays, int numRides){
        TransitCalculator calculator = new TransitCalculator();
        double weeksNedeed = Math.ceil(numDays/30);
        double totalCost = weeksNedeed * calculator.thirtyDayUnlimited;
        double returnCost = totalCost / numRides;
        return returnCost;
    }

    //Getting prices per package, note that one ride is calculated as 2.75 since we're basing on a per-ride value

    static double[] getRidePrices(double numDays, int numRides){
        
        double oneRide = 2.75;
        double sevenDayRide = unlimited7Price(numDays, numRides);
        double thirtyDayRide = unlimited30Price(numDays, numRides);
        double[] returnArray = {oneRide, sevenDayRide, thirtyDayRide};
        return returnArray;
    }

    //Getting the best fare avaiable

    String getBestFare(double numDays, int numRides){
    
            double lowestPrice = 9999;
            String bestFare = "";
            
            double[] doubleArray = TransitCalculator.getRidePrices(numDays, numRides);
    
            for (double d : doubleArray) {
                if (d <= lowestPrice) {
                    lowestPrice = d;
                }
            }
    
            for (int i = 0; i < doubleArray.length; i++) {
                if (doubleArray[i] == lowestPrice) {
                    switch (i) {
                        case 0:
                            bestFare = "Pay-per-ride";
                            break;
                        case 1:
                            bestFare = "7-day Unlimited Rides";
                            break;
                        case 2:
                            bestFare = "30-day Unlimited Rides";
                            break;
                        default:
                            break;
                    }
                }
            }

            //Formating:

            String finalString = String.format("You should get the %s option at %.2f per ride.", bestFare, lowestPrice);
    
            return finalString;
        }
    
        public static void main(String[] args) {

            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);
            
            TransitCalculator calculator = new TransitCalculator();

            //User input:

            System.out.println("Enter the number of days: ");
            calculator.numDays = scanner.nextInt();
            System.out.println("Enter the number of rides: ");
            calculator.numRides = scanner.nextInt();
            System.out.println(calculator.getBestFare(calculator.numDays, calculator.numRides));

    }
}
