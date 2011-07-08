package demo.spring;

import javax.jws.WebService;

import com.jpmorgan.ib.architecture.deia.cit.examples.database.persist.LocationDAO;

@WebService(endpointInterface = "demo.spring.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

	private LocationDAO locationDao;
	
    public LocationDAO getLocationDao() {
		return locationDao;
	}

	public void setLocationDao(LocationDAO locationDao) {
		this.locationDao = locationDao;
	}

	public String sayHi(String text) {
        System.out.println("sayHi called");
        locationDao.findByZip(text);
        return "Hello " + text;

    }
}