package rccookie.year2020.day13;

import com.github.rccookie.common.util.Console;

public class Day extends com.github.rccookie.adventofcode.util.Day {

    private final String[] input;

    {
        input = inputInLines();
    }

    /**
     * <h2>--- Day 13: Shuttle Search ---</h2><p>Your ferry can make it safely to a nearby port, but it won't get much further. When you call to book another ship, you discover that no ships embark from that port to your vacation island. You'll need to get from the port to the nearest airport.</p>
     * <p>Fortunately, a shuttle bus service is available to bring you from the sea port to the airport!  Each bus has an ID number that also indicates <em>how often the bus leaves for the airport</em>.</p>
     * <p>Bus schedules are defined based on a <em>timestamp</em> that measures the <em>number of minutes</em> since some fixed reference point in the past. At timestamp <code>0</code>, every bus simultaneously departed from the sea port. After that, each bus travels to the airport, then various other locations, and finally returns to the sea port to repeat its journey forever.</p>
     * <p>The time this loop takes a particular bus is also its ID number: the bus with ID <code>5</code> departs from the sea port at timestamps <code>0</code>, <code>5</code>, <code>10</code>, <code>15</code>, and so on. The bus with ID <code>11</code> departs at <code>0</code>, <code>11</code>, <code>22</code>, <code>33</code>, and so on. If you are there when the bus departs, you can ride that bus to the airport!</p>
     * <p>Your notes (your puzzle input) consist of two lines.  The first line is your estimate of the <em>earliest timestamp you could depart on a bus</em>. The second line lists the bus IDs that are in service according to the shuttle company; entries that show <code>x</code> must be out of service, so you decide to ignore them.</p>
     * <p>To save time once you arrive, your goal is to figure out <em>the earliest bus you can take to the airport</em>. (There will be exactly one such bus.)</p>
     * <p>For example, suppose you have the following notes:</p>
     * <pre><code>939
     * 7,13,x,x,59,x,31,19
     * </code></pre>
     * <p>Here, the earliest timestamp you could depart is <code>939</code>, and the bus IDs in service are <code>7</code>, <code>13</code>, <code>59</code>, <code>31</code>, and <code>19</code>. Near timestamp <code>939</code>, these bus IDs depart at the times marked <code>D</code>:</p>
     * <pre><code>time   bus 7   bus 13  bus 59  bus 31  bus 19
     * 929      .       .       .       .       .
     * 930      .       .       .       D       .
     * 931      D       .       .       .       D
     * 932      .       .       .       .       .
     * 933      .       .       .       .       .
     * 934      .       .       .       .       .
     * 935      .       .       .       .       .
     * 936      .       D       .       .       .
     * 937      .       .       .       .       .
     * 938      D       .       .       .       .
     * <em>939      .       .       .       .       .</em>
     * 940      .       .       .       .       .
     * 941      .       .       .       .       .
     * 942      .       .       .       .       .
     * 943      .       .       .       .       .
     * <em>944      .       .       D       .       .</em>
     * 945      D       .       .       .       .
     * 946      .       .       .       .       .
     * 947      .       .       .       .       .
     * 948      .       .       .       .       .
     * 949      .       D       .       .       .
     * </code></pre>
     * <p>The earliest bus you could take is bus ID <code>59</code>. It doesn't depart until timestamp <code>944</code>, so you would need to wait <code>944 - 939 = 5</code> minutes before it departs. Multiplying the bus ID by the number of minutes you'd need to wait gives <em><code>295</code></em>.</p>
     * <p><em>What is the ID of the earliest bus you can take to the airport multiplied by the number of minutes you'll need to wait for that bus?</em></p>
     * 
     * <p>This method will return the result for the personal input.
     */
    @Override
    public long resultPart1() throws Exception {
        Console.map("Input", input);
        return -1;
    }

    /**
     * This description will be generated once the first task is completed. Please don't change anything!
     * <p>This method will return the result for the personal input.
     */
    @Override
    public long resultPart2() throws Exception {
        super.resultPart2(); //Don't edit this until task 1 is done or the code will try to download the second description every time
        return -1;
    }
}
