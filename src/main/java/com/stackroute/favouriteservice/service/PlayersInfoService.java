package com.stackroute.favouriteservice.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import com.stackroute.favouriteservice.domain.IPlayersInfoDomain;
import com.stackroute.favouriteservice.dto.FavouritePlayersDTO;
import com.stackroute.favouriteservice.dto.ResponseDTO;

@Service
@EnableScheduling
public class PlayersInfoService implements IPlayersInfoService{

	@Autowired(required=true)
	private IPlayersInfoDomain playersDomain;
	

	
	@Override
	public List<FavouritePlayersDTO> fetchFavPlayersDetails(String userid) {
		List<FavouritePlayersDTO> favPlayersDtls = playersDomain.fetchFavPlayersDetails(userid);
		return favPlayersDtls;
	}



	@Override
	public ResponseDTO saveFavouritePlayers(String userid, FavouritePlayersDTO players) throws SQLException {
		ResponseDTO response = new ResponseDTO();
		response = playersDomain.saveFavouritePlayers(userid,players);
		return response;
	}


	@Override
	public ResponseDTO removeFavouritePlayers(String userid, FavouritePlayersDTO players) throws SQLException {
		ResponseDTO response = new ResponseDTO();
		response = playersDomain.removeFavouritePlayers(userid,players);
		return response;
	}

}
