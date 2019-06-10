import java.util.*;

public class Main
{
    public static void main(String[] args) {
        MathProblem problem = new Main().makeProblem();
        System.out.println(problem.Problem);
        System.out.println(problem.Solution);
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
            p.Problem += numbers.get(i).toString();
            if (operators.size() - 1 >= i) {
                p.Problem += c[operators.get(i).ordinal()];
            }
        }
        
        //First let's do multiplication and division
        for (int i = 0; i < operators.size(); i++) {
            
            System.out.println(numbers);
            System.out.println(operators);

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
                
            System.out.println(numbers);
            System.out.println(operators);

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
                
        System.out.println(numbers);
        System.out.println(operators);

        p.Solution = numbers.get(0);
        return p;
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
        public Float Solution = 0f;
        public String Problem = "";
    }
}