package com.aeon.stoomapiaddress.business;

import com.aeon.stoomapiaddress.exception.ResourceDefaultException;
import com.aeon.stoomapiaddress.model.Address;
import com.aeon.stoomapiaddress.model.dto.GeocodeResultDTO;
import com.aeon.stoomapiaddress.repository.AddressRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.errors.ApiException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {

    private static final String GEOCODING_RESOURCE = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    private static final String API_KEY = "AIzaSyDTK0igIQTCi5EYKL9tzOIJ9N6FUASGZos";

    @Autowired
    private AddressRepository repor;

    public Address addAddress(Address address) throws InterruptedException, ApiException, IOException {
        if (address.getId() == null){
            verifyParamMandatory(address);
            address.setId(UUID.randomUUID());
            if (address.getLatitude() == null || address.getLongitude() == null ||
                    address.getLatitude().isEmpty() || address.getLongitude().isEmpty()){
                setLogLng(address);
            }
            repor.saveAndFlush(address);
        }
        return address;
    }

    public Address listAddressById(UUID id){
        Optional<Address> address = repor.findById(id);
        return address.orElse(null);
    }

    public Address updateAddress(Address address) throws IOException {
        verifyIfAddressExists(address.getId().toString());
        verifyParamMandatory(address);
        Optional<Address> newAddress = repor.findById(address.getId());
        newAddress.get().setStreetName(address.getStreetName());
        newAddress.get().setNumber(address.getNumber());
        newAddress.get().setComplement(address.getComplement());
        newAddress.get().setNeighbourhood(address.getNeighbourhood());
        newAddress.get().setCity(address.getCity());
        newAddress.get().setState(address.getState());
        newAddress.get().setCountry(address.getCountry());
        newAddress.get().setZipcode(address.getZipcode());

        if (address.getLatitude().isEmpty() || address.getLongitude().isEmpty()){
            setLogLng(address);
        }else{
            newAddress.get().setLatitude(address.getLatitude());
            newAddress.get().setLongitude(address.getLongitude());
        }

        repor.saveAndFlush(newAddress.get());
        return newAddress.get();
    }

    public List<Address> listAllAddress(){
        return repor.findAll();
    }

    public void deleteAddress(UUID id){
        verifyIfAddressExists(id.toString());
        Optional<Address> address = repor.findById(id);
        repor.delete(address.get());
        repor.flush();
    }

    private void setLogLng(Address address) throws IOException {
        GeocodeResultDTO location = geoCodeSeach(address);
        if (location.getStatus().equals("OK")) {
            address.setLatitude(location.getResults().get(0).getGeometry().getGeocodeLocation().getLatitude());
            address.setLongitude(location.getResults().get(0).getGeometry().getGeocodeLocation().getLongitude());
        } else {
            address.setLatitude("ND");
            address.setLongitude("ND");
        }
    }

    private GeocodeResultDTO geoCodeSeach(Address address) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String encodedAddress = URLEncoder.encode(address.getStreetName()+" "+address.getNeighbourhood(), "UTF-8");
        Request request = new Request.Builder().url(GEOCODING_RESOURCE+encodedAddress+"&key="+API_KEY).get().build();

        ResponseBody responseBody = client.newCall(request).execute().body();
        ObjectMapper objectMapper = new ObjectMapper();

        GeocodeResultDTO result = objectMapper.readValue(responseBody.string(), GeocodeResultDTO.class);
        return  result;
    }

    private void verifyIfAddressExists(String id){
        if (listAddressById(UUID.fromString(id)) == null){
            throw new ResourceDefaultException("Address not found!");
        }
    }

    private void verifyParamMandatory(Address address){
        if (address.getStreetName() == null || address.getStreetName().isEmpty()){
            throw new ResourceDefaultException("Parameter StreetName is Required!");
        }

        if (address.getNumber() == null || address.getNumber().toString().isEmpty()){
            throw new ResourceDefaultException("Parameter Number is Required!");
        }

        if (address.getNeighbourhood() == null || address.getNeighbourhood().isEmpty()){
            throw new ResourceDefaultException("Parameter Neighbourhood is Required!");
        }

        if (address.getCity() == null || address.getCity().isEmpty()){
            throw new ResourceDefaultException("Parameter City is Required!");
        }

        if (address.getState() == null || address.getState().isEmpty()){
            throw new ResourceDefaultException("Parameter State is Required!");
        }

        if (address.getCountry() == null || address.getCountry().isEmpty()){
            throw new ResourceDefaultException("Parameter Country is Required!");
        }

        if (address.getZipcode() == null || address.getZipcode().isEmpty()){
            throw new ResourceDefaultException("Parameter Zipcode is Required!");
        }
    }

}
