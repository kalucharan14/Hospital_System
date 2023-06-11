package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Specalist;

public class SpecialistDao {
private Connection conn;
public SpecialistDao(Connection conn) {
	super();
	this.conn = conn;
}

public boolean addSpecialist(String name) {
	boolean br=false;
	
	String qry="insert into specialist(spec_name) values(?)";
	try {
		PreparedStatement pstm=conn.prepareStatement(qry);
		pstm.setString(1, name);
		int r=pstm.executeUpdate();
		if(r==1)
			br=true;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return br;
}
public List<Specalist> getAllSpecialist() {
	List<Specalist> list = new ArrayList<Specalist>();
	Specalist s = null;

	try {
		String sql = "select * from specialist";
		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			s = new Specalist();
			s.setId(rs.getInt(1));
			s.setSpecialistName(rs.getString(2));
			list.add(s);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}

	return list;
}
}
