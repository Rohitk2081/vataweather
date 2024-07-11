package com.weather.wataweather.model;

import java.util.List;

public class WeatherResponse {

    private Main main;
    private Wind wind;
    private List<Weather> weather;
    private Sys sys;
    private String name;
    private long dt;

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public Sys getSys() {
        return sys;
    }

    public String getName() {
        return name;
    }

    public long getDt() {
        return dt;
    }

    public class Main {
        private float temp;
        private float feels_like;
        private float temp_min;
        private float temp_max;
        private int pressure;
        private int humidity;

        public float getTemp() {
            return temp;
        }

        public float getFeels_like() {
            return feels_like;
        }

        public float getTemp_min() {
            return temp_min;
        }

        public float getTemp_max() {
            return temp_max;
        }

        public int getPressure() {
            return pressure;
        }

        public int getHumidity() {
            return humidity;
        }
    }

    public class Wind {
        private float speed;
        private int deg;

        public float getSpeed() {
            return speed;
        }

        public int getDeg() {
            return deg;
        }
    }

    public class Weather {
        private String description;
        private String icon;

        public String getDescription() {
            return description;
        }

        public String getIcon() {
            return icon;
        }
    }

    public class Sys {
        private String country;
        private long sunrise;
        private long sunset;

        public String getCountry() {
            return country;
        }

        public long getSunrise() {
            return sunrise;
        }

        public long getSunset() {
            return sunset;
        }
    }
}
