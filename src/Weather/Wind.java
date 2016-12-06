package Weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Wind
{
    private String speed;

    private String deg;
    
    private String gust;
    
    

    public String getGust() {
		return gust;
	}

	public void setGust(String gust) {
		this.gust = gust;
	}

	public String getSpeed ()
    {
        return speed;
    }

    public void setSpeed (String speed)
    {
        this.speed = speed;
    }

    public String getDeg ()
    {
        return deg;
    }

    public void setDeg (String deg)
    {
        this.deg = deg;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [speed = "+speed+", deg = "+deg+" gust"+gust+"]";
    }
}
			
			