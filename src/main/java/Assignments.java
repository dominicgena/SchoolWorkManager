import java.util.*;

public class Assignments {
    private HashMap<Course, ArrayList<Assignment>> assignmentMap;

    public Assignments() {
        assignmentMap = new HashMap<>();
    }

    public void addAssignment(Course c, Assignment a) {
        assignmentMap.putIfAbsent(c, new ArrayList<Assignment>());
        assignmentMap.get(c).add(a);
    }
}
