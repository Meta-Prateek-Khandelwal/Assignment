public class WorkingDays{
public  class WorkingDays {
    public static Integer getWorkingDays(Date startDate, Date endDate) {
        Integer workingDays = 0;
        Date current = startDate.addDays(1);

        while (current < endDate) {
            DateTime currentDateTime = DateTime.newInstance(current, Time.newInstance(0, 0, 0, 0));
            if (currentDateTime.format('E') != 'Sat' && currentDateTime.format('E') != 'Sun') {
                workingDays++;
            }
            current = current.addDays(1);
        }
        return workingDays;
    }
}
}
