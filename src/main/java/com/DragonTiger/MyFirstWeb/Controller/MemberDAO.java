package com.DragonTiger.MyFirstWeb.Controller;


import java.sql.*;

import com.DragonTiger.MyFirstWeb.VO.MemberVO;
import com.DragonTiger.MyFirstWeb.common.DBUtil;


public class MemberDAO {
	
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public int insertMemo(MemberVO mem) throws SQLException{
		try {
			con=DBUtil.getCon();
            String sql="insert into members values(?,?,?,?,sysdate)";
            ps=con.prepareStatement(sql);
            ps.setString(1, mem.getId());
            ps.setString(2, mem.getName());
            ps.setString(3, mem.getEmail());
            ps.setString(4, mem.getPassword());
            int n=ps.executeUpdate();
            return n;
        }finally {
            con.close();
        }
	}

    public MemberVO selectMemo(String id) throws SQLException{
        try {
            con=DBUtil.getCon();
            String sql="select * from members where id=?";
            ps=con.prepareStatement(sql);
            ps.setString(1, id);
            rs=ps.executeQuery();
            MemberVO mem=null;
            if(rs.next()) {
                mem=new MemberVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
            return mem;
        }finally {
            con.close();
        }
    }

    public int updateMemo(MemberVO mem) throws SQLException{
        try {
            con=DBUtil.getCon();
            String sql="update members set name=?, email=?, password=? where id=?";
            ps=con.prepareStatement(sql);
            ps.setString(1, mem.getName());
            ps.setString(2, mem.getEmail());
            ps.setString(3, mem.getPassword());
            ps.setString(4, mem.getId());
            int n=ps.executeUpdate();
            return n;
        }finally {
            con.close();
        }
    }

    public int deleteMemo(String id) throws SQLException{
        try {
            con=DBUtil.getCon();
            String sql="delete from members where id=?";
            ps=con.prepareStatement(sql);
            ps.setString(1, id);
            int n=ps.executeUpdate();
            return n;
        }finally {
            con.close();
        }
    }

    public MemberVO[] selectAllMemo() throws SQLException{
        try {
            con=DBUtil.getCon();
            String sql="select * from members";
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            MemberVO[] mems=null;
            if(rs.next()) {
                rs.last();
                mems=new MemberVO[rs.getRow()];
                rs.beforeFirst();
                int i=0;
                while(rs.next()) {
                    mems[i++]=new MemberVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                    System.out.println(mems[i-1]);
                }
            }
            return mems;
        }finally {
            con.close();
        }
    }
}
