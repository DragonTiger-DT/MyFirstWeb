package com.DragonTiger.MyFirstWeb.mapper;


import com.DragonTiger.MyFirstWeb.VO.MemberVO;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {

    @Select("select id,name from members")
    public List<MemberVO> getidname();

    @Select("select password from members where id=#{id}")
    public String getpassword(String id);

    @Insert("insert into members(id,name,email,password,regdate)"
            + "values(#{id},#{name},#{email},#{password},sysdate)")
    public void insertMember(MemberVO mem);

    @Delete("delete from members where id=#{id}")
    public void deleteMember(String id);



}
