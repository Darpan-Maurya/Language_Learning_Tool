class resul extends test implements sports {
    float total;
    public void put_wt(float x) {
        sp_wt = x;
        System.out.println(sp_wt);
    }

    void calc() 
    {
        total = mark1 + mark2 + sp_wt;
    }

    void display() 
    {
        System.out.println(mark1 + "" + mark2 + "" + roll_no + "" + name + "" + total);
    }
    public static void main(String[] args) {
        resul a = new resul();
        a.getdata(54, "Darpan");
        a.get_marks(94, 91);
        a.put_wt(2);
        a.calc();
        a.display();
    }
}

class student {
    int roll_no;
    String name;

    void getdata(int x, String y) {
        roll_no = x;
        name = y;
    }
}

class test extends student {
    int mark1, mark2;

    void get_marks(int x, int y) {
        mark1 = x;
        mark2 = y;
    }
}

interface sports {
    void put_wt(float x);
}
