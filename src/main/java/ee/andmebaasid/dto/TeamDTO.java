package ee.andmebaasid.dto;

/**
 * Created with IntelliJ IDEA.
 * User: iljad Date: 18.01.2016 Time: 19:54
 */
public class TeamDTO {
    private String teamName;
    private short sportCode;
    private String countryCode;
    private String email;
    private String description;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public short getSportCode() {
        return sportCode;
    }

    public void setSportCode(short sportCode) {
        this.sportCode = sportCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TeamDTO{" +
                "teamName='" + teamName + '\'' +
                ", sportCode=" + sportCode +
                ", countryCode='" + countryCode + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
