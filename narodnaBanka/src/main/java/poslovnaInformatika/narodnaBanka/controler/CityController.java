package poslovnaInformatika.narodnaBanka.controler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poslovnaInformatika.narodnaBanka.converters.CityConverter;
import poslovnaInformatika.narodnaBanka.dto.CityDTO;
import poslovnaInformatika.narodnaBanka.model.City;
import poslovnaInformatika.narodnaBanka.service.CityServiceInteface;

@RestController
@RequestMapping(value="salesystem/cities")
public class CityController {

	@Autowired
	private CityServiceInteface cityServiceInteface;
	
	@Autowired
	CityConverter cityConverter;
	
	
	@GetMapping(value="/all")
	public ResponseEntity<List<CityDTO>> getCities(){
		List<City> cities = cityServiceInteface.findAll();
		List<CityDTO> citiesDTO = new ArrayList<CityDTO>();
		for (City c : cities) {
			citiesDTO.add(cityConverter.toDTO(c));
		}
		return new ResponseEntity<List<CityDTO>>(citiesDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/{city_id}")
	public ResponseEntity<CityDTO> getCity(@PathVariable("city_id") Integer city_id){
		City city = cityServiceInteface.findOne(city_id);
		if(city == null) {
			return new ResponseEntity<CityDTO>(HttpStatus.NOT_FOUND);
		}
		CityDTO cityDTO = cityConverter.toDTO(city);
		return new ResponseEntity<CityDTO>(cityDTO, HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json", value="/add")
	public ResponseEntity<CityDTO> saveCity(@RequestBody CityDTO cDTO){
		City c = cityConverter.toCity(cDTO);
		City cy = cityServiceInteface.save(c);
		CityDTO cityDTO = cityConverter.toDTO(cy);
		return new ResponseEntity<CityDTO>(cityDTO, HttpStatus.CREATED);
	}
	
	@PutMapping(value="/{city_id}",consumes="application/json")
	public ResponseEntity<CityDTO> updateCity(@RequestBody CityDTO cDTO, @PathVariable("city_id") Integer city_id){
		City city = cityServiceInteface.findOne(city_id);
		if (city == null) {
			return new ResponseEntity<CityDTO>(HttpStatus.BAD_REQUEST);
		}
		City c = cityServiceInteface.save(cityConverter.toCity(cDTO));
		CityDTO cityDTO = cityConverter.toDTO(c);
		return new ResponseEntity<CityDTO>(cityDTO, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{city_id}")
	public ResponseEntity<Void> deleteCity(@PathVariable("city_id") Integer city_id){
		City city = cityServiceInteface.findOne(city_id);
		if (city != null) {
			cityServiceInteface.remove(city_id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
