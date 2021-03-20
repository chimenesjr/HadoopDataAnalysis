package Tests.Helpers;

public class Text {

    public Text(){}

    public Text(String s){
        this.valor = s;
    }

    private String valor;

    public void set(String s){
        this.valor = s;
    }

    public String toString(){
        return this.valor;
    }
}
