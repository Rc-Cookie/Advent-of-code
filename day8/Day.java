package day8;

import java.util.ArrayList;
import java.util.List;

import rccookie.util.Console;

public class Day extends util.Day {

    /**
     * <h2>--- Day 8: Handheld Halting ---</h2><p>Your flight to the major airline hub reaches cruising altitude without incident.  While you consider checking the in-flight menu for one of those drinks that come with a little umbrella, you are interrupted by the kid sitting next to you.</p>
     * <p>Their <a target="_blank" href="https://en.wikipedia.org/wiki/Handheld_game_console">handheld game console</a> won't turn on! They ask if you can take a look.</p>
     * <p>You narrow the problem down to a strange <em>infinite loop</em> in the <span title="A trendy new line of encrypted footwear?">boot code</span> (your puzzle input) of the device. You should be able to fix it, but first you need to be able to run the code in isolation.</p>
     * <p>The boot code is represented as a text file with one <em>instruction</em> per line of text. Each instruction consists of an <em>operation</em> (<code>acc</code>, <code>jmp</code>, or <code>nop</code>) and an <em>argument</em> (a signed number like <code>+4</code> or <code>-20</code>).</p>
     * <ul>
     * <li><code>acc</code> increases or decreases a single global value called the <em>accumulator</em> by the value given in the argument. For example, <code>acc +7</code> would increase the accumulator by 7. The accumulator starts at <code>0</code>. After an <code>acc</code> instruction, the instruction immediately below it is executed next.</li>
     * <li><code>jmp</code> <em>jumps</em> to a new instruction relative to itself. The next instruction to execute is found using the argument as an <em>offset</em> from the <code>jmp</code> instruction; for example, <code>jmp +2</code> would skip the next instruction, <code>jmp +1</code> would continue to the instruction immediately below it, and <code>jmp -20</code> would cause the instruction 20 lines above to be executed next.</li>
     * <li><code>nop</code> stands for <em>No OPeration</em> - it does nothing.  The instruction immediately below it is executed next.</li>
     * </ul>
     * <p>For example, consider the following program:</p>
     * <pre><code>nop +0
     * acc +1
     * jmp +4
     * acc +3
     * jmp -3
     * acc -99
     * acc +1
     * jmp -4
     * acc +6
     * </code></pre>
     * <p>These instructions are visited in this order:</p>
     * <pre><code>nop +0  | 1
     * acc +1  | 2, 8(!)
     * jmp +4  | 3
     * acc +3  | 6
     * jmp -3  | 7
     * acc -99 |
     * acc +1  | 4
     * jmp -4  | 5
     * acc +6  |
     * </code></pre>
     * <p>First, the <code>nop +0</code> does nothing. Then, the accumulator is increased from 0 to 1 (<code>acc +1</code>) and <code>jmp +4</code> sets the next instruction to the other <code>acc +1</code> near the bottom. After it increases the accumulator from 1 to 2, <code>jmp -4</code> executes, setting the next instruction to the only <code>acc +3</code>. It sets the accumulator to 5, and <code>jmp -3</code> causes the program to continue back at the first <code>acc +1</code>.</p>
     * <p>This is an <em>infinite loop</em>: with this sequence of jumps, the program will run forever. The moment the program tries to run any instruction a second time, you know it will never terminate.</p>
     * <p>Immediately <em>before</em> the program would run an instruction a second time, the value in the accumulator is <em><code>5</code></em>.</p>
     * <p>Run your copy of the boot code. Immediately before any instruction is executed a second time, <em>what value is in the accumulator?</em></p>
     * 
     * <p><b>This method will print the result for the personal input in the console.</b>
     */
    @Override
    public long resultPart1() throws Exception {
        Statement[] statements = parseStatements();
        Statement.ACCUMULATION = 0;
        Statement currentStatement = statements[0];

        do {
            Console.map("Current statement", currentStatement);
            currentStatement = currentStatement.process(statements);
        } while (currentStatement.getCallCount() == 0);

        return Statement.ACCUMULATION;
    }

