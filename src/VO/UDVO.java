package VO;

public class UDVO {

	private String sensor_id;
	private String    humidity;
	private String sensor_tag_value;
	private String sensor_status;
	private int temp;
	private String windSpeed;
	private String pressure;
	public int getTemp() {
		return temp;
	}
	public void setTemp(int temp) {
		this.temp = temp;
	}
	public String getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}
	public String getPressure() {
		return pressure;
	}
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	public String getSensor_id() {
		return sensor_id;
	}
	public void setSensor_id(String sensor_id) {
		this.sensor_id = sensor_id;
	}

	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getSensor_tag_value() {
		return sensor_tag_value;
	}
	public void setSensor_tag_value(String sensor_tag_value) {
		this.sensor_tag_value = sensor_tag_value;
	}
	public String getSensor_status() {
		return sensor_status;
	}
	public void setSensor_status(String sensor_status) {
		this.sensor_status = sensor_status;
	}
	
	
}
