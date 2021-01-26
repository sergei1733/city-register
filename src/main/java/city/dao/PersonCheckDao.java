package city.dao;

import city.domain.PersonRequest;
import city.domain.PersonResponse;
import city.exception.PersonCheckException;

import java.sql.*;

public class PersonCheckDao {

    private final String SQL_REQUEST = "SELECT temporal" +
            "from cr_address_person ap" +
            "inner join cr_person p on p.person_id = ap.person_id" +
            "inner join cr_address a on a.address_id = ap.address_id" +
            "where" +
            "CURRENT_DATE >= ap.start_date and (CURRENT_DATE <= ap.end_date or ap.end_date is null)"+
            "and upper(p.sur_name) = upper(?)  and" +
            "upper(p.given_name) = upper(?) and" +
            "upper(patronymic) = upper(?)and" +
            "p.date_of_birth = ?" +
            "and a.street_code = ? and" +
            "upper(a.building) = upper(?) and" +
            "upper(extension) =upper(?) and" +
            "upper(a.apartment) =upper(?)";

    public PersonResponse checkPerson (PersonRequest request) throws PersonCheckException {
        PersonResponse response = new PersonResponse();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQL_REQUEST)){

            stmt.setString(1,request.getSurName());
            stmt.setString(2,request.getGivenName());
            stmt.setString(3,request.getPatronymic());
            stmt.setDate(4,java.sql.Date.valueOf(request.getDateOfBirth()));
            stmt.setInt(5, request.getStreetCode());
            stmt.setString(6, request.getBuilding());
            stmt.setString(7, request.getExtension());
            stmt.setString(8, request.getApartment());




            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                response.setRegistered(true);
                response.setTemporal(rs.getBoolean("temporal"));
            }

        }catch (SQLException ex){
            throw new PersonCheckException(ex);
        }
        return response;

    }

    private Connection getConnection() throws SQLException {

        return DriverManager.getConnection("jdbc:postgresql://localhost/city_register",
                "postgres1", "123");
    }


}
