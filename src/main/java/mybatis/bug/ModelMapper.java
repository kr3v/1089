package mybatis.bug;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ModelMapper {
    void insert(@Param("t") Model model);

    Model select(@Param("t") Model model);

    Model selectById(@Param("t") int model);

    List<Model> selectAll(@Param("t") Model model);

    List<Model> selectAllById(@Param("t") int model);

    Model selectEnum(@Param("en") Outer.Inner en);
}
