package com.bmpl.ims.users.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bmpl.ims.common.dao.CommonDAO;
import com.bmpl.ims.common.dao.CommonSQLConstants;
import com.bmpl.ims.users.dto.RegisterDTO;

public class RegisterDAO {

	public boolean register(RegisterDTO registerDTO) throws SQLException, ClassNotFoundException {

		boolean registration = false;
		String sql = com.bmpl.ims.common.dao.CommonSQLConstants.REGISTRATION_SQL;
		Connection con = null;
		PreparedStatement pstmt = null;
		int rs;

		try {
			con = CommonDAO.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, registerDTO.getName());
			pstmt.setString(2, registerDTO.getFname());
			pstmt.setString(3, registerDTO.getAddress());
			pstmt.setString(4, registerDTO.getPhone());
			/*
			 * pstmt.setString(5, registerDTO.getBatch()); 
			 * pstmt.setString(6, registerDTO.getCountry());
			 * 
			 */
			pstmt.setString(5, registerDTO.getMail());
			pstmt.setString(6, registerDTO.getState());
			pstmt.setString(7, registerDTO.getCourse());
			rs = pstmt.executeUpdate();

			try {

				registration = true;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Registration Failed");
			}

		} finally {
			if (con != null) {
				con.close();
			}
		}

		return registration;

	}

}
