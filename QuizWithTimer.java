package Quizwithtimer;

import java.util.Scanner;

public class quizwithtimer {

    static Scanner sc = new Scanner(System.in);

    static class Stud {
        String name, arid;
        int quizzesTaken, totalMarks;
        boolean qtak=false;
        Stud(String n, String a) { name = n; arid = a; }
    }

    static class Question {
        String qa;
        String[] opt;
        int correct;
        Question(String q, String[] opt, int c) {
            this.qa = q;
            this.opt = opt;
            correct = c;
        }
    }

    static class QuizTimer extends Thread {
        int sec;
        boolean up = false;
        QuizTimer(int s) { sec = s; }
        public void run() {
            try {
                while (sec > 0) {
                    Thread.sleep(1000);
                    sec--;
                }
            } catch (Exception e) {}
            up = true;
        }
    }

    
    static class LLStudentNode {
        Stud s;
        LLStudentNode next;
        LLStudentNode(Stud s) { this.s = s; }
    }

    static class LLQuestionNode {
        Question q;
        LLQuestionNode next;
        LLQuestionNode(Question q) { this.q = q; }
    }

    static class LinkedListQuiz {
        LLStudentNode sHead;
        LLQuestionNode qHead;
        boolean loaded = false;

        void loadDefaultQuestions() {
            if (loaded) return;
            addQ("Java is ?", new String[]{"Language","OS","Browser","Game"}, 1);
            addQ("JVM stands for ?", new String[]{"Java Virtual Machine","Java VM","Just VM","None"}, 1);
            addQ("int size ?", new String[]{"2","4","8","16"}, 2);
            addQ("Which is OOP ?", new String[]{"C","Python","HTML","SQL"}, 2);
            loaded = true;
        }

        void addQ(String q, String[] o, int c) {
            LLQuestionNode nn = new LLQuestionNode(new Question(q, o, c));
            if (qHead == null) qHead = nn;
            else {
                LLQuestionNode t = qHead;
                while (t.next != null) t = t.next;
                t.next = nn;
            }
        }

        void addStudent() {
            System.out.print("Name: ");
            String n = sc.nextLine();
            String a="";
            boolean ex;
            LLStudentNode tt=sHead; 
            do {
            ex =false;
            System.out.print("ARID: ");
            a = sc.nextLine();
            while(tt!=null) {
            	
            
            if(tt.s.arid.equalsIgnoreCase(a)) {
            	System.out.print("enter unique");
            	ex=true;
            	break;
            }
            tt=tt.next;
            }
            	
            }while(ex);
            
            LLStudentNode nn = new LLStudentNode(new Stud(n, a));
            if (sHead == null) sHead = nn;
            else {
                LLStudentNode t = sHead;
                while (t.next != null) t = t.next;
                t.next = nn;
            }
        }

        void addQuestion() {
            System.out.print("Question: ");
            String q = sc.nextLine();
            String[] o = new String[4];
            for (int i = 0; i < 4; i++) {
                System.out.print("Option " + (i + 1) + ": ");
                o[i] = sc.nextLine();
            }
            int c;
            while (true) {
                System.out.print("Correct (1-4): ");
                c = sc.nextInt();
                sc.nextLine();
                if (c >= 1 && c <= 4) break;
            }
            addQ(q, o, c);
        }

        void showStudents() {
            LLStudentNode t = sHead;
            int i = 1;
            while (t != null) {
                System.out.println(i++ + ". " + t.s.name);
                t = t.next;
            }
        }

        Stud getStudent(int i) {
            LLStudentNode t = sHead;
            int c = 0;
            while (t != null) {
                if (c == i) return t.s;
                t = t.next;
                c++;
            }
            return null;
        }

        void takeQuiz(Stud s) {
        	
        	if(s.qtak) {
        		System.out.println("already given");
        		return;
        	}
        	
            if (qHead == null) {
                System.out.println("No questions");
                return;
            }
            int marks = 0;
            QuizTimer t = new QuizTimer(10);
            t.start();
            LLQuestionNode qh = qHead;
            while (qh != null) {
                if (t.up) {
                    System.out.println("\nTime is up!");
                    break;
                }
                System.out.println("\nTime Left: " + t.sec + " sec");
                System.out.println(qh.q.qa);
                for (int i = 0; i < 4; i++)
                    System.out.println((i + 1) + ". " + qh.q.opt[i]);
                int a = 0;
                while (true) {
                    if (t.up) {
                        System.out.println("\nTime is up!");
                        break;
                    }
                    System.out.print("Answer: ");
                    a = sc.nextInt();
                    sc.nextLine();
                    if (a >= 1 && a <= 4) break;
                }
                if (t.up) break;
                if (a == qh.q.correct) marks += 2;
                qh = qh.next;
            }
            s.quizzesTaken++;
            s.totalMarks += marks;
            if (t.up) System.out.println("Time is up");
            else System.out.println("Quiz Completed");
            System.out.println("Score: " + marks+"/10");
            s.qtak=true;
        }

