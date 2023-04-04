import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

class Solution {
    public int solution(String S) {
        String[] meetings = S.strip().split("\n");
        Arrays.stream(meetings).forEach(System.out::println);

        // Create an array of boolean flags for each minute of the week
        boolean[] busy = new boolean[7 * 24 * 60];

        // Parse each meeting and mark its time slot as busy in the array
        for (String meeting : meetings) {
            String[] parts = meeting.split(" ");
            DayOfWeek dayOfWeek = fromAbbreviation(parts[0]);
            String[] times = parts[1].split("-");
            LocalTime startTime = LocalTime.parse(times[0], DateTimeFormatter.ofPattern("HH:mm"));
            LocalTime endTime = LocalTime.parse(times[1], DateTimeFormatter.ofPattern("HH:mm"));
            int startMinute = (dayOfWeek.getValue() -1) * 24 * 60 + startTime.getHour() * 60 + startTime.getMinute();
            int endMinute = 0;
            if(endTime.equals(LocalTime.MIDNIGHT)){
                endMinute = (dayOfWeek.getValue() -1) * 24 * 60 + 24 * 60;
            } else{
                endMinute = (dayOfWeek.getValue() -1) * 24 * 60 + endTime.getHour() * 60 + endTime.getMinute();
            }

            for (int i = startMinute; i < endMinute; i++) {
                busy[i] = true;
            }
        }
        // Find the longest time slot when James can sleep
        int longestSleep = 0;
        int currentSleep = 0;
        boolean busyMinute = busy[0];
        for (int i = 0; i < busy.length; i++) {
            if(i > 0)
                busyMinute = busy[i-1];
            if (!busy[i]) {
                currentSleep++;
            } else if(currentSleep != 0) {
                longestSleep = Math.max(longestSleep, currentSleep);
                currentSleep = 0;
            }
        }
        longestSleep = Math.max(longestSleep, currentSleep);

        // Return the result in minutes
        return longestSleep;
    }

    private DayOfWeek fromAbbreviation(String abbr){
        switch (abbr) {
            case "Mon":
                return DayOfWeek.MONDAY;
            case "Tue":
                return DayOfWeek.TUESDAY;
            case "Wed":
                return DayOfWeek.WEDNESDAY;
            case "Thu":
                return DayOfWeek.THURSDAY;
            case "Fri":
                return DayOfWeek.FRIDAY;
            case "Sat":
                return DayOfWeek.SATURDAY;
            case "Sun":
                return DayOfWeek.SUNDAY;
        }
        throw new IllegalArgumentException("Wrong day format");
    }
}