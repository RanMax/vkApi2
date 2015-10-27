package net.acyuta.vk.api.account;

import com.google.gson.JsonObject;
import net.acyuta.vk.api.AbstractVkMethod;
import net.acyuta.vk.api.utils.City;
import net.acyuta.vk.api.utils.Country;
import org.apache.http.NameValuePair;

import java.util.List;

/**
 * Created by acyuta on 09.12.14.
 */
public class GetProfileInfo extends AbstractVkMethod {

    private String firstName, lastName, maiden_name, homeTown, birthday;
    private int sex, relation, birthday_visibility;
    private Country country;
    private City city;

    @Override
    public boolean recognize(JsonObject response) {
        if (response == null)
            return false;
        firstName = response.get("first_name").getAsString();
        lastName = response.get("last_name").getAsString();
        if (response.has("maiden_name"))
            maiden_name = response.get("maiden_name").getAsString();
        else
            maiden_name = null;
        sex = response.get("sex").getAsInt();
        relation = response.get("relation").getAsInt();
        birthday = response.get("bdate").getAsString();
        birthday_visibility = response.get("bdate_visibility").getAsInt();
        homeTown = response.get("home_town").getAsString();
        country = new Country(response.get("country").getAsJsonObject());
        city = new City(response.get("city").getAsJsonObject());
        return true;
    }

    @Override
    public List<NameValuePair> getArgs() {
        return null;
    }

    @Override
    public String getName() {
        return "account.getProfileInfo";
    }

    @Override
    public String toString() {
        return "GetProfileInfo{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", maiden_name='" + maiden_name + '\'' +
                ", homeTown='" + homeTown + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex=" + sex +
                ", relation=" + relation +
                ", birthday_visibility=" + birthday_visibility +
                ", country=" + country +
                ", city=" + city +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMaiden_name() {
        return maiden_name;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public String getBirthday() {
        return birthday;
    }

    public int getSex() {
        return sex;
    }

    public int getRelation() {
        return relation;
    }

    public int getBirthday_visibility() {
        return birthday_visibility;
    }

    public Country getCountry() {
        return country;
    }

    public City getCity() {
        return city;
    }
}
