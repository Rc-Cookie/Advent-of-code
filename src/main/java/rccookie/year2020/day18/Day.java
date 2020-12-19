package rccookie.year2020.day18;

import java.util.function.Supplier;

import com.github.rccookie.common.util.Console;

public class Day extends com.github.rccookie.adventofcode.util.Day {

    private final String[] input;

    {
        input = inputInLines();
    }

    /**
     * <h2>--- Day 18: Operation Order ---</h2><p>As you look out the window and notice a heavily-forested continent slowly appear over the horizon, you are interrupted by the child sitting next to you. They're curious if you could help them with their <span title="Or &quot;maths&quot;, if you have more than one.">math</span> homework.</p>
     * <p>Unfortunately, it seems like this "math" <a href="https://www.youtube.com/watch?v=3QtRK7Y2pPU&t=15" target="_blank">follows different rules</a> than you remember.</p>
     * <p>The homework (your puzzle input) consists of a series of expressions that consist of addition (<code>+</code>), multiplication (<code>*</code>), and parentheses (<code>(...)</code>). Just like normal math, parentheses indicate that the expression inside must be evaluated before it can be used by the surrounding expression. Addition still finds the sum of the numbers on both sides of the operator, and multiplication still finds the product.</p>
     * <p>However, the rules of <em>operator precedence</em> have changed. Rather than evaluating multiplication before addition, the operators have the <em>same precedence</em>, and are evaluated left-to-right regardless of the order in which they appear.</p>
     * <p>For example, the steps to evaluate the expression <code>1 + 2 * 3 + 4 * 5 + 6</code> are as follows:</p>
     * <pre><code><em>1 + 2</em> * 3 + 4 * 5 + 6
     *   <em>3   * 3</em> + 4 * 5 + 6
     *       <em>9   + 4</em> * 5 + 6
     *          <em>13   * 5</em> + 6
     *              <em>65   + 6</em>
     *                  <em>71</em>
     * </code></pre>
     * <p>Parentheses can override this order; for example, here is what happens if parentheses are added to form <code>1 + (2 * 3) + (4 * (5 + 6))</code>:</p>
     * <pre><code>1 + <em>(2 * 3)</em> + (4 * (5 + 6))
     * <em>1 +    6</em>    + (4 * (5 + 6))
     *      7      + (4 * <em>(5 + 6)</em>)
     *      7      + <em>(4 *   11   )</em>
     *      <em>7      +     44</em>
     *             <em>51</em>
     * </code></pre>
     * <p>Here are a few more examples:</p>
     * <ul>
     * <li><code>2 * 3 + (4 * 5)</code> becomes <em><code>26</code></em>.</li>
     * <li><code>5 + (8 * 3 + 9 + 3 * 4 * 3)</code> becomes <em><code>437</code></em>.</li>
     * <li><code>5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))</code> becomes <em><code>12240</code></em>.</li>
     * <li><code>((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2</code> becomes <em><code>13632</code></em>.</li>
     * </ul>
     * <p>Before you can help with the homework, you need to understand it yourself. <em>Evaluate the expression on each line of the homework; what is the sum of the resulting values?</em></p>
     * 
     * <p>This method will return the result for the personal input.
     */
    @Override
    public long resultPart1() throws Exception {
        long sum = 0;
        for(String line : input) sum += solve(new StringBuilder(line.replaceAll(" ", "")));
        return sum;
    }

    /**
     * <h2 id="part2">--- Part Two ---</h2><p>You manage to answer the child's questions and they finish part 1 of their homework, but get stuck when they reach the next section: <em>advanced</em> math.</p>
     * <p>Now, addition and multiplication have <em>different</em> precedence levels, but they're not the ones you're familiar with. Instead, addition is evaluated <em>before</em> multiplication.</p>
     * <p>For example, the steps to evaluate the expression <code>1 + 2 * 3 + 4 * 5 + 6</code> are now as follows:</p>
     * <pre><code><em>1 + 2</em> * 3 + 4 * 5 + 6
     *   3   * <em>3 + 4</em> * 5 + 6
     *   3   *   7   * <em>5 + 6</em>
     *   <em>3   *   7</em>   *  11
     *      <em>21       *  11</em>
     *          <em>231</em>
     * </code></pre>
     * <p>Here are the other examples from above:</p>
     * <ul>
     * <li><code>1 + (2 * 3) + (4 * (5 + 6))</code> still becomes <em><code>51</code></em>.</li>
     * <li><code>2 * 3 + (4 * 5)</code> becomes <em><code>46</code></em>.</li>
     * <li><code>5 + (8 * 3 + 9 + 3 * 4 * 3)</code> becomes <em><code>1445</code></em>.</li>
     * <li><code>5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))</code> becomes <em><code>669060</code></em>.</li>
     * <li><code>((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2</code> becomes <em><code>23340</code></em>.</li>
     * </ul>
     * <p><em>What do you get if you add up the results of evaluating the homework problems using these new rules?</em></p>
     * 
     * <p>This method will return the result for the personal input.
     */
    @Override
    public long resultPart2() throws Exception {
        long sum = 0;
        for(String line : input) sum += solve(prepare(new StringBuilder(line.replaceAll(" ", ""))));
        return sum;
    }


    private static long solve(StringBuilder expression) {
        long result = nextValue(expression, () -> solve(expression));
        char op;
        while((op = next(expression)) != ')') {
            long n = nextValue(expression, () -> solve(expression));
            switch(op) {
                case '+' -> result += n;
                case '*' -> result *= n;
                default -> Console.map("Unexpected operator", op);
            }
        }
        return result;
    }


    private static StringBuilder prepare(StringBuilder expression) {
        for(int i=0; (i = expression.indexOf("+", i)) != -1; i+=2) {
            if(expression.charAt(i-1) == ')') {
                expression.insert(openIndex(expression.toString(), i-1), '(');
            }
            else expression.insert(i-1, '(');
            if(expression.charAt(i+2) == '(') {
                expression.insert(closeIndex(expression.toString(), i+2), ')');
            }
            else expression.insert(i+3, ')');
        }
        return expression;
    }


    private static int closeIndex(String expression, int openIndex) {
        for(int i=openIndex+1;;i++) {
            if(i >= expression.length()) return expression.length();
            char c = expression.charAt(i);
            if(c == ')') return i;
            else if(c == '(') i = closeIndex(expression, i);
        }
    }

    private static int openIndex(String expression, int closeIndex) {
        for(int i=closeIndex-1;;i--) {
            if(i < 0) return -1;
            char c = expression.charAt(i);
            if(c == '(') return i;
            else if(c == ')') i = openIndex(expression, i);
        }
    }


    private static long nextValue(StringBuilder text, Supplier<Long> alternative) {
        try{
            return nextInt(text);
        } catch(NumberFormatException e) {
            return alternative.get();
        }
    }

    private static int nextInt(StringBuilder text) {
        return Integer.parseInt(Character.toString(next(text)));
    }

    private static char next(StringBuilder text) {
        if(text.length() == 0) return ')';
        char c = text.charAt(0);
        text.deleteCharAt(0);
        return c;
    }
}
