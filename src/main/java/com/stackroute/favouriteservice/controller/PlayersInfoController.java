package com.stackroute.favouriteservice.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.favouriteservice.dto.FavouritePlayersDTO;
import com.stackroute.favouriteservice.dto.ResponseDTO;
import com.stackroute.favouriteservice.service.IPlayersInfoService;

@RestController
@RequestMapping("/players")
public class PlayersInfoController {
	
	@Autowired
	IPlayersInfoService playersService;

	
	@RequestMapping(value = "/fav-players/{userid}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FavouritePlayersDTO> fetchFavPlayersDetails(@PathVariable String userid) {
		List<FavouritePlayersDTO> favPlayersDtls = playersService.fetchFavPlayersDetails(userid);
		return favPlayersDtls;
	}
	
	@RequestMapping(value = "/add-players/{userid}" , method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseDTO saveFavouritePlayers(@PathVariable String userid,@RequestBody FavouritePlayersDTO players) throws SQLException {
		ResponseDTO response = playersService.saveFavouritePlayers(userid,players);
		return response;
	}
	
	@RequestMapping(value = "/rmv-players/{userid}" , method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseDTO removeFavouritePlayers(@PathVariable String userid,@RequestBody FavouritePlayersDTO players) throws SQLException {
		ResponseDTO response = playersService.removeFavouritePlayers(userid,players);
		return response;
	}
	
}
