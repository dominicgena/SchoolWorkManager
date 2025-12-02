import java.time.LocalDate;

public class Assignment extends Courses {
    private Course owner;
    private LocalDate dueDateDate;
    private String title, dueDateString, assnObject;
    private Integer points;

    public Assignment(int i, String assignment, int m, int d, int y){
        setOwner(getCourse(i));
        setTitle(assignment);
        setDueDate(m,d,y);
    }

    public Assignment(Course c, String assignment, int m, int d, int y){
        setOwner(c);
        setTitle(assignment);
        setDueDate(m,d,y);
    }

    public void setOwner(Course c){ owner = c; }
    public void setTitle(String assignmentTitle){ title = assignmentTitle; }
    public void setDueDate(int m, int d, int y){
        String mm = (m < 10) ? "0" + m : "" + m;
        String dd = (d < 10) ? "0" + d : "" + d;
        int lastTwoDigits = y % 100;
        String yy = (lastTwoDigits < 10) ? "0" + lastTwoDigits : "" + lastTwoDigits;
        dueDateString = mm + "-" + dd + "-" + yy;
        dueDateDate = LocalDate.of(y,m,d);
    }

    public void setPoints(Integer p){ points = p; }
    public Course getOwner(){ return owner;}
    public String getTitle(){ return title; }
    public String getDueDateString(){ return dueDateString; }
    public LocalDate getDueDateDate(){ return dueDateDate; }
    public Integer getPoints(){ return points; }

    @Override
    public String toString(){// also acts as a getter
        String ownerStr = (owner != null) ? owner.toString(true) : " (No course provided)";
        String pointsStr = (points != null) ? points.toString() : "Not set";

        assnObject=  "Assignment: " + title
                + "\n  Class:" + ownerStr  +
                 "\n       Due: " + dueDateString +
                 "\n    Points: " + pointsStr;

        return assnObject;
    }
}
