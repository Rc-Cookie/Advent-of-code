package rccookie.year2020.day16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.github.rccookie.common.util.Console;
import com.github.rccookie.common.util.Range;

public class Day extends com.github.rccookie.adventofcode.util.Day {

    private final String[] input;

    {
        input = inputInLines();
    }

    /**
     * <h2>--- Day 16: Ticket Translation ---</h2><p>As you're walking to yet another connecting flight, you realize that one of the legs of your re-routed trip coming up is on a high-speed train. However, the train ticket you were given is in a language you don't understand. You should probably figure out what it says before you get to the train station after the next flight.</p>
     * <p>Unfortunately, you <span title="This actually happened to me once, but I solved it by just asking someone.">can't actually <em>read</em> the words on the ticket</span>. You can, however, read the numbers, and so you figure out <em>the fields these tickets must have</em> and <em>the valid ranges</em> for values in those fields.</p>
     * <p>You collect the <em>rules for ticket fields</em>, the <em>numbers on your ticket</em>, and the <em>numbers on other nearby tickets</em> for the same train service (via the airport security cameras) together into a single document you can reference (your puzzle input).</p>
     * <p>The <em>rules for ticket fields</em> specify a list of fields that exist <em>somewhere</em> on the ticket and the <em>valid ranges of values</em> for each field. For example, a rule like <code>class: 1-3 or 5-7</code> means that one of the fields in every ticket is named <code>class</code> and can be any value in the ranges <code>1-3</code> or <code>5-7</code> (inclusive, such that <code>3</code> and <code>5</code> are both valid in this field, but <code>4</code> is not).</p>
     * <p>Each ticket is represented by a single line of comma-separated values. The values are the numbers on the ticket in the order they appear; every ticket has the same format. For example, consider this ticket:</p>
     * <pre><code>.--------------------------------------------------------.
     * | ????: 101    ?????: 102   ??????????: 103     ???: 104 |
     * |                                                        |
     * | ??: 301  ??: 302             ???????: 303      ??????? |
     * | ??: 401  ??: 402           ???? ????: 403    ????????? |
     * '--------------------------------------------------------'
     * </code></pre>
     * <p>Here, <code>?</code> represents text in a language you don't understand. This ticket might be represented as <code>101,102,103,104,301,302,303,401,402,403</code>; of course, the actual train tickets you're looking at are <em>much</em> more complicated. In any case, you've extracted just the numbers in such a way that the first number is always the same specific field, the second number is always a different specific field, and so on - you just don't know what each position actually means!</p>
     * <p>Start by determining which tickets are <em>completely invalid</em>; these are tickets that contain values which <em>aren't valid for any field</em>. Ignore <em>your ticket</em> for now.</p>
     * <p>For example, suppose you have the following notes:</p>
     * <pre><code>class: 1-3 or 5-7
     * row: 6-11 or 33-44
     * seat: 13-40 or 45-50
     * 
     * your ticket:
     * 7,1,14
     * 
     * nearby tickets:
     * 7,3,47
     * 40,<em>4</em>,50
     * <em>55</em>,2,20
     * 38,6,<em>12</em>
     * </code></pre>
     * <p>It doesn't matter which position corresponds to which field; you can identify invalid <em>nearby tickets</em> by considering only whether tickets contain <em>values that are not valid for any field</em>. In this example, the values on the first <em>nearby ticket</em> are all valid for at least one field. This is not true of the other three <em>nearby tickets</em>: the values <code>4</code>, <code>55</code>, and <code>12</code> are are not valid for any field. Adding together all of the invalid values produces your <em>ticket scanning error rate</em>: <code>4 + 55 + 12</code> = <em><code>71</code></em>.</p>
     * <p>Consider the validity of the <em>nearby tickets</em> you scanned. <em>What is your ticket scanning error rate?</em></p>
     * 
     * <p>This method will return the result for the personal input.
     */
    @Override
    public long resultPart1() throws Exception {
        long count = 0;
        Set<Integer> allowed = getAllowed();
        for(int i=getTicketsStart(); i<input.length; i++) {
            for(String nString : input[i].split(",")) {
                int n = Integer.parseInt(nString);
                if(!allowed.contains(n)) count += n;
            }
        }
        return count;
    }

