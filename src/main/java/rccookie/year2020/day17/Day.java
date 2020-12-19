package rccookie.year2020.day17;

import java.util.HashSet;
import java.util.Set;

import com.github.rccookie.common.geometry.Vector3D;
import com.github.rccookie.common.util.Console;
import com.github.rccookie.common.util.Range;

public class Day extends com.github.rccookie.adventofcode.util.Day {

    private static final char ACTIVE = '#';

    private final String[] input;

    {
        input = inputInLines();
    }

    /**
     * <h2>--- Day 17: Conway Cubes ---</h2><p>As your flight slowly drifts through the sky, the Elves at the Mythical Information Bureau at the North Pole contact you. They'd like some help debugging a malfunctioning experimental energy source aboard one of their super-secret imaging satellites.</p>
     * <p>The experimental energy source is based on cutting-edge technology: a set of <span title="Rest in peace, Conway.">Conway</span> Cubes contained in a pocket dimension! When you hear it's having problems, you can't help but agree to take a look.</p>
     * <p>The pocket dimension contains an infinite 3-dimensional grid. At every integer 3-dimensional coordinate (<code>x,y,z</code>), there exists a single cube which is either <em>active</em> or <em>inactive</em>.</p>
     * <p>In the initial state of the pocket dimension, almost all cubes start <em>inactive</em>. The only exception to this is a small flat region of cubes (your puzzle input); the cubes in this region start in the specified <em>active</em> (<code>#</code>) or <em>inactive</em> (<code>.</code>) state.</p>
     * <p>The energy source then proceeds to boot up by executing six <em>cycles</em>.</p>
     * <p>Each cube only ever considers its <em>neighbors</em>: any of the 26 other cubes where any of their coordinates differ by at most <code>1</code>. For example, given the cube at <code>x=1,y=2,z=3</code>, its neighbors include the cube at <code>x=2,y=2,z=2</code>, the cube at <code>x=0,y=2,z=3</code>, and so on.</p>
     * <p>During a cycle, <em>all</em> cubes <em>simultaneously</em> change their state according to the following rules:</p>
     * <ul>
     * <li>If a cube is <em>active</em> and <em>exactly <code>2</code> or <code>3</code></em> of its neighbors are also active, the cube remains <em>active</em>. Otherwise, the cube becomes <em>inactive</em>.</li>
     * <li>If a cube is <em>inactive</em> but <em>exactly <code>3</code></em> of its neighbors are active, the cube becomes <em>active</em>. Otherwise, the cube remains <em>inactive</em>.</li>
     * </ul>
     * <p>The engineers responsible for this experimental energy source would like you to simulate the pocket dimension and determine what the configuration of cubes should be at the end of the six-cycle boot process.</p>
     * <p>For example, consider the following initial state:</p>
     * <pre><code>.#.
     * ..#
     * ###
     * </code></pre>
     * <p>Even though the pocket dimension is 3-dimensional, this initial state represents a small 2-dimensional slice of it. (In particular, this initial state defines a 3x3x1 region of the 3-dimensional space.)</p>
     * <p>Simulating a few cycles from this initial state produces the following configurations, where the result of each cycle is shown layer-by-layer at each given <code>z</code> coordinate (and the frame of view follows the active cells in each cycle):</p>
     * <pre><code>Before any cycles:
     * 
     * z=0
     * .#.
     * ..#
     * ###
     * 
     * 
     * After 1 cycle:
     * 
     * z=-1
     * #..
     * ..#
     * .#.
     * 
     * z=0
     * #.#
     * .##
     * .#.
     * 
     * z=1
     * #..
     * ..#
     * .#.
     * 
     * 
     * After 2 cycles:
     * 
     * z=-2
     * .....
     * .....
     * ..#..
     * .....
     * .....
     * 
     * z=-1
     * ..#..
     * .#..#
     * ....#
     * .#...
     * .....
     * 
     * z=0
     * ##...
     * ##...
     * #....
     * ....#
     * .###.
     * 
     * z=1
     * ..#..
     * .#..#
     * ....#
     * .#...
     * .....
     * 
     * z=2
     * .....
     * .....
     * ..#..
     * .....
     * .....
     * 
     * 
     * After 3 cycles:
     * 
     * z=-2
     * .......
     * .......
     * ..##...
     * ..###..
     * .......
     * .......
     * .......
     * 
     * z=-1
     * ..#....
     * ...#...
     * #......
     * .....##
     * .#...#.
     * ..#.#..
     * ...#...
     * 
     * z=0
     * ...#...
     * .......
     * #......
     * .......
     * .....##
     * .##.#..
     * ...#...
     * 
     * z=1
     * ..#....
     * ...#...
     * #......
     * .....##
     * .#...#.
     * ..#.#..
     * ...#...
     * 
     * z=2
     * .......
     * .......
     * ..##...
     * ..###..
     * .......
     * .......
     * .......
     * </code></pre>
     * <p>After the full six-cycle boot process completes, <em><code>112</code></em> cubes are left in the <em>active</em> state.</p>
     * <p>Starting with your given initial configuration, simulate six cycles. <em>How many cubes are left in the active state after the sixth cycle?</em></p>
     * 
     * <p>This method will return the result for the personal input.
     */
    @Override
    public long resultPart1() throws Exception {
        Set<Vector3D> active = new HashSet<>();
        final Vector3D size = new Vector3D(input[0].length(), input.length, 1);
        for(int y : Range.of(input.length)) for(int x : Range.of(input[y].length()))
            if(input[y].charAt(x) == ACTIVE) active.add(new Vector3D(x, y));

        for(int i : Range.of(6)) {
            Console.map(i, active);
            Set<Vector3D> newActive = new HashSet<>();
            for(long x : Range.of(-i, size.x() + i)) {
                for(long y : Range.of(-i, size.y() + i)) {
                    for(long z : Range.of(-i, size.z() + i)) {
                        Vector3D current = new Vector3D(x, y, z);
                        
                        HashSet<Vector3D> otherss = new HashSet<>(active);
                        otherss.remove(current);
                        int count = getNeighbours(current, otherss).size();

                        if(active.contains(current)) {
                            HashSet<Vector3D> others = new HashSet<>(active);
                            others.remove(current);
                            int neighbourCount = getNeighbours(current, others).size();
                            if(neighbourCount == 2 || neighbourCount == 3)
                                newActive.add(current);
                        }
                        else{
                            if(getNeighbours(current, active).size() == 3)
                                newActive.add(current);
                        }
                    }
                }
            }
            active = newActive;
        }

        return active.size();
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

    private static Set<Vector3D> getNeighbours(Vector3D target, Set<Vector3D> others) {
        Set<Vector3D> neighbours = new HashSet<>();
        for(Vector3D other : others) {
            Vector3D dif = target.subtracted(other);
            if(Math.abs(dif.x()) > 1 || Math.abs(dif.y()) > 1 || Math.abs(dif.z()) > 1){
                continue;
            }
            neighbours.add(other);
        }
        return neighbours;
    }

    public static void main(String[] args) {
        Set<Vector3D> test = new HashSet<>();
        test.add(new Vector3D());
        test.add(new Vector3D(0, 1));
        test.add(new Vector3D(0, 2));
        test.add(new Vector3D(1));
        test.add(new Vector3D(1, 2));
        test.add(new Vector3D(2));
        test.add(new Vector3D(2, 1));
        test.add(new Vector3D(2, 2));

        test.add(new Vector3D(0, 0, -1));
        test.add(new Vector3D(0, 1, -1));
        test.add(new Vector3D(0, 2, -1));
        test.add(new Vector3D(1, 0, -1));
        test.add(new Vector3D(1, 1, -1));
        test.add(new Vector3D(1, 2, -1));
        test.add(new Vector3D(2, 0, -1));
        test.add(new Vector3D(2, 1, -1));
        test.add(new Vector3D(2, 2, -1));

        test.add(new Vector3D(0, 0, 1));
        test.add(new Vector3D(0, 1, 1));
        test.add(new Vector3D(0, 2, 1));
        test.add(new Vector3D(1, 0, 1));
        test.add(new Vector3D(1, 1, 1));
        test.add(new Vector3D(1, 2, 1));
        test.add(new Vector3D(2, 0, 1));
        test.add(new Vector3D(2, 1, 1));
        test.add(new Vector3D(2, 2, 1));
        Console.log(getNeighbours(new Vector3D(1, 1), test).size());
    }
}
