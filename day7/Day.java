package day7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import rccookie.util.Console;

public class Day extends util.Day {

    /**
     * <h2>--- Day 7: Handy Haversacks ---</h2><p>You land at the regional airport in time for your next flight. In fact, it looks like you'll even have time to grab some food: all flights are currently delayed due to <em>issues in luggage processing</em>.</p>
     * <p>Due to recent aviation regulations, many rules (your puzzle input) are being enforced about bags and their contents; bags must be color-coded and must contain specific quantities of other color-coded bags. Apparently, nobody responsible for these regulations considered how long they would take to enforce!</p>
     * <p>For example, consider the following rules:</p>
     * <pre><code>light red bags contain 1 bright white bag, 2 muted yellow bags.
     * dark orange bags contain 3 bright white bags, 4 muted yellow bags.
     * bright white bags contain 1 shiny gold bag.
     * muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
     * shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
     * dark olive bags contain 3 faded blue bags, 4 dotted black bags.
     * vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
     * faded blue bags contain no other bags.
     * dotted black bags contain no other bags.
     * </code></pre>
     * <p>These rules specify the required contents for 9 bag types. In this example, every <code>faded blue</code> bag is empty, every <code>vibrant plum</code> bag contains 11 bags (5 <code>faded blue</code> and 6 <code>dotted black</code>), and so on.</p>
     * <p>You have a <code><em>shiny gold</em></code> bag. If you wanted to carry it in at least one other bag, how many different bag colors would be valid for the outermost bag? (In other words: how many colors can, eventually, contain at least one <code>shiny gold</code> bag?)</p>
     * <p>In the above rules, the following options would be available to you:</p>
     * <ul>
     * <li>A <code>bright white</code> bag, which can hold your <code>shiny gold</code> bag directly.</li>
     * <li>A <code>muted yellow</code> bag, which can hold your <code>shiny gold</code> bag directly, plus some other bags.</li>
     * <li>A <code>dark orange</code> bag, which can hold <code>bright white</code> and <code>muted yellow</code> bags, either of which could then hold your <code>shiny gold</code> bag.</li>
     * <li>A <code>light red</code> bag, which can hold <code>bright white</code> and <code>muted yellow</code> bags, either of which could then hold your <code>shiny gold</code> bag.</li>
     * </ul>
     * <p>So, in this example, the number of bag colors that can eventually contain at least one <code>shiny gold</code> bag is <code><em>4</em></code>.</p>
     * <p><em>How many bag colors can eventually contain at least one <code>shiny gold</code> bag?</em> (The list of rules is quite long; make sure you get all of it.)</p>
     * 
     * <p><b>This method will print the result for the personal input in the console.</b>
     */
    @Override
    public void run1() throws Exception {
        Map<String, Regulation> regulations = parseRegulations();

        Set<String> superTypes = regulations.get("shiny gold").superTypes;
        Set<String> allSuperTypes = new HashSet<>(superTypes);

        while(!superTypes.isEmpty()) {
            Set<String> old = superTypes;
            superTypes = new HashSet<>();
            for(String s : old) superTypes.addAll(regulations.get(s).superTypes);
            allSuperTypes.addAll(superTypes);
        }
        Console.log(allSuperTypes, allSuperTypes.size());
    }

    /**
     * <p><b>This method will print the result for the personal input in the console.</b>
     */
    @Override
    public void run2() throws Exception {
        Map<String, Regulation> regulations = parseRegulations();

        List<String> subTypes = new ArrayList<>();
        for(Regulation.SubBagType subType : regulations.get("shiny gold").subTypes) {
            for(int i=0; i<subType.count; i++)
                subTypes.add(subType.name);
        }
        List<String> allSubTypes = new ArrayList<>(subTypes);

        while(!subTypes.isEmpty()) {
            Console.map("SubTypes", subTypes);
            List<String> old = subTypes;
            subTypes = new ArrayList<>();
            for(String currentSubBag : old) {
                for(Regulation.SubBagType subType : regulations.get(currentSubBag).subTypes) {
                    for(int i=0; i<subType.count; i++)
                        subTypes.add(subType.name);
                }
            }
            allSubTypes.addAll(subTypes);
        }

        Console.log(allSubTypes, allSubTypes.size());
    }



    private Map<String, Regulation> parseRegulations() {
        Map<String, Regulation> regulations = new HashMap<>();

        // Add sub regulations
        for(String line : inputInLines()) {
            StringBuilder remaining = new StringBuilder(line);
            String type = remaining.substring(0, remaining.indexOf("bag") - 1);
            remaining.delete(0, line.indexOf("contain") + 8);
            if(remaining.toString().startsWith("no other bags.")) {
                regulations.put(type, new Regulation(new HashSet<>()));
                continue;
            }
            Set<Regulation.SubBagType> r = new HashSet<>();
            inlineLoop:
            while(true) {
                int count = Integer.parseInt(remaining.substring(0, remaining.indexOf(" ")));
                remaining.delete(0, remaining.indexOf(" ") + 1);
                r.add(new Regulation.SubBagType(remaining.substring(0, remaining.indexOf(" bag")), count));
                remaining.delete(0, remaining.indexOf(" bag") + 4);
                if(remaining.indexOf(",") == -1) break inlineLoop;
                remaining.delete(0, remaining.indexOf(" ") + 1);
            }
            regulations.put(type, new Regulation(r));
        }

        // Add super connections
        for(Entry<String, Regulation> bagRegulation : regulations.entrySet()) {
            for(Regulation.SubBagType subType : bagRegulation.getValue().subTypes) {
                regulations.get(subType.name).superTypes.add(bagRegulation.getKey());
            }
        }

        return regulations;
    }
}