    /**
     * <h2 id="part2">--- Part Two ---</h2><p>After some careful analysis, you believe that <em>exactly one instruction is corrupted</em>.</p>
     * <p>Somewhere in the program, <em>either</em> a <code>jmp</code> is supposed to be a <code>nop</code>, <em>or</em> a <code>nop</code> is supposed to be a <code>jmp</code>. (No <code>acc</code> instructions were harmed in the corruption of this boot code.)</p>
     * <p>The program is supposed to terminate by <em>attempting to execute an instruction immediately after the last instruction in the file</em>. By changing exactly one <code>jmp</code> or <code>nop</code>, you can repair the boot code and make it terminate correctly.</p>
     * <p>For example, consider the same program from above:</p>
     * <pre><code>nop +0
     * acc +1
     * jmp +4
     * acc +3
     * jmp -3
     * acc -99
     * acc +1
     * jmp -4
     * acc +6
     * </code></pre>
     * <p>If you change the first instruction from <code>nop +0</code> to <code>jmp +0</code>, it would create a single-instruction infinite loop, never leaving that instruction.  If you change almost any of the <code>jmp</code> instructions, the program will still eventually find another <code>jmp</code> instruction and loop forever.</p>
     * <p>However, if you change the second-to-last instruction (from <code>jmp -4</code> to <code>nop -4</code>), the program terminates! The instructions are visited in this order:</p>
     * <pre><code>nop +0  | 1
     * acc +1  | 2
     * jmp +4  | 3
     * acc +3  |
     * jmp -3  |
     * acc -99 |
     * acc +1  | 4
     * <em>nop</em> -4  | 5
     * acc +6  | 6
     * </code></pre>
     * <p>After the last instruction (<code>acc +6</code>), the program terminates by attempting to run the instruction below the last instruction in the file.  With this change, after the program terminates, the accumulator contains the value <em><code>8</code></em> (<code>acc +1</code>, <code>acc +1</code>, <code>acc +6</code>).</p>
     * <p>Fix the program so that it terminates normally by changing exactly one <code>jmp</code> (to <code>nop</code>) or <code>nop</code> (to <code>jmp</code>). <em>What is the value of the accumulator after the program terminates?</em></p>
     * 
     * <p><b>This method will print the result for the personal input in the console.</b>
     */
    @Override
    public long resultPart2() throws Exception {
        for(Statement[] modifiedStatements : parseAllModfiedStatements()) {
            try{
                Statement.ACCUMULATION = 0;
                Statement currentStatement = modifiedStatements[0];
                do {
                    currentStatement = currentStatement.process(modifiedStatements);
                } while (currentStatement != null && currentStatement.getCallCount() == 0);
                if(currentStatement != null) continue;
                return Statement.ACCUMULATION;
            } catch(IndexOutOfBoundsException e) { }
        }
        return -1;
    }

    private Statement[] parseStatements() {
        String[] input = inputInLines();
        Statement[] statements = new Statement[input.length];
        for(int i=0; i<statements.length; i++) {
            statements[i] = new Statement(
                input[i].substring(0, 3),
                Integer.parseInt(input[i].substring(4)),
                i
            );
        }
        return statements;
    }

    private Statement[][] parseAllModfiedStatements() {
        String[] input = inputInLines();
        List<Statement[]> allStatements = new ArrayList<>();
        for(int j=0; j<input.length; j++) {
            if(input[j].startsWith("acc")) continue;
            Statement[] statements = new Statement[input.length];
            for(int i=0; i<statements.length; i++) {
                if(i == j) {
                    if(input[i].startsWith("jmp")) {
                        statements[i] = new Statement(
                            "nop",
                            Integer.parseInt(input[i].substring(4)),
                            i
                        );
                    }
                    else {
                        statements[i] = new Statement(
                            "jmp",
                            Integer.parseInt(input[i].substring(4)),
                            i
                        );
                    }
                }
                else {
                    statements[i] = new Statement(
                        input[i].substring(0, 3),
                        Integer.parseInt(input[i].substring(4)),
                        i
                    );
                }
                
            }
            allStatements.add(statements);
        }
        
        return allStatements.toArray(new Statement[0][]);
    }
}
