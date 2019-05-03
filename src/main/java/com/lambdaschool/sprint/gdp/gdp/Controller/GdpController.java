package com.lambdaschool.sprint.gdp.gdp.Controller;


import com.lambdaschool.sprint.gdp.gdp.Exception.ResourceNotFoundException;
import com.lambdaschool.sprint.gdp.gdp.GdpApplication;
import com.lambdaschool.sprint.gdp.gdp.Models.GDP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@RestController
public class GdpController


{

    private static final Logger logger = LoggerFactory.getLogger(GdpController.class);

    @GetMapping(value = "/allcountries",
            produces = {"application/json"})
    public ResponseEntity<?> getAllCountryByName()
    {
        logger.info("We requested /allcountries resource");
        GdpApplication.ourGdpList.gdpList.sort((e1, e2) -> e1.getName().compareToIgnoreCase(e2.getName()));
        return new ResponseEntity<>(GdpApplication.ourGdpList.gdpList, HttpStatus.OK);
    }

    @GetMapping(value = "/allgdp",
            produces = {"application/json"})
    public ResponseEntity<?> getAllCountryByGdp()

    {
        logger.info("We requested /allgdp resource");
        GdpApplication.ourGdpList.gdpList.sort((e1, e2) -> e2.getGdpInt() - e1.getGdpInt());
        return new ResponseEntity<>(GdpApplication.ourGdpList.gdpList, HttpStatus.OK);
    }

    @GetMapping(value = "/countrylookup/{country}")
    public ResponseEntity<?> getCountry (@PathVariable String country)
    {
        logger.info("We requested /countrylookup resource with " + country);

        ArrayList<GDP> rtnCountry = GdpApplication.ourGdpList.
                findGdp(c -> c.getName().toUpperCase().equals(country.toUpperCase()));
        return new ResponseEntity<>(rtnCountry, HttpStatus.OK);
    }

    @GetMapping(value = "/countryId/{id}",
            produces = {"application/json"})
    public ResponseEntity<?> getCountryById(@PathVariable long id)
    {

        logger.info("We requested /countryId{id} resource with "+ id);
        GDP rtnGDP;
        if (GdpApplication.ourGdpList.findGdpId(e -> (e.getId() == id)) == null)
        {
            throw new ResourceNotFoundException("Country with id " + id + " not found");
        }

        else
        {
            rtnGDP = GdpApplication.ourGdpList.findGdpId(e -> (e.getId() == id));
        }
        return new ResponseEntity<>(rtnGDP, HttpStatus.OK);
    }

    @GetMapping(value = "/median")
    public ResponseEntity<?> getPopulationMedian()
    {
        logger.info("We requested /median resource");
        if(GdpApplication.ourGdpList.gdpList.size() %2 == 0)
        {
            int tmpIndex = GdpApplication.ourGdpList.gdpList.size()/2;
            GdpApplication.ourGdpList.gdpList
                    .sort((e1,e2) -> e1.getGdpInt() - e2.getGdpInt());


            return new ResponseEntity<>(GdpApplication.ourGdpList.gdpList.get(tmpIndex), HttpStatus.OK);
        }
        else {
            Double tmpIndex = ((GdpApplication.ourGdpList.gdpList.size()/2) + .5);
            GdpApplication.ourGdpList.gdpList
                    .sort((e1,e2) -> e1.getGdpInt() - e2.getGdpInt());


            return new ResponseEntity<>(GdpApplication.ourGdpList.gdpList.get(tmpIndex.intValue()), HttpStatus.OK);
        }


    }

    @GetMapping(value = "/economy/greatest/{gdp}")
    public ModelAndView getCountriesEconomyGt (@PathVariable int gdp)
    {
        logger.info("We requested /economy/greatest{gdp} resource with "+gdp);
        ArrayList<GDP> rtnCountries = GdpApplication.ourGdpList
                .findGdp(e-> e.getGdpInt() >= gdp);


        //thymeleaf ties html to hava

        ModelAndView mav = new ModelAndView();
        mav.setViewName("gdp_gt");
        mav.addObject("gdpList", rtnCountries);
        return mav;


    }

    @GetMapping(value = "/table")
    public ModelAndView getCountriesTable()
    {
        logger.info("We requested /table resource");
        GdpApplication.ourGdpList.gdpList.sort((e1, e2) -> e2.getGdpInt() - e1.getGdpInt());

        ArrayList<GDP> rtnCountries = GdpApplication.ourGdpList.toList();

        //thymeleaf ties html to hava

        ModelAndView mav = new ModelAndView();
        mav.setViewName("economy_table");
        mav.addObject("et", rtnCountries);
        return mav;


    }
}
