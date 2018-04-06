package com.achelous.dao;

import com.achelous.annotation.Mapper;
import com.achelous.annotation.Param;
import com.achelous.annotation.Select;
import com.achelous.beans.Blog;
/**
 * @Auther: fanJiang
 * @Date: Create in 20:51 2018/4/2
 */
@Mapper
public interface BlogMapper {

    @Select("select * from blog where bid = #{id}")
    Blog selectByPrimaryKey(Integer id);

    @Select("select * from blog where bid = #{id} and name = #{name}")
    Blog selectByIdAndName(@Param("id") Integer id, @Param("name") String name);
}