        void showResults() {
            LLStudentNode t = sHead;
            while (t != null) {
                System.out.println(t.s.name + " | " + t.s.totalMarks);
                t = t.next;
            }
        }
    }

    
    static class ArrayQuiz {
        Stud[] students = new Stud[50];
        Question[] questions = new Question[50];
        int sCount = 0, qCount = 0;
        boolean loaded = false;

        void loadDefaultQuestions() {
            if (loaded) return;
            addQ("Java is ?", new String[]{"Language","OS","Browser","Game"}, 1);
            addQ("JVM stands for ?", new String[]{"Java Virtual Machine","Java VM","Just VM","None"}, 1);
            addQ("int size ?", new String[]{"2","4","8","16"}, 2);
            addQ("Which is OOP ?", new String[]{"C","Python","HTML","SQL"}, 2);
            loaded = true;
        }

        void addQ(String q, String[] o, int c) {
            questions[qCount++] = new Question(q, o, c);
        }

        void addStudent() {
            System.out.print("Name: ");
            String n = sc.nextLine();
            System.out.print("ARID: ");
            String a = sc.nextLine();
            students[sCount++] = new Stud(n, a);
        }

        void addQuestion() {
            System.out.print("Question: ");
            String q = sc.nextLine();
            String[] o = new String[4];
            for (int i = 0; i < 4; i++) {
                System.out.print("Option " + (i + 1) + ": ");
                o[i] = sc.nextLine();
            }
            int c;
            while (true) {
                System.out.print("Correct (1-4): ");
                c = sc.nextInt();
                sc.nextLine();
                if (c >= 1 && c <= 4) break;
            }
            addQ(q, o, c);
        }

        void showStudents() {
            for (int i = 0; i < sCount; i++)
                System.out.println((i+1) + ". " + students[i].name);
        }

        void takeQuiz(Stud s) {
            if (qCount == 0) {
                System.out.println("No questions");
                return;
            }
            int marks = 0;
            QuizTimer t = new QuizTimer(10);
            t.start();
            for (int i = 0; i < qCount; i++) {
                if (t.up) {
                    System.out.println("\nTime is up!");
                    break;
                }
                System.out.println("\nTime Left: " + t.sec + " sec");
                System.out.println(questions[i].qa);
                for (int j = 0; j < 4; j++)
                    System.out.println((j+1) + ". " + questions[i].opt[j]);
                int a = 0;
                while (true) {
                    if (t.up) {
                        System.out.println("\nTime is up!");
                        break;
                    }
                    System.out.print("Answer: ");
                    a = sc.nextInt();
                    sc.nextLine();
                    if (a >= 1 && a <= 4) break;
                }
                if (t.up) break;
                if (a == questions[i].correct) marks += 2;
            }
            s.quizzesTaken++;
            s.totalMarks += marks;
            if (t.up) System.out.println("Time is up");
            else System.out.println("Quiz Completed");
            System.out.println("Score: " + marks);
        }

        void showResults() {
            for (int i = 0; i < sCount; i++)
                System.out.println(students[i].name + " | " + students[i].totalMarks+"/10");
        }
    }

    public static void main(String[] args) {
        System.out.println("1. Linked List  2. Array");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            LinkedListQuiz q = new LinkedListQuiz();
            q.loadDefaultQuestions();
            menuLL(q);
        } else {
            ArrayQuiz q = new ArrayQuiz();
            q.loadDefaultQuestions();
            menuArray(q);
        }
    }

    static void menuLL(LinkedListQuiz q) {
        int ch;
        do {
            System.out.println("\n1.Register 2.Add Question 3.Take Quiz 4.Results 5.Exit");
            ch = sc.nextInt();
            sc.nextLine();
            if (ch == 1) q.addStudent();
            else if (ch == 2) q.addQuestion();
            else if (ch == 3) {
                if (q.sHead == null) { System.out.println("Register student first"); continue; }
                q.showStudents();
                Stud s = q.getStudent(sc.nextInt()-1);
                sc.nextLine();
                if (s != null) q.takeQuiz(s);
            } else if (ch == 4) q.showResults();
        } while (ch != 5);
    }

    static void menuArray(ArrayQuiz q) {
        int ch;
        do {
            System.out.println("\n1.Register 2.Add Question 3.Take Quiz 4.Results 5.Exit");
            ch = sc.nextInt();
            sc.nextLine();
            if (ch == 1) q.addStudent();
            else if (ch == 2) q.addQuestion();
            else if (ch == 3) {
                if (q.sCount == 0) { System.out.println("Register student first"); continue; }
                q.showStudents();
                Stud s = q.students[sc.nextInt()-1];
                sc.nextLine();
                if (s != null) q.takeQuiz(s);
            } else if (ch == 4) q.showResults();
        } while (ch != 5);
    }
}
