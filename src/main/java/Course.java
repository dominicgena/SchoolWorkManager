import java.util.Objects;

public class Course {
    private String c, inst, loc;// code, instructor, location
    private Integer creds;// number of credits

    public Course(){
        this("Code", "Instructor", "Location", 0);
    }

    public Course(String code, String instructor, String location, Integer credits){
        setCode(code);
        setInstructor(instructor);
        setLocation(location);
        setCredits(credits);
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        // Check if the unique code (e.g., "CPSC 221") is the same
        return Objects.equals(c, course.c);
    }

    @Override
    public int hashCode() {
        // Generate a hash based on the code
        return Objects.hash(c);
    }

    public void setCode(String code)
        { if (safeSet(code, "Code") != null) this.c = safeSet(code, "Code"); }// set only if passed value is safe

    public void setInstructor(String instructor)
        { if (safeSet(instructor, "Instructor") != null) this.inst = safeSet(instructor, "Instructor"); }

    public void setLocation(String location)
        {if (safeSet(location, "Location") != null) this.loc = safeSet(location, "Location");}


    public void setCredits(Integer credits)
        { if (safeSet(credits, "Credits") != null) this.creds = safeSet(credits, "Credits"); }

    public String getCode(){ return c; }
    public String getInstructor() { return inst; }
    public String getLocation() { return loc; }
    public Integer getCredits() { return creds; }

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
}
