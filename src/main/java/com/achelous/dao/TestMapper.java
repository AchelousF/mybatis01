package com.achelous.dao;

import com.achelous.annotation.Mapper;
import com.achelous.annotation.Select;
import com.achelous.beans.Test;

/**
 * @Auther: fanJiang
 * @Date: Create in 20:51 2018/4/2
 */
@Mapper
public interface TestMapper {

    @Select("select * from test where id = %d")
    Test selectByPrimaryKey(Integer id);
}
