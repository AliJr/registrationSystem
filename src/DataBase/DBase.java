package DataBase;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.table.DefaultTableModel;

import org.graalvm.compiler.core.common.GraalBailoutException;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class DBase {

    private boolean connected = false;
    private Connection conn;

    public DBase(String username, String password) {

        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@coestudb.qu.edu.qa:1521/STUD.qu.edu.qa", username, password);
            connected = true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void addStudent(String name, String gender, String mobile, Date date, int nationalityID, int departmentID) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*)FROM STUDENT");
            rs.next();
            int studentID = rs.getInt(1) + 1;
            rs.close();
            stmt.close();
            PreparedStatement st = conn.prepareStatement("insert into student(STUDENT_ID,STUDENT_NAME,GENDER,MOBILE,"
                    + "BIRTH_DATE,DEPARTMENT_ID,NATIONALITY_ID) values(?,?,?,?,?,?,?)");
            st.setInt(1, studentID);
            st.setString(2, name);
            st.setString(3, gender);
            st.setInt(4, Integer.parseInt(mobile));
            st.setDate(5, (java.sql.Date) date);
            st.setInt(6, departmentID);
            st.setInt(7, nationalityID);
            st.executeUpdate();
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addSection(String time, int roomID, int instructorID, int semesterID, int courseID, int capacity, int available) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*)FROM SECTION");
            rs.next();
            int sectionID = rs.getInt(1) + 1;
            rs.close();
            stmt.close();
            PreparedStatement st = conn.prepareStatement("insert into SECTION(SECTION_ID,TIME,CAPACITY,AVAILABLE,"
                    + "ROOM_ID,INSTRUCTOR_ID,SEMESTER_ID,COURSE_ID) values(?,?,?,?,?,?,?,?)");
            st.setInt(1, sectionID);
            st.setString(2, time);
            st.setInt(3, capacity);
            st.setInt(4, available);
            st.setInt(5, roomID);
            st.setInt(6, instructorID);
            st.setInt(7, semesterID);
            st.setInt(8, courseID);
            st.executeUpdate();
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addNationality(String name, String abbreviation) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*)FROM NATIONALITY");
            rs.next();
            int NationalityID = rs.getInt(1) + 1;
            rs.close();
            stmt.close();
            PreparedStatement st = conn.prepareStatement("insert into NATIONALITY(NATIONALITY_ID,NATIONALITY_NAME,NATIONALITY_ABBREVIATION) values(?,?,?)");
            st.setInt(1, NationalityID);
            st.setString(2, name);
            st.setString(3, abbreviation);
            st.executeUpdate();
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addCollege(String name) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*)FROM COLLEGE");
            rs.next();
            int CollegeID = rs.getInt(1) + 1;
            rs.close();
            stmt.close();
            PreparedStatement st = conn.prepareStatement("insert into COLLEGE(COLLEGE_ID,COLLEGE_NAME) values(?,?)");
            st.setInt(1, CollegeID);
            st.setString(2, name);
            st.executeUpdate();
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addSemester(String name) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*)FROM SEMESTER");
            rs.next();
            int SemesterID = rs.getInt(1) + 1;
            rs.close();
            stmt.close();
            PreparedStatement st = conn.prepareStatement("insert into SEMESTER(SEMESTER_ID,SEMESTER_NAME) values(?,?)");
            st.setInt(1, SemesterID);
            st.setString(2, name);
            st.executeUpdate();
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addGrade(String grade, int courseID, int studentID) {
        try {
            PreparedStatement st = conn.prepareStatement("insert into STUDENT_RECORD(GRADE,COURSE_ID,STUDENT_ID) values(?,?,?)");
            st.setString(1, grade);
            st.setInt(2, courseID);
            st.setInt(3, studentID);
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addRoom(String buildingName, int capacity) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*)FROM ROOM");
            rs.next();
            int roomID = rs.getInt(1) + 1;
            rs.close();
            stmt.close();
            PreparedStatement st = conn.prepareStatement("insert into ROOM(ROOM_ID,BUILDING_ID,CAPACITY) values(?,?,?)");
            st.setInt(1, roomID);
            st.setString(2, buildingName);
            st.setInt(3, capacity);
            st.executeUpdate();
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addCourse(String name, int credit, int departmentID) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*)FROM COURSE");
            rs.next();
            int courseID = rs.getInt(1) + 1;
            rs.close();
            stmt.close();
            PreparedStatement st = conn.prepareStatement("insert into COURSE(COURSE_ID,COURSE_NAME,CREDIT,DEPARTMENT_ID) values(?,?,?,?)");
            st.setInt(1, courseID);
            st.setString(2, name);
            st.setInt(3, credit);
            st.setInt(4, departmentID);
            st.executeUpdate();
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addDepartment(String name, int collegeID) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*)FROM DEPARTMENT");
            rs.next();
            int departmentID = rs.getInt(1) + 1;
            rs.close();
            stmt.close();
            PreparedStatement st = conn.prepareStatement("insert into DEPARTMENT(DEPARTMENT_NAME,DEPARTMENT_ID,COLLEGE_ID) values(?,?,?)");
            st.setString(1, name);
            st.setInt(2, departmentID);
            st.setInt(3, collegeID);
            st.executeUpdate();
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addInstructor(String name, int departmentID) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*)FROM INSTRUCTOR");
            rs.next();
            int instructorID = rs.getInt(1) + 1;
            rs.close();
            stmt.close();
            PreparedStatement st = conn.prepareStatement("insert into INSTRUCTOR(INSTRUCTOR_ID,INSTRUCTOR_NAME,DEPARTMENT_ID) values(?,?,?)");
            st.setInt(1, instructorID);
            st.setString(2, name);
            st.setInt(3, departmentID);
            st.executeUpdate();
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addMajor(String name, int departmentID) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*)FROM MAJOR");
            rs.next();
            int majorID = rs.getInt(1) + 1;
            rs.close();
            stmt.close();
            PreparedStatement st = conn.prepareStatement("insert into MAJOR(MAJOR_NAME,MAJOR_ID,DEPARTMENT_ID) values(?,?,?)");
            st.setString(1, name);
            st.setInt(2, majorID);
            st.setInt(3, departmentID);
            st.executeUpdate();
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addPrerequisite(int prerequisiteID, int courseID) {
        try {
            PreparedStatement st = conn.prepareStatement("insert into PREREQUISITES(PREREQUISITE_ID,COURSE_ID) values(?,?)");
            st.setInt(1, prerequisiteID);
            st.setInt(2, courseID);
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addStudentRecord(int courseID, int studentID) {
        try {
            PreparedStatement st = conn.prepareStatement("insert into STUDENT_RECORD(COURSE_ID,STUDENT_ID) values(?,?)");
            st.setInt(1, courseID);
            st.setInt(2, studentID);
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addEnrolls(int sectionID, int studentID) {
        try {
            PreparedStatement st = conn.prepareStatement("insert into ENROLLS(SECTION_ID,STUDENT_ID) values(?,?)");
            st.setInt(1, sectionID);
            st.setInt(2, studentID);
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
   
    public ArrayList<String> getStudetRecord(int STUDENT_ID){
        try {
            ArrayList<String> courses = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COURSE_NAME FROM COUSRE WHERE COURSE_ID =(SELECT COURSE_ID FROM STUDENT_RECORD WHERE STUDENT_ID=?)");
            while (rs.next()) {
               
                courses.add(rs.getString(1));
            }
            rs.close();
            stmt.close();
            return courses;
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

     public ArrayList<String> getStudentGrades(int STUDENT_ID){
        try {
            ArrayList<String> Grades = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT GRADE FROM STUDENT_RECORD WHERE STUDENT_ID=?)");
            while (rs.next()) {
                
                Grades.add(rs.getString(1));
            }
            rs.close();
            stmt.close();
            return Grades;
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
 
     }
     
     private int getCredit(String COURSE_NAME){
        try {
            PreparedStatement st = conn.prepareStatement("SELECT CREDIT FROM COURSE WHERE COURSE_NAME=?");
            st.setString(1, COURSE_NAME);
            ResultSet rs = st.executeQuery();
            int temp;
            rs.next();
            temp = rs.getInt(1);
            rs.close();
            st.close();
            return temp;
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
     }

     private double getGPA(int STUDENT_ID){
        try {
            ArrayList<String> Grades = getStudentGrades(STUDENT_ID);
            ArrayList<String> names = getStudetRecord(STUDENT_ID);
            int credits = 0;
            double totalPoints=0;
            double GPA = 0;
            for(int i=0;i>=Grades.size();i++){
                String g = Grades.get(i);
                if(g.equals("A"))
                {
                    totalPoints=4*getCredit(names.get(i));
                    credits=credits+getCredit(names.get(i));
                    
                    
                }else if(g.equals("B+")){
                    totalPoints=3.5*getCredit(names.get(i));
                    credits=credits+getCredit(names.get(i));
                }
                else if(g.equals("B")){
                    totalPoints=3*getCredit(names.get(i));
                    credits=credits+getCredit(names.get(i));
                }
                    else if(g.equals("C+")){
                        totalPoints=2.5*getCredit(names.get(i));
                    credits=credits+getCredit(names.get(i));
                    }
                        else if(g.equals("C")){
                            totalPoints=2*getCredit(names.get(i));
                            credits=credits+getCredit(names.get(i));
                        }
                            else if(g.equals("D+")){
                                totalPoints=1.5*getCredit(names.get(i));
                    credits=credits+getCredit(names.get(i));
                            }
                                else if(g.equals("D")){
                                    totalPoints=1*getCredit(names.get(i));
                                    credits=credits+getCredit(names.get(i));
                                }
                                    else if(g.equals("F")){
                                        totalPoints=0*getCredit(names.get(i));
                                        credits=credits+getCredit(names.get(i));
                                    }
            }

            Double temp = totalPoints/credits;
         

            
            return temp;
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
 
     }
     
     public ArrayList<String> courseTought_Csemester(int INSTRUCTOR_ID){
        try {
            ArrayList<String> semesterName = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SEMESTER_NAME FROM SEMESTER WHERE SEMESTER_ID =(SELECT SEMESTER_ID FROM SECTION WHERE NSTRUCTOR_ID=?)");
            while (rs.next()) {
               
                semesterName.add(rs.getString(1));
            }
            rs.close();
            stmt.close();
            return semesterName;
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     }
     public ArrayList<String> courseTought_Ccode(int INSTRUCTOR_ID){
        try {
            ArrayList<String> coursesCodes = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUSRE_ID FROM SECTION WHERE NSTRUCTOR_ID=?");
            while (rs.next()) {
               
                coursesCodes.add(rs.getString(1));
            }
            rs.close();
            stmt.close();
            return coursesCodes;
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     }
     public ArrayList<String> courseTought_CName(int INSTRUCTOR_ID){
        try {
            ArrayList<String> coursesNames = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COURSE_NAME FROM COUSRE WHERE COURSE_ID =(SELECT COUSRE_ID FROM SECTION WHERE NSTRUCTOR_ID=?)");
            while (rs.next()) {
               
                coursesNames.add(rs.getString(1));
            }
            rs.close();
            stmt.close();
            return coursesNames;
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     }
     public ArrayList<String> courseTought_SectionID(int INSTRUCTOR_ID){
        try {
            ArrayList<String> SECTIONID = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SECTION_ID FROM SECTION WHERE NSTRUCTOR_ID=?");
            while (rs.next()) {
               
                SECTIONID.add(rs.getString(1));
            }
            rs.close();
            stmt.close();
            return SECTIONID;
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
        public ArrayList<String>  courseTought_SectionNoOfStudents(int INSTRUCTOR_ID){
        try {
            ArrayList<Integer> CAPACITY = new ArrayList<>();
            ArrayList<Integer> AVAILABLE = new ArrayList<>();
            ArrayList<Integer> numOfStudent = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT CAPACITY FROM SECTION WHERE NSTRUCTOR_ID=?");
            while (rs.next()) {
               
                CAPACITY.add(rs.getInt(1));
            }
            rs.close();
            

           
            rs = stmt.executeQuery("SELECT AVAILABLE FROM SECTION WHERE NSTRUCTOR_ID=?");
            while (rs.next()) {
               
                AVAILABLE.add(rs.getInt(1));
            }
            rs.close();
            stmt.close();


for (int i = 0 ; i>= CAPACITY.size();i++){
    numOfStudent.add(CAPACITY.get(i)-AVAILABLE.get(i));

}

           
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
       /*
    public void courseTought(int INSTRUCTOR_ID){


        ArrayList<String> semesterName = courseTought_Csemester(INSTRUCTOR_ID);
        ArrayList<String> coursesCodes = courseTought_Ccode(INSTRUCTOR_ID);
        ArrayList<String> coursesNames = courseTought_CName(INSTRUCTOR_ID);
        ArrayList<String> SECTIONID = courseTought_SectionID(INSTRUCTOR_ID);
        ArrayList<Integer> numOfStudent = courseTought_SectionNoOfStudents(INSTRUCTOR_ID);
       
        Object courseToughtObject[] = new Object[5];
        for (int i=0 ; i<= semesterName.size();i++){
            courseToughtObject[0]=semesterName.get(i);
            courseToughtObject[1]=coursesCodes.get(i);
            courseToughtObject[2]=coursesNames.get(i);
            courseToughtObject[3]=SECTIONID.get(i);
            courseToughtObject[4]=numOfStudent.get(i);


        }
       

 } 
 */
    public ArrayList<String> getNationalityList() {
        try {
            ArrayList<String> nationalities = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT NATIONALITY_NAME FROM NATIONALITY");
            while (rs.next()) {
                nationalities.add(rs.getString(1));
            }
            rs.close();
            stmt.close();
            return nationalities;
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<String> getDepartmentList() {
        try {
            ArrayList<String> departments = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DEPARTMENT_NAME FROM DEPARTMENT");
            while (rs.next()) {
                departments.add(rs.getString(1));
            }
            rs.close();
            stmt.close();
            return departments;
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<String> getCollegeList() {
        try {
            ArrayList<String> colleges = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COLLEGE_NAME FROM COLLEGE");
            while (rs.next()) {
                colleges.add(rs.getString(1));
            }
            rs.close();
            stmt.close();
            return colleges;
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<String> getSemesterCourseList(int semesterID) {
        try {
            ArrayList<Integer> courseIDs = new ArrayList<>();
            ArrayList<String> courseNames = new ArrayList<>();
            PreparedStatement st = conn.prepareStatement("SELECT COURSE_ID FROM SECTION WHERE SEMESTER_ID=? GROUP BY COURSE_ID");
            st.setInt(1, semesterID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                courseIDs.add(rs.getInt(1));
            }
            rs.close();
            st.close();
            if (!courseIDs.isEmpty()) {
                for (int i = 0; i < courseIDs.size(); i++) {
                    st = conn.prepareStatement("SELECT COURSE_NAME FROM COURSE WHERE COURSE_ID=?");
                    st.setInt(1, courseIDs.get(i));
                    rs = st.executeQuery();
                    while (rs.next()) {
                        courseNames.add(rs.getString(1));
                    }
                }

            }
            rs.close();
            st.close();
            return courseNames;
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<String> getCourseSectionList(int courseID) {
        try {
            ArrayList<String> sectionTime = new ArrayList<>();
            PreparedStatement st = conn.prepareStatement("SELECT TIME FROM SECTION WHERE COURSE_ID=?");
            st.setInt(1, courseID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                sectionTime.add(rs.getString(1));
            }
            rs.close();
            st.close();
            return sectionTime;
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<String> getCourseList() {
        try {
            ArrayList<String> courses = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COURSE_NAME FROM COURSE");
            while (rs.next()) {
                courses.add(rs.getString(1));
            }
            rs.close();
            stmt.close();
            return courses;
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<String> getRoomList() {
        try {
            ArrayList<String> rooms = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ROOM_ID FROM ROOM");
            while (rs.next()) {
                rooms.add(rs.getString(1));
            }
            rs.close();
            stmt.close();
            return rooms;
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<String> getSemesterList() {
        try {
            ArrayList<String> semesters = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SEMESTER_NAME FROM SEMESTER");
            while (rs.next()) {
                semesters.add(rs.getString(1));
            }
            rs.close();
            stmt.close();
            return semesters;
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<String> getInstructorList(int departmentID) {
        try {
            ArrayList<String> instructors = new ArrayList<>();
            PreparedStatement st = conn.prepareStatement("SELECT INSTRUCTOR_NAME FROM INSTRUCTOR WHERE DEPARTMENT_ID=?");
            st.setInt(1, departmentID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                instructors.add(rs.getString(1));
            }
            rs.close();
            st.close();
            return instructors;
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getNationalityID(String nationalityName) {
        try {
            PreparedStatement st = conn.prepareStatement("SELECT NATIONALITY_ID FROM NATIONALITY WHERE NATIONALITY_NAME=?");
            st.setString(1, nationalityName);
            ResultSet rs = st.executeQuery();
            int temp;
            rs.next();
            temp = rs.getInt(1);
            rs.close();
            st.close();
            return temp;
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getDepartmentID(String departmentName) {
        try {
            PreparedStatement st = conn.prepareStatement("SELECT DEPARTMENT_ID FROM DEPARTMENT WHERE DEPARTMENT_NAME=?");
            st.setString(1, departmentName);
            ResultSet rs = st.executeQuery();
            int temp;
            rs.next();
            temp = rs.getInt(1);
            rs.close();
            st.close();
            return temp;
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getCollegeID(String collegeName) {
        try {
            PreparedStatement st = conn.prepareStatement("SELECT COLLEGE_ID FROM COLLEGE WHERE COLLEGE_NAME=?");
            st.setString(1, collegeName);
            ResultSet rs = st.executeQuery();
            int temp;
            rs.next();
            temp = rs.getInt(1);
            rs.close();
            st.close();
            return temp;
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
     public int getInstructorID(String instructorName) {
        try {
            PreparedStatement st = conn.prepareStatement("SELECT INSTRUCTOR_ID FROM INSTRUCTOR WHERE INSTRUCTOR_NAME=?");
            st.setString(1, instructorName);
            ResultSet rs = st.executeQuery();
            int temp;
            rs.next();
            temp = rs.getInt(1);
            rs.close();
            st.close();
            return temp;
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
     
    public int getSemesterID(String semesterName) {
        try {
            PreparedStatement st = conn.prepareStatement("SELECT SEMESTER_ID FROM SEMESTER WHERE SEMESTER_NAME=?");
            st.setString(1, semesterName);
            ResultSet rs = st.executeQuery();
            int temp;
            rs.next();
            temp = rs.getInt(1);
            rs.close();
            st.close();
            return temp;
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getCourseID(String courseName) {
        try {
            PreparedStatement st = conn.prepareStatement("SELECT COURSE_ID FROM COURSE WHERE COURSE_NAME=?");
            st.setString(1, courseName);
            ResultSet rs = st.executeQuery();
            int temp;
            rs.next();
            temp = rs.getInt(1);
            rs.close();
            st.close();
            return temp;
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getSectionID(String sectionTime, int courseID) {
        try {
            PreparedStatement st = conn.prepareStatement("SELECT SECTION_ID FROM SECTION WHERE TIME=? AND COURSE_ID=?");
            st.setString(1, sectionTime);
            st.setInt(2, courseID);
            ResultSet rs = st.executeQuery();
            int temp;
            rs.next();
            temp = rs.getInt(1);
            rs.close();
            st.close();
            return temp;
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getDepartmentIDFromCourse(int courseID) {
        try {
            PreparedStatement st = conn.prepareStatement("SELECT DEPARTMENT_ID FROM COURSE WHERE COURSE_ID=?");
            st.setInt(1, courseID);
            ResultSet rs = st.executeQuery();
            int temp;
            rs.next();
            temp = rs.getInt(1);
            rs.close();
            st.close();
            return temp;
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public boolean checkConnection() {
        return connected;
    }

    public boolean checkTimeConflict(String time, int roomID) {
        try {
            String realTime[] = time.split("/");
            DateFormat dateFormat = new SimpleDateFormat("hh:mm");
            Date startTime = dateFormat.parse(realTime[0]);
            Date endTime = dateFormat.parse(realTime[1]);
            String days = realTime[2];
            boolean check = true;
            PreparedStatement st = conn.prepareStatement("SELECT TIME FROM SECTION WHERE ROOM_ID=?");
            st.setInt(1, roomID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String temp = rs.getString(1);
                realTime = temp.split("/");
                if (realTime[2].equals(days)) {
                    if (!(startTime.after(dateFormat.parse(realTime[1])) || endTime.before(dateFormat.parse(realTime[1])))) {
                        check = false;
                    }
                }
            }
            rs.close();
            st.close();
            return check;
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkRoomCapacity(int sectionCapacity, int roomID) {
        try {
            PreparedStatement st = conn.prepareStatement("SELECT CAPACITY FROM ROOM WHERE ROOM_ID=?");
            st.setInt(1, roomID);
            ResultSet rs = st.executeQuery();
            int temp;
            boolean check = true;
            rs.next();
            temp = rs.getInt(1);
            if (sectionCapacity <= temp) {
                check = true;
            } else {
                check = false;
            }
            rs.close();
            st.close();
            return check;
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public void test(){}

    public boolean canRegister(int courseID) {
        try {
            PreparedStatement st = conn.prepareStatement("SELECT GRADE FROM STUDENT_RECORD WHERE COURSE_ID=?");
            st.setInt(1, courseID);
            ResultSet rs = st.executeQuery();
            String temp="";
            boolean check = true;
            if(!rs.next())
            temp = rs.getString(1);
            if (temp.equals('F') || temp.equals("D") || temp.equals("D+") || temp.isEmpty()) {
                check = true;
            } else {
                check = false;
}
            rs.close();
            st.close();
            return check;
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean canAttend(int sectionID) {
        try {
            PreparedStatement st = conn.prepareStatement("SELECT AVAILABLE FROM SECTION WHERE SECTION_ID=?");
            st.setInt(1, sectionID);
            ResultSet rs = st.executeQuery();
            int available;
            boolean check = true;
            rs.next();
            available = rs.getInt(1);
            rs.close();
            st.close();
            if (available > 0) {
                return check;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean hasNoConflict(String time, int studentID) {
        try {
            PreparedStatement st = conn.prepareStatement("SELECT SECTION_ID FROM ENROLLS WHERE STUDENT_ID=?");
            st.setInt(1, studentID);
            ResultSet rs = st.executeQuery();
            ArrayList<Integer> sections = new ArrayList<>();
            ArrayList<String> times = new ArrayList<>();
            while (rs.next()) {
                sections.add(rs.getInt(1));
            }
            rs.close();
            st.close();
            for (int i = 0; i < sections.size(); i++) {
                st = conn.prepareStatement("SELECT TIME FROM SECTION WHERE SECTION_ID=? GROUP BY TIME");
                st.setInt(1, sections.get(i));
                rs = st.executeQuery();
                times.add(rs.getString(1));
            }
            String sectionTime[] = time.split("/");
            DateFormat dateFormat = new SimpleDateFormat("hh:mm");
            Date sectionStartTime = dateFormat.parse(sectionTime[0]);
            Date sectionEndTime = dateFormat.parse(sectionTime[1]);
            String sectionDays = sectionTime[2];
            boolean check = true;
            for (int i = 0; i < times.size(); i++) {
                String tempTime[] = times.get(i).split("/");
                Date tempStartTime = dateFormat.parse(sectionTime[0]);
                Date tempEndTime = dateFormat.parse(sectionTime[1]);
                String tempDays = sectionTime[2];
                if (sectionDays.equals(tempDays)) {
                    if (!(sectionStartTime.after(tempStartTime)) || sectionEndTime.before(tempEndTime)) {
                        check = false;
                    }
                }
            }
            return check;

        } catch (SQLException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
