import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Courses {
    public ArrayList<Course> courseList;

    public Courses(){
        courseList = new ArrayList<Course>();
    }
    public ArrayList<Course> getCourseList(){ return courseList; }
    public Course getCourse(int index){
        int count = 0;
        for(Course c : courseList){
            if(count == index)
                return c;
            count++;
        }
        return null;
    }

    public Course getCourse(String code){
        for (Course c : courseList) {
            if (c.getCode().equals(code))
                return c;
        }
        return null;
    }

    public String[] getCourseOptions() {
        String[] options = new String[courseList.size()];
        for (int i = 0; i < courseList.size(); i++) options[i] = courseList.get(i).toString();
        return options;
    }
    public void addCourses(Course... c){
        for(int i = 0; i < c.length; i++){
            if(!courseList.contains(c[i]))
                courseList.add(c[i]);
            else System.out.println("Can not add duplicate course.");
        }
    }
    public void removeCourses(Course... c){
        for (Course course : c) {
            if (courseList.contains(course)) {
                courseList.remove(course);
            }
        }
    }

    public Course handleCourse(int courseIndex){
        Scanner courseIn = Main.in;// idk why i went with this but ill change it when im not locked in with just making it work
        Course c = getCourse(courseIndex-1);// Fix bugged one-indexing
        System.out.println("Type 'main menu' during data entry to return to main menu.");
        mainEditModeLoop(courseIn,c);
        return c;
    }

    private Course mainEditModeLoop(Scanner courseIn, Course c){// i don't like public scanners, might change later

        while(true){
            String in = "";
            // this is where the user selects the action to take
            int action = Main.menu("Manage Course " + c.toString(true), "Actions", "Edit Course Data", "Delete Course", "Return to Main Menu");
            if(action == 1){
                boolean returnToMain = handleCriteria(courseIn, c);
                if(returnToMain)break;// break the outer loop if the inner loop (handleCriteria) was broken because user typed "main menu"
            }
            else if(action == 2){// I am confident this action is going to punish me
                removeCourses(c);// delete course
                return null;// please don't make me cry later
                // update - I'm not crying!!!
            }
            else break;// main menu
        }
        return c;
    }
    private boolean handleCriteria(Scanner courseIn, Course c){// return boolean to tell mainEditModeLoop when it should return to menu
        String in = "";
        while(true){
            int criterion = Main.menu("Edit " + c.toString(true) + " Course Data","Criteria",
                    "Code","Instructor","Location","Credits","Back");
            if(criterion == 5) return false;// break from inner loop to go back to action choice
            switch(criterion){
                case 1:// User wants to change course code
                    System.out.println("Hint: type 'main menu' during editing to return to menu");
                    System.out.println("Current Course Code: " + c.getCode());
                    System.out.print("Enter new course code, or enter to accept existing: ");
                    // If user types "CPSC221", it gets changed to "CPSC 221"
                    in = courseIn.nextLine().toUpperCase();
                    if(!should_return_to_main(in)) c.setCode(in.replaceAll("([a-zA-Z])(\\d)", "$1 $2"));// ex. replace CSA130 with CSA 130
                    break;
                case 2:// User wants to change instructor
                    System.out.println("Hint: type 'main menu' during editing to return to menu");
                    System.out.println("Current Instructor: " + c.getInstructor());
                    System.out.print("Please enter the new instructor, or enter to accept existing: ");
                    in = courseIn.nextLine();
                    if(!should_return_to_main(in)) c.setInstructor(in);
                    break;
                case 3:// User wants to change location
                    System.out.println("Hint: type 'main menu' during editing to return to menu");
                    System.out.println("Current Location: " + c.getLocation());
                    System.out.print("Please enter the new location, or enter to accept existing: ");
                    in = courseIn.nextLine();
                    if(!should_return_to_main(in)) c.setLocation(in);
                    break;
                case 4:// User wants to change credit count
                    System.out.println("Hint: type 'main menu' during editing to return to menu");
                    System.out.println("Current Credit Count: " + c.getCredits());
                    System.out.print("Please enter the new credit count, or enter existing to cancel: ");
                    in = courseIn.nextLine();
                    if(in.isEmpty())break;
                    if(!should_return_to_main(in)) c.setCredits(Main.validate(in,"Error. ",1,512));// that's a lot of credits.
                    break;
            }
            if(should_return_to_main(in))break;// break the inner loop
        }
        return false;// fallback
    }
    private boolean should_return_to_main(String s){ if(s.equalsIgnoreCase("main menu")) return true; else return false; }

    @Override
    public String toString(){
        int i = 1;
        StringBuilder sb = new StringBuilder();
        for(Course c : courseList){
            sb.append(i + ". ").append(c.toString().replaceAll("[\\[\\]]", ""));
            sb.append("\n");
            i++;
        }

        return sb.toString().replaceAll("[\\[\\]]", "");
    }
}