import java.util.Stack;

public class Main {

    //if you find a smallest element; then put the distance to it in the array; if not put number of elements
    //including current element in the array [reason is to calculate all combinations]

    static int M=(int)1e9+7;

    public static void main(String[] args) {


        System.out.println( sumSubarrayMins(new int[]{3,1,2,4}));

    }

    public static int sumSubarrayMins(int[] A) {
        // initialize previous less element and next less element of
        // each element in the array

        Stack<int[]> previousLess = new Stack<>();
        Stack<int[]> nextLess = new Stack<>();
        int[] leftDistance = new int[A.length];
        int[] rightDistance = new int[A.length];

        for(int i=0; i<A.length; i++)
        {
            // use ">=" to deal with duplicate elements
            while(!previousLess.isEmpty() && previousLess.peek()[0] >= A[i])
            {
                previousLess.pop();
            }

            leftDistance[i] = previousLess.isEmpty() ? i+1 : i - previousLess.peek()[1];
            previousLess.push(new int[]{A[i], i});

        }

        for(int i=A.length-1; i>=0; i--)
        {
            while(!nextLess.isEmpty() && nextLess.peek()[0] > A[i])
            {
                nextLess.pop();
            }

            rightDistance[i] = nextLess.isEmpty() ? A.length - i : nextLess.peek()[1] - i;
            nextLess.push(new int[]{A[i], i});
        }

        int ans = 0;
        for(int i=0; i<A.length; i++)
        {
            ans = (ans + A[i] * leftDistance[i] * rightDistance[i]) % M;
        }
        return ans;
    }

}
