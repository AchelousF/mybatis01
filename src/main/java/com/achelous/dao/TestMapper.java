package com.achelous.dao;

import com.achelous.annotation.Mapper;
import com.achelous.annotation.Param;
import com.achelous.annotation.Select;
import com.achelous.beans.Test;

/**
 * @Auther: fanJiang
 * @Date: Create in 20:51 2018/4/2
 */
@Mapper
public interface TestMapper {

    @Select("select * from test where id = #{id}")
    Test selectByPrimaryKey(Integer id);

    @Select("select * from test where id = #{id} and name = #{name}")
    Test selectByIdAndName(@Param("id") Integer id, @Param("name") String name);
}
