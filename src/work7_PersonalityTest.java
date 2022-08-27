import java.io.*;
import java.util.*;
import java.math.*;
public class work7_PersonalityTest  {
    public static void main(String[] arg) throws FileNotFoundException{
        Scanner console = new Scanner(System.in);
        begin();
        Scanner f = seeHasExist(console);
        PrintStream printOut = Stream(console);
        //分開存 名字存一堆 答案存一堆
        String answer ;
        int[] outcomeArray = new int[8];
        while(f.hasNextLine()){
            String next = f.nextLine();
            if(next.length()<69){         // isolate name & finished answer
                printOut.print(next +": ");
            } else {
                answer = next.toLowerCase();
                outcomeArray = compareWithAns(answer); // counts of A/B
                percentage(outcomeArray , printOut);
                printOut.println("");
            }
        }
    }
    public static void begin(){
        System.out.println("This program processes a file of answers to the");
        System.out.println("Keirsey Temperament Sorter. It converts the" );
        System.out.println("various A and B answers for each person into");
        System.out.println("a sequence of B-percentages and then into a" );
        System.out.println("four-letter personality type.");
        System.out.println("");
    }
    //print the introduction for this proj
    public static Scanner seeHasExist(Scanner console)throws FileNotFoundException{
        System.out.print("input file name? ") ;
        String in = console.nextLine();
        while(!(new File(in).exists())){
            System.out.print("input file name? ") ;
            in = console.nextLine();
        }
        Scanner f = new Scanner(new File(in));
        return f;
    }
    //see if the input file is exist or not ,
    //if users don't intput a correct file name it will keep asking until you input right file name
    //return the Scanner which is really exist
    public static PrintStream Stream(Scanner console) throws FileNotFoundException{
        System.out.print("output file name? ");
        String outPutName = console.nextLine();
        PrintStream output = new PrintStream(new File(outPutName));
        return output;
    }
    public static int[] compareWithAns(String answer){
        int[] answer1 = new int[8];
        int count = 1;
        int typenum;
        while(count <= 70){
            typenum = count%7;
            char ans = answer.charAt(count-1); //因為問題一是看answer的第0位置
            if(typenum == 1
            ){
                if( ans == 'a' )
                    answer1[0]++;
                else if ( ans == 'b')
                    answer1[4]++;
            }
            else if ( typenum == 3 || typenum == 2){
                if( ans == 'a' )
                    answer1[1]++;
                else if ( ans == 'b')
                    answer1[5]++;
            }
            else if (typenum == 4 || typenum == 5){
                if( ans == 'a' )
                    answer1[2]++;
                else if( ans == 'b')
                    answer1[6]++;
            }
            else{
                if( ans == 'a' )
                    answer1[3]++;
                else if( ans == 'b')
                    answer1[7]++;
            }
            count++;
        }
        return answer1;
        }
        //read charAB[count] and see is A or B or -
        // 1 8 15 ....is first kind of personality 2 3 9 10
    public static void percentage(int[] outcome , PrintStream printOut){
        int[] Bpercentage = new int[4];
        for (int i = 0; i <=3; i++) {
            Bpercentage[i] = (int)Math.round(outcome[i+4]*100/(outcome[i] + outcome[i+4]));
        }
        printOut.print(Arrays.toString(Bpercentage) + " = ");
        for (int i = 0; i <= 3; i++) {
            if (Bpercentage[i] > 50){
                if(i % 4 == 0)
                    printOut.print('E');
                else if(i % 4 == 1)
                    printOut.print('S');
                else if(i % 4 == 2)
                    printOut.print('T');
                else if(i % 4 == 3)
                    printOut.print('J');
            }
            else if(Bpercentage[i] < 50){
                if (Bpercentage[i] > 0.5){
                    if(i % 4 == 0)
                        printOut.print('I');
                    else if(i % 4 == 1)
                        printOut.print('N');
                    else if(i % 4 == 2)
                        printOut.print('F');
                    else if(i % 4 == 3)
                        printOut.print('P');
                }
            }
            else{
                printOut.print('X');
            }
        }
    }
}