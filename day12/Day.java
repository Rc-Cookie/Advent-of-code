package day12;

import com.github.rccookie.common.geometry.Transform2D;
import com.github.rccookie.common.geometry.Vector2D;
import com.github.rccookie.common.util.Console;

public class Day extends util.Day {

    public static final Vector2D N = Vector2D.UNIT_VECTOR_Y, S = N.inverted(), W = Vector2D.UNIT_VECTOR_X, E = W.inverted();

    private final String[] input;

    {
        input = inputInLines();
    }

    /**
     * <h2>--- Day 12: Rain Risk ---</h2><p>Your ferry made decent progress toward the island, but the storm came in <span title="At least it wasn't a Category Six!">faster than anyone expected</span>. The ferry needs to take <em>evasive actions</em>!</p>
     * <p>Unfortunately, the ship's navigation computer seems to be malfunctioning; rather than giving a route directly to safety, it produced extremely circuitous instructions. When the captain uses the <a href="https://en.wikipedia.org/wiki/Public_address_system" target="_blank">PA system</a> to ask if anyone can help, you quickly volunteer.</p>
     * <p>The navigation instructions (your puzzle input) consists of a sequence of single-character <em>actions</em> paired with integer input <em>values</em>. After staring at them for a few minutes, you work out what they probably mean:</p>
     * <ul>
     * <li>Action <em><code>N</code></em> means to move <em>north</em> by the given value.</li>
     * <li>Action <em><code>S</code></em> means to move <em>south</em> by the given value.</li>
     * <li>Action <em><code>E</code></em> means to move <em>east</em> by the given value.</li>
     * <li>Action <em><code>W</code></em> means to move <em>west</em> by the given value.</li>
     * <li>Action <em><code>L</code></em> means to turn <em>left</em> the given number of degrees.</li>
     * <li>Action <em><code>R</code></em> means to turn <em>right</em> the given number of degrees.</li>
     * <li>Action <em><code>F</code></em> means to move <em>forward</em> by the given value in the direction the ship is currently facing.</li>
     * </ul>
     * <p>The ship starts by facing <em>east</em>. Only the <code>L</code> and <code>R</code> actions change the direction the ship is facing. (That is, if the ship is facing east and the next instruction is <code>N10</code>, the ship would move north 10 units, but would still move east if the following action were <code>F</code>.)</p>
     * <p>For example:</p>
     * <pre><code>F10
     * N3
     * F7
     * R90
     * F11
     * </code></pre>
     * <p>These instructions would be handled as follows:</p>
     * <ul>
     * <li><code>F10</code> would move the ship 10 units east (because the ship starts by facing east) to <em>east 10, north 0</em>.</li>
     * <li><code>N3</code> would move the ship 3 units north to <em>east 10, north 3</em>.</li>
     * <li><code>F7</code> would move the ship another 7 units east (because the ship is still facing east) to <em>east 17, north 3</em>.</li>
     * <li><code>R90</code> would cause the ship to turn right by 90 degrees and face <em>south</em>; it remains at <em>east 17, north 3</em>.</li>
     * <li><code>F11</code> would move the ship 11 units south to <em>east 17, south 8</em>.</li>
     * </ul>
     * <p>At the end of these instructions, the ship's <a href="https://en.wikipedia.org/wiki/Manhattan_distance" target="_blank">Manhattan distance</a> (sum of the absolute values of its east/west position and its north/south position) from its starting position is <code>17 + 8</code> = <em><code>25</code></em>.</p>
     * <p>Figure out where the navigation instructions lead. <em>What is the Manhattan distance between that location and the ship's starting position?</em></p>
     * 
     * <p>This method will return the result for the personal input.
     */
    @Override
    public long resultPart1() throws Exception {
        Transform2D transform = new Transform2D();
        for(String line : input) {
            char action = line.charAt(0);
            int value = Integer.parseInt(line.substring(1));
            switch(action) {
                case 'N': transform.location.add(N.scaled(value)); break;
                case 'S': transform.location.add(S.scaled(value)); break;
                case 'W': transform.location.add(W.scaled(value)); break;
                case 'E': transform.location.add(E.scaled(value)); break;
                case 'L': transform.rotation += value; break;
                case 'R': transform.rotation -= value; break;
                case 'F': transform.location.add(Vector2D.angledVector(transform.rotation, value)); break;
            }
            transform.rotation %= 360;
            if(transform.rotation < 0) transform.rotation += 360;
        }
        Console.log(transform);
        transform.location.round();
        Console.log(transform);
        return (long)(Math.abs(transform.location.x()) + Math.abs(transform.location.y()));
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
