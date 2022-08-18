package pojos;

public class GoRestDataPojo {

    //1) Create private variables for every key
    private Integer id;
    private String name;
    private String email;
    private String gender;
    private String status;

    //2) Create constructors with all parameters and without any parameter.

    public GoRestDataPojo(Integer id, String name, String email, String gender, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.status = status;
    }

    public GoRestDataPojo() {
    }

    //3) Create getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


//    4) Create toString() method


    @Override
    public String toString() {
        return "GoRestDataPojo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
/*
     {
                    "meta": null,
                    "data": {
                        "id": 25,
                        "name": "Chandini Kaur",
                        "email": "chandini_kaur@wehner.org",
                        "gender": "male",
                        "status": "inactive"
                       }
                     }
 */