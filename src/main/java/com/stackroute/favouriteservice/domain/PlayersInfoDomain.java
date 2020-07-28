package com.stackroute.favouriteservice.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.stackroute.favouriteservice.dto.FavouritePlayersDTO;
import com.stackroute.favouriteservice.dto.ResponseDTO;

@Component
public class PlayersInfoDomain implements IPlayersInfoDomain {


	private Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys","SYSDB","root");
		return con;
	}

	
	public List<FavouritePlayersDTO> fetchFavPlayersDetails(String userid) {
		List<FavouritePlayersDTO> playersList = new ArrayList<FavouritePlayersDTO>();
		Connection conn = null;
		PreparedStatement ps = null;
		String fetchQuery = "select playerid,playername,country from favouriteplayers " + 
				" where id=(select userid from usersdtls where username=?)";
		try {
			conn = getConnection();
			ps = conn.prepareStatement(fetchQuery);
			ps.setString(1, userid);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				FavouritePlayersDTO playersDto=new FavouritePlayersDTO();
				playersDto.setPlayerId(rs.getString("playerid"));
				playersDto.setPlayersName(rs.getString("playername"));
				playersDto.setCountry(rs.getString("country"));
				playersList.add(playersDto);
			}
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return playersList;
	}



	@Override
	public ResponseDTO saveFavouritePlayers(String userid, FavouritePlayersDTO players) throws SQLException {
		String insertQuery = " insert into favouriteplayers (id,playerid,playername,country) values " + 
				" ((select userid from usersdtls where username=?),?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		ResponseDTO res =new ResponseDTO();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(insertQuery);
			ps.setString(1,userid);
			ps.setString(2, players.getPlayerId());
			ps.setString(3, players.getPlayersName());
			ps.setString(4,players.getCountry());
			ps.executeUpdate();

			ps.close();
			
			conn.close();
			res.setResponse("Favourite Player Details added to your system");
		} catch (SQLException e) {
			throw new SQLException(); 
		} finally {
			ps.close();
			conn.close();
		}
		return res;
	}


	@Override
	public ResponseDTO removeFavouritePlayers(String userid, FavouritePlayersDTO players) throws SQLException {
		String deleteQuery = "delete from favouriteplayers where playerid=? and playername=? and country=? and "
				+ " id= (select userid from usersdtls where username=?)";
		Connection conn = null;
		PreparedStatement ps = null;
		ResponseDTO res =new ResponseDTO();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(deleteQuery);
			ps.setString(1,players.getPlayerId());
			ps.setString(2, players.getPlayersName());
			ps.setString(3, players.getCountry());
			ps.setString(4, userid);
			ps.executeUpdate();
			ps.close();			
			conn.close();
			res.setResponse("Favourite Player details removed from the system");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(); 
		} finally {
			ps.close();
			conn.close();
		}
		return res;
	}
	
}
