import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Assignment {
    // region constructor
    public Assignment(Course owner, String title, int points, Priority assignmentPriority, int mm, int dd, int yyyy){// master constructor
        setOwner(owner);setTitle(title);setPoints(points);setAssignmentPriority(assignmentPriority);setDueDate(mm,dd,yyyy);
    }

    public Assignment(String title, int points, Priority assignmentPriority, int mm, int dd, int yyyy){// if called with no owner
        this(null, title, points, assignmentPriority, mm, dd, yyyy);
    }// praying for the compiler to not viciously attack me

    public Assignment(String title, int points, Priority assignmentPriority){// if assignment doesn't have a due date, make it due 1/1/1970
        this(null, title, points, assignmentPriority, 1,1,1970);
    }

    public Assignment(){ this(null, "Assignment", 100, Priority.NEGLIGIBLE, 1, 1, 1970); }
    // endregion constructor

    // region overrides
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.getTitle()).append("\n").append("     Points: ").append(this.getPoints()).append("\n").append("     Priority: ").append(this.getPriority()).append("\n");
        if(this.getOwner() != null) sb.append("     Belongs to: ").append(this.getOwner().getCode()).append("\n");
        if(!this.getDueDate().equals(LocalDate.of(1970,1,1))) sb.append("     Due Date: ").append(this.getDueDate()).append("\n");
        else sb.append("     No due date\n");
        return sb.toString();
    }
    // endregion overrides

    //region fields
    private String title;
    private Integer points;
    private Priority assignmentPriority;
    private LocalDate dueDate;
    private Course owner;

    public enum Priority{
        NEGLIGIBLE, VERY_LOW, LOW, MEDIUM, HIGH, VERY_HIGH, DO_THIS_RIGHT_NOW
    }//endregion

    // region setters and getters
    //region setters
    public void setOwner(Course course){
        this.owner = course; }
    public void setTitle(String title) { this.title = title; }
    public void setPoints(Integer points) { this.points = points; }
    public void setAssignmentPriority(Priority assignmentPriority) { this.assignmentPriority = assignmentPriority; }
    public void setDueDate(Integer mm, Integer dd, Integer yyyy){
        String dateString = String.format("%02d/%02d/%d", mm, dd, yyyy);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        this.dueDate = LocalDate.parse(dateString, formatter);
    }//endregion

    //region getters
    public Course getOwner() { return this.owner; }
    public String getTitle(){ return this.title; }
    public Integer getPoints() { return this.points; }
    public Priority getPriority() { return this.assignmentPriority; }
    public LocalDate getDueDate() { return this.dueDate; }//endregion
    // endregion setters and getters
}
