import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        Courses clist = new Courses();
        Course c1 = new Course("CPSC 221", "Doe, John", "SCI005", 4);
        Course c2 = new Course("MATH 110", "Bravo, Joe", "SCI024", 3);
        clist.addCourses(c1,c2);
        int choice1;

        Assignment a1 = new Assignment();

        while(true){
            choice1 = menu("Main","Options","Manage Existing Courses","Manage Assignments");
            switch(choice1){
                case 1:
                    clist.handleCourse(
                            menu("Manage Courses","Manageable Courses",clist.getCourseOptions())
                    );
                    break;
                case 2:
                    menu("Manage Assignments","From Course",clist.getCourseOptions());
                    break;
            }
        }
    }


    public static void cls(){ for(int i = 0; i < 50; i++) System.out.println(); }
    public static void title(String subtitle){
        cls();
        System.out.println("Dominic Gena");
        System.out.println(subtitle);
        System.out.println("School Work Manager\n");
    }
    public static void title(){
        cls();
        System.out.println("Dominic Gena");
        System.out.println("School Work Manager\n");
    }
    public static int menu(String type, String plural_options_type, String... options){
        //region build menu(appends to string "menu")
        String menu;
        title(type + " Menu");
        int i = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(plural_options_type).append("\n");
        for(String opt : options){
            i++;
            sb.append("    ").append(i).append(". ").append(opt).append("\n");
        }
        sb.append("\nPlease select your option: ");
        menu = sb.toString();// endregion build menu
        System.out.print(menu);
        return validate(in.nextLine(),"Error. ",1, options.length);
    }
    public static int validate(String src, String errorMessage, int min, int max){
        int x;
        while(true){
            try{
                x = Integer.parseInt(src);
                if(x < min || x > max) throw new Exception("Integer invalid");
                break;
            }catch(Exception e){
                System.out.print(errorMessage + "Enter integer between " + min + " and " + max + ": ");
                src = in.nextLine();
            }
        }
        return x;
    }
}