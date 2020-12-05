package day5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import rccookie.util.Console;

public class Day extends util.Day {

    @Override
    public void run1() throws Exception {
        final List<Seat> seats = getSeats();
        int maxId = -1;
        for(Seat seat : seats) if(seat.id > maxId) maxId = seat.id;
        Console.log("Highest seat id: " + maxId);
    }

    @Override
    public void run2() throws Exception {
        final List<Integer> ids = getSeats().stream().map(seat -> seat.id).collect(Collectors.toList());
        ids.sort(null);
        for(int i=ids.get(0); i<ids.get(ids.size() - 1); i++) {
            if(ids.contains(i)) continue;
            Console.log("Personal seat's id: " + i);
            return;
        }
    }


    private List<Seat> getSeats() {
        List<Seat> seats = new ArrayList<>();
        for(String s : inputInLines()) {
            int min = 0, max = 127;
            for(int i=0; min != max; i++) {
                if(s.charAt(i) == 'F') max -= (max - min + 1) / 2;
                else min += (max - min + 1) / 2;
            }
            int row = min;
            min = 0;
            max = 7;
            for(int i=7; min != max; i++) {
                if(s.charAt(i) == 'L') max -= (max - min + 1) / 2;
                else min += (max - min + 1) / 2;
            }
            seats.add(new Seat(row, min));
        }
        return seats;
    }
}
