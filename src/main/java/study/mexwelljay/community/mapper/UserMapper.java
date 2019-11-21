package study.mexwelljay.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import study.mexwelljay.community.pojo.User;

/**
 * @auther Jay
 * @date 2019/11/21
 * 模块概述：User数据持久层
 **/
@Mapper
public interface UserMapper {
    @Insert("insert into user (account_id,name,token,gmt_create,gmt_modified) values (#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);
}
