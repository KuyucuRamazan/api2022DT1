
package pojos;


import javax.xml.crypto.Data;

public class DummyRestApiPojo {

    private String status;
    private Data data;
    private String message;

    public DummyRestApiPojo() {
    }

    public DummyRestApiPojo(String status, Data data, String message) {
        super();
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DummyRestApiPojo{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}