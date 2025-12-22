import java.util.Objects;

public class Course implements Comparable<Course>{
    private String code, instructor, location;
    private Integer credits;// number of credits

    // region constructors
    public Course(){
        this("Code", "Instructor", "Location", 0);
    }

    public Course(String code, String instructor, String location, Integer credits){
        setCode(code);
        setInstructor(instructor);
        setLocation(location);
        setCredits(credits);
    }
    // endregion constructors

    // region overrides
    //region toString
    public String toString(boolean bare){
        StringBuilder c = new StringBuilder();
        if(!bare){
            c.append("\n      Code: ").append(this.getCode());
            c.append("\nInstructor: ").append(this.getInstructor());
            c.append("\n  Location: ").append(this.getLocation());
            c.append("\n   Credits: ").append(this.getCredits());
            c.append(('\n'));
        }else{
            c.append(this.getCode());
        }

        return c.toString().replaceAll("[\\[\\]]", "");
    }


    @Override
    public String toString() {
        // Call your custom method with a default value
        // Passing 'false' means it will print the full details by default
        return this.toString(true).replaceAll("[\\[\\]]", "");
    }
    //endregion toString

    @Override
    public int compareTo(Course o) {
        return this.getCode().toUpperCase().compareTo(o.getCode().toUpperCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        // Check if the unique code (e.g., "CPSC 221") is the same
        return Objects.equals(code, course.code);
    }

    @Override
    public int hashCode() {
        // Generate a hash based on the code
        return Objects.hash(code);
    }// probably not needed anymore
    // endregion overrides

    // region setters and getters
    // region setters
    public void setCode(String code)
        { if (safeSet(code, "Code") != null) this.code = safeSet(code, "Code"); }// set only if passed value is safe

    public void setInstructor(String instructor)
        { if (safeSet(instructor, "Instructor") != null) this.instructor = safeSet(instructor, "Instructor"); }

    public void setLocation(String location)
        {if (safeSet(location, "Location") != null) this.location = safeSet(location, "Location");}

    public void setCredits(Integer credits)
        { if (safeSet(credits, "Credits") != null) this.credits = safeSet(credits, "Credits"); }

    public void addAssignment(Assignment a){
        a.setOwner(this);
    }
    // endregion setters

    // region getters
    public String getCode(){ return code; }
    public String getInstructor() { return instructor; }
    public String getLocation() { return location; }
    public Integer getCredits() { return credits; }
    // endregion getters
    // endregion setters and getters

    // region helpers
    private <T> T safeSet(T data, String criteria){
        if (data == null) {
            System.out.println(criteria + " Cannot be null");
            return null;
        }
        if (data instanceof String) {
            if (((String) data).isBlank()) {
                System.out.println("Error: " + criteria + " cannot be empty.");
                return null;
            }
        }
        if (data instanceof Integer) {// this can't happen, but doesn't hurt.
            if ((Integer) data < 0) {
                System.out.println("Error: " + criteria + " cannot be negative.");
                return null;
            }
        }
        return data;
    }
    // endregion helpers
}
