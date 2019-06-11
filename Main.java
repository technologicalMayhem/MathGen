import java.math.BigDecimal;
import java.util.*;

public class Main
{
    public static void main(String[] args) {
        System.out.println("Solve this eqaution. Round to two decimals places. To quit enter q.");
        Main m = new Main();
        Scanner s = new Scanner(System.in);
        while (true) {
            MathProblem problem = m.makeProblem();
            System.out.println(problem.Problem);
            String input = s.nextLine();
            if (input.equals("q")) {
                break;
            }
            if (problem.Solution.equals(input)) {
                System.out.println("Correct!");
            }
            else
            {
                System.out.println("Wrong! The correct answer was: " + problem.Solution);
            }
        }
        s.close();
    }

    MathProblem makeProblem()
    {
        List<Float> numbers = new ArrayList<Float>();
        List<Operators> operators = new ArrayList<Operators>();
        Random r = new Random();
        MathProblem p = new MathProblem();
        
        //Make some random numbers
        for (int i = 0; i < r.nextInt(4) + 2; i++) {
            numbers.add((float)r.nextInt(9) + 1);
        }

        //And get operators for them
        Operators[] op = Operators.values();
        for (int i = 0; i < numbers.size() - 1; i++) {
            operators.add(op[r.nextInt(4)]);
        }

        //Write the problem down
        char[] c = new char[]{'+','-','*','/'};
        for (int i = 0; i < numbers.size(); i++) {
            p.Problem += formatFloat(numbers.get(i));
            if (operators.size() - 1 >= i) {
                p.Problem += c[operators.get(i).ordinal()];
            }
        }
        
        //First let's do multiplication and division
        for (int i = 0; i < operators.size(); i++) {
            switch (operators.get(i)) {
                case MULTIPLY:
                numbers.set(i, numbers.get(i) * numbers.get(i + 1));
                operators.remove(i);
                numbers.remove(i + 1);
                i--;
                break;
                case DEVIDE:
                numbers.set(i, numbers.get(i) / numbers.get(i + 1));
                operators.remove(i);
                numbers.remove(i + 1);
                i--;
                break;
                default:
                break;
            }
        }
        
        //Then addition and subtraction
        for (int i = 0; i < operators.size(); i++) {
            switch (operators.get(i)) {
                case ADD:
                numbers.set(i, numbers.get(i) + numbers.get(i + 1));
                operators.remove(i);
                numbers.remove(i + 1);
                i--;
                break;
                case SUBTRACT:
                numbers.set(i, numbers.get(i) - numbers.get(i + 1));
                operators.remove(i);
                numbers.remove(i + 1);
                i--;
                break;
                default:
                break;
            }
        }
        
        p.Solution = formatFloat(round(numbers.get(0), 2));
        return p;
    }

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public static String formatFloat(float f)
    {
        if(f == (long) f)
            return String.format("%d",(long)f);
        else
            return String.format("%s",f);
    }
    
    enum Operators
    {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DEVIDE
    }

    class MathProblem
    {
        public String Solution = "";
        public String Problem = "";
    }
}