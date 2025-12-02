import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Assignment extends Courses {
    //region attributes
    private Course owner;// The course the assignment belongs to
    private String title;
    private Integer points;
    private priority priority;
    private LocalDate dueDate;

    public enum priority{
        negligible, very_low, low, medium, high, very_high, do_this_right_now
    }//endregion

    //region setters
    public void setOwner(Course courseObject){ this.owner = courseObject; }
    public void setOwner(String courseCode) { this.owner = getCourse(courseCode); }// you can also pass the course code
    public void setOwner(Integer courseIndex) { this.owner = getCourse(courseIndex); }// and index. might be overkill but I can always delete
    public void setTitle(String title) { this.title = title; }
    public void setPoints(Integer points) { this.points = points; }
    public void setPriority(priority assignmentPriority) { this.priority = assignmentPriority; }
    public void setDueDate(Integer mm, Integer dd, Integer yyyy){
        String dateString = String.format("%02d/%02d/%d", mm, dd, yyyy);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        this.dueDate = LocalDate.parse(dateString, formatter);
    }//endregion

    //region getters
    public Course getOwner(){ return this.owner; }
    public String getTitle(){ return this.title; }
    public Integer getPoints() { return this.points; }
    public priority getPriority() { return this.priority; }
    public LocalDate getDueDate() { return this.dueDate; }//endregion
}
