import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class Solution {
    public int solution(String S) {
        boolean[] busyMinutesSlot = getBusyArray(S);
        // Find the longest time slot when James can sleep
        int sleepTimeInMinutes = 0;
        int possibleSleep = 0;
        for (boolean busySlot : busyMinutesSlot) {
            if (!busySlot) {
                possibleSleep++;
            } else if (possibleSleep != 0) {
                sleepTimeInMinutes = Math.max(sleepTimeInMinutes, possibleSleep);
                possibleSleep = 0;
            }
        }
        sleepTimeInMinutes = Math.max(sleepTimeInMinutes, possibleSleep);

        return sleepTimeInMinutes;
    }

    private boolean[] getBusyArray(String schedule) {
        String[] meetings = schedule.strip().split("\n");
        // Create an array of boolean flags for each minute of the week
        boolean[] busyMinutesSlot = new boolean[7 * 24 * 60];

        // Parse each meeting
        for (String meeting : meetings) {
            String[] parts = meeting.split(" ");
            DayOfWeek dayOfWeek = fromAbbreviation(parts[0]);
            String[] times = parts[1].split("-");
            LocalTime startTime = LocalTime.parse(times[0], DateTimeFormatter.ofPattern("HH:mm"));
            LocalTime endTime = LocalTime.parse(times[1], DateTimeFormatter.ofPattern("HH:mm"));
            int startMinute = (dayOfWeek.getValue() -1) * 24 * 60 + startTime.getHour() * 60 + startTime.getMinute();
            int endMinute;
            if(endTime.equals(LocalTime.MIDNIGHT)){
                endMinute = (dayOfWeek.getValue() -1) * 24 * 60 + 24 * 60;
            } else{
                endMinute = (dayOfWeek.getValue() -1) * 24 * 60 + endTime.getHour() * 60 + endTime.getMinute();
            }
            // Mark time slot as busy in the array
            for (int i = startMinute; i < endMinute; i++) {
                busyMinutesSlot[i] = true;
            }
        }
        return busyMinutesSlot;
    }

    // Parse day of month abbreviation to DayOfWork instance
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