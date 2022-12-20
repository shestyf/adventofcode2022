import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            File input = new File("Day02/input.txt");
            BufferedReader reader = new BufferedReader(new FileReader(input));

            //int firstScoreTotal = findFirstTotalScore(reader);
            //System.out.printf("score total from FIRST part: %d\n", firstScoreTotal);

            // i can't have both functions working at once, whichever one runs second ends up thinking the reader is empty, and i don't know how to fix that...
            // trying to catch up with aoc days so will leave this to another day. for now i managed to get the right answers at least.

            int secondScoreTotal = findSecondTotalScore(reader);
            System.out.printf("score total from SECOND part: %d\n", secondScoreTotal);

            reader.close();
    
        } catch (FileNotFoundException e) {
            throw e;
        }
    }

    public static int findSecondTotalScore(BufferedReader reader) {
        int mytotalscore = 0;
        int win = 6;
        int draw = 3;
        int lose = 0;

        Iterator<String> it = reader.lines().iterator();

        while (it.hasNext()) {
            String[] lineParts = it.next().split(" ");
            String myhand = "";
            String enemyHand = returnHandName(lineParts[0]);
            System.out.println(enemyHand);
            String result = lineParts[1];

            int handscore = 0;

            switch (result) {
                case "X":
                    if (enemyHand == "rock") {
                        myhand = "scissors";
                    } else if (enemyHand == "paper") {
                        myhand = "rock";
                    } else {
                        myhand = "paper";
                    }

                    handscore = returnScore(myhand);

                    mytotalscore = mytotalscore + lose + handscore;
                    System.out.printf("enemy played %s, we played %s. we get +%d for the hand, and +%d for the loss.\nour total score is now %d\n", enemyHand, myhand, handscore, lose, mytotalscore);

                    //lose
                    break;
                case "Y":
                    myhand = enemyHand;

                    handscore = returnScore(myhand);

                    mytotalscore = mytotalscore + draw + handscore;
                    System.out.printf("enemy played %s, so did we. we get +%d for the hand, and +%d for the draw.\nour total score is now %d\n", myhand, handscore, draw, mytotalscore);

                    //draw
                    break;
                case "Z":
                    if (enemyHand == "rock") {
                        myhand = "paper";
                    } else if (enemyHand == "paper") {
                        myhand = "scissors";
                    } else {
                        myhand = "rock";
                    }

                    handscore = returnScore(myhand);

                    mytotalscore = mytotalscore + win + handscore;
                    System.out.printf("enemy played %s, we played %s. we get +%d for the hand, and +%d for the win.\nour total score is now %d\n", enemyHand, myhand, handscore, win, mytotalscore);

                    //win
                    break;
            }
        }

        return mytotalscore;
    }

    public static int findFirstTotalScore(BufferedReader reader) {
        int win = 6;
        int draw = 3;
        int lose = 0;

        int enemyScore = 0;
        int myScore = 0;

        Iterator<String> it = reader.lines().iterator();

        int i = 1;

        while (it.hasNext()) {
            System.out.printf("match number %d! Go!\n", i);
            i++;

            String[] hands = it.next().split(" ");

            String enemy = returnHandName(hands[0]);
            String me = returnHandName(hands[1]);
            int myhandscore = returnScore(me);
            int enemyhandscore = returnScore(enemy);
            int myhandtotal = 0;
            int enemyhandtotal = 0;

            System.out.printf("enemy %s vs me %s\n", enemy, me);

            if (enemy == me) {
                System.out.println("hm, a draw... not very exciting");

                int total = draw + myhandscore;
                System.out.printf("you each get +%d for a draw, and +%d for playing %s, increasing both scores with a total of +%d\n", draw, myhandscore, me, total);
                enemyScore = enemyScore + total;
                myScore = myScore + total;
            } else {
                switch (enemy) {
                    case "rock":
                        if (me == "paper") {
                            myhandtotal = win + myhandscore;
                            System.out.printf("sweet victory!! you crushed him!\nyou get +%d for playing %s and +%d for the win! your score total increases with +%d", myhandscore, me, win, myhandtotal);
                            myScore = myScore + myhandtotal;

                            enemyhandtotal = lose + enemyhandscore;
                            System.out.printf("enemy gets +%d for the loss, and +%d for his hand, increasing his score with +%d\n", lose, enemyhandscore, enemyhandtotal);
                            enemyScore = enemyScore + enemyhandtotal;
                        } else {
                            myhandtotal = lose + myhandscore;
                            System.out.printf("oh snap, you lost! not to worry though, it's all according to plan...\nyou get +%d for playing %s and +%d losing. your score total increases with +%d\n", myhandscore, me, win, myhandtotal);
                            myScore = myScore + myhandtotal;

                            enemyhandtotal = win + enemyhandscore;
                            System.out.printf("enemy gets +%d for the win, and +%d for his hand, increasing his score with +%d!\n", lose, enemyhandscore, enemyhandtotal);
                            enemyScore = enemyScore + enemyhandtotal;
                        }
                        break;
                    case "paper":
                        if (me == "scissors") {
                            myhandtotal = win + myhandscore;
                            System.out.printf("sweet victory!! you crushed him!\nyou get +%d for playing %s and +%d for the win! your score total increases with +%d\n", myhandscore, me, win, myhandtotal);
                            myScore = myScore + myhandtotal;

                            enemyhandtotal = lose + enemyhandscore;
                            System.out.printf("enemy gets +%d for the loss, and +%d for his hand, increasing his score with +%d\n", lose, enemyhandscore, enemyhandtotal);
                            enemyScore = enemyScore + enemyhandtotal;
                        } else {
                            myhandtotal = lose + myhandscore;
                            System.out.printf("oh snap, you lost! not to worry though, it's all according to plan...\nyou get +%d for playing %s and +%d losing. your score total increases with +%d\n", myhandscore, me, win, myhandtotal);
                            myScore = myScore + myhandtotal;

                            enemyhandtotal = win + enemyhandscore;
                            System.out.printf("enemy gets +%d for the win, and +%d for his hand, increasing his score with +%d!\n", lose, enemyhandscore, enemyhandtotal);
                            enemyScore = enemyScore + enemyhandtotal;
                        }
                        break;
                    case "scissors":
                        if (me == "rock") {
                            myhandtotal = win + myhandscore;
                            System.out.printf("sweet victory!! you crushed him!\nyou get +%d for playing %s and +%d for the win! your score total increases with +%d", myhandscore, me, win, myhandtotal);
                            myScore = myScore + myhandtotal;

                            enemyhandtotal = lose + enemyhandscore;
                            System.out.printf("enemy gets +%d for the loss, and +%d for his hand, increasing his score with +%d\n", lose, enemyhandscore, enemyhandtotal);
                            enemyScore = enemyScore + enemyhandtotal;
                        } else {
                            myhandtotal = lose + myhandscore;
                            System.out.printf("oh snap, you lost! not to worry though, it's all according to plan...\nyou get +%d for playing %s and +%d losing. your score total increases with +%d\n", myhandscore, me, win, myhandtotal);
                            myScore = myScore + myhandtotal;

                            enemyhandtotal = win + enemyhandscore;
                            System.out.printf("enemy gets +%d for the win, and +%d for his hand, increasing his score with +%d!\n", lose, enemyhandscore, enemyhandtotal);
                            enemyScore = enemyScore + enemyhandtotal;
                        }
                        break;
                
                }
            }

            System.out.printf("CURRENT SCORES:\nme %d\nenemy %d\n", myScore, enemyScore);
        }

        return myScore;
    }

    public static int returnScore(String hand) {
        if (hand == "rock") {
            return 1;
        } else if (hand == "paper") {
            return 2;
        } else {
            return 3;
        }
    }
       
    public static String returnHandName(String hand) {
        String rock = "rock";
        String paper = "paper";
        String scissors = "scissors";
        
        switch (hand) {
            case "X":
            System.out.printf("parsing %s into %s\n", hand, rock);
                hand = rock;
                break;
            case "A":
            System.out.printf("parsing %s into %s\n", hand, rock);
                hand = rock;
                break;
            case "Y":
            System.out.printf("parsing %s into %s\n", hand, paper);
                hand = paper;
                break;
            case "B":
            System.out.printf("parsing %s into %s\n", hand, paper);
                hand = paper;
                break;
            case "Z":
            System.out.printf("parsing %s into %s\n", hand, scissors);
                hand = scissors;
                break;
            case "C":
            System.out.printf("parsing %s into %s\n", hand, scissors);
                hand = scissors;
                break;
        }

        return hand;
    } 
}
