package club.mydlq.mapper;

import club.mydlq.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author mydlq
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
