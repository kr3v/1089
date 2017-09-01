package mybatis.bug;

public class Outer {
    enum Inner {
        a(1), b(2), c(3);
        int id;

        Inner(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
