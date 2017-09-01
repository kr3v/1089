package mybatis.bug;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        InputStream inputStream = new FileInputStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        ModelMapper mp = sqlSession.getMapper(ModelMapper.class);

        try {
            mp.insert(new Model(1, "as"));
            mp.insert(new Model(2, "bs"));
        } catch (Exception ignored) {
            //if already added to db
        }

        test(() -> System.out.println(mp.select(new Model(1, ""))));
        test(() -> System.out.println(mp.selectAll(new Model(1, ""))));
        test(() -> System.out.println(mp.selectById(1)));
        test(() -> System.out.println(mp.selectAllById(1)));
        test(() -> System.out.println(mp.selectEnum(Outer.Inner.a)));
    }

    private static void test(Runnable f) {
        try {
            f.run();
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }
}

