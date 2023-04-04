public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String schedule = "Sun 10:00-20:00\nFri 05:00-10:00\nFri 16:30-23:50\nSat 10:00-24:00\nSun 01:00-04:00\nSat 02:00-06:00\nTue 03:30-18:15\nTue 19:00-20:00\nWed 04:25-15:14\nWed 15:14-22:40\nThu 00:00-23:59\nMon 05:00-13:00\nMon 15:00-21:00";
        String scheduleMin = "Monday 01:00-23:00\nTuesday 01:00-23:00\nWednesday 01:00-23:00\nThursday 01:00-23:00\nFriday 01:00-23:00\nSaturday 01:00-23:00\nSunday 01:00-21:00";
        Solution solution = new Solution();
//        System.out.println(solution.solution(schedule));
        System.out.println(solution.solution(scheduleMin));
    }
}