    /**
     * <h2 id="part2">--- Part Two ---</h2><p>Now that you've identified which tickets contain invalid values, <em>discard those tickets entirely</em>. Use the remaining valid tickets to determine which field is which.</p>
     * <p>Using the valid ranges for each field, determine what order the fields appear on the tickets. The order is consistent between all tickets: if <code>seat</code> is the third field, it is the third field on every ticket, including <em>your ticket</em>.</p>
     * <p>For example, suppose you have the following notes:</p>
     * <pre><code>class: 0-1 or 4-19
     * row: 0-5 or 8-19
     * seat: 0-13 or 16-19
     * 
     * your ticket:
     * 11,12,13
     * 
     * nearby tickets:
     * 3,9,18
     * 15,1,5
     * 5,14,9
     * </code></pre>
     * <p>Based on the <em>nearby tickets</em> in the above example, the first position must be <code>row</code>, the second position must be <code>class</code>, and the third position must be <code>seat</code>; you can conclude that in <em>your ticket</em>, <code>class</code> is <code>12</code>, <code>row</code> is <code>11</code>, and <code>seat</code> is <code>13</code>.</p>
     * <p>Once you work out which field is which, look for the six fields on <em>your ticket</em> that start with the word <code>departure</code>. <em>What do you get if you multiply those six values together?</em></p>
     * 
     * <p>This method will return the result for the personal input.
     */
    @Override
    public long resultPart2() throws Exception {

        // Getting values in tickets and the relesets

        int[][] values = Arrays.stream(getValidTickets())
            .map(line -> Arrays.stream(line.split(","))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray())
            .collect(Collectors.toList()).toArray(new int[0][]);
        List<Set<Integer>> allowedPerRow = getAllowedPerRow();
        List<Set<Integer>> allowedPerRule = new ArrayList<>();


        // For each ruleset figuring out which columns would be allowed

        // Iterates through every rule
        for(Set<Integer> allowedInCurrentRule : allowedPerRow) {
            Set<Integer> columnsThatFitRuleset = new HashSet<>();

            // Iterate through the columns
            columnLoop:
            for(int checkedColumn : Range.of(values[0].length)) {
                // Iterate through every value in the currently chacked column
                for(int row : Range.of(values.length)) {
                    if(!allowedInCurrentRule.contains(values[row][checkedColumn])) continue columnLoop;
                }
                columnsThatFitRuleset.add(checkedColumn);
            }
            allowedPerRule.add(columnsThatFitRuleset);
        }

        //Console.map("Allowed columns per rule", allowedPerRule);


        // Figure out the distribution where every rule corresponds to exactly one column

        int[] correspondingColumns = new int[getTicketsStart() - 5];

        solvingLoop:
        while(true) {
            // Check for a rule that is only matched by a single (remaining) column
            for(int i : Range.of(allowedPerRule.size())){
                if(allowedPerRule.get(i).size() == 1) {
                    correspondingColumns[i] = allowedPerRule.get(i).stream().findAny().get();
                    allowedPerRule.forEach(allowed -> allowed.remove(correspondingColumns[i]));
                    continue solvingLoop;
                }
            }

            // Check for a column that is only allowed by one rule
            Map<Integer, int[]> occurences = new HashMap<>();
            for(int i : Range.of(allowedPerRule.size())) {
                for(int column : allowedPerRule.get(i)) {
                    occurences.put(column, new int[] {occurences.getOrDefault(column, new int[] {0})[0] + 1, i});
                }
            }
            for(Entry<Integer, int[]> entry : occurences.entrySet()) {
                if(entry.getValue()[0] == 1) {
                    correspondingColumns[entry.getValue()[1]] = entry.getKey();
                    allowedPerRule.get(entry.getValue()[1]).clear();
                    continue solvingLoop;
                }
            }

            break;
        }

        Console.map("Index for each rule", Arrays.toString(correspondingColumns));

        int[] personal = Arrays.stream(input[getTicketsStart() - 3].split(",")).mapToInt(Integer::parseInt).toArray();

        long out = 1;
        for(int i : Range.of(6)) out *= (long)personal[correspondingColumns[i]];
        return out;
    }


    private int getTicketsStart() {
        int i=0;
        for(;!input[i].equals("nearby tickets:"); i++);
        return i + 1;
    }

    private List<Set<Integer>> getAllowedPerRow() {
        List<Set<Integer>> allowed = new ArrayList<>();

        for(int i=0; input[i].replaceAll(" ", "").length() != 0; i++) {
            Set<Integer> allowedInLine = new HashSet<>();
            StringBuilder line = new StringBuilder(input[i]);

            line.delete(0, line.indexOf(":") + 2);
            int min = Integer.parseInt(line.substring(0, line.indexOf("-")));
            line.delete(0, line.indexOf("-") + 1);
            int max = Integer.parseInt(line.substring(0, line.indexOf(" ")));
            for(int j=min; j<=max; j++) allowedInLine.add(j);

            line.delete(0, line.indexOf("or") + 3);
            min = Integer.parseInt(line.substring(0, line.indexOf("-")));
            line.delete(0, line.indexOf("-") + 1);
            max = Integer.parseInt(line.toString());
            for(int j=min; j<=max; j++) allowedInLine.add(j);

            allowed.add(allowedInLine);
        }
        return allowed;
    }

    private Set<Integer> getAllowed() {
        Set<Integer> allowed = new HashSet<>();
        for(Set<Integer> allowedInLine : getAllowedPerRow()) allowed.addAll(allowedInLine);
        return allowed;
    }

    private String[] getValidTickets() {
        Set<String> valid = new HashSet<>();
        Set<Integer> allowed = getAllowed();
        int start = getTicketsStart();
        valid.add(input[start - 3]);

        ticketLoop:
        for(int i=start; i<input.length; i++) {
            for(String n : input[i].split(",")) if(!allowed.contains(Integer.parseInt(n))) continue ticketLoop;
            valid.add(input[i]);
        }

        return valid.toArray(new String[0]);
    }
}
