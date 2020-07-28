package com.stackroute.favouriteservice.domain;

import java.sql.SQLException;
import java.util.List;

import com.stackroute.favouriteservice.dto.FavouritePlayersDTO;
import com.stackroute.favouriteservice.dto.ResponseDTO;

public interface IPlayersInfoDomain {

	
	List<FavouritePlayersDTO> fetchFavPlayersDetails(String userid);
	

	ResponseDTO saveFavouritePlayers(String userid, FavouritePlayersDTO players) throws SQLException;


	ResponseDTO removeFavouritePlayers(String userid, FavouritePlayersDTO players) throws SQLException;
}
