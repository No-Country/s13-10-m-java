package com.nocountrys13.ecoapp.dtos.response;

import java.util.List;

public class ApiResponse {

    private List<Resultado> results;
    private String status;

    public ApiResponse() {
    }

    public List<Resultado>  getResults() {
        return results;
    }

    
    public String getStatus() {
        return status;
    }

    

    public static class Resultado {

        private String formatted_address;
        private Geometry geometry;
        private List<AddressComponent> address_components;

        // Agregue otros campos del resultado según sea necesario

        public Resultado() {
        }

        public String getFormatted_address() {
            return formatted_address;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public Geometry getGeometry() {
            return geometry;
        }

        public void setGeometry(Geometry geometry) {
            this.geometry = geometry;
        }

        public List<AddressComponent> getAddress_components() {
            return address_components;
        }

        public void setAddress_components(List<AddressComponent> address_components) {
            this.address_components = address_components;
        }

        public static class Geometry {

            private Location location;

            // Agregue otros campos de geometría según sea necesario

            public Geometry() {
            }

            public Location getLocation() {
                return location;
            }

            public void setLocation(Location location) {
                this.location = location;
            }

            public static class Location {

                private double lat;
                private double lng;

                public Location() {
                }

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }
        }

        public static class AddressComponent {

            private String long_name;
            private String short_name;
            private List<String> types;

            public AddressComponent() {
            }

            public String getLong_name() {
                return long_name;
            }

            public void setLong_name(String long_name) {
                this.long_name = long_name;
            }

            public String getShort_name() {
                return short_name;
            }

            public void setShort_name(String short_name) {
                this.short_name = short_name;
            }

            public List<String> getTypes() {
                return types;
            }

            public void setTypes(List<String> types) {
                this.types = types;
            }
        }
    }
}


