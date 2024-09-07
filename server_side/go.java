import java.io.*;

class go {
    public static void main(String args[]) {
        try {
            String s = "india";
            if (s.equals("india")) {
                throw new no("bye");
            }
        } catch (no e) {
            System.out.println(e.getMessage());
        }
    }
}

class no extends Exception {
    no(String m) {
        super(m);
    }
}

